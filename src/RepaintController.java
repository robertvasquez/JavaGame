import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RepaintController implements ActionListener{

	private View view;
	
	public RepaintController(View v) {
		
		view = v;
		}

	public void actionPerformed(ActionEvent e) {
		view.repaint();	
		view.updateHUD();
	}

}
