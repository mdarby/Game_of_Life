/*
 * Cell.java
 *
 * Created on October 27, 2006, 7:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gameoflife;


public class Cell {
  private int m_size = 0;
  private boolean m_status = false;
  
  
  public Cell(boolean status) {
    m_status = status;
  }
  
  public boolean isAlive(){
    return m_status;
  }  
  
  public void kill(){
    m_status = false;
  }
  
  public void birth(){
    m_status = true;
  }
}
