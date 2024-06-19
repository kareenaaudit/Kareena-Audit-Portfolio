import java.util.HashSet;
import java.util.LinkedList;
import java.util.*;
import java.util.List;

/**
 * NB: DO NOT MODIFY THE METHOD NAMES, RETURN TYPES OR
 * PARAMETERS IN THIS CLASS IN ANY WAY.
 * 
 * Manage a collection of infection test results.
 *
 * @author (Kareena Audit)
 * @version (03/11/2021)
 */
public class InfectionResults
{
    private ArrayList<InfectionTest> infection;
    
    /**
     * Constructor for objects of class InfectionResults
     */
    /** the collection class to store the InfectionTest objects which
       is defined and initialised here*/
    
    public InfectionResults()
    {
        infection = new ArrayList<>();
    }
    
    /**
     * Add the given test to the collection.
     * @param test The test to be added.
     */
    /** this adds the infectiontest to the collection when participant
     writes in a test*/
    public void addTest(InfectionTest test)
    {
        infection.add(test);
    }
    
    /**
     * Get the number of tests currently stored.
     * @return the number of tests.
     */
    /** shows the number of tests that is stored in the collection*/
    public int getNumberOfTests()
    {
        return infection.size();
    }
    
    /**
     * List the tests in the collection.
     */
    /** this prints the details of the infectiontests that is stored
       in the collection*/
    public void list()
    {
       int index = 0;
       while(index < infection.size()){
           InfectionTest infectiontestname = infection.get(index);
           System.out.println(infectiontestname.getDetails());
           index++;;
       }
    }
    
    /**
     * Set the status of the given test.
     * If a test with the id is not found no action is required
     * and no error message should be printed.
     * @param id The id of the test.
     * @param positive Whether the test is positive or not.
     */
    /** the participant given id is then found in the collection to
       find the right infectiontest*/
    public void setStatus(String id, boolean positive)
    {
        boolean searching = true;
        for (InfectionTest infectiontestname : infection){
            if(infectiontestname.getID() == id)
            {
                infectiontestname.setStatus(positive);
            }
        }
    }
    
    /**
     * Find the test (if any) with the given id.
     * @return The test with the given id, or null if
     *         none matches.
     */
    /** the given id is found in the collection and shown*/
    public InfectionTest findTest(String id)
    {
        for (InfectionTest infectiontestname : infection)
        {
            if(infectiontestname.getID() == id)
        {
            return infectiontestname;
        }
    }
        return null;
    }

    
    /**
     * Return all the positive tests.
     * @return the positive tests.
     */
    /** the positive and true infectiontests in the collection is stored
       in a new hashset collection and is shown.
       returned collection is empty if theres no positive test but no 
       test are removed from the collection*/
    public HashSet<InfectionTest> getPositiveTests()
    {
       HashSet <InfectionTest> positive = new HashSet<>();
        for (InfectionTest infectiontestname : infection)
        {
            if(infectiontestname.isKnown())
                if(infectiontestname.isPositive())
                {
                    positive.add(infectiontestname);}
            }
        return positive;
    }

    
    /**
     * Remove all the tests with unknown status and return
     * those that were removed.
     * @return the removed tests.
     */
    /** the infectiontest in the collection whos status is false is 
       removed from the collection and stored in a new collection 
       (noCollection). unknown status tests are shown*/
    public LinkedList<InfectionTest> removeUnknownStatus()
    {
        LinkedList<InfectionTest> noCollection;
        noCollection= new LinkedList<>();
       Iterator<InfectionTest> it = infection.iterator();
       while(it.hasNext()) {
           InfectionTest test=it.next();
           if(test.isKnown() == false)
           {
               it.remove();
               noCollection.add(test);
           }
     
    }
    return noCollection;
    }
}



