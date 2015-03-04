import java.util.Scanner;

public class AsciiShop{

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String command = new String();
        int width, height;
        AsciiImage image = null;
        AsciiStack stack = new AsciiStack();
        try {
            if (sc.hasNext() && sc.next().equals("create") && sc.hasNextInt()) {
                width = sc.nextInt();
                if (sc.hasNextInt()) {
                    height = sc.nextInt();
                    if(sc.hasNext()){
                        String charset = sc.next();
                        image = new AsciiImage(width, height, charset);

                        while (sc.hasNext()) {
                            command = sc.next();

                            if (command.equals("load") && sc.hasNext()) {
                                stack.push(image);
                                String eof = sc.next();
                                String line = new String(), check = "";
                                int lines = 0;
                                boolean read = true;
                                while (sc.hasNext() && read) {
                                    check = sc.next();
                                    if (check.equals(eof)) {
                                        read = false;
                                    } else if (lines == height && !check.equals(eof)) {
                                        throw new Exception("INPUT MISMATCH");
                                    } else if (lines != height && check.equals(eof)) {
                                        throw new OperationException("OPERATION FAILED");
                                    } else {
                                        if (check.length() == width) {
                                            line += check + "\n";
                                            lines++;
                                        } else {
                                            throw new OperationException("OPERATION FAILED");
                                        }
                                    }
                                }
                                LoadOperation load = new LoadOperation(line);
                                image = load.execute(image);


                            } else if (command.equals("print")) {
                                System.out.println(image.toString());
                            } else if (command.equals("replace") && sc.hasNext()) {
                                char oldChar = sc.next().charAt(0);
                                if (sc.hasNext()) {
                                    stack.push(image);
                                    char newChar = sc.next().charAt(0);
                                    ReplaceOperation replace = new ReplaceOperation(oldChar, newChar);
                                    image = replace.execute(image);

                                }
                            } else if (command.equals("clear")) {
                                stack.push(image);
                                ClearOperation clear = new ClearOperation();
                                image = clear.execute(image);
                            } else if (command.equals("filter") && sc.hasNext()) {
                                String median = sc.next();
                                if (median.equals("median")) {
                                    stack.push(image);
                                    MedianOperation med = new MedianOperation();
                                    image = med.execute(image);
                                } else {
                                    throw new Exception("INPUT MISMATCH");
                                }
                            } else if (command.equals("undo")) {

                                if (stack.empty()) {
                                    System.out.println("STACK EMPTY");
                                } else {
                                    image = stack.pop();
                                }
                            } else {
                                throw new Exception("UNKNOWN COMMAND");
                            }

                        }
                    }else{
                        throw new Exception("INPUT MISMATCH");
                    }

                }else{
                    throw new Exception("INPUT MISMATCH");
                }

            }else{
                throw new Exception("INPUT MISMATCH");
            }
        }

        catch(OperationException e){
            System.out.println(e.getMessage());
            return;
        }

        catch (IllegalArgumentException i) {
            System.out.println("INPUT MISMATCH");
            return;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }



    }

}
