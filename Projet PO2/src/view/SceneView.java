package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Scene;


public class SceneView extends JPanel implements Observer {
	

	public Scene scene;
	
	public SceneView(final Scene scene) {
		this.scene = scene;
		this.setBackground(Color.WHITE);
		//this.setBorder(BorderFactory.createLineBorder(Color.black));	// TODO : REMOVE
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		scene.draw((Graphics2D)g);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Scene && arg != null && arg instanceof ArrayList) {
			repaint();
		}
	}
}
