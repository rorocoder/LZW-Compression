import java.util.*;

import java.io.*;
import java.nio.file.*;

public class RohanDecoder {

	public HashMap<Integer, String> dictionary;
	public StringBuilder binary;
	int binaryIndex;
	ArrayList<Integer> numberValues;

	public RohanDecoder() {
		binary = new StringBuilder();
		dictionary = new HashMap<Integer, String>();
		this.makeDictionary();
	}

	public void makeDictionary() { 
		for(int i = 0; i<256; i++) {
			dictionary.put(i, "" + (char)i);
		}
	}

	public void ByteToBinary() throws IOException {
		FileInputStream input = new FileInputStream("encoded.txt");

		binary = new StringBuilder(); 
		byte[] byteArray = input.readAllBytes();
		//System.out.println(byteArray);

		for(int i =0;i<byteArray.length;i++)
		{
			//System.out.println(byteArray[i]+" "+toBinary(byteArray[i]));

			binary.append(this.toBinary(byteArray[i]));
			//System.out.println(binary);
		}
		input.close();

	}

	public void decode() throws IOException { //algorithm from https://www.geeksforgeeks.org/lzw-lempel-ziv-welch-compression-technique/
		try
		{
			this.ByteToBinary();
			PrintWriter outputWriter = new PrintWriter(new BufferedWriter(new FileWriter("decoded.txt")));

			binaryIndex = 0;

			this.makeNumbersFromBinary(); //fills the variable numberValues with the number representations of each 9 bit binary string
			//System.out.println(this.numberValues);

			Integer old = numberValues.get(0);
			outputWriter.print(dictionary.get(old)); //outputs the value from old

			int nextDictIndex = 256;
			try
			{
				String combination = dictionary.get(old);
				String firstLetter = "" + combination.charAt(0);
			}
			catch(Exception E)
			{
				System.out.println(E);
				System.out.println("Error: Cannot find \"numberValues.get(0)/first value of old\" in the dictionary");
				return;
			}
			String combination = dictionary.get(old);
			String firstLetter = "" + combination.charAt(0);


			for(int i = 1; i< numberValues.size(); i++) {
				int newest = numberValues.get(i);

				if(dictionary.containsKey(newest)) {
					combination = dictionary.get(newest); //if it contains newest then we set the combo to its value
				}
				else {
					combination = dictionary.get(old); //if it doenst contain newest, then we set combo to old + firstLetter
					combination = new StringBuilder(combination).append(firstLetter).toString();
				}

				outputWriter.print(combination);

				firstLetter = "" + combination.charAt(0);
				dictionary.put(nextDictIndex, dictionary.get(old) + combination);
				nextDictIndex+=1;
				old = newest;
			}

			outputWriter.close();
			//		System.out.println(old);
			//		System.out.println(dictionary.get(old));


			//		String Binary2 = binary.substring(9,18);
			//		System.out.println("binary " + oldBinary);
			//		System.out.println("binary " + Binary2);
		}
		catch(Exception E)
		{
			System.out.println(E);
			System.out.println("Error: Decoder did not work for a reason other than the first value not being found in the dictionary");
		}
	}


	public Integer getNextNumberFromBinary() {

		String nextBinary = this.binary.substring(this.binaryIndex, this.binaryIndex+9);
		binaryIndex+=9;
		return Integer.parseInt(nextBinary,2);
	}

	public void makeNumbersFromBinary() {
		this.numberValues = new ArrayList<Integer>();

		for(int i = 0;i <= binary.length() - 9; i+=9) {
			//			System.out.println("next number = " + this.getNextNumberFromBinary());
			//			System.out.println("list =  " + this.numberValues);
			//System.out.println(dictionary.get(this.getNextNumberFromBinary()));
			//numberValues.add(getNextNumberFromBinary());
			//			if(i+9 > binary.length()) {
			//				break;
			//			}

			numberValues.add(binaryToNumber(binary.substring(i,i+9)));
		}
	}

	public static int binaryToNumber(String a) //found online
	{
		int ans = 0;
		for(int i = 0;i<9;i++)
		{
			if(a.charAt(i)=='1')
			{
				ans+=(1<<(8-i));
			}
		}
		return ans;
	}



	public String toBinary(int number)
	{
		String cur =Integer.toBinaryString(number);
		StringBuilder ans = new StringBuilder();
		while(cur.length()+ans.length()<8)
		{
			ans.append("0");
		}
		if(cur.length()>8)
		{
			cur = cur.substring(cur.length()-8);
		}
		ans.append(cur);
		return ans.toString();
	}
}
