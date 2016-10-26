import java.awt.Button;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class JFrameWithPicture {

    public JFrameWithPicture() throws MalformedURLException, IOException {
        initComponents();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new JFrameWithPicture();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void initComponents() throws MalformedURLException, IOException {
        JFrame frame = new JFrame("Frame with JPanel and background");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        
        frame.add(new Button("Button"));

        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
    }
}

class ImageUtils {

    public static BufferedImage scaleImage(int width, int height, String filename) {
        BufferedImage bi;
        try {
            ImageIcon ii = new ImageIcon(filename);
            bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = (Graphics2D) bi.createGraphics();
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
            g2d.drawImage(ii.getImage(), 0, 0, width, height, null);
        } catch (Exception e) {
            return null;
        }
        return bi;
    }

    static Image scaleImage(int width, int height, BufferedImage filename) {
        BufferedImage bi;
        try {
            bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = (Graphics2D) bi.createGraphics();
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
            g2d.drawImage(filename, 0, 0, width, height, null);
        } catch (Exception e) {
            return null;
        }
        return bi;
    }
}