import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import engine.DiceManager;
import engine.Types;

class DiceManagerTest
{
	final int NUM_DICE = 10;
	DiceManager d100 = new DiceManager(NUM_DICE, Types.PERCENTILE);
	
	@Test
	void sanityCheck() 
	{
		assertTrue(d100.toString().split(" ").length == NUM_DICE);
	}
	
	@Test
	void rollTest()
	{
		d100.rollDice();
		for(String value : d100.toString().split(" ")) 
		{
			
			assertTrue(Integer.parseInt(value) > 0);
		}
	}
	
	@Test
	void noDiceTest()
	{
		Exception e = assertThrows(IllegalArgumentException.class, () ->
		{
			new DiceManager(0, Types.D8);
		});
		
		assertTrue(e.getMessage() == "numDice must be > 0");
	}
}
