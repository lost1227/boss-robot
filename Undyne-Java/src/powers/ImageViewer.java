package powers;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class ImageViewer {
	
	private JFrame frame;
	private PictureComponent viewer;
	
	private class PictureComponent extends JComponent {

		private BufferedImage img;
		
		@Override
		public void paintComponent(Graphics g) {
			if(img != null) {
				setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
				g.drawImage(img, 0, 0, null);
			}
		}
		
		public void setImage(BufferedImage img) {
			this.img = img;
		}
		
	}

	public ImageViewer(Dimension size) {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Undyne Boss Assistant");
		
		viewer = new PictureComponent();
		viewer.setPreferredSize(size);
		
		frame.add(viewer);
		frame.pack();
	}
	
	public void setImage(BufferedImage img) {
		viewer.setImage(img);
		viewer.repaint();
	}
	
	public void show() {
		frame.setVisible(true);
	}

}
