package gameoflife;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Game extends Applet{
  private int seed = 15;
  private int cellSize = 1;
  private int columns = 100 / cellSize;
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
    g.setColor(Color.WHITE);
    
    for(int i=0; i<columns; i++){
      for(int k=0; k<columns; k++){
	if(currGen[i][k] == 1){
	  g.fillRect(i*cellSize,k*cellSize,cellSize,cellSize);
	} else{
	  g.clearRect(i*cellSize,k*cellSize,cellSize,cellSize);
	}
      }
    }
    life();
  }
  
  public void update(Graphics g) {
    paint(g);
  }
  
  public void init(){
    setBackground(Color.black);
    setSize(100,100);
    Game g = new Game();
    g.life();
  }
  
  private void display(){
    System.out.print("\n\n");
    for(int i=0; i<columns; i++){
      for(int k=0; k<columns; k++){
	System.out.print(currGen[i][k] + " ");
      }
      System.out.print("\n");
    }
    System.out.print("\n\n");
  }
  
  public int neighbors(int col, int row){
    int total = 0;
    
    if(col < columns && row < columns){
      total += currGen[col-1][row-1];
      total += currGen[col][row-1];
      total += currGen[col+1][row-1];
      
      total += currGen[col-1][row];
      total += currGen[col+1][row];
      
      total += currGen[col-1][row+1];
      total += currGen[col][row+1];
      total += currGen[col+1][row+1];
    }
    return total;
  }
}
