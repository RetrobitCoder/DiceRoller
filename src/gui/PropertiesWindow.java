package gui;

/**
 * Class for handling changes to the config.properties file
 * @author RetrobitCoder
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PropertiesWindow extends JFrame
{
	private static final long serialVersionUID = 1L;

	private ConfigProperties prop;

	private JTextField backgroundColor;
	private JTextField dieDefaultImagePath;
	private JTextField dieImagesLoc;
	private JTextField diceTrayWidth;
	private JTextField diceTrayHeight;
	private JTextField diceTrayImageWidth;
	private JTextField diceTrayImageHeight;
	private JTextField imageSide;
	private JTextField rollValueColor;

	final private int WINDOW_SIDE = 50;

	/**
	 * Handles setting up the GUI window for config.properties file
	 * 
	 * @param properties ConfigProperties class to get info from
	 */
	public PropertiesWindow(ConfigProperties properties)
	{
		prop = properties;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setTitle("Config");
		this.setLocation(
				(int) screenSize.getWidth() / 2 - WINDOW_SIDE * 4, (int) screenSize.getHeight() / 2 - WINDOW_SIDE * 2
		);
	}

	/**
	 * Handles setting up GUI components
	 */
	public void display()
	{
		JPanel layoutPanel = new JPanel();
		JPanel eastPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel westPanel = new JPanel();

		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.PAGE_AXIS));
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));

		addConfigLabels(westPanel);
		addConfigValueFields(centerPanel);
		addButtons(eastPanel);

		layoutPanel.add(westPanel, BorderLayout.WEST);
		layoutPanel.add(centerPanel, BorderLayout.CENTER);
		layoutPanel.add(eastPanel, BorderLayout.EAST);

		this.add(layoutPanel);
		this.pack();
		this.setVisible(true);
	}

	/**
	 * Adds all the text labels for the config.properties file
	 * 
	 * @param panel Panel to add labels to
	 */
	private void addConfigLabels(JPanel panel)
	{
		JLabel backgroundColorLabel = new JLabel("Background Color:");
		JLabel dieDefaultImagePathLabel = new JLabel("Use Default Image Path:");
		JLabel dieImagesLocLabel = new JLabel("Location for Die and Dice Tray Images:");
		JLabel diceTrayWidthLabel = new JLabel("Dice Tray Width:");
		JLabel diceTrayHeightLabel = new JLabel("Dice Tray Height;");
		JLabel diceTrayImageWidthLabel = new JLabel("Dice Tray Image Width:");
		JLabel diceTrayImageHeightLabel = new JLabel("Dice Tray Image Height;");
		JLabel imageSideLabel = new JLabel("Image Side;");
		JLabel rollValueColorLabel = new JLabel("Roll Value Color:");

		int spacing = 10;

		panel.add(backgroundColorLabel);
		panel.add(Box.createVerticalStrut(spacing));
		panel.add(dieDefaultImagePathLabel);
		panel.add(Box.createVerticalStrut(spacing));
		panel.add(dieImagesLocLabel);
		panel.add(Box.createVerticalStrut(spacing));
		panel.add(diceTrayWidthLabel);
		panel.add(Box.createVerticalStrut(spacing));
		panel.add(diceTrayHeightLabel);
		panel.add(Box.createVerticalStrut(spacing));
		panel.add(diceTrayImageWidthLabel);
		panel.add(Box.createVerticalStrut(spacing));
		panel.add(diceTrayImageHeightLabel);
		panel.add(Box.createVerticalStrut(spacing));
		panel.add(imageSideLabel);
		panel.add(Box.createVerticalStrut(spacing));
		panel.add(rollValueColorLabel);
	}

	/**
	 * Adds all the textfields that display current values and gets new values
	 * 
	 * @param panel Panel to add textfields to
	 */
	private void addConfigValueFields(JPanel panel)
	{
		backgroundColor = new JTextField(prop.getValue("backgroundColor"));
		dieDefaultImagePath = new JTextField(prop.getValue("dieDefaultImagePath"));
		dieImagesLoc = new JTextField(prop.getValue("dieImagesLoc"));
		diceTrayWidth = new JTextField(prop.getValue("diceTrayWidth"));
		diceTrayHeight = new JTextField(prop.getValue("diceTrayHeight"));
		diceTrayImageWidth = new JTextField(prop.getValue("diceTrayImageWidth"));
		diceTrayImageHeight = new JTextField(prop.getValue("diceTrayImageHeight"));
		imageSide = new JTextField(prop.getValue("imageSide"));
		rollValueColor = new JTextField(prop.getValue("rollValueColor"));

		backgroundColor.setToolTipText("Sets the background color for the drop down, button, and total areas");
		dieDefaultImagePath.setToolTipText(
				"Uses default path to find the images for dice. Set to false if Die Images Location is a specific location."
		);
		dieImagesLoc.setToolTipText(
				"Where to find the images for the dice and dice tray background. Set to /resources/ if use default image path is true."
		);
		diceTrayWidth.setToolTipText("How wide the area that displays the dice rolls is.");
		diceTrayHeight.setToolTipText("How tall the area that displays the dice rolls is.");
		diceTrayImageWidth.setToolTipText("How wide the image for the dice tray is.");
		diceTrayImageHeight.setToolTipText("How tall the image for the dice tray is.");
		imageSide.setToolTipText(
				"Die images are square images. This is how big a side is. Should be same for all die types."
		);
		rollValueColor.setToolTipText("The text color for values displayed on die images.");

		panel.add(backgroundColor);
		panel.add(dieDefaultImagePath);
		panel.add(dieImagesLoc);
		panel.add(diceTrayWidth);
		panel.add(diceTrayHeight);
		panel.add(diceTrayImageWidth);
		panel.add(diceTrayImageHeight);
		panel.add(imageSide);
		panel.add(rollValueColor);
	}

	/**
	 * Adds the save and default buttons
	 * 
	 * @param panel Panel to add buttons to
	 */
	private void addButtons(JPanel panel)
	{
		JButton save = new JButton("Save");
		JButton deflt = new JButton("Default");
		JButton load = new JButton("Load");

		save.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				saveFile();
			}
		});

		deflt.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				defaultOptions();
			}
		});

		load.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				loadFile();
			}
		});

		panel.add(deflt);
		panel.add(save);
		panel.add(load);
	}

	/**
	 * Writes out the config options to the config.properties file and reloads
	 * ConfigProperties class
	 */
	private void saveFile()
	{
		prop.setValue("backgroundColor", backgroundColor.getText());
		prop.setValue("dieDefaultImagePath", dieDefaultImagePath.getText());
		prop.setValue("dieImagesLoc", dieImagesLoc.getText());
		prop.setValue("diceTrayWidth", diceTrayWidth.getText());
		prop.setValue("diceTrayHeight", diceTrayHeight.getText());
		prop.setValue("diceTrayImageWidth", diceTrayImageWidth.getText());
		prop.setValue("diceTrayImageHeight", diceTrayImageHeight.getText());
		prop.setValue("imageSide", imageSide.getText());
		prop.setValue("rollValueColor", rollValueColor.getText());

		prop.save();
	}

	/**
	 * Load in new config file
	 */
	private void loadFile()
	{
		if (prop.load())
		{
			this.setVisible(false);
		}
	}

	/**
	 * Resets config options to default values
	 */
	private void defaultOptions()
	{

		backgroundColor.setText("#FFFFFF");
		dieDefaultImagePath.setText("true");
		dieImagesLoc.setText("/resources/");
		diceTrayWidth.setText("4000");
		diceTrayHeight.setText("698");
		diceTrayImageWidth.setText("1000");
		diceTrayImageHeight.setText("349");
		imageSide.setText("50");
		rollValueColor.setText("#FFFFFF");

		prop.reload(true);

		this.repaint();
	}
}
