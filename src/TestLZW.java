import java.io.File;
import java.io.IOException;

public class TestLZW {
	public static void main (String [] args) throws IOException
	{
		File testFile = new File ("lzw-file1.txt");
		LZWCompression compressy= new LZWCompression (testFile);
		
		RohanDecoder decode = new RohanDecoder();
		decode.decode();
		
	}
}
