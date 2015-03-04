import java.util.Collection;
import java.util.LinkedHashSet;

/** Diese generische Klasse implementiert ein spezielles Set auf der Basis von LinkedHashSet 
 * mit der Besonderheit, dass in dem MetricSet mit Hilfe einer Metrik nach Objekten gesucht werden kann,
 * die einem spezifizierten Objekt ähnlich sind. 
 * Beachten Sie dazu das Interface Metric. 
 * Die Klasse soll als Erweiterung der Klasse LinkedHashSet<E> definiert werden.
 *  Ein LinkedHashSet<E> ist Untertyp von HashSet<E> mit der Besonderheit, 
 * dass die Einfügereihenfolge der Elemente erhalten bleibt. 
 * Es gibt Methoden, die von der Klasse LinkedHashSet geerbt werden und hier nicht explizit angegeben sind.
 */

public class MetricSet<E> extends LinkedHashSet<E> {
    private LinkedHashSet<E> metricset;
    public MetricSet(){
        metricset = new LinkedHashSet<E>();
    }

    // initialisiert das MetricSet mit den Elementen aus c.
    public MetricSet(Collection<? extends E> c){
        this();
        for(E e : c){
            metricset.add(e);
        }
    }
/** liefert ein neues MetricSet zurück, in dem nur die Elemente enthalten sind, 
 * die die minimale Distanz zum spezifizierten Element e haben. 
 * Das kann auch nur ein Element sein. m ist die Metrik, die als Distanzmaß benutzt werden soll.
 */
    public MetricSet<E> search(E e, Metric<? super E> m){
        MetricSet<E> set = new MetricSet<E>();
        MetricSet<E> original = this;
        int currentDistance, minDistance = Integer.MAX_VALUE;

        for(E element : original){
            currentDistance = m.distance(e, element);
            if(currentDistance == minDistance){
                set.add(element);
            }else if(currentDistance < minDistance){
                set.clear();
                minDistance = currentDistance;
                set.add(element);
            }
        }
        return set;
    }

  /**  public boolean add(E e){
        boolean added = true;
        for(E element : metricset){
            if(e == null){
                if(element == null){
                    added = false;
                }else{
                    metricset.add(e);
                    added = true;
                }
            }else{
                if(e.equals(element)){
                    added = false;
                }else{
                    metricset.add(e);
                    added = true;
                }
            }
        }
        return added;
    }*/
}
