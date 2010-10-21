/**
 * 
 */
package edu.nps.jody.QsubManager;

import java.io.File;
import java.io.IOException;

import edu.nps.LibLinearManager.LibLinearManager;

import liblinear.InvalidInputDataException;
import liblinear.Model;

/**
 * @author jody
 *
 */
public class QsubLibLinearManager 
{
	//Data Members
	public static final String FILE_DELIM					= System.getProperty("file.separator");
	public static final String TRAIN_DIR_NAME 		= "train";
	public static final String MODEL_DIR_NAME		= "model";
	public static final String PREDICT_DIR_NAME 	= "predict";
	public static final String RESULT_DIR_NAME 	= "result";
	
	//Constructors
	
	
	//Methods
	public static void run(File baseDir, String baseFileName, long bias) throws IOException, InvalidInputDataException
	{
		File trainFile 		= new File(baseDir, TRAIN_DIR_NAME 	+ FILE_DELIM + baseFileName);
		File predictFile 	= new File(baseDir, PREDICT_DIR_NAME	+ FILE_DELIM + baseFileName);
		File resultFile 	= new File(baseDir, RESULT_DIR_NAME 	+ FILE_DELIM + baseFileName);
		
		if (trainFile.exists() && predictFile.exists())
		{
			Model model = LibLinearManager.TrainFile(trainFile, bias);
			LibLinearManager.PredictFile(predictFile, model, resultFile);
		}
		else
		{
			System.out.println("Train and Predict File missing");
		}
	}
	
	public static void main(String[] args) throws IOException, InvalidInputDataException 
	{
		long bias = 1;
		
		String baseFileName = "";
		
		File parentDirectory = new File(System.getProperty("user.dir"));
		
		for (int i = 0; i < args.length; i++)
		{
			if (args[i].equalsIgnoreCase("--parent"))
			{
				parentDirectory = new File(args[i+1]);
				i++;
			}
			else if (args[i].equalsIgnoreCase("--baseFileName"))
			{
				baseFileName = args[i+1];
				i++;
			}
			else if (args[i].equalsIgnoreCase("--bias"))
			{
				bias = Long.parseLong(args[i+1]);
				i++;
			}
		}
		run(parentDirectory, baseFileName, bias);
	}
}
