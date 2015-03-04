import java.util.Scanner;
/**Diese Daten werden einem Objekt der Klasse AsciiImage zeilenweise hinzugefügt.
* Nach dem Einlesen des Bildes können mehrere Befehle folgen,
* um das Bild vertikal zu flippen oder transponieren, bzw. die Anzahl an unterschiedlichen vorkommenden Zeichen zu bestimmen. 
* Zusätzlich soll auch der fill-Befehl benutzt werden können. 
* Abschließend wird das Endergebnis ausgegeben.
*/


public class AsciiShop{

    public static void main(String[] args){
	Scanner sc = new Scanner(System.in);
	String command = "", line = "";
	boolean mismatch = false, failed = false;
	AsciiImage image = new AsciiImage();
	
	/**read followed by int x
	* save an image with x rows
	*/
	
	if(sc.hasNext() && sc.next().equals("read") && sc.hasNextInt()){
		int lines = sc.nextInt();
		while(sc.hasNext()){
		    if(image.getHeight() < lines){
			image.addLine(sc.next());
		    }
		    
		    if(image.getHeight() == lines && sc.hasNext()){
			command = sc.next();
			if(command.equals("uniqueChars")){
			    System.out.println(image.getUniqueChars());
			}else if(command.equals("flip-v")){
			    image.flipV();
			}else if(command.equals("transpose")){
			    image.transpose();
			}else if(command.equals("symmetric-h")){
			    System.out.println(image.isSymmetricH());
			}else if(command.equals("fill") && sc.hasNextInt()){
			    int x, y;
			    x = sc.nextInt();
			    if(sc.hasNextInt()){
				y = sc.nextInt();
			    	if(sc.hasNext()){
				    char c = sc.next().charAt(0);
				    if(x >= 0 && x < image.getWidth()){
					if(y >= 0 && y < image.getHeight()){
					    image.fill(x, y, c);
					}else{
					    failed = true;
					}
				    }else{
					failed = true;
				    }
			    }else{
				mismatch = true;
			    }
			}else{
			    mismatch = true;
			}
			
		    }else{
			mismatch = true;
		    }
		    
		}
	    }
	}else{
	    mismatch = true;
	}
	if(mismatch == true){
	    System.out.println("INPUT MISMATCH");
	}else if(failed == true){
	    System.out.println("OPERATION FAILED");
	}else{
	    System.out.println(image);
	    System.out.println(image.getWidth()+ " "+  image.getHeight());
	}
    }
}