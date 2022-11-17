import java.util.Hashtable;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.StringIndexOutOfBoundsException;
import java.lang.*;
import java.util.*;

/**
 *  Reads in the data from positive and negative words as well as 
 *  negative and postive reviews.
 *  @author Nestor Govea
 *
 */
public class ReadInData 
{
    private Hashtable<Integer,String>HTP = new Hashtable<Integer,String>();
    private Hashtable<Integer,String>HTN = new Hashtable<Integer,String>();
    private Hashtable<String,String[]>HTStringsP = new Hashtable<String,String[]>();
    private File dir;
    private File dir2;
    private File[] posFileNames;
    private File[] negFileNames;
    private String posReviews;
    private String negReviews;
    private int classifiedNum;
    //private CreateStrings posStrings = new CreateStrings();

    /**
     * Constructor for ReadInData 
     * 
     */
    public ReadInData()
    {
        
    }
    
    /**
     * Reads in positive words and creates a Hashtable of positive words.
     * @param posFileName file path to positive files 
     * @throws FileNotFoundException Will throw FileNotFoundException if file
     * Not found.
     */
    public void ReadInPositiveWords(String posFileName) throws FileNotFoundException
    {
        File posWordsFile = new File(posFileName);
		Scanner scnr = new Scanner(posWordsFile); 
		String word = scnr.nextLine();
		int posCount = 0;
		while(scnr.hasNext()){
			
			if(!word.startsWith(";") && !word.isEmpty())
			{
				HTP.put(posCount,word); //maybe change this to init with 0 count, and add 1 if found in a reveiew when comparing
				posCount++;
			}
			word = scnr.nextLine();
		}
        HTN.put(posCount,word);
    }

    /**
     * Reads in negative words and creates a hashtable of negative words.
     * @param negFileName A string of location of negative words file.
     * @throws FileNotFoundException Throws if File not Found.
     */
    public void ReadInNegativeWords(String negFileName) throws FileNotFoundException
    {
        File negWordsFile = new File(negFileName);
		Scanner scnr2 = new Scanner(negWordsFile); //scnr now reads in negative words
		String word2 = scnr2.nextLine();
		int negCount = 0;
		while(scnr2.hasNext())
		{
			if(!word2.startsWith(";") && !word2.isEmpty())
			{   //if(word2.charAt(0) != ' '){
				HTN.put(negCount,word2); //maybe change this to init with 0 count, and add 1 if found in a reveiew when comparing
				negCount++;
                //}
			}
			word2 = scnr2.nextLine();
		}
        HTN.put(negCount,word2);
        //HTN.remove(0);
        //System.out.println(HTN);
    }

    /**
     * Reads in file names for positive reviews and creates an array of Files. 
     * @param posReviews A string of positive review location
     * @throws FileNotFoundException Throws if File not Found.
     */
    public void ReadInPosReviews(String posReviews) throws FileNotFoundException
    {
        this.posReviews = posReviews;
        dir = new File(posReviews);
		posFileNames = dir.listFiles();

    }

    /**
     * Reads in file names for negative reviews and creates an array of Files.
     * @param negReviews A string of negative review location
     * @throws FileNotFoundException Throws if File not Found.
     */
    public void ReadInNegReviews(String negReviews) throws FileNotFoundException
    {
        this.negReviews = negReviews;
        dir2 = new File(negReviews);
		negFileNames = dir2.listFiles();
    }

    /**
     * Creates Hashtables of positive review
     * names and array of strings for positive reviews.
     * @throws FileNotFoundException Throws if File not Found.
     */
    public void createPosStrings()throws FileNotFoundException{
        CreateStrings posStrings = new CreateStrings();
        HTStringsP = posStrings.CreatePosStrings(posFileNames, posReviews, HTP, HTN);
        //posStrings.printPStrings();
        //System.out.println(posStrings.comparePositive(HTStringsP, HTP, HTN));
        posStrings.comparePositive(HTStringsP, HTP, HTN);
        this.classifiedNum = posStrings.comparePositive(HTStringsP, HTP, HTN);
    
    }

    /**
     * Creates Hashtables of negative review 
     * names and array of strings for negative reviews.
     * @throws FileNotFoundException Throws if File not Found.
     */
    public void createNegStrings()throws FileNotFoundException{
        CreateStrings negStrings = new CreateStrings();
        HTStringsP = negStrings.CreateNegStrings(negFileNames, negReviews, HTP, HTN);
        //posStrings.printPStrings();
        //System.out.println(posStrings.compareNegative(HTStringsP, HTP, HTN));
        this.classifiedNum = negStrings.compareNegative(HTStringsP, HTP, HTN);
    }
    /**
     * Getter for correctly classified reviews.
     * @return number of correctly classified reviews.
     */
    public int numClassified(){
        
        return classifiedNum;
    }

    
    /* 
    public void PrintPosWords() 
    {
        System.out.println(HTP);
    }

    public void PrintPosRevStrings() 
    {
        System.out.println(HTStringsP);
    }

    public void PrintnegWords() 
    {
        System.out.println(HTN);
    }
    */


}