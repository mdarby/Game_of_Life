package gameoflife;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Window extends javax.swing.JFrame{
  
  private static int seed = 15;
  private static int cellSize = 5;
  private static int genCount = 0;
  private static int columns = 500 / cellSize;
  private boolean gameRunning = false;
  private boolean forwardOneGen = false;
  private static int currGen[][] = new int[columns][columns];
  private static int nextGen[][] = new int[columns][columns];
  
  public Window() {
    initComponents();
  }
  
  // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
  private void initComponents() {
    gamePanel = new javax.swing.JPanel();
    stepButton = new javax.swing.JButton();
    clearButton = new javax.swing.JButton();
    startButton = new javax.swing.JButton();
    stopButton = new javax.swing.JButton();
    quitButton = new javax.swing.JButton();
    slider = new javax.swing.JSlider();
    genLabel = new javax.swing.JLabel();

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

    quitButton.setText("Quit");
    quitButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        quitButtonMouseClicked(evt);
      }
    });

    slider.setMajorTickSpacing(100);
    slider.setMaximum(1000);
    slider.setMinorTickSpacing(10);

    genLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    genLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
          .add(org.jdesktop.layout.GroupLayout.LEADING, gamePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(org.jdesktop.layout.GroupLayout.LEADING, slider, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
          .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
            .add(startButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 82, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(stopButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(stepButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(clearButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 57, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(quitButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(genLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)))
        .addContainerGap())
    );

    layout.linkSize(new java.awt.Component[] {clearButton, quitButton, startButton, stepButton, stopButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(gamePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(slider, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
          .add(genLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .add(startButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .add(quitButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .add(stepButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .add(clearButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .add(stopButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    pack();
  }// </editor-fold>//GEN-END:initComponents
  
  private void stop(){
    gameRunning = false;
    Thread.currentThread().interrupt();    
  }
  
  private void quitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quitButtonMouseClicked
    stop();
    System.exit(0);
  }//GEN-LAST:event_quitButtonMouseClicked
  
  private void stopButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stopButtonMouseClicked
    stop();
  }//GEN-LAST:event_stopButtonMouseClicked
  
  private void startButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startButtonMouseClicked
    gameRunning = true;
    
    Thread t = new Thread(new Runnable() {
      public void run() {
        life();
      }
    });
    
    t.start();
    
  }//GEN-LAST:event_startButtonMouseClicked
  
  private void clearButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearButtonMouseClicked
    stop();
    
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
    g.fill(new Rectangle(X + 1, Y + 1, cellSize-1, cellSize-1));
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
      
      genCount++;
      updatePanel();
      
      if(forwardOneGen){
        forwardOneGen = false;
      }
      
      try {
        if (Thread.interrupted()) {
          return;
        } else{
          Thread.sleep(slider.getValue());
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
    
    genLabel.setText(String.valueOf(genCount));
    //paintGrid();
  }
  
  private void stepButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stepButtonMouseClicked
    stop();
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
    }else{
      currGen[X][Y] = 0;
      g.setColor(Color.BLACK);
    }
    
    g.fill(new Rectangle((X * cellSize)+1, (Y * cellSize)+1, cellSize-1, cellSize-1));
  }//GEN-LAST:event_gamePanelMouseClicked
  
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new Window().setVisible(true);
      }
    });
  }
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton clearButton;
  private static javax.swing.JPanel gamePanel;
  private static javax.swing.JLabel genLabel;
  private javax.swing.JButton quitButton;
  private javax.swing.JSlider slider;
  private javax.swing.JButton startButton;
  private javax.swing.JButton stepButton;
  private javax.swing.JButton stopButton;
  // End of variables declaration//GEN-END:variables
  
}
