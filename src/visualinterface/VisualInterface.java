package visualinterface;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * Class that will manage the visual interface where the user parsed binary JSON files.
 * @author danilo.melo.rocha
 *
 */
public class VisualInterface extends JFrame  {
	
	/**
	 * Constructor used to set elements that will be displayed on the JFrame used as visual interface.
	 */
	public VisualInterface() {
        setTitle("JSON binary Parser");
        setSize(980, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new FilePanel(), BorderLayout.WEST);
        add(new Logging(), BorderLayout.SOUTH);
        setVisible(true);
    }
	
	public static void main (String args[]) {
        VisualInterface frame = new VisualInterface();
    }

}
