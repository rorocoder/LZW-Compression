import java.io.File;
import java.io.IOException;

public class Test {
	public static void main (String [] args) throws IOException
	{
		File testFile = new File ("lzw-file1.txt");
		Encode compressy= new Encode (testFile);
		
	}
}
