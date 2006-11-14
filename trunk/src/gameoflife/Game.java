package gameoflife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import javax.swing.JFrame;

public class Game extends JFrame{
  private static int seed = 15;
  private static int cellSize = 1;
  private static int columns = 150 / cellSize;
  private int currGen[][] = new int[columns][columns];
  private int nextGen[][] = new int[columns][columns];
  
  
  public Game() {
    Random rand = new Random();
    
    for(int k=0; k<columns; k++){
      for(int i=0; i<columns; i++){
	currGen[k][i] = rand.nextInt(seed) == 1 ? 1 : 0;
      }
    }
  }
  
  private void life(){
    for(int k=1; k<columns-1; k++){
      for(int i=1; i<columns-1; i++){
	int count = neighbors(k,i);
	int status = currGen[k][i];
	
	if((status == 0 && count == 3) || (status == 1 && (count == 2 || count == 3))){
	  nextGen[k][i] = 1;
	} else{
	  nextGen[k][i] = 0;
	}
      }
    }

    System.arraycopy(nextGen, 0, currGen, 0, Math.min(nextGen.length, currGen.length));
    repaint();
  }
  
  public void paint(Graphics g) {
    g.setColor(Color.RED);
    
    for(int k=1; k<columns-1; k++){
      for(int i=1; i<columns-1; i++){
	if(currGen[k][i] == 1){
	  g.fillRect(k*cellSize,i*cellSize,cellSize,cellSize);
	} else{
	  g.clearRect(k*cellSize,i*cellSize,cellSize,cellSize);
	}
      }
    }
   
    life();    
  }
  
  public void update(Graphics g) {
    paint(g);
  }
  
  public int neighbors(int col, int row){
    int total = 0;
    
    total += currGen[col-1][row-1];
    total += currGen[col][row-1];
    total += currGen[col+1][row-1];
    
    total += currGen[col-1][row];
    total += currGen[col+1][row];
    
    total += currGen[col-1][row+1];
    total += currGen[col][row+1];
    total += currGen[col+1][row+1];
    
    return total;
  }
  
  public static void main(String[] args) {
    Game g = new Game();
    
    g.addWindowListener(new WindowAdapter() {
      public void windowClosing( WindowEvent evt ){
	System.exit(0);
      }
    });
    
    g.setBackground(Color.BLACK);
    g.pack();
    g.life();
    g.setVisible(true);
    g.setSize(columns,columns);
    g.setLocation(500,300);
  }
}
