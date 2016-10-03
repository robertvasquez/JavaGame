import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyboardManager implements KeyListener{
	
	int result;
	
	public keyboardManager(){
		result = 0;
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			result = -1;
		} else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			result = 1;
		} else if(e.getKeyCode() == KeyEvent.VK_F){
			result = 2;
		} else if(e.getKeyCode() == KeyEvent.VK_S){
			result = 3;
		}
	}

	public void keyReleased(KeyEvent e) {
		result = 0;
	}

	public void keyTyped(KeyEvent e) {
		//result = 0;
	}
	
	public int getResult(){
		return result;
	}
}
