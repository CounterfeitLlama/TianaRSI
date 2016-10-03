import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import javax.swing.*;
import java.awt.image.*;

public class FaceDetector {
	final String OS = System.getProperty("os.name");
	final String HOME = System.getProperty("user.home");
	
	String faceDetectorPath;
	String eyeDetectorPath;
	String imagePath;
	String outputPath;
	
	CascadeClassifier faceDetector;
	CascadeClassifier eyeDetector;
	
	public FaceDetector() {
		this.run();
	}
	
    public void run() {
    	// Load library
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    	
    	// Set file paths
        initializePaths();
        
        // Set CascadeClassifiers
        faceDetector = new CascadeClassifier(faceDetectorPath);
        eyeDetector = new CascadeClassifier(eyeDetectorPath);
        Detection detector = new Detection();
    	
    	VideoCapture camera = new VideoCapture(0);
    	Mat frame = new Mat();
        JFrame jframe = new JFrame("Live Video");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel vidpanel = new JLabel();
        jframe.setContentPane(vidpanel);
        jframe.setVisible(true);

        while (true) {
            if (camera.read(frame)) {
            	detector.detect(faceDetector, frame);
            	detector.detect(eyeDetector, frame);
                ImageIcon image = new ImageIcon(mat2Img(frame));
                vidpanel.setIcon(image);
                vidpanel.repaint();
            }
        }
    }
    
    public void initializePaths() {
    	// Initialize all paths depending on OS
        if (OS.equals("Windows 10")) {
        	faceDetectorPath = HOME + "\\Downloads\\opencv\\build\\etc\\haarcascades\\haarcascade_frontalface_alt.xml";
        	eyeDetectorPath = HOME + "\\Downloads\\opencv\\build\\haarcascades\\haarcascade_eye.xml";
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
    
    public BufferedImage mat2Img(Mat m) {
    	int type = BufferedImage.TYPE_BYTE_GRAY;
        if (m.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
        m.get(0, 0, ((DataBufferByte)image.getRaster().getDataBuffer()).getData()); // Get all the pixels
        return image;
     }
}