import java.util.Arrays;

public class ReplicateBlockGenerator extends BlockGenerator{
	
	public ReplicateBlockGenerator(int size){
		super(size);
	}

	/**
	 * die am Rand befindlichen Pixel werden über den Bildrand hinweg fortgesetzt.
	 * die out-of-bound-pixel sind die pixel, welche als letzte in ihrer zeile vorkommen (x out of bound)
	 * welche als letzte in ihrer reihe vorkommen (y out of bound && y & x out of bound)
	 */

	public int checkPixel(AsciiImage image, int x, int y){
       	
       	try{
       		char c = image.getPixel(x, y);
            return image.getCharset().lastIndexOf(c);
       	}catch(IndexOutOfBoundsException i){
        	if(x >= image.getWidth()){
        		x= image.getWidth()-1;
            }else if(x < 0){
				x = 0;
			}
			if(y >= image.getHeight()) {
				y = image.getHeight()-1;
            }else if(y < 0){
				y = 0;
			}
			char c = image.getPixel(x,y);
			return image.getCharset().lastIndexOf(c);
        }

    }
}