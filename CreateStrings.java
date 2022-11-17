import java.util.Hashtable;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.StringIndexOutOfBoundsException;
import java.lang.*;
import java.util.*;

/**
 * CreateStrings class creates strings of postiive and negative reviews
 * aswell as comparing reviews with positive and negative words.
 * 
 * 
 * @author Nestor Govea Partner: Karim Cisse.
 *  
 */
public class CreateStrings extends ReadInData
{
    private Hashtable<String,String[]>HTStrings = new Hashtable<String,String[]>();
    String[] reviewFileName = new String[500];

/**
 * CreateStrings is a constructor for CreateStrings class.
 * 
 */
    public CreateStrings(){
        
    }
/**
 * CreatePosStrings method creates a HashTable for positive review filenames
 * and string array of review content strings.
 * @param fileNames Takes in ArrayofFileNames.
 * @param directory location of positivereviews
 * @param pos Hashtable containing count and positive words. 
 * @param neg Hashtable containing count and negative words. 
 * @throws FileNotFoundException when the file is not found
 * @return Hashtabke of review filenames and array of strings of reviews. 
 */
    public Hashtable<String,String[]> CreatePosStrings(File[] fileNames, String directory, Hashtable<Integer,String>pos, Hashtable<Integer,String>neg) throws FileNotFoundException
    {
        
		//take a file name
		String currentReview;
		//String[] reviewFileName = new String[500];
		
		StringBuilder temp = new StringBuilder();	
		for(int i = 0; i < fileNames.length; i++)
		{

			currentReview = String.valueOf(fileNames[i]);

			int k = currentReview.length() - 5;
	
			String reviewName = "";
			while(currentReview.charAt(k) != '/')
			{

				reviewName = reviewName + currentReview.charAt(k);
				k--;
			
				if(currentReview.charAt(k) == '/')
				{
					break;
				}
			}
		
			temp.append(reviewName);
			temp.reverse();
			reviewFileName[i] = String.valueOf(temp); // holds review names
			
			
			File file = new File(String.valueOf(fileNames[i]));
			Scanner scnr = new Scanner(file);
			String review = scnr.nextLine(); //review text
			review = review.replaceAll("\\p{Punct}",""); //remove punctations from text
			review = review.toLowerCase();
			String [] reviewArray = review.split("\s"); //creates an array of words from current review

	
			HTStrings.put(reviewFileName[i],reviewArray); // inputing (review name, array of words)
		
			reviewName = "";
			
			temp.setLength(0);
			
		}
			
		//Maybe need to call function below and return that
		//System.out.println(comparePositive(HTStrings, reviewFileName, pos, neg));
		return HTStrings;
    }
/**
* CreateNegStrings method creates a HashTable for negative review filenames
* and string array of review content strings.
* @param fileNames Takes in ArrayofFileNames.
* @param directory location of negative reviews
* @param pos Hashtable containing count and positive words. 
* @param neg Hashtable containing count and negative words. 
* @throws FileNotFoundException when the file is not found
* @return Hashtable of review filenames and array of strings of reviews. 
*/
	public Hashtable<String,String[]> CreateNegStrings(File[] fileNames, String directory, Hashtable<Integer,String>pos, Hashtable<Integer,String>neg) throws FileNotFoundException
  
