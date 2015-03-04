/** Diese Klasse repräsentiert einen Punkt, spezifiziert durch zwei ganzzahlige Koordinaten.
* Diese Klasse ist unveränderlich (immutable), sprich die Koordinaten sollen nachträglich nicht mehr veränderbar sein.
*/

public class AsciiPoint{

    private int x, y;

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
        return "("+x+","+y+")";
    }
}