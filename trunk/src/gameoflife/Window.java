package gameoflife;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

public class Window extends javax.swing.JFrame{
  
  private static Graphics2D g2d;
  private static int cellSize = 3;
  private static int genCount = 0;
  private boolean gameRunning = false;
  private boolean forwardOneGen = false;  
  private static Color LIVE = Color.RED;
  private static Color DEAD = Color.BLACK;  
  private static int matrixSize = 500 / cellSize;
  private static int currGen[][] = new int[matrixSize][matrixSize];
  private static int nextGen[][] = new int[matrixSize][matrixSize];
  
  public Window() {
    initComponents();
  }

  private static AlphaComposite makeComposite(float alpha) {
    int type = AlphaComposite.SRC_OVER;
    return(AlphaComposite.getInstance(type, alpha));
  }  
  
  private void chooseColor(){
    LIVE = JColorChooser.showDialog(this, "Alive Cell Color", LIVE);
    updatePanel();
  }
  
  private void clear(){
    stop();
    genCount = 0;
    genLabel.setText("");
    
    Graphics2D g2d = (Graphics2D) gamePanel.getGraphics();
    
    for(int k = 0; k < matrixSize; k++){
      for(int i = 0; i < matrixSize; i++){
	currGen[k][i] = 0;
      }
    }
    
    updatePanel(); 
  }  
  
  private void life(){
    while(gameRunning || forwardOneGen){
      for(int x = 0; x < matrixSize; x++){
	for(int y = 0; y < matrixSize; y++){
	  int count = neighbors(x,y);
	  int status = currGen[x][y];
	  
	  if((status == 0 && count == 3) || (status == 1 && (count == 2 || count == 3))){
	    nextGen[x][y] = 1;
	  } else {
	    nextGen[x][y] = 0;
	  }
	}
      }
      
      for(int x = 0; x < matrixSize; x++){
	for(int y = 0; y < matrixSize; y++){
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
  
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
	Window w = new Window();
	
	g2d = (Graphics2D) gamePanel.getGraphics();
	g2d.setComposite(makeComposite(.5F));
	g2d.setBackground(DEAD);
	
	w.slider.setValue(0);
	w.setTitle("Java :: Game of Life");
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screenSize = toolkit.getScreenSize();
	
	int x = (screenSize.width - w.getWidth()) / 2;
	int y = (screenSize.height - w.getHeight()) / 2;
	
	w.setVisible(true);
	w.setLocation(x,y);
      }
    });
  }    
  
  private int neighbors(int X, int Y){
    int total = 0;
    
    Vector p = new Vector();
    p.add(new Point(X-1, Y-1));
    p.add(new Point(X, Y-1));
    p.add(new Point(X+1, Y-1));
    p.add(new Point(X-1, Y));
    p.add(new Point(X+1, Y));
    p.add(new Point(X-1, Y+1));
    p.add(new Point(X, Y+1));
    p.add(new Point(X+1, Y+1));
    
    for(int i=0; i < p.size(); i++){
      
      Point currPosition = (Point)p.elementAt(i);
      int cX = (int)currPosition.getX();
      int cY = (int)currPosition.getY();
      
      if(cX < 0){cX = matrixSize - 1;}
      if(cY < 0){cY = matrixSize - 1;}
      
      if(cX >= matrixSize){cX = 0;}
      if(cY >= matrixSize){cY = 0;}
      
      total += currGen[cX][cY];
    }
    
    return total;
  }  
  
  private void quit(){
    stop();
    System.exit(0); 
  }   
  
  private void start(){
    if(!gameRunning){
      gameRunning = true;
      startButton.setEnabled(false);
      startMenuItem.setEnabled(false);
            
      Thread t = new Thread(new Runnable() {
	public void run() {
	  life();
	}
      });

      t.start();
    }
  }
  
  private void stop(){
    gameRunning = false;
    Thread.currentThread().interrupt(); 
    startButton.setEnabled(true);
    startMenuItem.setEnabled(true);
  }
  
  private void step(){
    if(gameRunning){
      stop();
    }
    
    forwardOneGen = true;
    life();  
  }
  
