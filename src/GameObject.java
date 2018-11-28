public abstract class GameObject {
  
  int positionX;
  int positionY;
  String image;
  
  GameObject(int positionX, int positionY, String image) {
    this.positionX = positionX;
    this.positionY = positionY;
    this.image = image;
  }
  GameObject(int positionX, int positionY) {
    this.positionX = positionX;
    this.positionY = positionY;
  }
}
