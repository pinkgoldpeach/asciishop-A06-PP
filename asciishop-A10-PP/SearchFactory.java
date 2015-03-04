import java.util.Scanner;

public class SearchFactory implements Factory{

    MetricSet<AsciiImage> saved;
/** erzeugt eine neue SearchFactory. 
 * saved ist eine Referenz auf ein MetricSet, in dem sich die gespeicherten Bilder befinden.
 */
    public SearchFactory(MetricSet<AsciiImage> saved){
        this.saved = saved;
    }
/** Erzeugt eine neue SearchOperation.
 * Dazu wird zunächst mit dem angegebenen Scanner ein String eingelesen,
 * der angibt, welche Metrik benutzt werden soll ("pixelcount" oder "uniquechars"). 
 * Kann dieser String nicht eingelesen werden, oder ist der eingelesene String unbekannt, 
 * wird eine FactoryException geworfen.
 */

    public Operation create(Scanner scanner) throws FactoryException{
        if(scanner.hasNext()){
            String metric = scanner.next();
            if(metric.equals("pixelcount")){
                return new SearchOperation(saved, new PixelCountMetric());
            }else if(metric.equals("uniquechars")){
                return new SearchOperation(saved, new UniqueCharsMetric());
            }
        }
        throw new FactoryException("INPUT MISMATCH");
    }
}
