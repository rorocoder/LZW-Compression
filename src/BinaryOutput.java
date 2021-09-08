
public class BinaryOutput {
	private int decimal;
	private int binary;
	
	
	public BinaryOutput(int decimal)
	{
		binary = 0;
		this.decimal = decimal;
		while (decimal != 0)
		{
			int remainder = decimal%2;
			binary = binary* 10 + remainder;
			decimal = decimal/2;
		}
	}

	
}
