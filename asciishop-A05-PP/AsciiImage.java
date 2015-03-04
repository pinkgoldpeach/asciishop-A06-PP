public class AsciiImage{
	
	int height, width;
	char[] [] image;
	
	/**height = rows
	* width = columns
	*/
	
	public AsciiImage(int width, int height){
		this.width = width;
		this.height = height;
		
		image = new char[height] [width];
		clear();
	}
	
	/**clears the image, returns only '.'
   * i is running through rows
   * j is running through columns
   */
	public void clear(){
		for(int i = 0; i < width;i++){
			for(int j = 0; j < height; j++){
				image[j][i] = '.';
			}
		}
	}
	
	/**draws a line from x1,y1 to x2,y2 with the char c
	* DDA Algorithm
	*/
	public void drawLine(int x1, int y1, int x2, int y2, char c){
		int dx;
		float dy;
		dx = x2 - x1;
		dy = y2 - y1;
		float m;
		float temp;
		
		if(dx >= 0 && Math.abs(dy) <= Math.abs(dx)){
			m = dy/dx;
			int x = x1, y;
			temp = y1;
			setPixel(y1, x1, c);
			while(x < x2){
				x++;
				temp += m;
				y = Math.round(temp);
				setPixel(y, x, c);
				
			}
		}else if(dx < 0 && Math.abs(dy) <= Math.abs(dx)){
			m = -dy/dx;
			int x = x1, y = y1;
			temp = y1;
			setPixel(y1, x1, c);
			while(x > x2){
				x--;
				temp += m;
				y = Math.round(temp);
				setPixel(y, x, c);
			}
			
		}else if(dy >= 0 && Math.abs(dy) > Math.abs(dx)){
			m = dx/dy;
			int y = y1, x;
			temp = x1;
			setPixel(y1, x1, c);
			while(y < y2){
				y++;
				temp += m;
				x = Math.round(temp);
				setPixel(y, x, c);
			}
		}else if(dy < 0 && Math.abs(dy) > Math.abs(dx)){
			m = -dx/dy;
			int y = y1, x;
			temp = x1;
			setPixel(y1, x1, c);
			while(y > y2){
				y--;
				temp += m;
				x = Math.round(temp);
				setPixel(y, x, c);
			}
		}
		
		
	}
	
	public int getHeight(){
		return height;
	}
	/**
   * Returns value of pixel on x and y
   * @param   x   x-cooardinate
   * @param   y   y-cooardinate
   * @return  char  character at this pointer
   */
	public char getPixel(int y, int x){
		char c = image [y] [x];
		return c;
	}
	
	public int getWidth(){
		return width;
	}
	
	/**
   * Looks for the char you want to replace and replaces it
   * @param   oldChar   char you want to replace
   * @param   newChar   char you want to place instead
   */
	public void replace(char oldChar, char newChar){
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				if(	image[y] [x] == oldChar){
					setPixel(y, x, newChar);
				}
			}
		}
	}
	/**
   * @param   x   x-cooardinate
   * @param   y   y-cooardinate
   * @param   c   char you want to set at this point
   */
	public void setPixel(int y, int x, char c){
		image [y] [x] = c;
	}
	
	public String toString(){
		String temp = new String();
		int counter = 0;
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				temp += image[i] [j];
				counter ++;
				if(counter% width == 0 && temp.length() != 0){
					temp += "\n";
				}
			}
		}
		
		return temp;
	}
	
	/** transposes matrix
	* switch height and width
	* x -> y, and y -> x
	* fill in the image
	*/
	public void transpose(){
		char[] [] tempA = new char[width] [height];
		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				tempA[x] [y] = image[y] [x];
			}
		}
		int tempWidth = height;
		height = width;
		width = tempWidth;
		image = tempA;
	}
	/**floodfill algorithm
	* replaces every neighbour of an original char with c, if it is the
	* same char as the original char
	*/
	public void fill(int x, int y, char c){
		char oldC = getPixel(y, x);
		if(oldC != c){
			setPixel(y, x, c);
			if(y-1 >= 0 && getPixel(y-1, x) == oldC){
				fill(x, y-1, c);
			}
			if(y+1 < height && getPixel(y+1, x) == oldC){
				fill(x, y+1, c);
			}
			if(x-1 >= 0 && getPixel(y, x-1) == oldC){
				fill(x-1, y, c);
			}
			if(x+1 < width && getPixel(y, x+1) == oldC){
				fill(x+1, y, c);
			}
		}
	}
	/**loads  char into the array in the end there's an image in the object AsciiImage
	* y = columns, x = rows;
	*/
	public void load(int y, int x, char charAtXY){
		image[y] [x] = charAtXY;
		
	}
	
}