    {
        
		//take a file name
		String currentReview;
		//String[] reviewFileName = new String[500];
		
		StringBuilder temp = new StringBuilder();	
		for(int i = 0; i < fileNames.length; i++)
		{

			currentReview = String.valueOf(fileNames[i]);

			int k = currentReview.length() - 5;
	
			String reviewName = "";
			while(currentReview.charAt(k) != '/')
			{

				reviewName = reviewName + currentReview.charAt(k);
				k--;
			
				if(currentReview.charAt(k) == '/')
				{
					break;
				}
			}
		
			temp.append(reviewName);
			temp.reverse();
			reviewFileName[i] = String.valueOf(temp); // holds review names
			
			
			File file = new File(String.valueOf(fileNames[i]));
			Scanner scnr = new Scanner(file);
			String review = scnr.nextLine(); //review text
			review = review.replaceAll("\\p{Punct}",""); //remove punctations from text
			review = review.toLowerCase();
			String [] reviewArray = review.split("\s"); //creates an array of words from current review

			
			HTStrings.put(reviewFileName[i],reviewArray); // inputing (review name, array of words)
		
			reviewName = "";
			
			temp.setLength(0);
			
		}
			
		//Maybe need to call function below and return that
		//System.out.println(comparePositive(HTStrings, reviewFileName, pos, neg));
		return HTStrings;
    }
/**
 * Compares positive string arrays reviews with positive and negative words.
 * @param HT hashtable of reviews name and array of strings of their reviews.
 * @param HTP hashtable of positive words.
 * @param HTN hashtable of negative words.
 * @return number positive reviews classified as postiive.
 */
    public int comparePositive(Hashtable<String,String[]> HT, 
	Hashtable<Integer,String>HTP, Hashtable<Integer,String>HTN)
    {
        
		int positiveTotal = 0;
		int negativeTotal = 0;
		for(int i = 0; i < HT.size(); i++)
		{
		
			String fileName = reviewFileName[i];
			String[] tempString = HT.get(fileName);
			int p = 0;
			int n = 0;
			
			for(int j = 0; j < tempString.length; j++)
			{
				String search = tempString[j];
				
				if(HTP.containsValue(search))
				{
					//System.out.println("found pos word");
					p++;
				}
				if(HTN.containsValue(search))
				{
					//System.out.println("found neg word");
					n++;
				}
			}

			if(n > p)
			{
				//System.out.println(n + " " + p +  " more neg than pos");
				System.out.println(reviewFileName[i] + ": positive review, " + "predicted as negative");
				negativeTotal++;
			}
			if (p > n)
			{
				//System.out.println(n + " " + p + " more pos than neg");
				System.out.println(reviewFileName[i] + ": positive review, " + "predicted as positive");
				positiveTotal++;
			}
			if( p == n)
			{
				//System.out.println(n + " " + p + " equal amount of neg and pos");
				System.out.println(reviewFileName[i] + ": positive review, " + "predicted as negative");
				negativeTotal++;
			}
		}
		return 500 - negativeTotal;
    }

/**
 * Compares negative string arrays reviews with positive and negative words.
 * @param HT hashtable of reviews name and array of strings of their reviews.
 * @param HTP hashtable of positive words.
 * @param HTN hashtable of negative words.
 * @return number positive reviews classified as postiive.
 */
	public int compareNegative(Hashtable<String,String[]> HT, 
	Hashtable<Integer,String>HTP, Hashtable<Integer,String>HTN)
    {
		
        //System.out.println("negative review count = " + HT.size());
		int positiveTotal = 0;
		int negativeTotal = 0;
		for(int i = 0; i < HT.size(); i++)
		{
		
			String fileName = reviewFileName[i];
			String[] tempString = HT.get(fileName);
			//System.out.println(tempString.length);
			int p = 0;
			int n = 0;
			//check positive matches or negative matches
			for(int j = 0; j < tempString.length; j++)
			{
				String search = tempString[j];
				
				if(HTP.containsValue(search))
				{
					//System.out.println("found pos word");
					//System.out.println(search + " found in positive word hashtable");
					p++;
				}
				if(HTN.containsValue(search))
				{
					//System.out.println("found neg word");
					//System.out.println(search + " found in negative word hashtable");
					n++;
				}
				else{
					//System.out.print(fileName);
					//System.out.println(" no match found for: " + search);
				}
			}

			if(n > p)
			{
				//System.out.println(n + " " + p +  " more neg than pos");
				System.out.println(reviewFileName[i] + ": negative review, " + "predicted as negative");
				negativeTotal++;
			}
			if (p > n)
			{
				//System.out.println(n + " " + p + " more pos than neg");
				System.out.println(reviewFileName[i] + ": negative review, " + "predicted as positive");
				positiveTotal++;
			}
			if (p == n)
			{
				//System.out.println(n + " " + p + " equal amount of neg and pos");
				System.out.println(reviewFileName[i] + ": negative review, " + "predicted as negative");
				//System.out.println(fileName);
				//System.out.println("equal found: n = " + n + " p= " + p);
				negativeTotal++;
			}
		}

		return 500 - positiveTotal;
    }


}