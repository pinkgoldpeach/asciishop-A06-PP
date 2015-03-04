public class CircularBlockGenerator extends BlockGenerator{

	public CircularBlockGenerator(int size){
		super(size);
	}

	/**
	 * Hierbei wird quasi das Bild mehrfach nebeneinander gelegt, 
	 * direkt an die letzte Spalte schließt die erste Spalte an und analog für die Zeilen.
	 */

	public int checkPixel(AsciiImage image, int x, int y){

		/**
		 * in boundaries
		 */

		try{
			char c = image.getPixel(x, y);
			return image.getCharset().lastIndexOf(c);

			/**
			 * out of boundaries
			 * (-1 + image width)%image width = last pixel of the image
			 */
		}catch(IndexOutOfBoundsException i){
			x = (x+image.getWidth())%image.getWidth();
			y = (y+image.getHeight())%image.getHeight();
			char c = image.getPixel(x, y);
			return image.getCharset().lastIndexOf(c);
		}
	}
}