package powers;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class UndertaleWindow {
	
	private Robot robot;
	
	private Rectangle window;
	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static Dimension captureSize = new Dimension(639,479);
	
	
	public UndertaleWindow() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		findWindow();
		
	}
	
	
	public void findWindow() {
		int x = -300, y = 0;
		BufferedImage img = robot.createScreenCapture(new Rectangle(0,0,screenSize.width, screenSize.height));
		
		System.out.println("Searching for undertale window...");
		MAINLOOP : while(true) {

			x += 300;
			if(x >= screenSize.getWidth()) {
				x = 0;
				y += 10;
			}
			if(y >= screenSize.getHeight()) {
				y = 0;
				robot.delay(100);
				img = robot.createScreenCapture(new Rectangle(0,0,screenSize.width, screenSize.height));
			}
			
			
			if(img.getRGB(x,y) == Color.WHITE.getRGB()) {
				int x2 = x, y2 = y;
				
				while(img.getRGB(x2, y2) != Color.RED.getRGB()) {
					x2--;
					if(x2 < 0 || x - x2 > 300) continue MAINLOOP;
				}
				while(img.getRGB(x2, y2) == Color.RED.getRGB()) {
					x2--;
				}
				if(x2 < 0) continue MAINLOOP;
				while(img.getRGB(x2, y2) == Color.WHITE.getRGB()) {
					x2--;
				}
				if(x2 < 0) continue MAINLOOP;
				x2++;
				if(img.getRGB(x2, y2) != Color.WHITE.getRGB()) continue MAINLOOP;
				while(img.getRGB(x2, y2) == Color.WHITE.getRGB()) {
					y2--;
					if(y2 <= 0 || y-y2 > 10) continue MAINLOOP;
				}
				y2++;
				while(img.getRGB(x2, y2) == Color.WHITE.getRGB()) {
					x2--;
				}
				if(x2 < 0) continue MAINLOOP;
				x2++;
				
				window = new Rectangle(x2, y2+24, captureSize.width, captureSize.height);
				if(checkWindow()) break MAINLOOP;
				
			}
		}
	}
	
	
	public boolean checkWindow() {
		
		BufferedImage capture = robot.createScreenCapture(new Rectangle(window.x+4, window.y - 20, 16, 16));
		
		
		int[][] redPix = { {3,1}, {12,1}, {1,6}, {7,7}, {14,6}, {7,15} };
		int[][] whitePix = { {0,0}, {7,0}, {15,0}, {7,3}, {0,15}, {15,15}};
		
		for(int[] pt : redPix) {
			if(capture.getRGB(pt[0],pt[1]) != Color.RED.getRGB()) {
				return false;
			}
		}
		for(int[] pt : whitePix) {
			if(capture.getRGB(pt[0],pt[1]) != Color.WHITE.getRGB()) {
				return false;
			}
		}
		
		return true;
	}
	
	public BufferedImage getWindow() {
		return robot.createScreenCapture(window);
	}

}
