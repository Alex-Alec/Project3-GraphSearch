import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Trimmer {
	public static void main(String[] args) throws IOException {
		final Scanner in = new Scanner(new File("IMDB/actors.list"), "ISO-8859-1");
		final PrintWriter pw = new PrintWriter("IMDB/actors_first_100000_lines.list","ISO-8859-1");
		for(int i = 0; i < 100000; i++){
			pw.println(in.nextLine());
		}
		in.close();
		pw.close();
	}
}
