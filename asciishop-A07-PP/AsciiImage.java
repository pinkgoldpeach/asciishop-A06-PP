import java.lang.Exception;
import java.util.ArrayList;

/** Diese Klasse repräsentiert ein ASCII-Bild, 
* es speichert die Zeichen des Bildes und bietet entsprechende Methoden zur Modifikation 
* und zur Abfrage von Eigenschaften, wie beispielsweise Höhe und Breite.
* In dieser Runde werden zunächst alle das Bild verändernden Operationen aus der Klasse AsciiImage ausgegliedert. 
* Jede einzelne Operation wird dabei in eine eigene Klasse gekapselt. 
*/

public class AsciiImage{
    private int width;
    private int height;
    private String charset;
    private char[][] image;

    public AsciiImage(int width, int height, String charset) throws IllegalArgumentException{
        if(height <= 0 || width <= 0){
            throw new IllegalArgumentException();
        }
        String check = new String();
        for(int i = 0; i < charset.length(); i++){
            if(check.indexOf(charset.charAt(i)) == -1){
                check += charset.charAt(i);
            }
        }
        if(charset.length() == 0 || check.length() != charset.length()){
            throw new IllegalArgumentException();
        }
        this.width = width;
        this.height = height;
        this.charset = charset;
        image = new char[height][width];
        clear();

    }
    public AsciiImage(AsciiImage img){
        this(img.width, img.height, img.charset);
        //deepcopy of array
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                this.image[y][x] = img.image[y][x];
            }
        }
    }

      private void clear(){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                image[y][x] = charset.charAt(charset.length()-1);
            }
        }
    }

    public String getCharset(){
        return charset;
    }

    public int getHeight(){
        return height;
    }

    public char getPixel(int x, int y) throws IndexOutOfBoundsException{
        if(boundCheck(x,y)){
            throw new IndexOutOfBoundsException();
        }
        return image[y][x];
    }

    public char getPixel(AsciiPoint p){
        int x = p.getX();
        int y = p.getY();
        return getPixel(x, y);
    }

    public ArrayList<AsciiPoint> getPointList(char c){
        ArrayList<AsciiPoint> pointlist = new ArrayList<AsciiPoint>();
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                if(image[y][x] == c){
                    AsciiPoint point = new AsciiPoint(x, y);
                    pointlist.add(point);
                }
            }
        }
     return pointlist;
     }

    public int getWidth(){
        return width;
    }

    public void setPixel(int x, int y, char c)throws IndexOutOfBoundsException{
        if(boundCheck(x,y) || charset.indexOf(c) == -1){
            throw new IndexOutOfBoundsException();
        }else {
            image[y][x] = c;
        }
    }

    public void setPixel(AsciiPoint p, char c){
        int x = p.getX();
        int y = p.getY();
        setPixel(x, y, c);
    }

    public String toString(){
        String ret = "";
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++) {
                ret += image[y][x];

            }
            ret += "\n";
        }

    return ret;
}

   /** public void addLine(String line, int y){
        if(line.length() == width){
            for(int x = 0; x < width; x++){
                setPixel(x, y, line.charAt(x));
            }
        }
    }*/

    public void replace(char oldChar, char newChar){
        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (image[y][x] == oldChar) {
                    image[y][x] = newChar;
                }
            }
        }
    }

    private boolean boundCheck(int x, int y){
        if(x < 0 || x >= width || y < 0 || y >= height) {
            return true;
        }else{
            return false;
        }
    }


}