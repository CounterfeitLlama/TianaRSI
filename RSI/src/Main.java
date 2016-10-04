import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//System.getProperties().list(System.out);
        System.out.println("Starting...");
        System.out.println("Operating System: " + System.getProperty("os.name"));
        System.out.println("OS Version: " + System.getProperty("os.version"));
        System.out.println("Bit count: " + System.getProperty("sun.arch.data.model"));
        System.out.println("Java Verion: " + System.getProperty("java.version"));
        
		Scanner input = new Scanner(System.in);
		System.out.print("\nActivate camera? (y/n): ");
		String activate = input.next().toLowerCase();
		switch (activate) {
			case "y":
				new FaceDetector();
				break;
			case "n":
				System.out.println("Exiting...");
				System.exit(0);
			default:
				System.out.println("Invalid input.\nExiting...");
				System.exit(0);
		}
	}
}