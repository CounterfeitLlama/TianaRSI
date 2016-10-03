import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
public class FaceDetector {
	final String OS = System.getProperty("os.name");
	final String HOME = System.getProperty("user.home");
	String faceDetectorPath;
	String imagePath;
	String outputPath;
	
    public void run() {
    	//System.getProperties().list(System.out);
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("Starting...");
        System.out.println("Operating System: " + OS);
        
        //Set file paths
        initializePaths();

        // Open the facial recognition file
        CascadeClassifier faceDetector = new CascadeClassifier(faceDetectorPath);
        
        // Read in face to detect
        Mat image = Imgcodecs.imread(imagePath);
 
        // Detect faces in the image
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);
        
        // Display how many faces were found
        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
 
        // For each face that is detected, create a rectangle surrounding it
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
        }
 
        // Save the new file with rectangles displaying the detected faces
        System.out.println(String.format("Done. Writing %s", outputPath));
        Imgcodecs.imwrite(outputPath, image);
    }
    
    public void initializePaths() {
    	// Initialize all paths depending on OS
        if (OS.equals("windows")) {
        	faceDetectorPath = HOME + "\\Downloads\\opencv\\build\\etc\\haarcascades\\haarcascade_frontalface_alt.xml";
        	imagePath = HOME + "\\Desktop\\startrek.jpg";
        	outputPath = HOME + "\\Desktop\\output.jpg";
        }
        else if (OS.equals("Mac OS X")) {
        	faceDetectorPath = HOME + "/Downloads/opencv-master/data/haarcascades/haarcascade_frontalface_alt.xml";
        	imagePath = HOME + "/Desktop/startrek.jpg";
        	outputPath = HOME + "/Desktop/output.jpg";
        }
    }
 
    public static void main (String[] args) {
    	FaceDetector fd = new FaceDetector();
    	fd.run();
    }
}