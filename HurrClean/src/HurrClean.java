/*
 * HurrClean
 * 
 * Parses through the HurrDataJSON file of hurricane data and removes
 * older storms with no pressure data and storms with many missing data items.
 * For those storms that meet the minimal criteria, missing values are 
 * interpolated using a cubic spline.
 *
 * @author rkwright / www.geofx.com
 *
 * Copyright 2018, All rights reserved.
 *
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class HurrClean
{

	public static boolean hurrClean ( String fileName ) {

		try	{
			
			// read json file data to String
			byte[] jsonData = Files.readAllBytes(Paths.get(fileName));

			// create ObjectMapper instance
			ObjectMapper objectMapper = new ObjectMapper();
		     
		    List<StormData> storms = objectMapper.readValue(jsonData , new TypeReference<List<StormData>>(){});
			System.out.println("Length: " + storms.size());
			
			for ( int i=0; i<storms.size(); i++ ) {
				StormData storm = storms.get(i);
				System.out.println("name: " + storm.getName() + "  numTracks: " + storm.NumTracks());
				
				int k = 0;
				for ( int j=0; j<storm.entries.length; j++ ) {
					String[] entry = storm.Entries()[j];
					System.out.println(j + " date: " + entry[0] + entry[1] + entry[2]);
					
					if (Arrays.asList(entry).contains("-999.000000")) {
						
						k++;
	                }
				}
				
                double pc = ((double)k/storm.entries.length * 100);
                if (pc < 50)
                    System.out.printf( "id: %s name: %s, %.2f %% missing\n",storm.atcID, storm.name, pc);
                else
                    System.out.println( "------- BAD ------ " +  storm.entries[i][0] + " " + storm.atcID + " " + storm.name + " " + pc + "% missing");

			}
			 
		}
		catch (IOException ioE) {
			ioE.printStackTrace();
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		
		String hurrFile = "/Users/rkwright/dev/Sandbox_Java/HurrClean/hurrdata-miss.json";
		hurrClean( hurrFile );
	}

}
