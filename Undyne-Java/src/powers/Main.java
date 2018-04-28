package powers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Main {
	
	public static void main(String[] args) {
		ImageViewer viewer = new ImageViewer(UndertaleWindow.captureSize);
		ImgTools imgtools = new ImgTools();
		viewer.setImage(imgtools.searchPlaceholderScreen());
		viewer.show();
		
		FPSMeter meter = new FPSMeter();
		
		UndertaleWindow window = new UndertaleWindow();
		GameInterpreter interpreter = new GameInterpreter();
		PushKeys keys = new PushKeys();
		
		while(true) {
			BufferedImage img = window.getWindow();
			if(!window.checkWindow()) {
				viewer.setImage(imgtools.searchPlaceholderScreen());
				window.findWindow();
			}
			if(interpreter.isGreenCenter(img)) {
				String status;
				switch(interpreter.closestArrow(img)) {
				case UP:
					status = "UP";
					keys.pushUp();
					break;
				case RIGHT:
					status = "RIGHT";
					keys.pushRight();
					break;
				case DOWN:
					status = "DOWN";
					keys.pushDown();
					break;
				case LEFT:
					status = "LEFT";
					keys.pushLeft();
					break;
				case NONE:
				default:
					status = "ENABLED";
					break;
				}
				img = interpreter.getLastImg();
				img = imgtools.writeStatus(status, img);
				img = imgtools.writeFPS(String.format("%.2f FPS", meter.getFPS()), img);
				viewer.setImage(img);
				
			} else {
				img = imgtools.writeStatus("DISABLED", img);
				img = imgtools.writeFPS(String.format("%.2f FPS", meter.getFPS()), img);
				viewer.setImage(img);
			}
			
		}
		
		
	}

}
