

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;

/**
 * The test class QLearnTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class QLearnTest
{
    /**
     * Default constructor for test class QLearnTest
     */
    public QLearnTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void hashMapTest(){
        QLearn work = new QLearn(.1,.1);
        
        double[] info = {400, 300, 0, 0, 0, 0, 50, 0, 70000, 200};
        Status test = new Status(info);
        
        Move m = new Move("Jab");
        
       double[] info2 = {400, 300, 0, 0, 0, 0, 50, 0, 70000, 200};
        Status test2 = new Status(info2);
        
       work.newQValue(test, m, 350, 290, .5);
       
       HashMap<Move, Double> values = work.findState(test2);
       
      assertNotNull(values);
    }
}
