package gameoflife;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Game_Of_Life{
  
  public GamePanel() {
  }
  
  public void paint(Graphics g){
    g.setColor(Color.BLACK);
    g.fillRect(0,0,500,500);
    
    g.setColor(Color.decode("#444444"));
    
    for(int k = 0; k < 500; k += cellSize){
      g.drawLine(0, k, 500, k);
      g.drawLine(k, 0, k, 500);
    }
  }
  
  public void update(Graphics g){
    paint(g);
  }
}
