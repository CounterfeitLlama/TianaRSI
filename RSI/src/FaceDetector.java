import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.Scanner;

public class FaceDetector {
	final String OS = System.getProperty("os.name");
	final String HOME = System.getProperty("user.home");
	
	String faceDetectorPath;
	String eyeDetectorPath;
	String imagePath;
	String outputPath;
	
	int camNumber = 0;
	
	CascadeClassifier faceDetector;
	CascadeClassifier eyeDetector;
	
	/**
	 * Constructor for the FaceDetector class
	 */
	public FaceDetector(int camera) {
		this.run(camera);
	}
	
	/**
	 * Automatically run when constructor is created
	 */
    public void run(int camNum) {
    	camNumber = camNum;
    	
    	// Load library
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    	
    	// Set file paths
        initializePaths();
        
        // Set CascadeClassifiers
        faceDetector = new CascadeClassifier(faceDetectorPath);
        eyeDetector = new CascadeClassifier(eyeDetectorPath);
        Detection detector = new Detection();
    	
    	VideoCapture camera = new VideoCapture(camNum);
    	Mat frame = new Mat();
        JFrame jframe = new JFrame("Live Video");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel vidpanel = new JLabel();
        jframe.setContentPane(vidpanel);
        jframe.setVisible(true);
        jframe.addKeyListener(new KeyListener() {
        	@Override
        	public void keyTyped(KeyEvent e) { // May implement later for reasons
        	}
        	@Override
        	public void keyReleased(KeyEvent e) { // May implement later for reasons
        	}
        	@Override
        	public void keyPressed(KeyEvent e) { // This one is important
        		String pressedKey = KeyEvent.getKeyText(e.getKeyCode());
        		if (pressedKey.equals("⎋") && OS.equals("Mac OS X")) {
        			jframe.dispose();
        			quit();
        		}
        		else if (pressedKey.equals("Escape") && OS.equals("Windows 10")) {
        			jframe.dispose();
        			quit();
        		}
        		System.out.println("Key Character: " + e.getKeyChar() + "; Key Code: " + KeyEvent.getKeyText(e.getKeyCode()));
        	}
        	});

        while (true) {
            if (camera.read(frame)) {
            	//detector.detect(faceDetector, frame);
            	detector.detect(eyeDetector, frame);
                ImageIcon image = new ImageIcon(mat2Img(frame));
                vidpanel.setIcon(image);
                vidpanel.repaint();
            }
        }
    }
    
    public void initializePaths() {
    	/**
    	 * Initialize all paths depending on OS
    	 * Windows uses \ as delimeter and Mac uses \
    	 */
        if (OS.equals("Windows 10")) {
        	faceDetectorPath = HOME + "\\Downloads\\opencv\\build\\etc\\haarcascades\\haarcascade_frontalface_alt.xml";
        	eyeDetectorPath = HOME + "\\Downloads\\opencv\\build\\etc\\haarcascades\\frontEyes.xml";
        	imagePath = HOME + "\\Desktop\\startrek.jpg";
        	outputPath = HOME + "\\Desktop\\output.jpg";
        }
        else if (OS.equals("Mac OS X")) {
        	faceDetectorPath = HOME + "/Downloads/opencv-master/data/haarcascades/haarcascade_frontalface_alt.xml";
        	eyeDetectorPath = HOME + "/Downloads/opencv-master/data/haarcascades/haarcascade_eye.xml";
        	imagePath = HOME + "/Desktop/startrek.jpg";
        	outputPath = HOME + "/Desktop/output.jpg";
        }
    }
    
    /**
     * Convert a Mat image to BufferedImage so it can be displayed
     */
    public BufferedImage mat2Img(Mat m) {
    	int type = BufferedImage.TYPE_BYTE_GRAY;
        if (m.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
        m.get(0, 0, ((DataBufferByte)image.getRaster().getDataBuffer()).getData()); // Get all the pixels
        return image;
     }
    
    /**
     * Method to quit the application after escape key is pressed
     */
    public void quit() {
		Scanner in = new Scanner(System.in);
    	System.out.print("Quit program? (y/n): ");
		String quit = in.next();
		switch (quit) {
			case "y":
				System.out.println("Exiting...");
				System.exit(0);
			case "n":
				System.out.println("Activate camera? (y/n): ");
				String activate = in.next();
				switch (activate) {
					case "y":
						run(camNumber);
					case "n":
						System.out.println("Exiting...");
						System.exit(0);
					default:
						System.out.println("Invalid input.\n Exiting anyways...");
						System.exit(1);
				}
		}
    }
}