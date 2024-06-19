import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;

/**
 * The test class InfectionTestTester.
 *
 * @author  David J. Barnes
 * @version 2021.11.09)
 */
public class InfectionTestTester
{
    // A generator of random values.
    private Random rand;
    // The attributes of an InfectionTest.
    private String id;
    private InfectionTest example;
    
    /**
     * Default constructor for test class InfectionTestTester
     */
    public InfectionTestTester()
    {
        rand = new Random();
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        id = "t: " + (100 + rand.nextInt(100));
        example = new InfectionTest(id);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
    
    /**
     * Test the ID matches the value given.
     */
    @Test
    public void testInitialState1()
    {
        assertEquals(id, example.getID());
    }
    
    /**
     * Test that the initial state is unknown.
     */
    @Test
    public void testInitialState2()
    {
        assertEquals(false, example.isKnown());
    }
    
    /**
     * Test that isPositive throws an exception when
     * the status is not known.
     */
    @Test
    public void testInitialState3()
    {
        assertThrows(IllegalStateException.class, 
                     () -> example.isPositive(),
                    "The status is not valid.");
    }
    
    /**
     * Test setStatus for false.
     */
    @Test
    public void testSetStatusFalse()
    {
        example.setStatus(false);
        assertEquals(true, example.isKnown());
        assertEquals(false, example.isPositive());
    }
    
    /**
     * Test setStatus for true.
     */
    @Test
    public void testSetStatusTrue()
    {
        example.setStatus(true);
        assertEquals(true, example.isKnown());
        assertEquals(true, example.isPositive());
    }
    
    /**
     * Test getDetails with unknown status.
     */
    @Test
    public void testGetDetailsUnknown()
    {
        assertEquals(id + " status unknown", example.getDetails());
    }
    
    /**
     * Test getDetails with known status and false.
     */
    @Test
    public void testGetDetailsNegative()
    {
        example.setStatus(false);
        assertEquals(id + " is negative", example.getDetails());
    }
    
    /**
     * Test getDetails with known status and true.
     */
    @Test
    public void testGetDetailsPositive()
    {
        example.setStatus(true);
        assertEquals(id + " is positive", example.getDetails());
    }
}
