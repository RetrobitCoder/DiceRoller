package gui;

/**
 * Class for controlling dice roller app and displaying contents
 * @author RetrobitCoder
 */
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import engine.DiceManager;
import engine.Types;

public class DiceWindow
{
	private TreeMap<Types, DiceManager> diceSet = new TreeMap<Types, DiceManager>();

	private JFrame frame;

	private DiceSelect diceSelect;
	private DiceTotals diceTotals;
	private DiceButtons diceButtons;
	private DiceTray diceTray;
	private PropertiesWindow propWindow;
	private ConfigProperties prop;

	final private int WINDOW_WIDTH = 1100;
	final private int WINDOW_HEIGHT = 500;

	/**
	 * Handles setting up the GUI Window for rolling dice
	 * 
	 * @param title Window has title in top bar
	 */
	public DiceWindow(String title)
	{
		prop = new ConfigProperties(this);

		setUp(title);
	}

	/**
	 * Setup window
	 * 
	 * @param title Title of window
	 */
	private void setUp(String title)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		frame = new JFrame();
		frame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		frame.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(title);
		frame.setLocation(
				(int) screenSize.getWidth() / 2 - WINDOW_WIDTH / 2, (int) screenSize.getHeight() / 2 - WINDOW_HEIGHT / 2
		);

		diceSelect = new DiceSelect(this, prop);
		diceTotals = new DiceTotals(WINDOW_WIDTH, prop);
		diceButtons = new DiceButtons(this, WINDOW_WIDTH, prop);
		diceTray = new DiceTray(prop.getDiceTrayWidth(), prop.getDiceTrayHeight(), diceSet, prop);
		propWindow = new PropertiesWindow(prop);
	}

	/**
	 * Handles setting up the GUI components to be in the window
	 */
	public void display()
	{
		JPanel layoutPanel = new JPanel();
		JScrollPane scroll = new JScrollPane(diceTray);

		layoutPanel.setLayout(new BoxLayout(layoutPanel, BoxLayout.PAGE_AXIS));

		layoutPanel.add(diceSelect);
		layoutPanel.add(diceTotals);
		layoutPanel.add(diceButtons);
		layoutPanel.add(scroll);

		frame.add(layoutPanel);
		frame.pack();
		frame.repaint();
		frame.setVisible(true);
	}

	/**
	 * Handles setting DiceManagers in diceSet based on what was selected and how
	 * much
	 * 
	 * @param type   The type of DiceManager being set
	 * @param amount Amount of dice the DiceManager knows about (0 removes existing
	 *               from the list)
	 */
	public void dropDownAction(Types type, int amount)
	{
		if (amount == 0)
		{
			diceSet.remove(type);
			clearTotals(type);
		}
		else
		{
			diceSet.put(type, new DiceManager(amount, type));
		}
	}

	/**
	 * Updates the totals for each die selected and the total sum
	 */
	private void updateTotals()
	{
		long totalSum = 0;
		int diceTotal = 0;

		for (Types type : diceSet.keySet())
		{
			diceTotal = diceSet.get(type).sumDice();
			totalSum += diceTotal;

			diceTotals.updateTotals(type, diceTotal);
		}

		diceTotals.updateTotals(totalSum);

		frame.repaint();
	}

	/**
	 * Clear the the total label for the give type
	 * 
	 * @param type Type of dice to clear the total of
	 */
	private void clearTotals(Types type)
	{
		diceTotals.clearTotals(type);
		diceTray.updateDice(diceSet);
		updateTotals();
	}

	/**
	 * Handles rolling dice when roll button is pushed
	 */
	public void rollAction()
	{
		for (DiceManager dice : diceSet.values())
		{
			dice.rollDice();
			diceTray.updateDice(diceSet);
			updateTotals();
		}
	}

	/**
	 * Handles rolling advantage when advantage button is pushed
	 */
	public void advantageAction()
	{
		if (diceSet.containsKey(Types.D20))
		{
			diceSet.get(Types.D20).advantageRoll();
			diceTray.updateDice(diceSet);
			updateTotals();
		}
	}

	/**
	 * Handles rolling disadvantage when disadvantage button is pushed
	 */
	public void disadvantageAction()
	{
		if (diceSet.containsKey(Types.D20))
		{
			diceSet.get(Types.D20).disadvantageRoll();
			diceTray.updateDice(diceSet);
			updateTotals();
		}
	}

	/**
	 * Handles re-rolling 1s and 2s when reroll button is pushed
	 */
	public void reroll1s2sAction()
	{
		for (Types type : diceSet.keySet())
		{
			if (!type.equals(Types.D20) && !type.equals(Types.PERCENTILE))
			{
				diceSet.get(type).reroll1And2();
				updateTotals();
			}
		}

		diceTray.updateDice(diceSet);

		frame.repaint();
	}

	/**
	 * Handles clearing when clear button is pushed
	 */
	public void clearAction()
	{
		for (Types type : Types.values())
		{
			diceSelect.resetDropDown(type);
		}
		diceSet.clear();
	}

	/**
	 * Handles config action and reloads window
	 */
	public void configAction()
	{
		propWindow.display();
	}

	/**
	 * Reload the window items for new loaded in config.properties file
	 */
	public void reload()
	{
		frame.dispose();
		
		setUp("Dice Roller");
		
		display();
	}
}