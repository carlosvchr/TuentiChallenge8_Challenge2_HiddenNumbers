package tc.hiddennumberschallenge.mainpk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class HiddenNumbers {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		// Gets the input file path from the user.
		System.out.print("Input file path: ");
		String ipath = s.nextLine();
		
		// Gets the input file path from the user.
		System.out.print("Output file path (e.g. /home/user/output.txt): ");		
		String opath = s.nextLine();
		
		// Closes the scanner stream
		s.close();
		
		// Gets the reader and writer
		BufferedReader br = IOManager.getReader(ipath);
		PrintWriter pw = IOManager.getWriter(opath);

		// Check writer and reader has been created successfully. Else, ends the program safely.
		if(br == null || pw == null) {
			System.out.println("Error when reader or writer has been created.");
			return;
		}
		
		// Calculates all cases and generate the output
		if(processCases(br, pw)) {
			System.out.println("Results has been generated successfully!");
		}else {
			System.out.println("Terminated with errors.");
		}
		
		// Closes input and output streams
		IOManager.closeStreams(br, pw);
		
	}
	
	
	/** Process all cases and write the output */
	public static boolean processCases(BufferedReader br, PrintWriter pw) {
		try {
			int ncases = Integer.parseInt(br.readLine());
			for(int i=0; i<ncases; i++) {
				// Reading new case
				String line = br.readLine();
				
				// Gets the minimum and maximum values and calculates the difference
				BigInteger sol = getMax(line).subtract(getMin(line));
				
				// Print the case in th output file
				pw.println("Case #"+(i+1)+": "+sol);	
			}
		}catch(NumberFormatException nfe) {
			// Error when parsing number of cases
			System.out.println("File hasn't got a right format.");
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/** Gets the smallest number in decimal base */
	public static BigInteger getMin(String s) {
		/* As the smallest input must be at least length 2 and it
		 * must not start with 0 val, we can predefine the starting value*/
		String num = "10";
		for(int i=2; i<s.length(); i++) {
			if(i <= 9) {
				num += i;
			}else {
				// From 10, we must add the character (ASCII code)
				num += (char)(i+87);
			}
		}

		return new BigInteger(num, s.length());
	}
	
	/** Gets the biggest number in decimal base */
	public static BigInteger getMax(String s) {
		String num = "";
		for(int i=s.length()-1; i>=0; i--) {
			if(i <= 9) {
				num += i;
			}else {
				// From 10, we must add the character (ASCII code)
				num += (char)(i+87);
			}
		}
		
		return new BigInteger(num, s.length());
	}
	
	
}
