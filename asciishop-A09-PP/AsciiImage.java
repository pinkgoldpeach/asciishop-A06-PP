import java.util.ArrayList;

public class AsciiImage {

    private int height, width;
    private String charset;
    private char[][] image;

    public AsciiImage(int width, int height, String charset){
        if(width < 0 || height < 0 || !checkCharset(charset)){
            throw new IllegalArgumentException();
        }else {
            this.width = width;
            this.height = height;
            this.charset = charset;
            image = new char[this.height][this.width];
            for(int y = 0; y < this.height; y++){
                for(int x = 0; x < width; x++){
                    image[y][x] = charset.charAt(charset.length()-1);
                }
            }
        }
    }

    public AsciiImage(AsciiImage img){
        this(img.getWidth(), img.getHeight(), img.getCharset());
        image = new char[this.height][this.width];
        for(int y = 0; y < this.height; y++){
            for(int x = 0; x < width; x++){
                image[y][x] = img.getPixel(x, y);
            }
        }
    }

    public String getCharset(){
        return charset;
    }

    public int getHeight(){
        return height;
    }

    public char getPixel(int x, int y){
        return image[y][x];
    }

    public char getPixel(AsciiPoint p){
        int x = p.getX();
        int y = p.getY();
        return getPixel(x, y);
    }

    public ArrayList<AsciiPoint> getPointList(char c){
        ArrayList<AsciiPoint> points = new ArrayList<AsciiPoint>();

        for(int y = 0; y < this.height; y++){
            for(int x = 0; x < width; x++){
                if(image[y][x] == c){
                    points.add(new AsciiPoint(x, y));
                }
            }
        }
        return points;
    }

    public int getWidth(){
        return width;
    }

    public void setPixel(int x, int y, char c){
        image[y][x] = c;
    }

    public void setPixel(AsciiPoint p, char c){
        int x = p.getX();
        int y = p.getY();
        setPixel(x, y, c);
    }

    public String toString(){
        String ret = "";
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                ret += image[y][x];
                if(x != 0 && (x %(width-1)) == 0){
                    ret += "\n";
                }
            }
        }
        return ret;
    }

    public boolean checkCharset(String charset){
        String check = "";
        boolean ok = true;
        for(int i = 0; i < charset.length(); i++){
            if(check.indexOf(charset.charAt(i)) == -1){
                check += charset.charAt(i);
            }
            if(check.length() == charset.length()){
                ok = true;
            }else{
                ok = false;
            }
        }
        return ok;
    }
    public boolean pixelCheck(int x, int y){
        return (x >= 0 && x <width && y >= 0 && y < height);
    }
}
