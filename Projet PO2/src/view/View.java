package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Model;
import model.MyShape;
import model.Namable;
import model.ShapeSelection;
import model.events.CreateShapeEvent;
import model.events.DeleteShapeEvent;


public final class View implements Observer {
	
	private final Model model;
	private final DefaultListModel<MyShape> listModel = new DefaultListModel<MyShape>();
	private final PropertiesPanel propertiesPanel;
	
	public View(final Model model) {
		this.model = model;
		model.addObserver(this);

		final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setResizeWeight(0.5);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);

        propertiesPanel = createPropertiesPanel(model, new EmptyBorder(4, 4, 4, 4));
        final JPanel hierarchyPanel = createHierarchyPanel(model, listModel);

        final JPanel controlPanel = createControlPanel(model, propertiesPanel, hierarchyPanel);
        splitPane.add(controlPanel);
        splitPane.add(createSceneView(model));
        
		final JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(splitPane, BorderLayout.CENTER);
		
		final JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.add(createToolbar(model), BorderLayout.NORTH);
		contentPane.add(mainPanel, BorderLayout.CENTER);
		
		createFrame(model, contentPane);
	}
	
	public void update(Observable observable, Object object) {
		if (observable instanceof Model) {	
			if (object != null) {
				if (object instanceof CreateShapeEvent) 
					listModel.addElement(((CreateShapeEvent)object).getCreatedShape());	
				else if (object instanceof DeleteShapeEvent) {

					listModel.removeElement(((DeleteShapeEvent)object).getDeletedShape());	
				}
			}
		}
	}
	
	
	
	
	private static JPanel createToolbar(final Model model) {
		final JPopupMenu fileOptionsPopupMenu = new JPopupMenu("File");
		fileOptionsPopupMenu.add(new JMenuItem("New"));
		fileOptionsPopupMenu.add(new JMenuItem("Open"));
		fileOptionsPopupMenu.add(new JMenuItem("Save"));
		fileOptionsPopupMenu.add(new JMenuItem("Save as..."));
		fileOptionsPopupMenu.add(new JMenuItem("Save all"));
		fileOptionsPopupMenu.add(new JMenuItem("Close"));
		
		final JButton fileOptionsButton = new JButton("File");
		fileOptionsButton.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent arg0) {
				final Component source = (Component)arg0.getSource(); 
				final Dimension size = source.getSize(); 

			    final int xPos = (size.width - fileOptionsPopupMenu.getPreferredSize().width) / 2; 
			    final int yPos = size.height;

			    fileOptionsPopupMenu.show(source, xPos, yPos);
			}
		});
		
		final JPanel toolBar = new JPanel();
		toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));
		toolBar.setBackground(Color.RED);
		toolBar.add(fileOptionsButton);	
		return toolBar;
	}
	
	private static PropertiesPanel createPropertiesPanel(final Model model, final Border border) {
		final PropertiesPanel propertiesPanel = new PropertiesPanel(model, border);
		return propertiesPanel;
	}
	
	private static JPanel createHierarchyPanel(final Model model, final DefaultListModel<? extends Namable> listModel) {	
		final OverviewList list = new OverviewList(model, listModel);
		list.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
                list.repaint();
            }
        });
		list.addMouseMotionListener(new MouseMotionListener() {		
			private int mouseOver;	
			public void mouseMoved(MouseEvent e) {
				final int index = list.locationToIndex(e.getPoint());
				if (mouseOver != index) {
					mouseOver = index;
					list.repaint();
				}
			}
			public void mouseDragged(MouseEvent e) {
				
			}
		});
		list.addListSelectionListener(new ListSelectionListener() {		
			public void valueChanged(ListSelectionEvent e) {
				final Vector<Integer> selectedShapesIndices = new Vector<>();
				for (int i = e.getFirstIndex(); i <= e.getLastIndex(); ++i)
					if (list.isSelectedIndex(i))
						selectedShapesIndices.add(new Integer(i));	
				MyShape selectedShape;
				if (selectedShapesIndices.size() != 1)
					selectedShape = null;
				else {
					final int selectedShapeIndex = selectedShapesIndices.get(0);
					selectedShape = model.getShape(selectedShapeIndex);
				}

				model.setShapeSelection(new ShapeSelection(selectedShapesIndices, selectedShape));
			}
		});
		
		final JPopupMenu objectOptionsPopupMenu = new JPopupMenu("File");
		objectOptionsPopupMenu.add(new JMenuItem("Delete"));		
		
		list.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}	
			@Override
			public void mousePressed(MouseEvent e) {			
			}			
			@Override
			public void mouseExited(MouseEvent e) {
			}			
			@Override
			public void mouseEntered(MouseEvent e) {
			}		
			@Override
			public void mouseClicked(MouseEvent e) {		
				if (e.getButton() == MouseEvent.BUTTON3 && list.getModel().getSize() > 0) {
					final int index = list.locationToIndex(e.getPoint());
					final Rectangle cellBounds = list.getCellBounds(index, index);
					if (cellBounds != null && cellBounds.contains(e.getPoint())) {
						final Point cellBoundsLocation = cellBounds.getLocation();
						final Dimension cellBoundsSize = cellBounds.getSize();
						final int x = cellBoundsLocation.x + (cellBoundsSize.width - objectOptionsPopupMenu.getPreferredSize().width) / 2;
						final int y = cellBoundsLocation.y + cellBoundsSize.height;
						objectOptionsPopupMenu.show(list, x, y);
					}
				}
			}
		});


		final JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        final PropertiesPanel.PropertyPanel hierarchyPanel = new PropertiesPanel(model, null).new PropertyPanel("Overview", new EmptyBorder(4, 4, 4, 4));
		hierarchyPanel.setContentPanel(scrollPane);
		
		return hierarchyPanel;
	}
	
	private static JPanel createControlPanel(final Model model, final JPanel propertiesPanel, final JPanel hierarchyPanel) {
		final JPanel controlPanel = new JPanel(new BorderLayout());
		controlPanel.setBackground(Color.GREEN);
		controlPanel.add(propertiesPanel, BorderLayout.NORTH);	
		controlPanel.add(hierarchyPanel, BorderLayout.CENTER);		
		return controlPanel;
	}
	
	private static JPanel createSceneView(final Model model) {
		final JPanel sceneView = new JPanel();
		sceneView.setBackground(Color.BLUE);
		sceneView.add(new JLabel("VUE GRAPHIQUE"));
		return sceneView;
	}
	
	private static JFrame createFrame(final Model model, final JPanel contentPane) {
		final JFrame frame = new JFrame();
		frame.setContentPane(contentPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.addKeyListener(new KeyListener() {		
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyChar() == 'c')
					model.createShape();
				if (arg0.getKeyChar() == 'd') {
					final MyShape selectedShape = model.getShapeSelection().getSelectedShape();
					if (selectedShape != null)
						model.deleteShape(selectedShape);
				}			
			}
		
			public void keyReleased(KeyEvent arg0) {

			}		
			public void keyPressed(KeyEvent arg0) {
	
			}
		});
        return frame;
	}
}
