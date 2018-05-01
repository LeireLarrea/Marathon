import java.util.*;
import java.io.*;
import ou.*;

/**
 * A class that reads a list of runners from runners.txt and adds them to an ArrayList.
 * it also runs a virtual/random marathon, sorts the receiver's runners list 
 * and categorises them.
 * 
 * @author (Leire Larrea Casado - OU Student E1662175 - M250 Ð TMA03 - Question 3) 
 * @version (29th April 2017)
 * 
 */
public class MarathonAdmin
{
   // instance variables
   private List <Runner> runners;
   private SortedMap <String, Integer> juniorResults;
   private SortedMap <String, Integer> standardResults;
   private SortedMap <String, Integer> seniorResults;

   /**
    * Constructor for objects of class MarathonAdmin
    */
   public MarathonAdmin()
   {
      runners = new ArrayList<Runner>();
      juniorResults = new TreeMap <String, Integer>();
      standardResults = new TreeMap <String, Integer>();
      seniorResults = new TreeMap <String, Integer>();
   }

   /**
    * Prompts the user for a pathname and then attemps to open a stream
    * on the specified file. The method expects a file containing the details
    * of Runner objects (name and age) in CSV format.
    * An arrayList must be created (using MaratonAdmin constructor)
    * prior to running this method
    * so as the Runner objects created can be stored in it.
    * 
    */
   public void readInRunners()
   {
      String pathname = OUFileChooser.getFilename();
      File runnersFile = new File(pathname);
      Scanner bufferedScanner = null;   
      
      try
      {
         String runnerName;
         int runnerAge;
         String runnerAgeGroup = " ";
         String currentLine;
         Scanner lineScanner;
         Runner aRunner = new Runner ();
         int runnerNumber = 0;
         bufferedScanner = new Scanner(new BufferedReader (new FileReader (runnersFile)));
         
         while(bufferedScanner.hasNextLine())
         {
            currentLine = bufferedScanner.nextLine();
            lineScanner = new Scanner(currentLine);
            lineScanner.useDelimiter(",");
            
            runnerName = lineScanner.next();
            runnerAge = lineScanner.nextInt();
            
            if(runnerAge < 18)
            {
               runnerAgeGroup = "Junior";
            }
            if(runnerAge >= 55)
            {
               runnerAgeGroup = "Senior";
            }
            if(18 < runnerAge  && runnerAge < 55)
            {
               runnerAgeGroup = "Standard";
            }
            
            aRunner.setAgeGroup(runnerAgeGroup);
            runners.add(new Runner());
            runners.get(runnerNumber).setName(runnerName);
            runners.get(runnerNumber).setAgeGroup(runnerAgeGroup);
            runnerNumber ++;
         }
         
      }
       catch (Exception anException)
      {
         System.out.println("Error: " + anException);
      }
      finally
      { 
         try
         {
            bufferedScanner.close();
         }
         catch (Exception anException)
         {
            System.out.println("Error: " + anException);
         }
      }
   }
   
   /**
    * Iterates over runners and for each runner generates 
    * a random number between 90 and 180 (inclusive)
    * which is used to set the time (in minutes) for that runner.
    * 
    */
   public void runMarathon()
   {
      int runnerTime;
      for(Runner eachRunner: runners)
      {
         Random generator = new Random();
		   runnerTime = generator.nextInt(90)+ 91; 
         eachRunner.setTime(runnerTime);
      }
   }
   
   /**
    * Sorts the runners List by the instance variable time of each Runner object
    * 
    */
   public void sortRunnerList()
   {
      Collections.sort(runners);
   }
   
    /**
    * Categorises runners into juniorResults, standardResults and seniorResults Maps 
    * 
    */
   public void categorise()
   {
      for (Runner eachRunner : runners)
      {
         if(eachRunner.getAgeGroup() == "Junior")
         {
            juniorResults.put(eachRunner.getName(), eachRunner.getTime());
         }
         
         if(eachRunner.getAgeGroup() == "Senior")
         {
            seniorResults.put(eachRunner.getName(), eachRunner.getTime());
         }
         
         if(eachRunner.getAgeGroup() == "Standard")
         {
            standardResults.put(eachRunner.getName(), eachRunner.getTime());
         }
      }
   }
}

