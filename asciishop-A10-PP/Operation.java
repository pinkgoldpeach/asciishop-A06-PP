/** Dieses Interface wird von allen Operationen implementiert und definiert eine Methode, 
 * die zum Ausführen der Operation dient.
 * führt die Operation aus und gibt das Ergebnis als neues AsciiImage zurück. 
 * Das übergebene AsciiImage wird von der Methode nicht verändert.
 * Mögliche Parameter der Operation müssen im Konstruktor übergeben werden. 
 * Sollte beim Ausführen der Operation ein Fehler auftreten, so soll eine OperationException geworfen werden.
 */
public interface Operation {
    AsciiImage execute(AsciiImage image) throws OperationException;
}
