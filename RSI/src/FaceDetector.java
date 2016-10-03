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
 
    public void run() {
 
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("Starting...");

        // Open the facial recognition file
        CascadeClassifier faceDetector = new CascadeClassifier("/Users/aturley/Downloads/opencv-master/data/haarcascades/haarcascade_frontalface_alt.xml");

        // Read in face to detect
        Mat image = Imgcodecs.imread("/Users/aturley/Desktop/startrek.jpg");
 
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
        String filename = "/Users/aturley/Desktop/ouput.jpg";
        System.out.println(String.format("Done. Writing %s", filename));
        Imgcodecs.imwrite(filename, image);
    }
 
    public static void main (String[] args) {
    	FaceDetector fd = new FaceDetector();
    	fd.run();
    }
}