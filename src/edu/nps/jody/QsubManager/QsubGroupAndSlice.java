/**
 * 
 */
package edu.nps.jody.QsubManager;

import java.io.IOException;

import edu.nps.jody.GroupAndSlice.GroupAndSlice;
import edu.nps.jody.GroupAndSlice.GroupTypes;

/**
 * @author jody
 *
 */
public class QsubGroupAndSlice 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException 
	{
		String sourceDirectory	= System.getProperty("user.dir");
		GroupTypes groupType	= GroupTypes.valueOf("GROUP_SMALL_TO_LARGE");
		int[] groupSizes 					= {5, 10, 25, 50, 75, 150};
		int titleDigits 					= 3;
		int crossValNumber 		= 5;
		
		//Initialize the count for groupSizes
		int						groupSizesCount	= 0;
		
		for (int i=0; i < args.length; i++)
		{
			if (args[i].equalsIgnoreCase("--sourceDirectory"))
			{
				sourceDirectory = args[i + 1];
				i++;
			}
			else if(args[i].equalsIgnoreCase("--groupType"))
			{
				groupType = GroupTypes.valueOf(args[i + 1]);
				i++;
			}
			else if(args[i].equalsIgnoreCase("--groupSizes"))
			{
				i++;
				int j = i;
				while ((j < args.length)&& ( !args[j].contains("--")))
				{
					groupSizesCount++;
					j++;
				}
				
				groupSizes = new int[groupSizesCount];
				
				for (int k = 0; k < groupSizesCount; k++)
				{
					groupSizes[k] = Integer.parseInt(args[i]);
					i++;
				}
			}
			else if(args[i].equalsIgnoreCase("--titleDigits"))
			{
				titleDigits = Integer.parseInt(args[i + 1]);
				i++;
			}
			else if(args[i].equalsIgnoreCase("--crossVal"))
			{
				crossValNumber = Integer.parseInt(args[i + 1]);
				i++;
			}
		}
		
		for (int i=0; i < groupSizes.length;i++)
		{
			GroupAndSlice.groupAndSlicePrep(sourceDirectory, groupType, groupSizes[i], titleDigits, crossValNumber);
		}
	}
}

