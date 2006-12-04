package gameoflife;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Window extends javax.swing.JFrame implements Game_Of_Life, Runnable{
  
  private boolean gameRunning = false;
  private boolean forwardOneGen = false;
  private volatile Thread t = new Thread();  
  
  public Window() {
    initComponents();
    this.setVisible(true);
  }
  
  // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
  private void initComponents() {
    gamePanel = new GamePanel();
    stepButton = new javax.swing.JButton();
    clearButton = new javax.swing.JButton();
    startButton = new javax.swing.JButton();
    stopButton = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    gamePanel.setBackground(new java.awt.Color(0, 0, 0));
    gamePanel.setMaximumSize(new java.awt.Dimension(500, 500));
    gamePanel.setMinimumSize(new java.awt.Dimension(500, 500));
    gamePanel.setPreferredSize(new java.awt.Dimension(500, 500));
    gamePanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseDragged(java.awt.event.MouseEvent evt) {
        gamePanelMouseDragged(evt);
      }
    });
    gamePanel.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        gamePanelMouseClicked(evt);
      }
    });

    org.jdesktop.layout.GroupLayout gamePanelLayout = new org.jdesktop.layout.GroupLayout(gamePanel);
    gamePanel.setLayout(gamePanelLayout);
    gamePanelLayout.setHorizontalGroup(
      gamePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(0, 500, Short.MAX_VALUE)
    );
    gamePanelLayout.setVerticalGroup(
      gamePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(0, 500, Short.MAX_VALUE)
    );

    stepButton.setText("Step");
    stepButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        stepButtonMouseClicked(evt);
      }
    });

    clearButton.setText("Clear");
    clearButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        clearButtonMouseClicked(evt);
      }
    });

    startButton.setText("Start");
    startButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        startButtonMouseClicked(evt);
      }
    });

    stopButton.setText("Stop");
    stopButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        stopButtonMouseClicked(evt);
      }
    });

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(gamePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
        .addContainerGap(144, Short.MAX_VALUE)
        .add(startButton)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(stopButton)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(stepButton)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(clearButton)
        .add(134, 134, 134))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(gamePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(startButton)
          .add(stopButton)
          .add(stepButton)
          .add(clearButton))
        .addContainerGap())
    );
    pack();
  }// </editor-fold>//GEN-END:initComponents
  
  private void stopButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stopButtonMouseClicked
    gameRunning = false;
    Thread.currentThread().interrupt();
  }//GEN-LAST:event_stopButtonMouseClicked
  
  private void startButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startButtonMouseClicked
    gameRunning = true;
    life();
  }//GEN-LAST:event_startButtonMouseClicked
  
  private void clearButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearButtonMouseClicked
    Graphics2D g2d = (Graphics2D) gamePanel.getGraphics();
    
    for(int k = 0; k < columns; k++){
      for(int i = 0; i < columns; i++){
	currGen[k][i] = 0;
      }
    }
    
    updatePanel();
  }//GEN-LAST:event_clearButtonMouseClicked
  
  public int neighbors(int col, int row){
    int total = 0;
    
    total += (col-1 >= 0 && row-1 >= 0) ? currGen[col-1][row-1] : 0;
    total += (col >= 0 && row-1 >= 0) ? currGen[col][row-1] : 0;
    total += (col+1 < columns && row-1 >= 0) ? currGen[col+1][row-1] : 0;
    
    total += (col-1 >= 0) ? currGen[col-1][row] : 0;
    total += (col+1 < columns) ? currGen[col+1][row] : 0;
    
    total += (col-1 >= 0 && row+1 < columns) ? currGen[col-1][row+1] : 0;
    total += (row+1 < columns) ? currGen[col][row+1] : 0;
    total += (col+1 < columns && row+1 < columns) ? currGen[col+1][row+1] : 0;
    
    return total;
  }
  
  public static void updateCell(int X, int Y, Color c, Graphics2D g){
    g.setColor(c);
    g.fill(new Rectangle(X, Y, cellSize, cellSize));
  }
  
  private static void paintGrid(){
    Graphics2D g2d = (Graphics2D)gamePanel.getGraphics();
    g2d.setColor(Color.decode("#444444"));
    
    for(int k = 0; k < 500; k += cellSize){
      g2d.drawLine(0, k, 500, k);
      g2d.drawLine(k, 0, k, 500);
    }
  }
  
  private void life(){
    while(gameRunning || forwardOneGen){
      for(int x = 0; x < columns; x++){
	for(int y = 0; y < columns; y++){
	  int count = neighbors(x,y);
	  int status = currGen[x][y];
	  
	  if((status == 0 && count == 3) || (status == 1 && (count == 2 || count == 3))){
	    nextGen[x][y] = 1;
	  } else {
	    nextGen[x][y] = 0;
	  }
	}
      }
      
      for(int x = 0; x < columns; x++){
	for(int y = 0; y < columns; y++){
	  currGen[x][y] = nextGen[x][y];
	} 
      }
      
      updatePanel();
      
      if(forwardOneGen){
	forwardOneGen = false;
      }
      
      try {
	if (Thread.interrupted()) {
	  return;
	} else{
	  Thread.sleep(100);
	}
      } catch (InterruptedException ex) {
	ex.printStackTrace();
      }
      
    }
  }
  
  private static void updatePanel(){
    Graphics2D g2d = (Graphics2D) gamePanel.getGraphics();
    
    for(int x = 0; x < columns; x++){
      for(int y = 0; y < columns; y++){
	if(currGen[x][y] == 1){
	  updateCell(cellSize * x, cellSize * y, Color.RED, g2d);
	} else {
	  updateCell(cellSize * x, cellSize * y, Color.BLACK, g2d);
	}
      }
    }
    
    paintGrid();
  }
  
  private void stepButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stepButtonMouseClicked
    forwardOneGen = true;
    life();
  }//GEN-LAST:event_stepButtonMouseClicked
  
  private void gamePanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gamePanelMouseDragged
    Graphics2D g = (Graphics2D) gamePanel.getGraphics();
    g.setColor(Color.RED);
    
    int X = (int)Math.floor(evt.getX() / cellSize);
    int Y = (int)Math.floor(evt.getY() / cellSize);
    
    currGen[X][Y] = 1;
    
    g.fill(new Rectangle((X * cellSize)+1, (Y * cellSize)+1, cellSize-1, cellSize-1));
  }//GEN-LAST:event_gamePanelMouseDragged
  
  private void gamePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gamePanelMouseClicked
    Graphics2D g = (Graphics2D) gamePanel.getGraphics();
    
    int X = (int)Math.floor(evt.getX() / cellSize);
    int Y = (int)Math.floor(evt.getY() / cellSize);
    
    if(currGen[X][Y] == 0){
      currGen[X][Y] = 1;
      g.setColor(Color.RED);
      g.fill(new Rectangle((X * cellSize)+1, (Y * cellSize)+1, cellSize-1, cellSize-1));
    }else{
      currGen[X][Y] = 0;
      g.setColor(Color.BLACK);
      g.fill(new Rectangle((X * cellSize)+1, (Y * cellSize)+1, cellSize-1, cellSize-1));
    }    
  }//GEN-LAST:event_gamePanelMouseClicked
  
  public static void main(String args[]) {
//    java.awt.EventQueue.invokeLater(new Runnable() {
//      public void run() {
//	new Window().setVisible(true);
//      }
//    });
    (new Thread(new Window())).start();
  }
  
  public void run(){
  }
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton clearButton;
  private static javax.swing.JPanel gamePanel;
  private javax.swing.JButton startButton;
  private javax.swing.JButton stepButton;
  private javax.swing.JButton stopButton;
  // End of variables declaration//GEN-END:variables
  
}
