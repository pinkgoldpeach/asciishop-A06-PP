import java.util.Scanner;

/** Diese Factory erzeugt ClearOperations.
 */

public class ClearFactory implements Factory {

    public Operation create(Scanner scanner) throws FactoryException{

        return new ClearOperation();
    }
}
