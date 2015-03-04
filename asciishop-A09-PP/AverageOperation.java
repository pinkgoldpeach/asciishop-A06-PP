/** Diese Klasse glättet ein Bild mit einem 3x3-Mittelwertfilter.
 */
public class AverageOperation extends FilterOperation {
    
    public AverageOperation(BlockGenerator generator){
        super(generator);
    }
/** geerbt von FilterOperation
 */
    public AsciiImage execute(AsciiImage img) throws OperationException {
        return super.execute(img);
    }
/** führt mit dem übergebenen Block den Mittelwertfilter aus.
 * Dafür wird das arithmetische Mittel der Helligkeitswerte bestimmt. 
 * Das Ergebnis wird mathematisch gerundet und als Ergebnis für diesen Block zurückgegeben.
 */
    public int filter(int[] values){
        float sum = 0;
        float mean = 0;
        for(int i = 0; i < values.length; i++){
            sum += (float)values[i];
        }
        mean = sum/values.length;
        return Math.round(mean);

    }
}
