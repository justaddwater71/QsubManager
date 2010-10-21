/**
 * 
 */
package edu.nps.jody.QsubManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import edu.nps.jody.GroupAndSlice.GroupAndSlice;

/**
 * @author jody
 *
 */
public class QsubManager 
{
	//Data Members
	public final static String FILE_DELIM						= System.getProperty("file.separator");
	public final static String FILENAME_SPACER 		= "_";
	public final static String PREDICT_DIR_NAME 		= "predict";
	public final static String TRAIN_DIR_NAME			= "train";
	public final static String RESULT_DIR_NAME 		= "result";
	public final static String SCRIPT_DIR_NAME 		= "script";
	public final static String COMMAND_DIR_NAME 	= "java";
	
	//Constructor
	
	
	//Methods
	public static String fileNameArray(String textDirectoryName, int groupSize, int zeroes)
	{
		//String scriptArray="\"";
		String scriptArray="";
		
		File textDirectory = new File (textDirectoryName);
		
		File[] fileList = textDirectory.listFiles();
		
		String outputFileName;
		String baseFileName;
		int count = 0;
		
		for (int i = 0; i < fileList.length; i++)
		{
			if (i != 0 && i%groupSize == 0)
			{
				baseFileName = GroupAndSlice.intToStringWithLeadingZeroes(i - groupSize, zeroes);
				
				outputFileName = baseFileName +	"_" + GroupAndSlice.intToStringWithLeadingZeroes(i  - 1, zeroes);
				
				scriptArray = scriptArray +outputFileName + " ";
			}
			
			count = i +1;
		}
		
		//Catch leftover files
		if (count > 0)
		{
			baseFileName = GroupAndSlice.intToStringWithLeadingZeroes(count - groupSize, zeroes);
			
			outputFileName = baseFileName +	"_" + GroupAndSlice.intToStringWithLeadingZeroes(count  - 1, zeroes);
			
			scriptArray = scriptArray +outputFileName;
		}
		
		//scriptArray = scriptArray + "\"";
		
		return scriptArray;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String textDirectoryName = args[0];
		
		int groupSize = Integer.parseInt(args[1]);
		
		int zeroes = Integer.parseInt(args[2]);
		
		System.out.println(fileNameArray(textDirectoryName, groupSize, zeroes));

	}

}
