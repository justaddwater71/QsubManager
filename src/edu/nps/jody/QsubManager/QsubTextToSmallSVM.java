/**
 * 
 */
package edu.nps.jody.QsubManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.nps.jody.CorpusSVMTester.FeatureTypes;
import edu.nps.jody.CorpusSVMTester.SVMToSmallSVM;
import edu.nps.jody.CorpusSVMTester.TextToSVM;

/**
 * @author jody
 *
 */
public class QsubTextToSmallSVM {

	public static void textToSmallSVM(File corpusDirectory, int maxGap, FeatureTypes featureType, int modelNumber) throws FileNotFoundException, IOException
	{
		//FIXME get all the dir_name constants coming out of one file to eliminate matching issues after the basic process is proven out
		//Feature type is not its own directory because the model must match the feature, so this is a human being task to match model to feature creation
		TextToSVM textToSVM 		= new TextToSVM();
		
		File textDirectory 				= new File(corpusDirectory, TextToSVM.TEXT_DIR_NAME);
		
		File featureDirectory		= new File(corpusDirectory, featureType.toString());
		
		File modelDirectory 			= new File(featureDirectory, Integer.toString(modelNumber));
		
		File largeSVMDirectory 	= new File(modelDirectory, TextToSVM.SVM_DIR_NAME);
		
		File cmphDirectory 			= new File(modelDirectory, TextToSVM.CMPH_DIR_NAME);
		
		File keyFile							= new File(cmphDirectory, TextToSVM.KEY_FILE_NAME);
		
		File signatureFile				= new File(cmphDirectory, TextToSVM.SIGNATURE_FILE_NAME);
		
		textToSVM.processFiles(textDirectory, maxGap, featureType, keyFile.getAbsolutePath(), signatureFile.getAbsolutePath(), largeSVMDirectory);
		
		SVMToSmallSVM svmToSmallSVM = new SVMToSmallSVM();
		
		svmToSmallSVM.processLargeSVMDirectory(largeSVMDirectory);
	}
	
	/**
	 * --parent
	 * --gap
	 *  --featuretype
	 * --groupsize
	 * --titledigits
	 * --slices number of slices in cross validation
	 * 
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException 
	{
		//If the program is given no parameters or incomplete parameters, these parameters are used
		//user.dir is current working direct -- where the this command was issued
		File 						corpusDirectory	= new File(System.getProperty("user.dir"));
		int 						maxGap					= 3;
		FeatureTypes 	featureType			= FeatureTypes.ORTHOGONAL_SPARSE_BIGRAM;
		int 						modelNumber		=0;
		
		for (int i = 0; i < args.length; i++)
		{
			if(args[i].equalsIgnoreCase("--parent"))
			{
				corpusDirectory = new File(args[i + 1]);
				i++;
			}
			else if(args[i].equalsIgnoreCase("--gap"))
			{
				maxGap = Integer.parseInt(args[i + 1]);
				i++;
			}
			else if(args[i].equalsIgnoreCase("--featuretype"))
			{
				featureType = FeatureTypes.valueOf(args[i + 1]);
				i++;
			}
			else if(args[i].equalsIgnoreCase("--modelNumber"))
			{
				modelNumber = Integer.parseInt(args[i + 1]);
				i++;
			}
		}
		
		textToSmallSVM(corpusDirectory, maxGap, featureType, modelNumber);
	}

}
