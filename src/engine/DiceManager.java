package engine;

/**
* Class for handling all the dice of the same type (d4, d6, etc)
* @author RetrobitCoder
*/

import java.util.ArrayList;
import java.lang.StringBuffer;

public class DiceManager
{
	private ArrayList<Die> dice;
	private StringBuffer valuesList = new StringBuffer();
	private final Types dieType;

	/**
	 * DiceManager constructor to make a manager for a specific die type
	 * 
	 * @param numDice Positive number of dice to keep track of
	 * @param type    Type of dice from enum Types
	 */
	public DiceManager(int numDice, Types type)
	{
		if (numDice <= 0)
			throw new IllegalArgumentException("numDice must be > 0");

		dice = new ArrayList<Die>();
		dieType = type;

		for (int i = 0; i < numDice; i++)
		{
			dice.add(new Die(type));
		}
	}

	/**
	 * Roll each die the manager knows about and keep track of values
	 */
	public void rollDice()
	{
		for (Die die : dice)
		{
			die.rollDie();
		}
	}

	/**
	 * Reroll 1s or 2s
	 */
	public void reroll1And2()
	{
		dice.forEach((x) ->
		{
			if (x.getValue() == 1 || x.getValue() == 2)
				x.rollDie();
		});
	}

	/**
	 * Handles doing advantage rolls Advantage: Die x Die y, if x less than y use y
	 */
	public void advantageRoll()
	{
		Die die = new Die(dieType);

		for (int i = 0; i < dice.size(); i++)
		{
			die.rollDie();
			dice.get(i).rollDie();

			if (die.getValue() > dice.get(i).getValue())
			{
				dice.set(i, die);
			}
		}
	}

	/**
	 * Handles doing disadvantage rolls Disadvantage: Die x Die y, if x greater than y use y
	 */
	public void disadvantageRoll()
	{
		Die die = new Die(dieType);

		for (int i = 0; i < dice.size(); i++)
		{
			die.rollDie();
			dice.get(i).rollDie();

			if (die.getValue() < dice.get(i).getValue())
			{
				dice.set(i, die);
			}
		}
	}

	/**
	 * Sums all the dice together
	 * 
	 * @return Total sum
	 */
	public int sumDice()
	{
		int total = 0;

		for (Die die : dice)
		{
			total += die.getValue();
		}

		return total;
	}

	/**
	 * Get the values of the dice as a String
	 */
	public String toString()
	{
		valuesList.delete(0, valuesList.length());

		for (Die die : dice)
		{
			valuesList.append(die.toString() + ' ');
		}

		return valuesList.toString().trim();
	}
}