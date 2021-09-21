import java.io.File;
import java.io.IOException;

public class TestLZW {
	public static void main (String [] args) throws IOException
	{
		
		long startTime = System.nanoTime();
		File testFile = new File ("lzw-file3.txt");
		LZWCompression compressy= new LZWCompression (testFile);
		
		
		RohanDecoder decode = new RohanDecoder();
		decode.decode();
		long endTime = System.nanoTime();
		double diff =(endTime-startTime)/1e6;
		System.out.println("Program took "+diff+" milliseconds");
		
	}
}
