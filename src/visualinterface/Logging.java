package visualinterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Logging extends JPanel implements WindowListener,
ActionListener, Runnable {

	private JTextArea textArea;

    private JLabel label = new JLabel("Logs:");

    private JLabel vazio = new JLabel("");

    private Thread reader;

    private Thread reader2;

    private boolean quit;

    private final PipedInputStream pin = new PipedInputStream();

    private final PipedInputStream pin2 = new PipedInputStream();


    public Logging() {
        // create all components and add them
        addComponents();

        try {
            PipedOutputStream pout = new PipedOutputStream(this.pin);
            System.setOut(new PrintStream(pout, true));
        } catch (java.io.IOException io) {
            textArea.append("Couldn't redirect STDOUT to this console\n" + io.getMessage());
        } catch (SecurityException se) {
            textArea.append("Couldn't redirect STDOUT to this console\n" + se.getMessage());
        }

        try {
            PipedOutputStream pout2 = new PipedOutputStream(this.pin2);
            System.setErr(new PrintStream(pout2, true));
        } catch (java.io.IOException io) {
            textArea.append("Couldn't redirect STDERR to this console\n" + io.getMessage());
        } catch (SecurityException se) {
            textArea.append("Couldn't redirect STDERR to this console\n" + se.getMessage());
        }

        quit = false; // signals the Threads that they should exit

        // Starting two separate threads to read from the PipedInputStreams
        //
        reader = new Thread(this);
        reader.setDaemon(true);
        reader.start();
        //
        reader2 = new Thread(this);
        reader2.setDaemon(true);
        reader2.start();
    }

    private void addComponents(){
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_START; //bottom of space
        c.gridx = 0;       //aligned with button 2
        c.gridwidth = 1;   //2 columns wide
        c.gridy = 0;       //third row
        c.insets = new Insets(5, 5, 5, 5);
        add(label, c);

        GridBagConstraints cVazio = new GridBagConstraints();
        cVazio.fill = GridBagConstraints.HORIZONTAL;
        cVazio.ipady = 0;       //reset to default
        cVazio.weighty = 1.0;   //request any extra vertical space
        cVazio.anchor = GridBagConstraints.PAGE_START; //bottom of space
        cVazio.gridx = 1;       //aligned with button 2
        cVazio.gridwidth = 1;   //2 columns wide
        cVazio.gridy = 1;       //third row
        cVazio.insets = new Insets(5, 5, 5, 5);
        add(vazio, cVazio);

        GridBagConstraints c2 = new GridBagConstraints();
        c2.fill = GridBagConstraints.HORIZONTAL;
        c2.ipady = 0;       //reset to default
        c2.weighty = 1.0;   //request any extra vertical space
        c2.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c2.gridx = 1;       //aligned with button 2
        c2.gridwidth = 1;   //2 columns wide
        c2.insets = new Insets(5, 5, 5, 5);
        c2.gridy = 0;       //third row

        textArea = new JTextArea();
        textArea.setRows(20);
        textArea.setColumns(62);
        textArea.setEditable(false);
        add(new JScrollPane(textArea), c2);
//        add(new JScrollPane(textArea),
//                BorderLayout.CENTER);

        GridBagConstraints c3 = new GridBagConstraints();
        c3.fill = GridBagConstraints.HORIZONTAL;
        c3.ipady = 0;       //reset to default
        c3.weighty = 1.0;   //request any extra vertical space
        c3.anchor = GridBagConstraints.PAGE_START; //bottom of space
        c3.gridx = 2;       //aligned with button 2
        c3.gridwidth = 1;   //2 columns wide
        c3.insets = new Insets(5, 5, 5, 5);
        c3.gridy = 0;       //third row
        JButton button = new JButton("Clear logs");
        add(button, c3);
        setVisible(true);

        button.addActionListener(this);
    }

    public synchronized void windowClosed(WindowEvent evt) {
        quit = true;
        this.notifyAll(); // stop all threads
        try {
            reader.join(1000);
            pin.close();
        } catch (Exception e) {
        }
        try {
            reader2.join(1000);
            pin2.close();
        } catch (Exception e) {
        }
        System.exit(0);
    }

    public synchronized void windowClosing(WindowEvent evt) {
        setVisible(false); // default behaviour of JPanel
    }

    public synchronized void actionPerformed(ActionEvent evt) {
        textArea.setText("");
    }

    public synchronized void run() {
        try {
            while (Thread.currentThread() == reader) {
                try {
                    this.wait(100);
                } catch (InterruptedException ie) {
                }
                if (pin.available() != 0) {
                    String input = this.readLine(pin);
                    textArea.append(input);
                }
                if (quit)
                    return;
            }

            while (Thread.currentThread() == reader2) {
                try {
                    this.wait(100);
                } catch (InterruptedException ie) {
                }
                if (pin2.available() != 0) {
                    String input = this.readLine(pin2);
                    textArea.append(input);
                }
                if (quit)
                    return;
            }
        } catch (Exception e) {
            textArea.append("\nConsole reports an Internal error.");
            textArea.append("The error is: " + e);
        }


    }

    public synchronized String readLine(PipedInputStream in) throws IOException {
        String input = "";
        do {
            int available = in.available();
            if (available == 0)
                break;
            byte b[] = new byte[available];
            in.read(b);
            input = input + new String(b, 0, b.length);
        } while (!input.endsWith("\n") && !input.endsWith("\r\n") && !quit);
        return input;
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }
	
}
