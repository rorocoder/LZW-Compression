import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Hashtable;

public class Encode {
//	private String input;
	private String output;
	private HashMap<String, String> dict;
	
	public Encode (File inputFile) throws FileNotFoundException
	{
		
		dict = new HashMap <String, String> ();
		
		for (int x = 0; x<266; x++)
		{
			char ch = (char)x;
			dict.put(String.valueOf(x), String.valueOf(ch));
		}
		
		BufferedReader buffy = new BufferedReader (new FileReader(inputFile));
	}

	
		

	
	
	

}
