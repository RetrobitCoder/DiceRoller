package gui;

/**
 * Class for handling the config.properties file
 * @author RetrobitCoder
 */

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ConfigProperties
{
	private Properties properties = new Properties();

	private DiceWindow window;

	private ImageIcon icon;

	private boolean configNotFound = false;

	private InputStream config = getClass().getResourceAsStream("/resources/config.properties");

	/**
	 * Handles reading config.properties file and doing conversions of string to
	 * appropriate object types.
	 * 
	 * @param diceWindow Window that is displaying the app
	 */
	public ConfigProperties(DiceWindow diceWindow)
	{
		window = diceWindow;

		try
		{
			icon = new ImageIcon(ImageIO.read(getClass().getResource("/resources/errorIcon.png")));
		}
		catch (IOException | IllegalArgumentException e)
		{
			// use default icon
			icon = null;
		}

		try
		{
			properties.load(config);
		}
		catch (Exception e)
		{
			configNotFound = true;

			JOptionPane.showMessageDialog(
					null, "Config file not found or issue reading it, using defaults.", "Config Error",
					JOptionPane.ERROR_MESSAGE, icon
			);
		}
	}

	public Color getBackgroundColor()
	{
		if (configNotFound || properties.getProperty("backgroundColor") == null)
			return Color.WHITE;

		try
		{
			return Color.decode(properties.getProperty("backgroundColor").trim());
		}
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog(
					null, "Background color value couldn't be converted. Using default.", "Config Error",
					JOptionPane.ERROR_MESSAGE, icon
			);

			return Color.WHITE;
		}
	}

	public boolean getDieDefaultImagePath()
	{
		if (configNotFound || properties.getProperty("dieDefaultImagePath") == null)
			return true;

		return (properties.getProperty("dieDefaultImagePath").toLowerCase().toString().equals("true"));
	}

	public String getdieImagesLoc()
	{
		if (configNotFound || properties.getProperty("dieImagesLoc") == null)
			return "/resources/";

		return properties.getProperty("dieImagesLoc").trim();
	}

	public int getDiceTrayWidth()
	{

		if (configNotFound || properties.getProperty("diceTrayWidth") == null)
			return 3600;

		try
		{
			return Integer.parseInt(properties.getProperty("diceTrayWidth").trim());
		}
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog(
					null, "Dice tray width couldn't be read as a number. Using default.", "Config Error",
					JOptionPane.ERROR_MESSAGE, icon
			);

			return 3600;
		}
	}

	public int getDiceTrayHeight()
	{
		if (configNotFound || properties.getProperty("diceTrayHeight") == null)
			return 500;

		try
		{
			return Integer.parseInt(properties.getProperty("diceTrayHeight").trim());
		}
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog(
					null, "Dice tray height couldn't be read as a number. Using default.", "Config Error",
					JOptionPane.ERROR_MESSAGE, icon
			);

			return 500;
		}
	}

	public int getImageSide()
	{
		if (configNotFound || properties.getProperty("imageSide") == null)
			return 50;

		try
		{
			return Integer.parseInt(properties.getProperty("imageSide").trim());
		}
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog(
					null, "Image side couldn't be read as a number. Using default.", "Config Error",
					JOptionPane.ERROR_MESSAGE, icon
			);

			return 50;
		}
	}

	public Color getRollValueColor()
	{
		if (configNotFound || properties.getProperty("rollValueColor") == null)
			return Color.WHITE;

		try
		{
			return Color.decode(properties.getProperty("rollValueColor").trim());
		}
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog(
					null, "Roll value color couldn't be converted. Using default.", "Config Error",
					JOptionPane.ERROR_MESSAGE, icon
			);
			return Color.WHITE;
		}
	}

	/**
	 * Try reloading config file
	 * 
	 * @param deflt Boolean if reloading default values
	 */
	public void reload(boolean deflt)
	{
		try
		{
			if (deflt)
			{
				config = getClass().getResourceAsStream("/resources/config.properties");
			}
			properties.load(config);
		}
		catch (Exception e)
		{
			configNotFound = true;

			JOptionPane.showMessageDialog(
					null, "Config file not found or issue reading it, using defaults.", "Config Error",
					JOptionPane.ERROR_MESSAGE, icon
			);
		}
	}

	/**
	 * Get the text stored in the config.properties file
	 * 
	 * @param key Key to get value for
	 * @return Value of key
	 */
	public String getValue(String key)
	{
		return properties.getProperty(key);
	}

	/**
	 * Set the text store in the config.properties file
	 * 
	 * @param key   Key to update existing value for
	 * @param value New value for key
	 */
	public void setValue(String key, String value)
	{
		properties.put(key, value);
	}

	/**
	 * Write out the properties file
	 */
	public void save()
	{
		JFileChooser fileChooser = new JFileChooser();

		FileNameExtensionFilter filter = new FileNameExtensionFilter("Only .properties files", "properties");

		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setDialogTitle("Select or make a .properties file for saving.");
		fileChooser.addChoosableFileFilter(filter);

		int option = fileChooser.showSaveDialog(null);

		if (option == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				FileWriter writer;

				if (fileChooser.getSelectedFile().toString().contains(".properties"))
				{
					writer = new FileWriter(fileChooser.getSelectedFile());
				}
				else
				{
					writer = new FileWriter(fileChooser.getSelectedFile() + ".properties");
				}

				properties.store(writer, "DiceRoller Properties");
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(
						null, "Not able to save config file.", "Save Error", JOptionPane.ERROR_MESSAGE, icon
				);
			}
		}
	}

	/**
	 * Load in the new config file
	 * 
	 * @return If load succeeded or not
	 */
	public boolean load()
	{
		JFileChooser fileChooser = new JFileChooser();

		FileNameExtensionFilter filter = new FileNameExtensionFilter("Only .properties files", "properties");

		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setDialogTitle("Select .properties file.");
		fileChooser.addChoosableFileFilter(filter);

		int option = fileChooser.showOpenDialog(null);

		if (option == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				config = new FileInputStream(fileChooser.getSelectedFile());

				reload(false);

				window.reload();

				return true;
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(
						null, "Not able to open config file.", "Open Error", JOptionPane.ERROR_MESSAGE, icon
				);
			}
		}

		return false;
	}
}
