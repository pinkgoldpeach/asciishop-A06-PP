import java.util.Scanner;

/** "INPUT MISMATCH" 
* 1. Befehl nicht create, nicht genug Parameter vom richtigen Typ, Beim load-Befehl fehlt eof, nach filter steht nicht `median'.
* "OPERATION FAILED"
* Zwischen load-Befehl und eof sind mehr oder weniger Zeilen als das Bild hat, 
* eine Bildzeile hat mehr oder weniger Zeichen als das Bild,
* ein Zeichen der zu ladenden Daten ist nicht Teil des Zeichensatzes,
* einer der Parameter eines Befehls ist ungültig, Index außerhalb der Bildgrenzen
* "UNKNOWN COMMAND" 
* Nach create folgt ein unbekannter Befehl oder erneut der Befehl create.
*/


public class AsciiShop {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AsciiImage image = null;
        String command = new String();
        String charset = new String();
        int width = 0, height = 0;
        boolean executeOp = true;
        AsciiStack stack = new AsciiStack();
        try{
            if(sc.hasNext() && sc.next().equals("create")) {
                if(sc.hasNextInt()) {
                    width = sc.nextInt();
                }else{
                    throw new Exception("INPUT MISMATCH");
                }
                if(sc.hasNextInt()){
                    height = sc.nextInt();
                }else{
                    throw new Exception("INPUT MISMATCH");
                }if(sc.hasNext()){
                    charset = sc.next();
                    image = new AsciiImage(width, height, charset);

                    while(sc.hasNext()){
                        command = sc.next();
                        Factory factory = null;
                            if(command.equals("clear")){
                                factory = new ClearFactory();
                            }else if(command.equals("load")){
                                factory = new LoadFactory();
                            }else if(command.equals("print")){
                                System.out.println(image.toString());
                                executeOp = false;
                            }else if(command.equals("replace")){
                                factory = new ReplaceFactory();
                            }else if(command.equals("filter")){
                                factory = new FilterFactory();
                            }else if(command.equals("binary")){
                                factory = new BinaryFactory();
                            }else if(command.equals("histogram")){
                                Histogram hist = new Histogram();
                                AsciiImage histogram = hist.getHistogram(image);
                                System.out.println(histogram);
                                executeOp = false;
                            }else if(command.equals("undo")){
                                if(!stack.empty()) {
                                    image = stack.pop();
                                }else{
                                    System.out.println("STACK EMPTY");
                                }
                                executeOp = false;
                            }else{
                                throw new Exception("UNKNOWN COMMAND");
                            }

                        if(executeOp == true) {
                            stack.push(new AsciiImage(image));
                            Operation op = factory.create(sc);
                            image = op.execute(image);
                        }

                        executeOp = true;


                    }
                }else{
                    throw new Exception("error");
                }

            }
        }catch(Exception e){
            //e.printStackTrace();
            System.out.println(e.getMessage());
            }
    }
}
