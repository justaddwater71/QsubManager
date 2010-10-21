/**
 * 
 */
package edu.nps.jody.QsubManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.nps.jody.MergeAndAnalyze.Analyzer;
import edu.nps.jody.MergeAndAnalyze.Merger;

/**
 * @author jody
 *
 */
public class QsubMergeAndAnalyze 
{
	//Data Members
	public final static String MERGE_DIR_NAME = "merge";
	public final static String FILE_DELIM = System.getProperty("file.separator");
	
	//Constructors
	
	
	//Methods
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException 
	{
		String baseFileName	= "000_004";
		int startExt = 0;
		int endExt = 4;
		File inFileDirectory 			= new File( System.getProperty("user.dir"));
		File outFileDirectory 		= new File( System.getProperty("user.dir"));
		File mergeFileDirectory	= new File(inFileDirectory.getParentFile(), MERGE_DIR_NAME);
		File mergeFile					= new File(mergeFileDirectory, baseFileName);
		long size = 0;
		
		for (int i = 0; i < args.length; i++)
		{
			if (args[i].equalsIgnoreCase("--baseFileName"))
			{
				baseFileName = args[i+1];
				mergeFile = new File(mergeFileDirectory, baseFileName);
				i++;
			}
			else if (args[i].equalsIgnoreCase("--startExt"))
			{
				startExt = Integer.parseInt(args[i+1]);
				i++;
			}
			else if (args[i].equalsIgnoreCase("--endExt"))
			{
				endExt = Integer.parseInt(args[i+1]);
				i++;
			}
			else if (args[i].equalsIgnoreCase("--parent"))
			{
				inFileDirectory = new File(args[i+1] + FILE_DELIM + QsubLibLinearManager.PREDICT_DIR_NAME);
				outFileDirectory = new File(args[i+1] + FILE_DELIM + QsubLibLinearManager.RESULT_DIR_NAME);
				mergeFileDirectory = new File(args[i+1] + FILE_DELIM + QsubLibLinearManager.RESULT_DIR_NAME);
				mergeFile = new File(mergeFileDirectory, baseFileName);
			}
			else if (args[i].equalsIgnoreCase("--inFileDirectory"))
			{
				inFileDirectory = new File(args[i+1]);
				i++;
			}
			else if (args[i].equalsIgnoreCase("--outFileDirectory"))
			{
				outFileDirectory = new File(args[i+1]);
				i++;
			}
			else if (args[i].equalsIgnoreCase("--mergeFileDirectory"))
			{
				mergeFileDirectory = new File(args[i+1]);
				mergeFile = new File(mergeFileDirectory, baseFileName);
			}
			else if (args[i].equalsIgnoreCase("--mergeFile"))
			{
				mergeFile = new File(args[i+1]);
			}
		}
		
		Merger.mergeInAndOut(baseFileName, startExt, endExt, inFileDirectory, outFileDirectory, mergeFile);
		size = Merger.sumFileSizes(baseFileName, startExt, endExt, inFileDirectory);
		Analyzer.analyzeLists(mergeFile, size);
	}

}
