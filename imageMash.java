import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

public class imageMash {

	public static void main(String[] args) {

		// Check to see if there's actually a list file.
		if(args[0] == null) {
			System.out.println("You must specify the input file.");
			System.exit(0);
		}
		
		// Where we're storing the desired combinations
		List<String[]> combos;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(args[0]));
		}
		
		// Try loading the combos file
		if(reader == null)
			System.out.println("No tokens were found. Check your file.");
		else {
				while(reader.ready()) {
					String currentLine = reader.readLine();
					
					StringTokenizer b = new StringTokenizer(currentLine, "\t");
					String[] n = new String[3];
					
					for(int i = 0; i < 3; i++)
						n[i] = b.nextToken();
					combos.add(n);
				}
		}
		
		File in = new File(args[0]);
		String path = in.getPath();
		
		// Get ready to crunch through all of the combinations
		ListIterator<String[]> place = combos.listIterator();
		
		while(place.hasNext()) {
			String[] current = place.next();
			
			BufferedImage out = null;
			
			BufferedImage face = null;
			try {
				face = ImageIO.read(new File(path + current[1] + ".jpg"));
			}
			catch(IOException e) {
				// EXCEPTION HANDLING
				e.printStackTrace()
			}
			
			BufferedImage house = null;
			try {
				house = ImageIO.read(new File(path + current[2] + ".jpg"));
			}
			catch(IOException e) {
				// EXCEPTION HANDLING
				e.printStackTrace()
			}
			
			out = doMash(face, house);
			
			try {
				ImageIO.write(out, "JPEG", new File(path + current[0] + ".jpg"));
			}
			catch(IOException e) {
				// EXCEPTION HANDLING
				e.printStackTrace()
			}
			catch(IllegalArgumentException e) {
				// EXCEPTION HANDING
				e.printStackTrace()
			}
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
}
