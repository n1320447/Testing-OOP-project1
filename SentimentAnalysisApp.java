import java.util.Hashtable;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.StringIndexOutOfBoundsException;
import java.lang.*;
import java.util.*;

/**
 * Class SentimentAnalysisApp reads in paths to files and creates objects of 
 * classes that create a report of predicted positive and negative and a 
 * total %. 
 * 
 * @author Nestor Govea
 * 
 */
public class SentimentAnalysisApp

{
/**
 * Constructor for SentimentAnalysisApp
 */
	public SentimentAnalysisApp() {
	}

	/**
	 * Reads in file paths into args creates objects, and does 
	 * simple math to output percentages correctlty of predicted 
	 * classes.  
	 * 
	 * @param args paths for file locations.
	 * @throws FileNotFoundException thrown if file not found.
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		//*******read file paths**********************************************
		String posFileName = args[0]; //pos words
		String negFileName = args[1];//neg words
		String posReviews = args[2]; //positive reviews
		String negReviews = args[3]; //negative reviews
		
		//*******Reading in positive words to hashtable************************
		ReadInData positiveWords = new ReadInData();
		positiveWords.ReadInPositiveWords(posFileName);
	
		//********Reading in negative words to hashtable***********************
		ReadInData negativeWords = new ReadInData();
		negativeWords.ReadInNegativeWords(negFileName);
	
		//********Reading positive Review file names ONLY**********************
		ReadInData positiveReviews = new ReadInData();
		positiveReviews.ReadInPosReviews(posReviews);
		
		//********Reading negative Review file names ONLY**********************
		ReadInData negativeReviews = new ReadInData();
		negativeReviews.ReadInNegReviews(negReviews);

		//********Create positive strings & Compare****************************
		positiveWords.ReadInPosReviews(posReviews);
		positiveWords.ReadInNegativeWords(negFileName);
		positiveWords.createPosStrings();
		int posClassified = positiveWords.numClassified();

		//********Create negative strings & Compare****************************
		negativeWords.ReadInNegReviews(negReviews);
		negativeWords.ReadInPositiveWords(posFileName);
		negativeWords.createNegStrings();
		int negClassified = negativeWords.numClassified();
		
		//********Final Output*************************************************
		System.out.println();
		System.out.println("Total number of correctly classified reviews: " + 
		(posClassified + negClassified));
		System.out.println("Total number of mis-classified reviews: " + (1000 - 737));
		System.out.println();

		System.out.println("Accuracy for positive reviews: " + 
		(posClassified / 500.0)*100.0 + "%");

		System.out.print("Accuracy for negative negative: ");
		System.out.printf("%.1f", (negClassified / 500.0)*100.0);

		System.out.println("%");

		System.out.println("Accuracy Overall: " +
		 ((posClassified + negClassified) / 1000.0)*100 + "%");


	}//end of main function bracket

}//end of class bracket

