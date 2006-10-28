/*
 * Main.java
 *
 * Created on October 27, 2006, 6:45 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gameoflife;

import java.applet.Applet;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;
import org.w3c.dom.events.MouseEvent;


public class Main {
  private int cell_size = 1;
  private int seed = 20;
  private int columns = 500 / cell_size;
  private int total_cells = columns * columns;
  private int[] cells = new int[total_cells];
  private int gen_count = 0;
  private int[] next_gen = new int[total_cells];
  private volatile Thread runner = null;
  private long INTERVAL = 0;
  Vector list = new Vector();
   
  public void mouseDragged(MouseEvent e) {
    if ( list.size() >= 5 ) {
      list.removeElementAt( 0 );
    }
    
    list.addElement(new Point(e.getX(), e.getY()));
    next_gen[e.getX() + ((e.getY()-1) * columns)] = 1;
    repaint();
    e.consume();
  }

}
