package powers;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class GameInterpreter {

	public enum Direction {UP, RIGHT, DOWN, LEFT, NONE};
	
	private Point center = new Point(319, 240);
	
	private Color green = new Color(0,192,0);
	private Color red = new Color(237, 41, 66);
	private Color blue = new Color(15,172,226);
	private Color gold = new Color(252, 255, 71);
	
	int depth = 3;
	
	private int arrowRadius = 80;
	private int goldRadius = 62;
	
	private BufferedImage infoImg;
	
	
	public GameInterpreter() {
		
	}
	
	public boolean colorsClose(Color c1, Color c2, int range) {
		return Math.abs(c1.getRed() - c2.getRed()) < range && Math.abs(c1.getGreen() - c2.getGreen()) < range && Math.abs(c1.getBlue() - c2.getBlue()) < range; 
	}
	public boolean isGreenCenter(BufferedImage img) {
		return colorsClose(new Color(img.getRGB(center.x, center.y)), green, 80);
	}
	
	public boolean isRedArrow(BufferedImage img, int x, int y, Direction dir) {
		switch(dir) {
		case UP:
			for(int i = 0; i < depth; i++) {
				if(!colorsClose(new Color(img.getRGB(x, y-i)), red, 80)) return false;
			}
			return true;
		case LEFT:
			for(int i = 0; i < depth; i++) {
				if(!colorsClose(new Color(img.getRGB(x-i, y)), red, 80)) return false;
			}
			return true;
		case DOWN:
			for(int i = 0; i < depth; i++) {
				if(!colorsClose(new Color(img.getRGB(x, y+i)), red, 80)) return false;
			}
			return true;
		case RIGHT:
			for(int i = 0; i < depth; i++) {
				if(!colorsClose(new Color(img.getRGB(x+i, y)), red, 80)) return false;
			}
			return true;
		default:
			return false;
		}
	}
	
	public boolean isBlueArrow(BufferedImage img, int x, int y, Direction dir) {
		switch(dir) {
		case UP:
			for(int i = 0; i < depth; i++) {
				if(!colorsClose(new Color(img.getRGB(x, y-i)), blue, 80)) return false;
			}
			return true;
		case LEFT:
			for(int i = 0; i < depth; i++) {
				if(!colorsClose(new Color(img.getRGB(x-i, y)), blue, 80)) return false;
			}
			return true;
		case DOWN:
			for(int i = 0; i < depth; i++) {
				if(!colorsClose(new Color(img.getRGB(x, y+i)), blue, 80)) return false;
			}
			return true;
		case RIGHT:
			for(int i = 0; i < depth; i++) {
				if(!colorsClose(new Color(img.getRGB(x+i, y)), blue, 80)) return false;
			}
			return true;
		default:
			return false;
		}
	}
	
	public boolean isGoldArrow(BufferedImage img, int x, int y, Direction dir) {
		switch(dir) {
		case UP:
			for(int i = 0; i < depth; i++) {
				if(!colorsClose(new Color(img.getRGB(x, y-i)), gold, 80)) return false;
			}
			return true;
		case LEFT:
			for(int i = 0; i < depth; i++) {
				if(!colorsClose(new Color(img.getRGB(x-i, y)), gold, 80)) return false;
			}
			return true;
		case DOWN:
			for(int i = 0; i < depth; i++) {
				if(!colorsClose(new Color(img.getRGB(x, y+i)), gold, 80)) return false;
			}
			return true;
		case RIGHT:
			for(int i = 0; i < depth; i++) {
				if(!colorsClose(new Color(img.getRGB(x+i, y)), gold, 80)) return false;
			}
			return true;
		default:
			return false;
		}
	}
	
	public Direction closestArrow(BufferedImage img) {
		int x1 = center.x, x2 = center.x, y1 = center.y, y2 = center.y, r = 0;
		while(r < arrowRadius) {
			if(isRedArrow(img, center.x, y1, Direction.UP) || isBlueArrow(img, center.x, y1, Direction.UP) || (r < goldRadius && isGoldArrow(img, center.x, y1, Direction.UP))) return Direction.UP;
			if(isRedArrow(img, center.x, y2, Direction.DOWN) || isBlueArrow(img, center.x, y2, Direction.DOWN) || (r < goldRadius && isGoldArrow(img, center.x, y2, Direction.DOWN))) return Direction.DOWN;
			if(isRedArrow(img, x1, center.y, Direction.LEFT) || isBlueArrow(img, x1, center.y, Direction.LEFT) || (r < goldRadius && isGoldArrow(img, x1, center.y, Direction.LEFT))) return Direction.LEFT;
			if(isRedArrow(img, x2, center.y, Direction.RIGHT) || isBlueArrow(img, x2, center.y, Direction.RIGHT) || (r < goldRadius && isGoldArrow(img, x2, center.y, Direction.RIGHT))) return Direction.RIGHT;
			
			Color c;
			if(r < goldRadius) {
				c = Color.WHITE;
			} else {
				c = Color.CYAN;
			}
			img.setRGB(center.x, y1, c.getRGB());
			img.setRGB(center.x, y2, c.getRGB());
			img.setRGB(x1, center.y, c.getRGB());
			img.setRGB(x2, center.y, c.getRGB());
			infoImg = img;
			
			x1--;
			x2++;
			y1--;
			y2++;
			r++;
		}
		return Direction.NONE;
	}
	
	public BufferedImage getLastImg() {
		return infoImg;
	}
	
	
	

}
