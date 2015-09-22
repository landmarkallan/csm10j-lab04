// *****************************************************************************
// * Student Name: Mark Land                          Moorpark College         *
// * Program Name: Lab04.java                         CS M10J (Intro Java)     *
// * Comment     : Control Structures Program         Intro to Prog using Java *
// *               ch. 7, Prog Exer #15, pg. 461      Fall of 2015             *
// *               Due on 9-22-15, mean + std dev     Prof. John C. Reynolds   *
// *****************************************************************************

/*
Credit for help with getting this program diagnosed~troubleshooted~fixed, and
finally working:

prof. John C. Reynolds (my Java class teacher)
colleague~student Igor in my C++ class (don't know his last name yet)
*/

/*
Program Purpose:

The purpose of this program is to get input values from a file and calculate
the mean and standard deviation, and displaying the results to the user.
*/

// Packages~Libraries~Modules:

import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;

import static java.lang.Math.pow; // this is for only needing to use ' pow() '
import static java.lang.Math.sqrt; // this is for only needing to use ' sqrt() '

// otherwise, I have to use ' Class_name.Function_name '
// aka for this case: ' Math.pow() ' and ' Math.sqrt() '
// the ' Math ' Class is apart of the ' lang ' package,
// which is auto-imported by the java program already

// Classes:

public class Lab04
{
	// Class-Global Constants ~ Variables ~ Object Instantiation:
	
	static Scanner console = new Scanner(System.in);
	
	static double mean, standard_deviation;
	
	static String file_data;
	
	static final String file_name = "input.txt";
	
	// Methods~Functions:
	
	public static void main(String[] args) throws FileNotFoundException
	{
		Header();
		Crediting_Helpers();
		Purpose();
		Program();
	}
	
	public static void Header()
	{
		// Header Info Displayment:
		
		System.out.println("\n\n****************************************" +
			"****************************************");
		System.out.println("* Student Name: Mark Land                        " +
			"     Moorpark College         *");
		System.out.println("* Program Name: Lab04.java                         "
			+ "   CS M10J (Intro Java)     *");
		System.out.println("* Comment     : Control Structures Program         "
			+ "   Intro to Prog using Java *");
		System.out.println("*               ch. 7, Prog Exer #15, pg. 461      "
			+ "   Fall of 2015             *");
		System.out.println("*               Due on 9-22-15, mean + std dev     "
			+ "   Prof. John C. Reynolds   *");
		System.out.println("****************************************" +
			"****************************************\n\n");
	}
	
	public static void Crediting_Helpers()
	{
		System.out.println("Credit goes to prof. John C. Reynolds (my Java " +
			"class teacher) and a\nfellow student~colleague Igor (don't know " +
			"his last name yet), who've\nhelped me with fixing my errors in my "
			+ "program, and getting it to \nfinally work.\n\n");
		
		System.out.println("----------------------------------------" +
			"----------------------------------------\n\n");
	}
	
	public static void Purpose()
	{
		// Purpose Displayment:
		
		System.out.println("The purpose of this program is to get input values "
			+ "from a file and \ncalculate the mean and standard deviation, and"
			+ " displaying the results \nto the user.\n\n");
	}
	
	public static void Program() throws FileNotFoundException
	{
		// Local Variable Definitions ~ Initializations ~ Object Instantiating:
	
		Scanner read_file = new Scanner(new FileReader("input.txt"));
		
		// just for segmenting these file prompts from rest of program:
		
		System.out.println("----------------------------------------" +
			"----------------------------------------\n\n");
		
		// prompting the user that the 'input.txt' file is opened, and being
		// 		read by the program:
		
		System.out.println("The \'input.txt\' file has been opened, and is " +
			"being read by the program.\n\n");
		
		// Get, Concatenate~Compute, and return new file data values, storing
		//		them into variables for the later-on displayment:
		
		file_data = Concatenate_File_Data(read_file);
		
		// close the 'input.txt' File (due to being at the end of the file):
		
		read_file.close();
		
		if (file_data != null)
		{
			// re-create it, as now it's back to the beginning of file
		
			Scanner read_file_again = new Scanner(new FileReader("input.txt"));
			
			mean = Compute_Mean(read_file_again);
			
			// close the 'input.txt' File (due to being at the end of the file):
		
			read_file_again.close();
			
			// re-create it, as now it's back to the beginning of file
		
			Scanner read_file_again_again =
				new Scanner(new FileReader("input.txt"));
			
			standard_deviation =
				Compute_Standard_Deviation(read_file_again_again, mean);
				
			// close the 'input.txt' File:
		
			read_file_again_again.close();
		}
		
		// prompt the user that the 'input.txt' file has been closed:
		
		System.out.println("The \'input.txt\' file's data has been read and \n"
			+ "stored, and the file has been closed.\n\n");
			
		// no simple way of clearing the console screen with java, so I am just
		//		separating the file prompts from actual stock ticker program:
			
		System.out.println("----------------------------------------" +
			"----------------------------------------\n\n");
		
		// checking+correcting variables for their displaying of the results,
		//		and the displayment of the results:
		
		if (file_data == null)
		{
			System.out.printf("%s contains:%nInput: null (The file has no " +
				"integer data that this program requires)%nMean: null%nStandard"
				+ " Deviation: null%n%n", file_name);
		}
		else if (((mean == 0.0)||(mean >= 0.0))&&(standard_deviation == -1))
		{
			System.out.printf("%s contains:%nInput: %s%nMean: %.2f%nStandard "
				+ "Deviation: (Error: imaginary numbers: square root of " +
				"negative number)%n%n", file_name, file_data, mean,
				standard_deviation);
		}
		else
		{
			System.out.printf("%s contains:%nInput: %s%nMean: %.2f%nStandard "
				+ "Deviation: %.2f%n%n", file_name, file_data, mean,
				standard_deviation);
		}
	}
	
	public static String Concatenate_File_Data(Scanner read_file_parameter)
	{	
		if (!read_file_parameter.hasNextInt())
		{
			return (null);
		}
		else
		{
			String local_string = String.valueOf(read_file_parameter.nextInt());
			
			while (read_file_parameter.hasNextInt())
			{
				local_string +=
					(" " + String.valueOf(read_file_parameter.nextInt()));
			}
			return(local_string);
		}
	}
	
	public static double Compute_Mean(Scanner read_file_parameter)
	{
		int local_sum = 0, local_counter = 0;
		
		while (read_file_parameter.hasNextInt())
		{
			local_sum += read_file_parameter.nextInt();
			++local_counter;
		}
		
		if (local_sum == 0)
		{
			return((double)(local_sum));
		}
		else
		{
			return((double)(local_sum)/(double)(local_counter));
		}
	}
	
	public static double Compute_Standard_Deviation(Scanner read_file_parameter,
		double mean_parameter)
	{
		double local_sum = 0.0;
		int local_counter = 0;
		
		while (read_file_parameter.hasNextInt())
		{
			local_sum += Math.pow((double)(read_file_parameter.nextInt()) -
				mean_parameter, 2.0);
			++local_counter;
		}
		
		if (local_sum > 0.0)
		{
			return(Math.sqrt(local_sum/(double)(local_counter)));
		}
		else if (local_sum == 0.0)
		{
			return (local_sum);
		}
		else
		{
			return (-1);
		}
	}
}
