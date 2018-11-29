import java.awt.event.KeyEvent;

public abstract class Plane extends GameObject {
  
  int hp;
  int dmg;
  int armor;
  Moveset moveset = new Moveset();
  
  public Plane(int positionX, int positionY, String image) {
    super(positionX, positionY, image);
  }
  
  Plane(int positionX, int positionY) {
    super(positionX, positionY);
  }
  
  boolean move(KeyEvent e) {
    if (moveset.moveList.contains(e.getKeyCode())) {
      if (e.getKeyCode() == (moveset.moveList.get(0)) && (Board.notOutOfBounds(positionX, positionY + 1))) {
        positionY += 1;
        return true;
      }
      if (e.getKeyCode() == moveset.moveList.get(1) && Board.notOutOfBounds(positionX, positionY - 1)) {
        positionY -= 1;
        return true;
      }
      if (e.getKeyCode() == moveset.moveList.get(2) && Board.notOutOfBounds(positionX - 1, positionY)) {
        positionX -= 1;
        return true;
      }
      if (e.getKeyCode() == moveset.moveList.get(3) && Board.notOutOfBounds(positionX + 1, positionY)) {
        positionX += 1;
        return true;
      }
    }
    return false;
  }
}



