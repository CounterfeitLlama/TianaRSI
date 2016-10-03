import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Activate camera? (y/n)");
		String activate = input.next().toLowerCase();
		switch (activate) {
			case "y":
				FaceDetector faceDetector = new FaceDetector();
				break;
			case "n":
				System.out.println("Exiting...");
				System.exit(0);
			default:
				System.out.println("Invalid input\nExiting...");
				System.exit(0);
		}
	}
}