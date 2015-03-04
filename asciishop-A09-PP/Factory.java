import java.util.Scanner;

/** Dieses Interface wird von allen Factories implementiert 
* und definiert die Schnittstelle über die eine Operation bezogen werden kann. 
* Die Aufgabe der Factory ist es bei Bedarf Eingaben einzulesen und dann eine neue Operation zu erzeugen.
*/

public interface Factory {
    public Operation create(Scanner scanner) throws FactoryException;



}
