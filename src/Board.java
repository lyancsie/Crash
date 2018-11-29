import javafx.geometry.Pos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JComponent implements KeyListener {
  
  private Tile[][] tileSet = new Tile[10][10];
  private F16 myPlane = new F16(0, 0);
  private Plane enemyPlane = new F16(9, 9);
  private int tileSize = 72;
  private boolean player1Round = true;
  
  Board() {
    // set the size of your draw board
    setPreferredSize(new Dimension(720, 720));
    //Moveset.filler();
    setVisible(true);
  }
  
  private void initialize(Graphics g) {
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
  
  private void setTiles(Graphics g) {
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
    initialize(graphics);
  }
  
  public static void main(String[] args) {
    JFrame frame = new JFrame("?");
    Board board = new Board();
    frame.add(board);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.pack();
    frame.addKeyListener(board);
  }
  
  // To be a KeyListener the class needs to have these 3 methods in it
  @Override
  public void keyTyped(KeyEvent e) {
  
  }
  
  static boolean notOutOfBounds(int whereToMoveX, int whereToMoveY) {
    return (whereToMoveX >= 0 && whereToMoveY >= 0 && whereToMoveX <= 9 && whereToMoveY <= 9);
  }
  
  @Override
  public void keyPressed(KeyEvent e) {
    if (player1Round) {
      if (myPlane.move(e)) {
        player1Round = false;
      }
    } else {
      if (enemyPlane.move(e)) {
        player1Round = true;
      }
    }
  }
  
  @Override
  public void keyReleased(KeyEvent e) {
    repaint();
  }
}