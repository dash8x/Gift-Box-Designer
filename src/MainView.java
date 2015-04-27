import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;

import javax.swing.border.EmptyBorder;

import java.awt.Insets;
import java.io.IOException;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainView {

	private JFrame frame;
	private JImagePanel img_panel_box_shape;
	private JImagePanel img_panel_ribbon;
	private JImagePanel img_panel_bow;
	//private JPanel img_panel_box_shape;
	private FilledLayeredPane layeredPane;
	private PNGImage img_box_shape = new PNGImage();
	private PNGImage img_ribbon = new PNGImage();
	private PNGImage img_bow = new PNGImage();
	private PNGImage img_box_shape_pattern = new PNGImage(); 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 737, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel container = new JPanel();
		container.setBorder(new EmptyBorder(10, 10, 10, 10));
		frame.getContentPane().add(container, BorderLayout.CENTER);
		GridBagLayout gbl_container = new GridBagLayout();
		gbl_container.columnWidths = new int[]{600, 0, 0, 0, 0};
		gbl_container.rowHeights = new int[]{0, 0, 0, 0, 600, 0};
		gbl_container.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_container.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		container.setLayout(gbl_container);
		
		layeredPane = new FilledLayeredPane();
		layeredPane.setPreferredSize(new Dimension(600,600));		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 5;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		container.add(layeredPane, gbc_panel);
		
		try {
			img_box_shape.read("resources/giftbox-cylinder.png");			
			img_ribbon.read("resources/cylinder-ribbon.png");		
			img_bow.read("resources/cylinder-bow.png");		
			img_box_shape_pattern.read("resources/heart_pattern.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//shape
		img_panel_box_shape = new JImagePanel(img_box_shape.getImage());
		//img_panel_box_shape = new JPanel();		
		img_panel_box_shape.setOpaque(false);		
		layeredPane.add(img_panel_box_shape, new Integer(0));
		
		//ribbon
		img_panel_ribbon = new JImagePanel(img_ribbon.getImage());
		//img_panel_ribbon = new JPanel();		
		img_panel_ribbon.setOpaque(false);		
		layeredPane.add(img_panel_ribbon, new Integer(1));
		
		//bow
		img_panel_bow = new JImagePanel(img_bow.getImage());
		//img_panel_ribbon = new JPanel();		
		img_panel_bow.setOpaque(false);		
		layeredPane.add(img_panel_bow, new Integer(2));
		
		JButton btnRed = new JButton("Box Color");
		btnRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color color = JColorChooser.showDialog(frame,
	                     "Choose Box Color",
	                     Color.WHITE);
				if ( color != null ) {
					applyImageColor(img_box_shape, img_panel_box_shape, color.getRed(),color.getGreen(),color.getBlue());
				}
			}
		});
		GridBagConstraints gbc_btnRed = new GridBagConstraints();
		gbc_btnRed.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRed.insets = new Insets(0, 0, 5, 5);
		gbc_btnRed.gridx = 2;
		gbc_btnRed.gridy = 0;
		container.add(btnRed, gbc_btnRed);
		
		JButton btnGreen = new JButton("Ribbon Color");
		btnGreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color color = JColorChooser.showDialog(frame,
	                     "Choose Ribbon Color",
	                     Color.WHITE);
				if ( color != null ) {
					applyImageColor(img_ribbon, img_panel_ribbon, color.getRed(),color.getGreen(),color.getBlue());
				}
			}
		});
		GridBagConstraints gbc_btnGreen = new GridBagConstraints();
		gbc_btnGreen.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGreen.insets = new Insets(0, 0, 5, 5);
		gbc_btnGreen.gridx = 2;
		gbc_btnGreen.gridy = 1;
		container.add(btnGreen, gbc_btnGreen);
		
		JButton btnPattern = new JButton("Pattern");
		btnPattern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				applyImageColor(img_box_shape, img_panel_box_shape, img_box_shape_pattern);
			}
		});
		
		JButton btnBowColor = new JButton("Bow Color");
		btnBowColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(frame,
	                     "Choose Bow Color",
	                     Color.WHITE);
				if ( color != null ) {
					applyImageColor(img_bow, img_panel_bow, color.getRed(),color.getGreen(),color.getBlue());
				}
			}
		});
		GridBagConstraints gbc_btnBowColor = new GridBagConstraints();
		gbc_btnBowColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBowColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnBowColor.gridx = 2;
		gbc_btnBowColor.gridy = 2;
		container.add(btnBowColor, gbc_btnBowColor);
		GridBagConstraints gbc_btnPattern = new GridBagConstraints();
		gbc_btnPattern.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPattern.insets = new Insets(0, 0, 5, 5);
		gbc_btnPattern.gridx = 2;
		gbc_btnPattern.gridy = 3;
		container.add(btnPattern, gbc_btnPattern);
	}
	
	/**
	 * Applies color to box
	 */
	protected void applyImageColor(PNGImage orig_img, JImagePanel img_panel, int r, int g, int b) {
		img_panel.setImage(ImageController.applyColor(orig_img, r, g, b).getImage());			
		layeredPane.repaint();
	}
	
	/**
	 * Applies color to box
	 */
	protected void applyImageColor(PNGImage orig_img, JImagePanel img_panel, PNGImage img) {
		img_panel.setImage(ImageController.applyPattern(orig_img, img).getImage());
		layeredPane.repaint();
	}
}
