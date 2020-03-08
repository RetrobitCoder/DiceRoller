package gui;

/**
 * Class for handling running totals for dice
 * @author RetrobitCoder
 */
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Types;

public class DiceTotals extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JLabel d2Total = new JLabel("0");
	private JLabel d4Total = new JLabel("0");
	private JLabel d6Total = new JLabel("0");
	private JLabel d8Total = new JLabel("0");
	private JLabel d10Total = new JLabel("0");
	private JLabel d12Total = new JLabel("0");
	private JLabel d20Total = new JLabel("0");
	private JLabel d100Total = new JLabel("0");
	private JLabel total = new JLabel("0");

	final private int WINDOW_WIDTH;

	/**
	 * Create a JPanel that handles the running totals for the different dice types
	 * 
	 * @param width Width this panel should take up
	 * @param prop Config properties to load in
	 */
	public DiceTotals(int width, ConfigProperties prop)
	{
		WINDOW_WIDTH = width;

		this.setBackground(prop.getBackgroundColor());
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

		addLabels();
	}

	/**
	 * Adds the labels to display the totals
	 */
	private void addLabels()
	{
		JLabel d2SumLabel = new JLabel("D2s:");
		JLabel d4SumLabel = new JLabel("D4s:");
		JLabel d6SumLabel = new JLabel("D6s:");
		JLabel d8SumLabel = new JLabel("D8s:");
		JLabel d10SumLabel = new JLabel("D10s:");
		JLabel d12SumLabel = new JLabel("D12s:");
		JLabel d20SumLabel = new JLabel("D20s:");
		JLabel d100SumLabel = new JLabel("Percentiles:");
		JLabel totalSumLabel = new JLabel("Total:");

		int totalsSpacing = 50;
		int valueSpacing = 10;

		this.add(d2SumLabel);
		this.add(Box.createHorizontalStrut(valueSpacing));
		this.add(d2Total);
		this.add(Box.createHorizontalStrut(totalsSpacing));
		this.add(d4SumLabel);
		this.add(Box.createHorizontalStrut(valueSpacing));
		this.add(d4Total);
		this.add(Box.createHorizontalStrut(totalsSpacing));
		this.add(d6SumLabel);
		this.add(Box.createHorizontalStrut(valueSpacing));
		this.add(d6Total);
		this.add(Box.createHorizontalStrut(totalsSpacing));
		this.add(d8SumLabel);
		this.add(Box.createHorizontalStrut(valueSpacing));
		this.add(d8Total);
		this.add(Box.createHorizontalStrut(totalsSpacing));
		this.add(d10SumLabel);
		this.add(Box.createHorizontalStrut(valueSpacing));
		this.add(d10Total);
		this.add(Box.createHorizontalStrut(totalsSpacing));
		this.add(d12SumLabel);
		this.add(Box.createHorizontalStrut(valueSpacing));
		this.add(d12Total);
		this.add(Box.createHorizontalStrut(totalsSpacing));
		this.add(d20SumLabel);
		this.add(Box.createHorizontalStrut(valueSpacing));
		this.add(d20Total);
		this.add(Box.createHorizontalStrut(totalsSpacing));
		this.add(d100SumLabel);
		this.add(Box.createHorizontalStrut(valueSpacing));
		this.add(d100Total);
		this.add(Box.createHorizontalStrut(totalsSpacing));
		this.add(totalSumLabel);
		this.add(Box.createHorizontalStrut(valueSpacing));
		this.add(total);
		this.add(Box.createHorizontalStrut(WINDOW_WIDTH));
	}

	/**
	 * Updates the totals label with the given sum for the type of die
	 * 
	 * @param type Type of die whose total label is updating
	 * @param sum  Total sum of the dice rolled for that die type
	 */
	public void updateTotals(Types type, int sum)
	{
		switch (type)
		{
			case D2:
				d2Total.setText(Integer.toString(sum));
				break;
			case D4:
				d4Total.setText(Integer.toString(sum));
				break;
			case D6:
				d6Total.setText(Integer.toString(sum));
				break;
			case D8:
				d8Total.setText(Integer.toString(sum));
				break;
			case D10:
				d10Total.setText(Integer.toString(sum));
				break;
			case D12:
				d12Total.setText(Integer.toString(sum));
				break;
			case D20:
				d20Total.setText(Integer.toString(sum));
				break;
			case PERCENTILE:
				d100Total.setText(Integer.toString(sum));
				break;
		}
	}

	/**
	 * Updates the overall total with the given sum
	 * 
	 * @param totalSum Total sum of all dice rolled
	 */
	public void updateTotals(Long totalSum)
	{
		total.setText(Long.toString(totalSum));
	}

	/**
	 * Clear the the total label for the give type
	 * 
	 * @param type Type of dice to clear the total of
	 */
	public void clearTotals(Types type)
	{
		switch (type)
		{
			case D2:
				d2Total.setText("0");
				break;
			case D4:
				d4Total.setText("0");
				break;
			case D6:
				d6Total.setText("0");
				break;
			case D8:
				d8Total.setText("0");
				break;
			case D10:
				d10Total.setText("0");
				break;
			case D12:
				d12Total.setText("0");
				break;
			case D20:
				d20Total.setText("0");
				break;
			case PERCENTILE:
				d100Total.setText("0");
				break;
		}
	}
}
