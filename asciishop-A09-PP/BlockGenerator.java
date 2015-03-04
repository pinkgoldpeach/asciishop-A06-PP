import java.util.Arrays;


public abstract class BlockGenerator{
	
	protected int size;

	public BlockGenerator(int size){
		this.size = size;
	}

	public int getSize(){
		return size*size;
	}
	/**
	 * gibt die einzelnen Pixel des zu Blocks an checkPixel um zu klären welcher Pixel an die Stelle gehört.
	 * in den einzelnen Generator-klassen wird besonders auf ide out-of-boundary Pixel eingegangen.
	 * checkPixel gibt einen int-wert zurück, den wert, den das pixel im charset annimmt.
	 * getBlock gibt ein int[] mit allen Pixelwerten zurück an execute von Filteroperation,
	 * wo dann mithilfe der filter methode, das pixel gewählt wird, welches an der stelle x,y eingesetzt wird.
	 */
	public int[] getBlock(AsciiImage img, int x, int y){

		AsciiImage image = new AsciiImage(img);
        int [] pixels = new int[size*size];
        int counter = 0;

        for (int m = (y + size/2); m >= (y - size/2); m--) {
        	for(int n = (x+size/2); n >= (x-size/2); n--){
                pixels[counter++] = checkPixel(img, n, m);
            }
        }
        counter = 0;
        Arrays.sort(pixels);
        return pixels;


	}
	public abstract int checkPixel(AsciiImage image, int x, int y);
}