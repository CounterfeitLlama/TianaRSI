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
	final String OS = System.getProperty("sun.desktop");
	final String HOME = System.getProperty("user.home");
	
    public void run() {
    	//System.getProperties().list(System.out);
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("Starting...");
        System.out.println("Operating System: " + OS);

        // Open the facial recognition file depending on the OS
        CascadeClassifier faceDetector = null;
        if (OS.equals("windows")) {
        	faceDetector = new CascadeClassifier(HOME + "\\Downloads\\opencv\\build\\etc\\haarcascades\\haarcascade_frontalface_alt.xml");
        }
        else if (OS.equals("mac")) {
        	faceDetector = new CascadeClassifier(HOME + "/Downloads/opencv-master/data/haarcascades/haarcascade_frontalface_alt.xml");
        }
        
        // Check if faceDetector was initialized
        if (faceDetector == null) {
        	throw new NullPointerException("FaceDetector is null, bad OS");
        }
        
        // Read in face to detect
        Mat image = null;
        if (OS.equals("windows")) {
        	image = Imgcodecs.imread(HOME + "\\Desktop\\startrek.jpg");
        }
        else if (OS.equals("mac")) {
        	image = Imgcodecs.imread(HOME + "/Desktop/startrek.jpg");
        }
        
        // Check if image was initialized properly
        if (image == null) {
        	throw new NullPointerException("Image is null, no image found");
        }
 
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
        String filename = null;
        if (OS.equals("windows")) {
        	filename = HOME + "\\Desktop\\output.jpg";
        }
        else if (OS.equals("mac")) {
        	filename = HOME + "/Desktop/ouput.jpg";
        }
        System.out.println(String.format("Done. Writing %s", filename));
        Imgcodecs.imwrite(filename, image);
    }
 
    public static void main (String[] args) {
    	FaceDetector fd = new FaceDetector();
    	fd.run();
    }
}