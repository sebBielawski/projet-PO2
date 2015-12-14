package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;

import model.Model;
import model.Shape;
import model.ShapeSelection;
import model.events.SelectionChangeEvent;


@SuppressWarnings("serial")
public final class PropertiesPanel extends JPanel implements Observer {

	final class PropertyPanel extends JPanel {
			
		private final JPanel contentPanel = new JPanel(new BorderLayout());
	
		
		public PropertyPanel(final String name, final Border border) {
			setLayout(new BorderLayout());
			setBorder(border);		
			
			final JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
			nameLabel.setBackground(Color.LIGHT_GRAY);
			nameLabel.setOpaque(true);
			
			contentPanel.setBorder(border);
			add(nameLabel, BorderLayout.NORTH);
			add(contentPanel, BorderLayout.CENTER);
			
			final JPanel p = new JPanel();
			p.setBackground(Color.MAGENTA);
			setContentPanel(p);
	}
	
	public JPanel getContentPanel() {
		return contentPanel;
	}
	
	public void setContentPanel(final Component panel) {
		contentPanel.removeAll();
		contentPanel.add(panel, BorderLayout.CENTER);
	}
}

	private final JTextField nameTextField = new JTextField();
	private final JTextField xPositionTextField = new JTextField();
	private final JTextField yPositionTextField = new JTextField();
	private final JTextField widthTextField = new JTextField();
	private final JTextField heightTextField = new JTextField();
	private final JButton colorPickerButton = new JButton(" ");
	
