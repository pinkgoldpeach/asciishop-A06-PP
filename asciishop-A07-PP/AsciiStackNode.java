/**Diese Klasse implementiert einen Knoten des Stacks.
*/
public class AsciiStackNode {

    private AsciiImage image;
    private AsciiStackNode next;
    private static int counter = 0;


    public AsciiStackNode(AsciiImage image, AsciiStackNode next){
        this.image = image;
        this.next = next;
        counter++;
    }
/**liefert das vom Knoten referenzierte AsciiImage zurück.
*/
    public AsciiImage getImage(){
        return image;
    }
/** liefert eine Referenz auf den nächsten Knoten zurück.
*/
    public AsciiStackNode getNext(){
        return next;
    }
/** liefert die Anzahl der Knoten in der von diesem Knoten referenzierten Restliste plus eins (für diesen Knoten)
* counter = static
*/
    public int size(){
        return counter;
    }
}
