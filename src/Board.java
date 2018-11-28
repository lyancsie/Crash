import javafx.geometry.Pos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JComponent implements KeyListener {
  
  Tile[][] tileSet = new Tile[10][10];
  F16 myPlane = new F16(0, 0);
  Plane enemyPlane = new F16(9, 9);
  int tileSize = 72;
  boolean player1Round = true;
  
  public Board() {
    // set the size of your draw board
    setPreferredSize(new Dimension(720, 720));
    //Moveset.filler();
    setVisible(true);
  }
  
  public void initialize(Graphics g) {
    setTiles(g);
    setPlanes(g);
    myPlane.moveset.moveList.add(0x28);
    myPlane.moveset.moveList.add(0x26);
    myPlane.moveset.moveList.add(0x25);
    myPlane.moveset.moveList.add(0x27);
    
    enemyPlane.moveset.moveList.add(0x53);
    enemyPlane.moveset.moveList.add(0x57);
    enemyPlane.moveset.moveList.add(0x41);
    enemyPlane.moveset.moveList.add(0x44);
    
  }
  
  public void setTiles(Graphics g) {
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        PositionedImage image = new PositionedImage("img/Tile.png", i * tileSize, j * tileSize);
        tileSet[i][j] = new BasicTile(i, j);
        image.draw(g);
      }
    }
  }
  
  public void setPlanes(Graphics g) {
    PositionedImage myPlaneImage = new PositionedImage(myPlane.image, myPlane.positionX * tileSize, myPlane.positionY * tileSize);
    myPlaneImage.draw(g);
    PositionedImage enemyPlaneImage = new PositionedImage(enemyPlane.image, enemyPlane.positionX * tileSize, enemyPlane.positionY * tileSize);
    enemyPlaneImage.draw(g);
  }
  
  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    //graphics.fillRect(testBoxX, testBoxY, 100, 100);
    // here you have a 720x720 canvas
    // you can create and draw an image using the class below e.g.
    initialize(graphics);
  }
  
  public static void main(String[] args) {
    // Here is how you set up a new window and adding our board to it
    JFrame frame = new JFrame("?");
    Board board = new Board();
    frame.add(board);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.pack();
    // Here is how you can add a key event listener
    // The board object will be notified when hitting any key
    // with the system calling one of the below 3 methods
    frame.addKeyListener(board);
    // Notice (at the top) that we can only do this
    // because this Board class (the type of the board object) is also a KeyListener
    
  }
  
  // To be a KeyListener the class needs to have these 3 methods in it
  @Override
  public void keyTyped(KeyEvent e) {
  
  }
  
  public void player1Move(KeyEvent e) {
    //if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
    
  }
  
  public void player2Move(KeyEvent e) {
    boolean moved = false;
    if (Moveset.player2.contains(e.getKeyCode())) {
      if (e.getKeyCode() == KeyEvent.VK_S && notOutOfBounds(enemyPlane.positionX, enemyPlane.positionY + 1)) {
        enemyPlane.positionY += 1;
        moved = true;
      }
      if (e.getKeyCode() == KeyEvent.VK_W && notOutOfBounds(enemyPlane.positionX, enemyPlane.positionY - 1)) {
        enemyPlane.positionY -= 1;
        moved = true;
      }
      if (e.getKeyCode() == KeyEvent.VK_A && notOutOfBounds(enemyPlane.positionX - 1, enemyPlane.positionY)) {
        enemyPlane.positionX -= 1;
        moved = true;
      }
      if (e.getKeyCode() == KeyEvent.VK_D && notOutOfBounds(enemyPlane.positionX + 1, enemyPlane.positionY)) {
        enemyPlane.positionX += 1;
        moved = true;
      }
      if (moved) {
        player1Round = true;
      }
    }
  }
  
  public static boolean notOutOfBounds(int whereToMoveX, int whereToMoveY) {
    return (whereToMoveX >= 0 && whereToMoveY >= 0 && whereToMoveX <= 9 && whereToMoveY <= 9);
  }
  
  @Override
  public void keyPressed(KeyEvent e) {
    if (player1Round) {
      myPlane.move(e);
      player1Round = false;
    } else {
      enemyPlane.move(e);
      player1Round = true;
    }
  }
  
  // But actually we can use just this one for our goals here
  @Override
  public void keyReleased(KeyEvent e) {
    // When the up or down keys hit, we change the position of our box
    
    // and redraw to have a new picture with the new coordinates
    repaint();
  }
}