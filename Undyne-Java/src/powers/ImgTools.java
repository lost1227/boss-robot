package powers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImgTools {
	
	public BufferedImage searchPlaceholderScreen() {
		BufferedImage img = new BufferedImage(UndertaleWindow.captureSize.width, UndertaleWindow.captureSize.height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();
		g.setPaint(Color.BLACK);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
		g.setPaint(Color.WHITE);
		g.drawString("Locating Undertale Window...", 5, 30);
		
		return img;
		
	}
	
	public BufferedImage writeStatus(String status, BufferedImage img) {
		Graphics2D g = img.createGraphics();
		g.setPaint(Color.WHITE);
		g.drawString(status, 2, 13);
		return img;
	}
	
	public BufferedImage writeFPS(String FPS, BufferedImage img) {
		Graphics2D g = img.createGraphics();
		g.setPaint(Color.WHITE);
		g.drawString(FPS, 2, 25);
		return img;
	}
}