	private final Model model;

	
	public PropertiesPanel(final Model model, final Border emptyBorder) {
		this.model = model;
		model.addObserver(this);
		
		final KeyListener keyListener = new KeyListener() {		
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
					requestFocusInWindow();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		nameTextField.addKeyListener(keyListener);
		nameTextField.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if (model.getShapeSelection().size() == 1) {
					final int selectedShapeIndex = (int)model.getShapeSelection().toArray()[0];
					final Shape selectedShape = model.getShape(selectedShapeIndex);
					selectedShape.setName(nameTextField.getText());
				}
				requestFocusInWindow();
			}
		});		

		xPositionTextField.addKeyListener(keyListener);
		xPositionTextField.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if (model.getShapeSelection().size() == 1) {
					final int selectedShapeIndex = (int)model.getShapeSelection().toArray()[0];
					final Shape selectedShape = model.getShape(selectedShapeIndex);
					final int x = Integer.valueOf(xPositionTextField.getText());
					final int y = selectedShape.getPosition().y;
					selectedShape.setPosition(new Point(x, y));
				}
				requestFocusInWindow();
			}
		});	
		
		yPositionTextField.addKeyListener(keyListener);
		yPositionTextField.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if (model.getShapeSelection().size() == 1) {
					final int selectedShapeIndex = (int)model.getShapeSelection().toArray()[0];
					final Shape selectedShape = model.getShape(selectedShapeIndex);
					final int x = selectedShape.getPosition().x;
					final int y =  Integer.valueOf(yPositionTextField.getText());
					selectedShape.setPosition(new Point(x, y));
				}
				requestFocusInWindow();
			}
		});		
		
		widthTextField.addKeyListener(keyListener);
		widthTextField.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if (model.getShapeSelection().size() == 1) {
					final int selectedShapeIndex = (int)model.getShapeSelection().toArray()[0];
					final Shape selectedShape = model.getShape(selectedShapeIndex);
					final int width = Integer.valueOf(widthTextField.getText());
					final int height = selectedShape.getDimension().height;
					selectedShape.setDimension(new Dimension(width, height));
				}
				requestFocusInWindow();
			}
		});
		
		heightTextField.addKeyListener(keyListener);
		heightTextField.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if (model.getShapeSelection().size() == 1) {
					final int selectedShapeIndex = (int)model.getShapeSelection().toArray()[0];
					final Shape selectedShape = model.getShape(selectedShapeIndex);
					final int width = selectedShape.getDimension().width; 
					final int height = Integer.valueOf(heightTextField.getText());
					selectedShape.setDimension(new Dimension(width, height));
				}
				requestFocusInWindow();
			}
		});	

		
		final JPanel namePanel = new JPanel(new BorderLayout());
		namePanel.add(nameTextField, BorderLayout.CENTER);
		
		final JPanel xPositionPanel = new JPanel(new GridLayout(1, 2));
		xPositionPanel.add(new JLabel("x"));
		xPositionPanel.add(xPositionTextField);
		
		final JPanel yPositionPanel = new JPanel(new GridLayout(1, 2));
		yPositionPanel.add(new JLabel("y"));
		yPositionPanel.add(yPositionTextField);
		
		final JPanel coordinatesPanel = new JPanel(new GridLayout(2, 1));
		coordinatesPanel.add(xPositionPanel);
		coordinatesPanel.add(yPositionPanel);
		
		final JPanel widthPanel = new JPanel(new GridLayout(1, 2));
		widthPanel.add(new JLabel("width"));
		widthPanel.add(widthTextField);
		
		final JPanel heightPanel = new JPanel(new GridLayout(1, 2));
		heightPanel.add(new JLabel("height"));
		heightPanel.add(heightTextField);
		
		final JPanel dimensionsPanel = new JPanel(new GridLayout(2, 1));
		dimensionsPanel.add(widthPanel);
		dimensionsPanel.add(heightPanel);
		
		colorPickerButton.setOpaque(true);
		colorPickerButton.setBackground(UIManager.getColor("Panel.background"));
		colorPickerButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		colorPickerButton.setFocusable(false);
		colorPickerButton.setFocusPainted(false);
		colorPickerButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				final Color initialBackground = colorPickerButton.getBackground();
		        final Color background = JColorChooser.showDialog(null, "Color picker", initialBackground);
		        if (background != null) {
		        	colorPickerButton.setBackground(background);
		        	if (model.getShapeSelection().size() == 1) {
						final int selectedShapeIndex = (int)model.getShapeSelection().toArray()[0];
						final Shape selectedShape = model.getShape(selectedShapeIndex);
						selectedShape.setColor(background);
					}
		        }
			}
		});

		final PropertyPanel namePropertyPanel = new PropertyPanel("Name", emptyBorder);
		namePropertyPanel.setContentPanel(namePanel);
	
		final PropertyPanel positionPropertyPanel = new PropertyPanel("Position", emptyBorder);
		positionPropertyPanel.setContentPanel(coordinatesPanel);
	
		final PropertyPanel dimensionPropertyPanel = new PropertyPanel("Dimension", emptyBorder);
		dimensionPropertyPanel.setContentPanel(dimensionsPanel);
	
		final PropertyPanel colorPropertyPanel = new PropertyPanel("Color", emptyBorder);
		colorPropertyPanel.setContentPanel(colorPickerButton);
		
		// liste des proprietes
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.BLACK);
		setBorder(emptyBorder);
		add(namePropertyPanel);
		add(Box.createRigidArea(new Dimension(0, 2)));
		add(positionPropertyPanel);
		add(Box.createRigidArea(new Dimension(0, 2)));
		add(dimensionPropertyPanel);
		add(Box.createRigidArea(new Dimension(0, 2)));
		add(colorPropertyPanel);
	}
	
	@Override
	public void update(Observable observable, Object object) {
		if (observable instanceof Model) {
			if (object != null) {
				if (object instanceof SelectionChangeEvent) {
					final SelectionChangeEvent event = (SelectionChangeEvent)object;
					final Shape selectedShape = event.getNewShapeSelection().getSelectedShape();
					if (selectedShape == null) {
						nameTextField.setText("");
						xPositionTextField.setText("");
						yPositionTextField.setText("");
						widthTextField.setText("");
						heightTextField.setText("");
						colorPickerButton.setBackground(UIManager.getColor("Panel.background"));
					} else {	
						final String shapeName = selectedShape.getName();
						final Point shapeLocation = selectedShape.getPosition();
						final Dimension shapeDimension = selectedShape.getDimension();
						final Color shapeColor = selectedShape.getColor();
						
						nameTextField.setText(shapeName);
						xPositionTextField.setText(String.valueOf(shapeLocation.x));
						yPositionTextField.setText(String.valueOf(shapeLocation.y));
						widthTextField.setText(String.valueOf(shapeDimension.width));
						heightTextField.setText(String.valueOf(shapeDimension.height));
						colorPickerButton.setBackground(shapeColor);
					}
				}
			}
		}
	}
}
