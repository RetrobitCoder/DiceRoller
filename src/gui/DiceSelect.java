package gui;

/**
 * Class for handling select lists for dice
 * @author RetrobitCoder
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Types;

public class DiceSelect extends JPanel
{
	private static final long serialVersionUID = 1L;

	private DiceWindow window;

	private JComboBox<String> d2SelectList;
	private JComboBox<String> d4SelectList;
	private JComboBox<String> d6SelectList;
	private JComboBox<String> d8SelectList;
	private JComboBox<String> d10SelectList;
	private JComboBox<String> d12SelectList;
	private JComboBox<String> d20SelectList;
	private JComboBox<String> d100SelectList;

	private String[] numberList = new String[51];

	/**
	 * Create a JPanel that handles the select lists for the different dice types
	 * 
	 * @param diceWindow Window that is displaying this panel to handle calling back
	 *                   when select list is 0
	 * @param prop Config properties to load in
	 */
	public DiceSelect(DiceWindow diceWindow, ConfigProperties prop)
	{
		window = diceWindow;

		for (int i = 0; i < numberList.length; i++)
		{
			numberList[i] = Integer.toString(i);
		}
		this.setBackground(prop.getBackgroundColor());
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setBorder(BorderFactory.createLoweredBevelBorder());

		addLists();
		setActions();
	}

	/**
	 * Add the select lists and labels to this panel
	 */
	private void addLists()
	{
		d2SelectList = new JComboBox<String>(numberList);
		JLabel d2Label = new JLabel("D2");
		d4SelectList = new JComboBox<String>(numberList);
		JLabel d4Label = new JLabel("D4");
		d6SelectList = new JComboBox<String>(numberList);
		JLabel d6Label = new JLabel("D6");
		d8SelectList = new JComboBox<String>(numberList);
		JLabel d8Label = new JLabel("D8");
		d10SelectList = new JComboBox<String>(numberList);
		JLabel d10Label = new JLabel("D10");
		d12SelectList = new JComboBox<String>(numberList);
		JLabel d12Label = new JLabel("D12");
		d20SelectList = new JComboBox<String>(numberList);
		JLabel d20Label = new JLabel("D20");
		d100SelectList = new JComboBox<String>(numberList);
		JLabel d100Label = new JLabel("Percentile");

		this.add(d2Label);
		this.add(d2SelectList);
		this.add(d4Label);
		this.add(d4SelectList);
		this.add(d6Label);
		this.add(d6SelectList);
		this.add(d8Label);
		this.add(d8SelectList);
		this.add(d10Label);
		this.add(d10SelectList);
		this.add(d12Label);
		this.add(d12SelectList);
		this.add(d20Label);
		this.add(d20SelectList);
		this.add(d100Label);
		this.add(d100SelectList);
	}

	/**
	 * Sets action listeners for drop downs
	 */
	private void setActions()
	{
		d2SelectList.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dropDownAction(Types.D2, Integer.parseInt(d2SelectList.getSelectedItem().toString()));
			}
		});

		d4SelectList.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dropDownAction(Types.D4, Integer.parseInt(d4SelectList.getSelectedItem().toString()));
			}
		});

		d6SelectList.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dropDownAction(Types.D6, Integer.parseInt(d6SelectList.getSelectedItem().toString()));
			}
		});

		d8SelectList.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dropDownAction(Types.D8, Integer.parseInt(d8SelectList.getSelectedItem().toString()));
			}
		});

		d10SelectList.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dropDownAction(Types.D10, Integer.parseInt(d10SelectList.getSelectedItem().toString()));
			}
		});

		d12SelectList.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dropDownAction(Types.D12, Integer.parseInt(d12SelectList.getSelectedItem().toString()));
			}
		});

		d20SelectList.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dropDownAction(Types.D20, Integer.parseInt(d20SelectList.getSelectedItem().toString()));
			}
		});

		d100SelectList.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dropDownAction(Types.PERCENTILE, Integer.parseInt(d100SelectList.getSelectedItem().toString()));
			}
		});
	}

	/**
	 * Wrapper to have DiceWindow update the pieces needed when the drop downs
	 * update
	 * 
	 * @param type   The type of DiceManager being set
	 * @param amount Amount of dice the DiceManager knows about (0 removes existing
	 *               from the list)
	 */
	private void dropDownAction(Types type, int amount)
	{
		window.dropDownAction(type, amount);
	}

	/**
	 * Resets the drop down list for the die to 0
	 * 
	 * @param type Die type
	 */
	public void resetDropDown(Types type)
	{
		switch (type)
		{
			case D2:
				d2SelectList.setSelectedIndex(0);
				break;
			case D4:
				d4SelectList.setSelectedIndex(0);
				break;
			case D6:
				d6SelectList.setSelectedIndex(0);
				break;
			case D8:
				d8SelectList.setSelectedIndex(0);
				break;
			case D10:
				d10SelectList.setSelectedIndex(0);
				break;
			case D12:
				d12SelectList.setSelectedIndex(0);
				break;
			case D20:
				d20SelectList.setSelectedIndex(0);
				break;
			case PERCENTILE:
				d100SelectList.setSelectedIndex(0);
				break;
		}
	}
}
