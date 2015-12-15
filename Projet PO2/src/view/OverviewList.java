package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

import model.Model;
import model.MyShape;
import model.Namable;
import model.events.EditNameShapeEvent;
import model.events.SelectionChangeEvent;


public final class OverviewList extends JList<Namable> implements Observer {

	private static final long serialVersionUID = 1L;
	private final Model model;
	private MyShape selectedShape;
	
	
	
	public OverviewList(final Model model, final DefaultListModel<? extends Namable> listModel) {
		super((ListModel<Namable>) listModel);
		
		this.model = model;
		model.addObserver(this);

		this.setOpaque(true);
		this.setFocusable(false);
		this.setCellRenderer(new ListCellRenderer<Namable>() {
			@Override
			public Component getListCellRendererComponent(JList<? extends Namable> list, Namable value, int index, boolean isSelected, boolean cellHasFocus) {
				final JLabel label = new JLabel(value.getName(), JLabel.CENTER);
				label.setOpaque(true);
				
				if (isSelected)
					label.setBackground(Color.GREEN);			
				else if (index >= 0) {
					final Point p = list.getMousePosition();
					if (p != null && list.locationToIndex(p) == index)
						label.setBackground(Color.ORANGE);
				}

				return label;
			}	
		});
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Model) {
			if (arg != null) {
				if (arg instanceof SelectionChangeEvent) {
					if (this.selectedShape != null)
						this.selectedShape.deleteObserver(this);
					this.selectedShape = ((SelectionChangeEvent)arg).getNewShapeSelection().getSelectedShape();	
					this.selectedShape.addObserver(this);
				} else if (arg instanceof EditNameShapeEvent) 
					this.repaint();
			}
		}
	}
}
