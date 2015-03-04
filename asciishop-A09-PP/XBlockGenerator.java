import java.util.Arrays;

public class XBlockGenerator extends BlockGenerator{

	public XBlockGenerator(int size){
		super(size);
	}

	public XBlockGenerator(){
		this(3);
	}
	/**
	 * gibt für alle out-of-bound-pixel den wert '.' zurück.
	 */

	public int checkPixel(AsciiImage image, int x, int y){
        try{
            char c = image.getPixel(x, y);
            return image.getCharset().lastIndexOf(c);

        }catch(IndexOutOfBoundsException i){
            return image.getCharset().length()-1;
        }
    }
}