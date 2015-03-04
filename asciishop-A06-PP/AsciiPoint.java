public class AsciiPoint{
    int x, y;
    public AsciiPoint(int x, int y){
    this.x = x;
    this.y = y;
    }
    
    public int getX(){
    
    return x;
    }
    
    public int getY(){
    
    return y;
    }
    
    public String toString(){
    String asciiP = "(" + getX() + "," + getY() + ")";
    return asciiP;
    }
    

}