/*
 * Window.java
 *
 * Created on October 27, 2006, 7:34 PM
 */

package gameoflife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author  mdarby
 */
public class Window extends javax.swing.JFrame implements Runnable {
  
  /** Creates new form Window */
  public Window() {
    initComponents();
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
  private void initComponents() {
    
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(0, 400, Short.MAX_VALUE)
      );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(0, 300, Short.MAX_VALUE)
      );
    pack();
  }
  // </editor-fold>//GEN-END:initComponents
  
  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new Window().setVisible(true);
      }
    });
  }
  
  public void drawGen(Graphics g){
    g.setColor(Color.white);
    int x=0,y=0,old_status=0,new_status=0;
    
    cells = (int[]) ((gen_count == 0) ? default_columns() : next_gen.clone());
    
    for(int d=0;d<cells.length;d++){
      old_status = cells[d];
      new_status = is_alive(old_status,neighbors(cells,d));
      
      if(d%columns == 0 && d!=0){
        x = 0;
        y += cell_size;
      } else{
        x += cell_size;
      }
      
      if(old_status == 1 && new_status == 0){
        g.clearRect(x, y, cell_size, cell_size);
      } else if(new_status == 1){
        g.fillRect(x, y, cell_size, cell_size);
      }
      
      next_gen[d] = new_status;
    }
    drawCount(g);
    g.drawLine(0,0,500,0);
    
    gen_count++;
    repaint();
  }  

  public void paint(Graphics g){
    if(runner != null){
      drawGen(g);
      if(list.size() > 0){
        for ( int j = 1; j < list.size(); ++j ) {
          Point A = (Point)(list.elementAt(j-1));
          Point B = (Point)(list.elementAt(j));
          g.drawLine( A.x, A.y, B.x, B.y );
        }
      }
    } else{
      g.setColor(Color.black);
      g.fillRect(0,0,500,500);
    }
  }  

  public void run(){
    try {
      Thread.sleep(INTERVAL);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    repaint();
  }
  
  public void start() {
    if (runner == null){
      runner = new Thread(this);
      runner.start();
    }
  }  
 
  public void init() {
    setBackground(Color.black);
  }  

  public synchronized void stop(){
    if (runner != null) {
      runner = null;
    }
  }  
 
  public void update(Graphics g){
    paint(g);
  }  

  public void drawCount(Graphics g){
    g.setColor(Color.red);
    g.fillRect(450, 480, 50, 12);
    g.setColor(Color.white);
    g.drawString(String.valueOf(gen_count), 450, 490);
  }  
  
  public void clear(){
    stop();
    gen_count=0;
    repaint();
  }  
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  // End of variables declaration//GEN-END:variables
  
}