
public class BinaryOutput {
	private int decimal;
	private int binary;
	
	
	public BinaryOutput(int decimal)
	{
		binary = 0;
		this.decimal = decimal;
		double counter = .1;
		while (decimal != 0)
		{
			int remainder = decimal%2;
			binary = binary + (int)(counter* 10* remainder);
			decimal = decimal/2;
			counter = counter*10;
		}
	}
	
	public int getBinary ()
	{
		return binary;
	}

	
}
