import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

public class imageMash {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Check to see if there's actually a list file.
		if(args[0] == null) {
			System.out.println("You must specify the input file.");
			System.exit(0);
		}
		
		// Where we're storing the desired combinations
		List<String[]> combos;
		
		// Try loading the information into the combos
		try {
			BufferedReader a = new BufferedReader(new FileReader(args[0]));
			combos = getFiles(a);
			a.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		BufferedImage out = null;
		
		BufferedImage face = null;
		try {
			face = ImageIO.read(new File("file.jpg"));
		}
		catch(IOException e) {
			// EXCEPTION HANDLING HERE
		}
		
		BufferedImage house = null;
		try {
			house = ImageIO.read(new File("file.jpg"));
		}
		catch(IOException e) {
			// EXCEPTION HANDLING HERE
		}
		
		out = doMash(face, house);
		
		try {
			ImageIO.write(out, "JPEG", new File("out.jpg"));
		}
		catch(IOException e) {
			// EXCEPTION HANDLING
		}
		catch(IllegalArgumentException e) {
			// EXCEPTION HANDING
		}
		
	}
	
	public static BufferedImage doMash(BufferedImage face, BufferedImage house) {
		
		// make a buffimg for output
		BufferedImage output = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
		// something to give us the flipflip effect for the loop
		boolean flop = false;
		
		for(int i = 0; i < 200; i++) {
			for(int j = 0; j < 200; j++) {
				
				if(flop) {
					output.setRGB(i, j, face.getRGB(i, j));
					flop = false;
				}
				else {
					output.setRGB(i, j, house.getRGB(i, j));
					flop = true;
				}
				j++;
			}
			i++;
		}
		
		return output;
	}
	
	public static List<String[]> getFiles(BufferedReader a) throws IOException {
		
		List<String[]> o = null;
		
		if(a == null)
			System.out.println("No tokens were found. Check your file.");
		else {
				while(a.ready()) {
					String currentLine = a.readLine();
					
					StringTokenizer b = new StringTokenizer(currentLine, "\t");
					String[] n = new String[3];
					
					for(int i = 0; i < 3; i++)
						n[i] = b.nextToken();
				}
		}
		return o;
	}
}
