package gameoflife;

public interface Game_Of_Life {
  static int seed = 15;
  static int cellSize = 15;
  static int columns = 500 / cellSize;
  static int currGen[][] = new int[columns][columns];
  static int nextGen[][] = new int[columns][columns];  
}
