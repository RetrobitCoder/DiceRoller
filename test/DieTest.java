import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import engine.Die;
import engine.Types;

class DieTest
{
	Die d2 = new Die(Types.D2);

	@Test
	void rollTest()
	{
		for (int i = 0; i < 100; i++)
		{
			d2.rollDie();
			assertTrue(d2.getValue() > 0);
		}
	}
	
	@Test
	void stringTest()
	{
		assertTrue(Integer.toString(d2.getValue()).equals(d2.toString()));
	}
}
