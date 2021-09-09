import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

public class Encode {
//	private String input;
	private String stringOutput;
	private HashMap<String, Integer> dict;
	private byte [] outputInByteArray;
	
	public Encode (File inputFile) throws IOException
	{
		stringOutput = "";
		outputInByteArray = null;
		dict = new HashMap <String, Integer> ();
		
		this.fillInitialValues();
		this.fillDict (inputFile);
		this.populateByteArray();
	}
	
	private void fillInitialValues()
	{
		for (int x = 0; x<266; x++)
		{
			char ch = (char)x;
			dict.put(String.valueOf(ch), BinaryConvert.get(x));
		}
	}
	
	private void fillDict (File inputFile) throws IOException
	{
		BufferedReader buffy = new BufferedReader (new FileReader(inputFile));
		
		String current = "" +(char)buffy.read();
		String next = "" + (char)buffy.read();
		int dictValue = 256;
		
		for (int x = 0; x<inputFile.length(); x++)
		{
			if (dict.containsKey(current+next))
			{
				current = current+next;
				next = "" + (char)buffy.read();
			}
			else
			{
				int binary = BinaryConvert.get(dictValue);
				dict.put(current + next, binary);
				stringOutput += " " + binary;
				dictValue++;
				current = next;
				next = "" + (char)buffy.read();
			}
		}
		
		buffy.close();
	}
	
	private void populateByteArray()
	{
		BinaryCodec helper = new BinaryCodec();
		outputInByteArray = helper.toByteArray(stringOutput);
	}
	
	public File getBinaryOutput() throws IOException
	{
		File output = new File ("output.bin");
		FileOutputStream fos = new FileOutputStream(output);
		fos.write(outputInByteArray);
		fos.close();
		
		return output;
	}

	
		

	
	
	

}
