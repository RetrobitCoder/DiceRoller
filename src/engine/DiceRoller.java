package engine;

/**
 * Runs the app
 * @author RetrobitCoder
 */

import javax.swing.SwingUtilities;

import gui.DiceWindow;

public class DiceRoller
{
	public static void main(String[] args)
	{
		DiceWindow tray = new DiceWindow("Dice Roller");

		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				tray.display();
			}
		});
	}
}
