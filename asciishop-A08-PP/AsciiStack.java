import java.util.Stack;

/**Diese Klasse implementiert einen Stack , der seine Größe dynamisch anpasst. 
* Er kann eine beliebige Anzahl an AsciiImage-Objekten speichern, 
* wobei der Zugriff immer nur auf das oberste Element möglich ist.
*/
public class AsciiStack {
    private Stack<AsciiImage> stack;

    public AsciiStack(){
        stack = new Stack<AsciiImage>();
    }

    public boolean empty(){
        return stack.empty();
    }
    public AsciiImage pop(){
        return stack.pop();
    }
    public AsciiImage peek(){
        return stack.peek();
    }
    public void push(AsciiImage img){
        stack.push(img);
    }
    public int size(){
        return stack.size();
    }
}
