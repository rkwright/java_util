import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Text2JSON {

	public static boolean text2JSON ( String fileName ) {
		File 			fiche;	
		BufferedReader 	reader = null;
		BufferedWriter 	writer = null;
		File			newFiche;
		String			curLine;
		String 			delims = "[ ]+";
		String[] 		tokens;

		try
		{
			fiche = new File(fileName + ".txt");	
			reader = new BufferedReader(new FileReader(fiche.getPath()));
			newFiche = new File(fileName + ".js");
			writer = new BufferedWriter(new FileWriter(newFiche.getPath()));
			
			// the first line is the number of vertices, written as Vertices: 468
			// we don't care about saving this so just throw it away
			if ((curLine = reader.readLine()) != null) {
				//tokens = curLine.split(":");
				//curLine = tokens[1];
			}
		
			int 	last = fileName.lastIndexOf('/');
			String	varName = fileName.substring(last + 1);
			curLine = "var " + varName + " =\n[\n";
			writer.write(curLine);

			int nL = 0;
			while ((curLine = reader.readLine()) != null) 
			{
				// read the three values
				tokens = curLine.split(delims);
				
				if (nL++ != 0) {
					curLine = String.format(",\n");
					writer.write(curLine);
				}
				
				curLine = String.format("    [%s, %s, %s]", tokens[0], tokens[1], tokens[2]);
				writer.write(curLine);
		
				// System.out.println(curLine);			
			}

			curLine = "\n];\n";
			writer.write(curLine);

			reader.close();
			writer.close();		
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return true;
	}
	public static void main(String[] args) {
		if (args.length < 1)
		{
			System.err.println("No filename supplied??");
		}
		else
		{
			text2JSON( args[0] );
		}
	}

}
