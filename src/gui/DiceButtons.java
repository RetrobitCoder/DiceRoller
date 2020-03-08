package gui;

/**
 * Class for handling the different buttons for rolling and clearing totals
 * @author RetrobitCoder
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class DiceButtons extends JPanel
{
	private static final long serialVersionUID = 1L;

	private DiceWindow window;

	private JButton roll = new JButton("Roll");
	private JButton advantage = new JButton("Advantage");
	private JButton disadvantage = new JButton("Disadvantage");
	private JButton reroll1s2s = new JButton("Reroll 1s and 2s");
	private JButton clear = new JButton("Clear Dice");
	private JButton config = new JButton("Config");

	final private int WINDOW_WIDTH;

	/**
	 * Create a JPanel that handles the different buttons
	 * 
	 * @param diceWindow Window that is displaying this panel to handle calling back
	 *                   when buttons are pushed
	 * @param width The width of the window
	 * @param prop Config properties to load in
	 */
	public DiceButtons(DiceWindow diceWindow, int width, ConfigProperties prop)
	{
		window = diceWindow;

		WINDOW_WIDTH = width;

		this.setBackground(prop.getBackgroundColor());
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setBorder(BorderFactory.createLoweredBevelBorder());

		roll.setToolTipText("Rolls all dice normally");
		advantage.setToolTipText("Rolls each d20 twice and takes the higher value");
		disadvantage.setToolTipText("Rolls each d20 twice and takes the lower value");
		reroll1s2s.setToolTipText(
				"Must \"roll\" dice first, then will re-roll those dice that are not d20s or percentiles with a value of 1 or 2"
		);
		clear.setToolTipText("Clear all rolls and reset everything back to 0");

		addButtons();
		setActions();
	}

	/**
	 * Adds the buttons
	 */
	private void addButtons()
	{
		int buttonSpacing = 50;

		this.add(Box.createHorizontalStrut(50));
		this.add(Box.createHorizontalStrut(buttonSpacing));
		this.add(roll);
		this.add(Box.createHorizontalStrut(buttonSpacing));
		this.add(advantage);
		this.add(Box.createHorizontalStrut(buttonSpacing));
		this.add(disadvantage);
		this.add(Box.createHorizontalStrut(buttonSpacing));
		this.add(reroll1s2s);
		this.add(Box.createHorizontalStrut(buttonSpacing));
		this.add(clear);
		this.add(Box.createHorizontalStrut(buttonSpacing));
		this.add(config);
		this.add(Box.createHorizontalStrut(WINDOW_WIDTH));
	}

	/**
	 * Sets the action listeners for the buttons
	 */
	private void setActions()
	{
		roll.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				window.rollAction();
			}
		});

		advantage.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				window.advantageAction();
			}
		});

		disadvantage.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				window.disadvantageAction();
			}
		});

		reroll1s2s.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				window.reroll1s2sAction();
			}
		});

		clear.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				window.clearAction();
			}
		});

		config.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				window.configAction();
			}
		});
	}
}
