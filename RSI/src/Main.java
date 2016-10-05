import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws InvalidInputException {
		//System.getProperties().list(System.out);
        System.out.println("Starting...");
        System.out.println("Operating System: " + System.getProperty("os.name"));
        System.out.println("OS Version: " + System.getProperty("os.version"));
        System.out.println("Bit count: " + System.getProperty("sun.arch.data.model"));
        System.out.println("Java Verion: " + System.getProperty("java.version"));
        
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.println("Activate which camera? (-1 for exit): ");
			String num = input.next();
			switch (num) {
				case "-1":
					System.out.println("Exiting...");
					System.exit(0);
				default:
					try {
						int cameraNumber = Integer.parseInt(num);
						new FaceDetector(cameraNumber);
						break;
					}
					catch (Exception e) {
						throw new InvalidInputException();
					}
			}
		}
	}
}
