/**Diese Klasse implementiert einen Stack (vgl. Stapelspeicher), 
* der seine Größe dynamisch anpasst. Es kann eine beliebige Anzahl an AsciiImage-Objekten gespeichert werden, 
* wobei der Zugriff immer nur auf das oberste Element möglich ist. 
* Diese Implementierung nutzt intern die Klasse AsciiStackNode um mehrere Bilder in einer Liste zu verketten.
*/



public class AsciiStack{

    private AsciiStackNode top;
    private static int counter = 0;

    public AsciiStack(){

    }

    public boolean empty(){
        return top == null;
    }
/** @param ret = return image
* @param top = next image
* image on top of tack will be returned and removed
*/
    public AsciiImage pop(){
       AsciiImage ret = null;
        if(top != null) {
            ret = top.getImage();
            top = top.getNext();
            counter--;
        }
        return ret;
    }
/** image on top of stack will be returned, but not removed
*/
    public AsciiImage peek(){

        return top.getImage();
    }
/** pushes an image on top of the stack
*/
    public void push(AsciiImage img){
        AsciiStackNode node = new AsciiStackNode(img, top);
        top = node;
        counter++;
    }
/** push = counter++ 
* pop = counter--
*/
    public int size(){
        return counter;
    }
}