public class Enemy extends Plane {
  
  public Enemy(int positionX, int positionY, String image) {
    super(positionX, positionY);
  }
  
  Enemy(int positionX, int positionY) {
    super(positionX, positionY);
    image = "img/baseplane.png";
    hp = 50;
    direction = "left";
  
  
    moveset.moveList.add(0x53);
    moveset.moveList.add(0x57);
    moveset.moveList.add(0x41);
    moveset.moveList.add(0x44);
  }
}
