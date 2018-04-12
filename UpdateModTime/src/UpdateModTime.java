import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UpdateModTime
{
	static String 	dcterms_modified =  "dcterms:modified";
	static Pattern	pattern = Pattern.compile(dcterms_modified);
	
	public static void main(String[] args)
	{
		if (args.length < 1)
		{
			System.err.println("No filename supplied??");
		}
		else
		{
			File opfFile = new File(args[0]);	
			updateTime(opfFile);
		}
	}

	public static void updateTime(File fiche)
	{	
		BufferedReader 	reader = null;
		BufferedWriter 	writer = null;
		File			newFiche;
		String			curLine;
		
		try
		{
			reader = new BufferedReader(new FileReader(fiche.getPath()));
			newFiche = new File(fiche.getPath()+".tmp");
			writer = new BufferedWriter(new FileWriter(newFiche.getPath()));
			
			while ((curLine = reader.readLine()) != null) 
			{
			    Matcher matcher = pattern.matcher(curLine);

				if (matcher.find())
				{
					// find the timestamp and replace it
					Date	date = new Date();
					String	front = curLine.substring(0, curLine.indexOf(">") +1 );
					SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat timeFmt = new SimpleDateFormat("HH:mm:ss");
					curLine = front + dateFmt.format(date) + "T" + timeFmt.format(date) + "Z</meta>";
				}
				
				writer.write(curLine);
				writer.newLine();
						
				System.out.println(curLine);			
			}

			reader.close();
			writer.close();
			
			fiche.delete();
			newFiche.renameTo(fiche);			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
