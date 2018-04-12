import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StormData {
		
	@JsonCreator
	public StormData ( 
			@JsonProperty("atcID") String atcID, 
			@JsonProperty("name") String name,
			@JsonProperty("numTracks") String numTracks,
			@JsonProperty("entries") String[][] entries) {
		
		this.atcID = atcID;
		this.name = name;
		this.numTracks = numTracks;
		this.entries = entries;
	};

	public String getAtcID()	{
		return atcID;
	}

	public void setAtcID( String atcID )	{
		this.atcID = atcID;
	}

	public String getName()	{
		return this.name;
	}

	public void setName( String name )	{
		this.name = name;
	}

	public String NumTracks()	{
		return this.numTracks;
	}

	public void setNumTracks( String numTracks )	{
		this.numTracks = numTracks;
	}

	public String[][] Entries()	{
		return this.entries;
	}

	public void setNumEntries( String[][] entries )	{
		this.entries = entries;
	}

	String		atcID;
	String 		name;
	String		numTracks;
	String[][] 	entries;
}
