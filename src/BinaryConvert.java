
public class BinaryConvert {
	
	public static int get(int decimal)
	{
		
		int binary = 0;
		double counter = .1;
		while (decimal != 0)
		{
			int remainder = decimal%2;
			binary = binary + (int)(counter* 10* remainder);
			decimal = decimal/2;
			counter = counter*10;
		}
		return binary;
	}
	


	
}
 
