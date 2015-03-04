/**
 * Diese Klasse erweitert Exception und wird zum Behandeln aller Fehlerfälle, 
 * die in einer Factory, also beim Einlesen von Parametern oder dem Erzeugen eines Befehls, auftreten, eingesetzt.
 */
public class FactoryException extends Exception{
    public FactoryException(){
        super();
    }

    public FactoryException(String message){
        super(message);
    }
}

