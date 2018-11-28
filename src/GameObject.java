public abstract class GameObject {
  
  int positionX;
  int positionY;
  String image;
  
  public GameObject(int positionX, int positionY, String image) {
    this.positionX = positionX;
    this.positionY = positionY;
    this.image = image;
  }
  public GameObject(int positionX, int positionY) {
    this.positionX = positionX;
    this.positionY = positionY;
  }
}
