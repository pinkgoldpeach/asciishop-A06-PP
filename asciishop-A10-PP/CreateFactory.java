import java.util.Scanner;

/** Diese Factory erzeugt CreateOperation.
 * liest mit Hilfe des Scanners Breite und Höhe und einen String ein
 * und gibt eine damit initialisierte neue CreateOperation zurück.
 * Tritt beim Einlesen ein Fehler (zu wenig Parameter, falsche Parameter), so wird eine FactoryException geworfen.
 */

public class CreateFactory implements Factory{

    public CreateFactory(){

    }

    public Operation create(Scanner scanner) throws FactoryException{
        if(scanner.hasNextInt()){
            int width = scanner.nextInt();
            if(width > 0 && scanner.hasNextInt()){
                int height = scanner.nextInt();
                if(height > 0 && scanner.hasNext()){
                    String charset = scanner.next();
                    return new CreateOperation(width, height, charset);
                }
            }
        }
        throw new FactoryException("INPUT MISMATCH");
    }


}
