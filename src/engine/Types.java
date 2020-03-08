package engine;

/**
 * Defines the type of dice available and the max number of sides
 * 
 * @author RetrobitCoder
 *
 */
public enum Types
{
	D2(2), D4(4), D6(6), D8(8), D10(10), D12(12), D20(20), PERCENTILE(100);

	private final int MAX_SIDES;

	Types(final int sides)
	{
		MAX_SIDES = sides;
	}

	public int getSides()
	{
		return MAX_SIDES;
	}
}