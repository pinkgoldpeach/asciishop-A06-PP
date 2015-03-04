/** Diese Klasse erweitert Exception und wird zum Behandeln aller Fehlerfälle, 
 * die beim Ausführen von Operationen auftreten, eingesetzt.
 */
public class OperationException extends Exception {
    public OperationException(){
        super();
    }

    public OperationException(String message){
        super(message);
    }
}
