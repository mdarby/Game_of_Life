package gameoflife;
import junit.framework.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import javax.swing.JFrame;
/*
 * GameTest.java
 * JUnit based test
 *
 * Created on November 14, 2006, 2:25 PM
 */

/**
 *
 * @author mdarby
 */
public class GameTest extends TestCase {
  
  public GameTest(String testName) {
    super(testName);
  }
  
  protected void setUp() throws Exception {
  }
  
  protected void tearDown() throws Exception {
  }
  
  public void testPaint() {
    System.out.println("paint");
    
    Graphics g = null;
    gameoflife.Game instance = new gameoflife.Game();
    
    instance.paint(g);
    
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
  public void testUpdate() {
    System.out.println("update");
    
    Graphics g = null;
    gameoflife.Game instance = new gameoflife.Game();
    
    instance.update(g);
    
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
  public void testNeighbors() {
    System.out.println("neighbors");
    
    int col = 0;
    int row = 0;
    gameoflife.Game instance = new gameoflife.Game();
    
    int expResult = 0;
    int result = instance.neighbors(col, row);
    assertEquals(expResult, result);
    
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
  public void testMain() {
    System.out.println("main");
    
    String[] args = null;
    
    gameoflife.Game.main(args);
    
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
