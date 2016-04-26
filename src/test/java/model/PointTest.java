

package model;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Spock
 */


public class PointTest {
    

    private Point point;
    
    @Before
    public void setUp(){
        point = new Point(1,1);
    }
    
    @Test
    public void GetX(){
        int x = point.getX();
        assertTrue(x == 1);
    }

    @Test
    public void GetY(){
        int y = point.getY();
        assertTrue(y == 1);
    }
    
    @Test
    public void TryToSetValue(){
        int y = point.getY();
        y=2;
        assertFalse(y == point.getY());
    }
    
    @Test
    public void CopyClassAndChangeValue(){
        Point p = point;
        int x = p.getX();
        x = 2;
        assertFalse(x == point.getX());        
    }
    
    @Test
    public void CopyClass(){
        Point p = point;
        p = new Point(2,2);
        assertFalse(p.getX() == point.getX());
    }
}
