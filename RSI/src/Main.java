import java.awt.*;       // Using AWT layouts
import java.awt.event.*; // Using AWT event classes and listener interfaces
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;    // Using Swing components and containers

// A Swing GUI application inherits from top-level container javax.swing.JFrame
public class Main extends JFrame {   // JFrame instead of Frame
	private JTextField tfCount;  // Use Swing's JTextField instead of AWT's TextField
	private JButton btnCount;    // Using Swing's JButton instead of AWT's Button
	private int count = 0;

	// Constructor to setup the GUI components and event handlers
	public Main() throws MalformedURLException, IOException {
		initComponents();
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Main();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void initComponents() throws MalformedURLException, IOException {
		// Retrieve the content-pane of the top-level container JFrame
		// All operations done on the content-pane
		//Container cp = getContentPane();
		//cp.setLayout(new FlowLayout());   // The content-pane sets its layout
		
        JFrame frame = new JFrame("Frame with JPanel and background");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		frame.add(new JLabel("Counter"));
		tfCount = new JTextField("0", 10);
		tfCount.
		tfCount.setEditable(false);
		frame.add(tfCount);

		btnCount = new JButton("Count");
		frame.add(btnCount);

		// Allocate an anonymous instance of an anonymous inner class that
		//  implements ActionListener as ActionEvent listener
		btnCount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				++count;
				tfCount.setText(count + "");
			}
		});
		
        final Image background = ImageUtils.scaleImage(300, 300, ImageIO.read(new URL("https://lh4.googleusercontent.com/enVZiSNpLcn_qa1u8iLJHT8NcxqE4TGuShfiKvgXuR6EmK_2bB3f5W4ptSI2HctW8byaK6WcZQ%3Ds640-h400-e365")));
        final Dimension jpanelDimensions = new Dimension(new ImageIcon(background).getIconWidth(), new ImageIcon(background).getIconHeight());

        frame.add(new JPanel() {
            @Override
            protected void paintComponent(Graphics grphcs) {
                super.paintComponent(grphcs);
                grphcs.drawImage(background, 0, 0, this);
            }

            @Override
            public Dimension getPreferredSize() {
                return jpanelDimensions;
            }
        });

        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
    }
}