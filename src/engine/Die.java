package engine;

/**
* Class for handling a single die
* @author RetrobitCoder
*/

import java.util.Random;

public class Die
{
	private Random generator;
	private final Types dieType;
	private int roll;
	private static long offset = 303; //used to add randomness to subsequent die rolls
	/**
	 * Die constructor to make a die of a specific type
	 * 
	 * @param type Type of die from enum Types
	 */
	public Die(Types type)
	{
		dieType = type;
		generator = new Random(System.currentTimeMillis() * offset);
		
		offset *= 333;
	}

	/**
	 * Roll the die to get a random number from 1 to MAX_SIDES
	 */
	public void rollDie()
	{
		roll = generator.nextInt(dieType.getSides()) + 1;		
	}

	/**
	 * Get die value
	 * 
	 * @return Value of roll
	 */
	public int getValue()
	{
		return roll;
	}

	/**
	 * Get the value of the die
	 */
	public String toString()
	{
		return Integer.toString(roll);
	}
}
