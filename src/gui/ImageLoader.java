package gui;

/**
 * Class for handling loading images for dice tray
 * @author RetrobitCoder
 */
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import engine.Types;

public class ImageLoader
{
	private HashMap<Types, Image> images;

	private Image background;
	private ImageIcon icon;

	/**
	 * Handles reading in the images used in for displaying dice results
	 * 
	 * @param prop Congi properties to load in
	 * 
	 * @throws IOException If issue occurs while reading in images
	 */
	public ImageLoader(ConfigProperties prop) throws IOException
	{
		images = new HashMap<Types, Image>();

		Image die;

		try
		{
			icon = new ImageIcon(ImageIO.read(getClass().getResource("/resources/errorIcon.png")));
		}
		catch (IOException | IllegalArgumentException e)
		{
			icon = null;
		}

		try
		{
			String location = prop.getdieImagesLoc();

			for (Types type : Types.values())
			{
				if (prop.getDieDefaultImagePath())
				{
					die = ImageIO.read(getClass().getResource("/resources/d" + type.getSides() + ".png"));
				}
				else
				{
					die = ImageIO.read(new File(location + "/d" + type.getSides() + ".png"));
				}

				images.put(type, die);
			}

			if (prop.getDieDefaultImagePath())
			{
				background = ImageIO.read(getClass().getResource("/resources/diceTray.png"));
			}
			else
			{
				background = ImageIO.read(new File(location + "/diceTray.png"));
			}

		}
		catch (IOException | IllegalArgumentException e)
		{
			JOptionPane.showMessageDialog(
					null, "Image files couldn't be found. Using defaults.", "Config Error", JOptionPane.ERROR_MESSAGE,
					icon
			);

			throw new IOException();
		}
	}

	/**
	 * Get the image associated with the die type
	 * 
	 * @param type Type of die
	 * @return Image for type of die
	 */
	public Image getImage(Types type)
	{
		return images.get(type);
	}

	public Image getBackground()
	{
		return background;
	}
}
