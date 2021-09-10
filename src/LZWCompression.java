import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

public class LZWCompression {
//	private String input;
	private String stringOutput;
	private HashMap<String, Integer> dict;
	
	public LZWCompression (File inputFile) throws IOException
	{
		stringOutput = "";
		dict = new HashMap <String, Integer> ();
		
		this.fillInitialValues();
		this.compress (inputFile);
	}
	
	private void fillInitialValues()
	{
		for (int x = 0; x<256; x++)
		{
			char ch = (char)x;
			dict.put(String.valueOf(ch), x);
		}
	}
	
	private void compress (File inputFile) throws IOException
	{
		BufferedReader buffy = new BufferedReader (new FileReader(inputFile));
		
		String current = "" +(char)buffy.read();
		String next = "" + (char)buffy.read();
		int dictValue = 256;
		String binaryString= "";
		for (int x = 0; x<inputFile.length(); x++)
		{
			if (dict.containsKey(current+next))
			{
				current = current+next;
				next = "" + (char)buffy.read();
				 
			}
			else
			{
				int outputAddition = dict.get (current);
//				stringOutput += " " + outputAddition;
				binaryString += toBinary (outputAddition, 9); 

				
				dict.put(current + next, dictValue);

				dictValue++;
				current = next;
				next = "" + (char)buffy.read();
				
			}
		}
		buffy.close();
		
		char [] encodedChars = binaryString.toCharArray ();
				
		byte[] outputInBytes = BinaryCodec.fromAscii (encodedChars);
				
		FileOutputStream fos = new FileOutputStream("trashbin");
		fos.write(outputInBytes);
		fos.close();

				
//		System.out.println (stringOutput);
	}
	
	
	public static String toBinary (int x, int len)
	{
		if (len>0)
		{
			return String.format("%" + len + "s", Integer.toBinaryString(x)).replaceAll(" ", "0");
		}
		return null;
	}
	


	
	
	

}
