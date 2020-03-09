package gui;

/**
 * Class for handling displaying dice rolls
 * @author RetrobitCoder
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.util.TreeMap;

import javax.swing.JPanel;

import engine.DiceManager;
import engine.Types;

public class DiceTray extends JPanel
{
	private static final long serialVersionUID = 1L;

	private TreeMap<Types, DiceManager> diceSet;

	private ImageLoader images;
	private ConfigProperties prop;

	final private int LINE_SPACING = 60;
	final private int UPPER_X = 0;
	final private int UPPER_Y = 0;

	private boolean stringOutput = false;

	/**
	 * Creates a JPanel that handles displaying the dice rolls
	 * 
	 * @param width  The width of the panel
	 * @param height The height of the panel
	 * @param dice dice Map of the dice that was rolled to display
	 * @param properties Config properties to load in
	 */
	public DiceTray(int width, int height, TreeMap<Types, DiceManager> dice, ConfigProperties properties)
	{
		prop = properties;

		try
		{
			images = new ImageLoader(properties);

		}
		catch (IOException e)
		{
			prop.reload(true);

			try
			{
				images = new ImageLoader(properties);
			}
			catch (IOException e1)
			{
				stringOutput = true;
			}
		}

		diceSet = dice;

		this.setPreferredSize(new Dimension(width, height));
	}

	/**
	 * Updates the dice it the tray knows about for displaying
	 * 
	 * @param dice Dice that should be shown
	 */
	public void updateDice(TreeMap<Types, DiceManager> dice)
	{
		diceSet = dice;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		if (!stringOutput)
		{
			imagePrinting(g);
		}
		else
		{
			defaultPrinting(g);
		}

	}

	/**
	 * Handles drawing the dice images and putting the rolls on the images
	 * 
	 * @param g Graphics object doing the drawing
	 */
	private void imagePrinting(Graphics g)
	{
		int x = UPPER_X;
		int y = UPPER_Y;
		final int X_OFFSET = prop.getImageSide() / 2 - 8;
		final int Y_OFFSET = prop.getImageSide() / 2 + 6;
		final int IMAGE_SPACING = 60;

		drawBackground(g);

		g.setColor(prop.getRollValueColor());

		for (Types type : diceSet.keySet())
		{
			for (String value : diceSet.get(type).toString().split(" "))
			{
				g.drawImage(images.getImage(type), x, y, null);
				g.drawString(value, x + X_OFFSET, y + Y_OFFSET);

				x += IMAGE_SPACING;
			}
			x = UPPER_X;
			y += LINE_SPACING;

		}
	}
	
	/**
	 * Handle drawing background
	 * @param g
	 */
	private void drawBackground(Graphics g)
	{
		final int BG_WIDTH = prop.getDiceTrayImageWidth();
		final int BG_HEIGHT = prop.getDiceTrayImageHeight();

		for(int i = 0; i < prop.getDiceTrayWidth(); i += BG_WIDTH)
		{
			for(int j = 0; j < prop.getDiceTrayHeight(); j += BG_HEIGHT)
			{
				g.drawImage(images.getBackground(), i, j, null);
			}
		}
	}

	/**
	 * Handles printing the string values of the dice rolls to the DiceTray
	 * 
	 * @param g Graphics object doing the drawing
	 */
	private void defaultPrinting(Graphics g)
	{
		int x = UPPER_X;
		int y = UPPER_Y + 10;
		final int LABEL_SPACING = 80;

		for (Types type : diceSet.keySet())
		{
			g.drawString(type.toString() + ":", x, y);

			x += LABEL_SPACING;

			g.drawString(diceSet.get(type).toString(), x, y);

			x = 0;
			y += LINE_SPACING;
		}
	}
}
