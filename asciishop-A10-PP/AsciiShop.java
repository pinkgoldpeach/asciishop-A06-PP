import java.util.HashMap;
import java.util.Scanner;

public class AsciiShop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int width, height;
        String command = new String();
        AsciiImage image = null;
        AsciiStack stack = new AsciiStack();
        Operation op;
        MetricSet<AsciiImage> set = new MetricSet<AsciiImage>();

        HashMap<String, Factory> commands = new HashMap<String, Factory>();
        commands.put("create", new CreateFactory());
        commands.put("load", new LoadFactory());
        commands.put("clear", new ClearFactory());
        commands.put("replace", new ReplaceFactory());
        commands.put("filter", new FilterFactory());
        commands.put("binary", new BinaryFactory());
        try {
            if (sc.hasNext() && sc.next().equals("create")) {
                op = commands.get("create").create(sc);
                image = op.execute(image);

                while(sc.hasNext()){
                    command = sc.next();
                    if(commands.containsKey(command)){
                        stack.push(image);
                        image = commands.get(command).create(sc).execute(image);
                    }else if(command.equals("undo")){
                        if(stack.empty()){
                            System.out.println("STACK EMPTY");
                        }else {
                            image = stack.pop();
                        }
                    }else if(command.equals("print")){
                        System.out.println(image.toString());
                    }else if(command.equals("save")){
                       image = new SaveFactory(set).create(sc).execute(image);
                    }else if(command.equals("printsaved")){
                        if(set.isEmpty()){
                            System.out.println("NO SAVED IMAGES");
                        }else{
                            for(AsciiImage img : set){
                                System.out.println(img);
                            }
                        }
                    }else if(command.equals("search")){
                        stack.push(image);
                        image = new SearchFactory(set).create(sc).execute(image);
                    }else{
                        throw new Exception("UNKNOWN COMMAND");
                    }

                }


            }
        }catch(Exception e){
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
