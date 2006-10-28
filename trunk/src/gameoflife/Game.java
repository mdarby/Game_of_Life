/*
 * Game.java
 *
 * Created on October 27, 2006, 7:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gameoflife;

import java.util.Random;
import java.util.Vector;


public class Game {
  private int cell_size = 1;
  private int seed = 20;
  private static int gen_count = 0;
  private int columns = 500 / cell_size;
  private int total_cells = columns * columns;  
  private Cell board[][] = new Cell[columns][columns];  
  
  public Game() {
    Random rand = new Random();
    
    for(int k=0; k<columns; k++){
      for(int i=0; i<columns; i++){
        boolean life = rand.nextInt(seed) == 1 ? true : false;
        board[k][i] = new Cell(life);
      }
    }
  }

  public void updateStatus(){
    int count = neighbors();
    
    if((m_status == 0 && count == 3) || (m_status == 1 && (count == 2 || count == 3))){
       return true;
    }
    else{
      return false;
    }
  }
  
  public int neighbors(){
    int total = 0;
    int i = 0;
    
    total += i-columns-1 <= 0 ? 0 : p[i-columns-1];
    total += i-columns <= 0 ? 0 : p[i-columns];
    total += i-columns+1 <= 0 ? 0 : p[i-columns+1];
    
    total += i-1 <= 0 ? 0 : p[i-1];
    total += i+1 >= total_cells ? 0 : p[i+1];
    
    total += i+columns-1 >= total_cells ? 0 : p[i+columns-1];
    total += i+columns >= total_cells ? 0 : p[i+columns];
    total += i+columns+1 >= total_cells ? 0 : p[i+columns+1];
    
    return total;
  }  
  
  public int getCount(){
    return gen_count;
  }
}