  private static void updateMatrixSize(){
    int newMatrixSize = Integer.parseInt(JOptionPane.showInputDialog("New Cell Size"));
    
    int newGen[][] = new int[newMatrixSize][newMatrixSize];
    
    if(newMatrixSize > matrixSize){
      for(int x = 0; x < newMatrixSize; x++){
	for(int y = 0; y < newMatrixSize; y++){
	  
	}
      }
    }
    
    matrixSize = newMatrixSize; 
  }
  
  private static void updatePanel(){
    
    for(int x = 0; x < matrixSize; x++){
      for(int y = 0; y < matrixSize; y++){
	
	if(currGen[x][y] == 1){
	  g2d.fillRect((cellSize * x) + 1, (cellSize * y) + 1, cellSize - 1, cellSize - 1);
	} else {
	  g2d.clearRect((cellSize * x) + 1, (cellSize * y) + 1, cellSize - 1, cellSize - 1);
	}
	
      }
    }
    
    genLabel.setText(String.valueOf(genCount));
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
    menuBar = new javax.swing.JMenuBar();
    fileMenu = new javax.swing.JMenu();
    startMenuItem = new javax.swing.JMenuItem();
    stopMenuItem = new javax.swing.JMenuItem();
    stepMenuItem = new javax.swing.JMenuItem();
    clearMenuItem = new javax.swing.JMenuItem();
    quitMenuItem = new javax.swing.JMenuItem();
    optionsMenu = new javax.swing.JMenu();
    aliveColorMenuItem = new javax.swing.JMenuItem();
    setCellSizeMenuItem = new javax.swing.JMenuItem();
    aboutMenu = new javax.swing.JMenu();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    addWindowFocusListener(new java.awt.event.WindowFocusListener() {
      public void windowGainedFocus(java.awt.event.WindowEvent evt) {
        formWindowGainedFocus(evt);
      }
      public void windowLostFocus(java.awt.event.WindowEvent evt) {
      }
    });

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

    fileMenu.setText("File");
    startMenuItem.setText("Start");
    startMenuItem.setName("startMenuItem");
    startMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(java.awt.event.MouseEvent evt) {
        startMenuItemMousePressed(evt);
      }
    });

    fileMenu.add(startMenuItem);

    stopMenuItem.setMnemonic('t');
    stopMenuItem.setText("Stop");
    stopMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(java.awt.event.MouseEvent evt) {
        stopMenuItemMousePressed(evt);
      }
    });

    fileMenu.add(stopMenuItem);

    stepMenuItem.setMnemonic('e');
    stepMenuItem.setText("Step");
    stepMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(java.awt.event.MouseEvent evt) {
        stepMenuItemMousePressed(evt);
      }
    });

    fileMenu.add(stepMenuItem);

    clearMenuItem.setMnemonic('c');
    clearMenuItem.setText("Clear");
    clearMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(java.awt.event.MouseEvent evt) {
        clearMenuItemMousePressed(evt);
      }
    });

    fileMenu.add(clearMenuItem);

    quitMenuItem.setMnemonic('q');
    quitMenuItem.setText("Quit");
    quitMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(java.awt.event.MouseEvent evt) {
        quitMenuItemMousePressed(evt);
      }
    });

    fileMenu.add(quitMenuItem);

    menuBar.add(fileMenu);

    optionsMenu.setText("Options");
    aliveColorMenuItem.setText("Choose Alive Cell Color");
    aliveColorMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(java.awt.event.MouseEvent evt) {
        aliveColorMenuItemMousePressed(evt);
      }
    });

    optionsMenu.add(aliveColorMenuItem);

    setCellSizeMenuItem.setText("Set Cell Size");
    setCellSizeMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(java.awt.event.MouseEvent evt) {
        setCellSizeMenuItemMousePressed(evt);
      }
    });

    optionsMenu.add(setCellSizeMenuItem);

    menuBar.add(optionsMenu);

    aboutMenu.setText("About");
    aboutMenu.addMenuListener(new javax.swing.event.MenuListener() {
      public void menuCanceled(javax.swing.event.MenuEvent evt) {
      }
      public void menuDeselected(javax.swing.event.MenuEvent evt) {
      }
      public void menuSelected(javax.swing.event.MenuEvent evt) {
        aboutMenuMenuSelected(evt);
      }
    });

    menuBar.add(aboutMenu);

    setJMenuBar(menuBar);

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(gamePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(slider, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
          .add(layout.createSequentialGroup()
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
          .add(stopButton))
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
    updatePanel();
  }//GEN-LAST:event_formWindowGainedFocus

  private void setCellSizeMenuItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setCellSizeMenuItemMousePressed
    updateMatrixSize();
  }//GEN-LAST:event_setCellSizeMenuItemMousePressed

  private void aliveColorMenuItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aliveColorMenuItemMousePressed
    chooseColor();
  }//GEN-LAST:event_aliveColorMenuItemMousePressed

  private void quitMenuItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quitMenuItemMousePressed
    quit();
  }//GEN-LAST:event_quitMenuItemMousePressed

  private void clearMenuItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearMenuItemMousePressed
    clear();
  }//GEN-LAST:event_clearMenuItemMousePressed

  private void stepMenuItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stepMenuItemMousePressed
    step();
  }//GEN-LAST:event_stepMenuItemMousePressed

  private void stopMenuItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stopMenuItemMousePressed
    stop();
  }//GEN-LAST:event_stopMenuItemMousePressed

  private void startMenuItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startMenuItemMousePressed
    start();
  }//GEN-LAST:event_startMenuItemMousePressed
  
  private void aboutMenuMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_aboutMenuMenuSelected
    String message = "Java :: Game of Life\n\nWritten by Matt Darby\n\nmatt@matt-darby.com\nwww.matt-darby.com\n\n2006-12-05";
    JOptionPane.showMessageDialog(this,message);
  }//GEN-LAST:event_aboutMenuMenuSelected
  
  private void quitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quitButtonMouseClicked
    quit();
  }//GEN-LAST:event_quitButtonMouseClicked
  
  private void stopButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stopButtonMouseClicked
    stop();
  }//GEN-LAST:event_stopButtonMouseClicked
  
  private void startButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startButtonMouseClicked
    start();
  }//GEN-LAST:event_startButtonMouseClicked
  
  private void clearButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearButtonMouseClicked
    clear();
  }//GEN-LAST:event_clearButtonMouseClicked
    
  private void stepButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stepButtonMouseClicked
    step();
  }//GEN-LAST:event_stepButtonMouseClicked
  
  private void gamePanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gamePanelMouseDragged
    g2d.setColor(LIVE);

    int X = (int)Math.floor(evt.getX() / cellSize);
    int Y = (int)Math.floor(evt.getY() / cellSize);

    currGen[X][Y] = 1;

    g2d.fillRect((X * cellSize)+1, (Y * cellSize)+1, cellSize-1, cellSize-1);
  }//GEN-LAST:event_gamePanelMouseDragged
  
  private void gamePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gamePanelMouseClicked
 
    int X = (int)Math.floor(evt.getX() / cellSize);
    int Y = (int)Math.floor(evt.getY() / cellSize);
    
    if(currGen[X][Y] == 0){
      currGen[X][Y] = 1;
      g2d.fillRect((X * cellSize)+1, (Y * cellSize)+1, cellSize-1, cellSize-1);
    }else{
      currGen[X][Y] = 0;
      g2d.clearRect((X * cellSize)+1, (Y * cellSize)+1, cellSize-1, cellSize-1);
    }
  }//GEN-LAST:event_gamePanelMouseClicked
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenu aboutMenu;
  private javax.swing.JMenuItem aliveColorMenuItem;
  private javax.swing.JButton clearButton;
  private javax.swing.JMenuItem clearMenuItem;
  private javax.swing.JMenu fileMenu;
  private static javax.swing.JPanel gamePanel;
  private static javax.swing.JLabel genLabel;
  private javax.swing.JMenuBar menuBar;
  private javax.swing.JMenu optionsMenu;
  private javax.swing.JButton quitButton;
  private javax.swing.JMenuItem quitMenuItem;
  private javax.swing.JMenuItem setCellSizeMenuItem;
  private javax.swing.JSlider slider;
  private javax.swing.JButton startButton;
  private javax.swing.JMenuItem startMenuItem;
  private javax.swing.JButton stepButton;
  private javax.swing.JMenuItem stepMenuItem;
  private javax.swing.JButton stopButton;
  private javax.swing.JMenuItem stopMenuItem;
  // End of variables declaration//GEN-END:variables
  
}
