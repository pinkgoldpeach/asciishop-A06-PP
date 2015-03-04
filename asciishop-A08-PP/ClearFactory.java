import java.util.Scanner;

/** Diese Factory erzeugt ClearOperations.
 */
public class ClearFactory implements Factory {
    public ClearFactory(){

    }
    public Operation create(Scanner scanner){

        return new ClearOperation();
    }

}
