public class Ally extends Plane {
  
  public Ally(int positionX, int positionY, String image) {
    super(positionX, positionY);
    
  }
  
  Ally(int positionX, int positionY) {
    super(positionX, positionY);
    image = "img/baseplane.png";
    direction = "right";
    hp = 50;
    
    moveset.moveList.add(0x28);
    moveset.moveList.add(0x26);
    moveset.moveList.add(0x25);
    moveset.moveList.add(0x27);
  }
}
