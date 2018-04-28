package powers;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class PushKeys {

	private Robot robot;
	
	private int delay = 25;
	
	public PushKeys() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public void pushUp() {
		robot.keyPress(KeyEvent.VK_UP);
		robot.delay(delay);
		robot.keyRelease(KeyEvent.VK_UP);
	}
	
	public void pushLeft() {
		robot.keyPress(KeyEvent.VK_LEFT);
		robot.delay(delay);
		robot.keyRelease(KeyEvent.VK_LEFT);
	}
	
	public void pushDown() {
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.delay(delay);
		robot.keyRelease(KeyEvent.VK_DOWN);
	}
	
	public void pushRight() {
		robot.keyPress(KeyEvent.VK_RIGHT);
		robot.delay(delay);
		robot.keyRelease(KeyEvent.VK_RIGHT);
	}

}
