public class SymmetricBlockGenerator extends BlockGenerator{
	
	public SymmetricBlockGenerator(int size){
		super(size);
	}

	/**
	 * Bei dieser Randbehandlung wird das Bild entlang der Kanten gespiegelt.
	 * die out-of-bound-pixel, sind die pixel des gespiegelten bildes.
	 */
	public int checkPixel(AsciiImage image, int x, int y){
		try{
			char c = image.getPixel(x, y);
			return image.getCharset().lastIndexOf(c);

		}catch(IndexOutOfBoundsException i){
			if(x >= image.getWidth()){
				x = Math.abs(x - 2*image.getWidth() + 1);
			}else if(x < 0){
				x = Math.abs(x+1);
			}
			if(y >= image.getHeight()){
				y = Math.abs(y-2*image.getHeight()+1);
			}else if(y < 0){
				y = Math.abs(y+1);
			}
			char c = image.getPixel(x, y);
			return image.getCharset().lastIndexOf(c);
		}
	}
}