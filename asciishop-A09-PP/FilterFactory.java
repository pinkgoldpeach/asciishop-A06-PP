import java.util.Scanner;

/** Diese Factory erzeugt MedianOperations und AverageOperations.
 * liest den nächsten String ein und gibt, je nach Parameter, 
 * eine neue MedianOperation (bei ‘median’) oder eine neue AverageOperation (bei ‘average’) zurück. 
 * Ist der Typ unbekannt, so wird eine FactoryExceptionn geworfen.
 */

public class FilterFactory implements Factory {

    public FilterFactory(){

    }

    public Operation create(Scanner scanner) throws FactoryException{
        String gener = "";
        BlockGenerator generator = getGenerator(3, "x");
        if(scanner.hasNextLine()){
            String allCommands = scanner.nextLine().trim();
            String[] commands = allCommands.split(" ");
            String filter = commands[0];
            int size = 3;
           /** debug
            * System.out.println(allCommands);
            System.out.println("length: "+commands.length);
            System.out.println("filter: "+filter);
            for(int i= 0; i < commands.length; i++){
                System.out.println("comm "+i+" "+commands[i]);
               
            }
            */

            
            try{
                switch(commands.length){
                    //!size && !generator
                    case 1:
                        return getOperation(filter, generator);
                    case 2:
                        //size && !generator
                        try{
                            size = Integer.parseInt(commands[1]);
                            generator = getGenerator(size, "x");
                            return getOperation(filter, generator);
                        //!size && generator
                        }catch(Exception e){
                            gener = commands[1];
                            generator = getGenerator(3, gener);
                            return getOperation(filter, generator);
                        }
                    case 3:
                        //size && generator
                        try{
                            size = Integer.parseInt(commands[1]);
                            gener = commands[2];
                            generator = getGenerator(size, gener);
                            return getOperation(filter, generator);
                        }catch(Exception e){
                            throw new FactoryException("INPUT MISMATCH");
                        }
                    }
            }catch(FactoryException f){
                throw new FactoryException("INPUT MISMATCH");
            }
        }
            
            throw new FactoryException("INPUT MISMATCH");
      
    }

    private BlockGenerator getGenerator(int size, String generator) throws FactoryException{
        if (generator.equals("x")) {
            return new XBlockGenerator(size);
        } else if (generator.equals("circular")) {
            return new CircularBlockGenerator(size);
            //break;
        } else if (generator.equals("replicate")) {
            return new ReplicateBlockGenerator(size);
            //break;
        } else if (generator.equals("symmetric")) {
            return new SymmetricBlockGenerator(size);
            //break;
        } else {
            throw new FactoryException("INPUT MISMATCH");
        }
    }

    private Operation getOperation(String filter, BlockGenerator generator) throws FactoryException{
        if(filter.equals("median")){
            return new MedianOperation(generator);
        }else if(filter.equals("average")){
            return new AverageOperation(generator);
        }else{
            throw new FactoryException("INPUT MISMATCH");
        }
    }
}
