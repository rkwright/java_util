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
		     
		    List<StormData> myObjects = objectMapper.readValue(jsonData , new TypeReference<List<StormData>>(){});
			System.out.println(myObjects);
			 
		}
		catch (IOException ioE) {
			ioE.printStackTrace();
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		
		String hurrFile = "/Users/rkwright/dev/Sandbox_Util/workspace/HurrClean/hurrdat-short.json";
		hurrClean( hurrFile );
	}

}
