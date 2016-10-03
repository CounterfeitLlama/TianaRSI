import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class Detection {
	public Detection() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	public Mat detect(CascadeClassifier classifier, Mat image) {
        // Detect faces in the image
        MatOfRect faceDetections = new MatOfRect();
        classifier.detectMultiScale(image, faceDetections);
        
        // Display how many faces were found
        //System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
 
        // For each face that is detected, create a rectangle surrounding it
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
        }
        return image;
	}
}