public class AsciiImage{


  
  int height;
  int width;
  String image;
  
  public AsciiImage(){
      height = 0;
      width = 0;
      image = new String();
  }
  /**adds a line to the image
	* if first line < 1 return false
	* every line has to have the same length
	* returns true, if the line has same length as the width of the image
	*/
  public boolean addLine(String line){
      boolean added = true;
      if(height == 0 && line.length() > 0){
	  width = line.length();
	  image += line;
	  height++;
      }else if(height > 0){
	  if(line.length() == width){
	      image += line;
	      height++;
	  }else{
	      added = false;
	  }
      }else{
	  added = false;
      }
      return added;
  }
  
  public int getWidth(){
      return width;
  }
  
  public int getHeight(){
      return height;
  }
  
  
  public String toString(){
      String temp = "";
      int counter = 0;
      for(int i = 0; i < image.length(); i++){
	 
	  if(i != 0 && i%width ==0){
	      temp += "\n";
	  }
	   temp += image.charAt(i);
      }
      return temp;
  }
  
  public int getUniqueChars(){
      String compare = "";
      for(int i = 0; i < image.length(); i++){
	    if(compare.indexOf(image.charAt(i)) == -1){
		compare += image.charAt(i);
	    }
      }
      return compare.length();
  }
  /** returns vertically flipped image
	* last line --> first line, second line --> next to last one
	* add substring in reverse order to array
	* substring(lasst char - width, last char)
	*/
  public void flipV(){
      String flip = "";
      for(int i = (image.length()-width); i >= 0; i-=width){
	  flip += image.substring(i, i+width);
      }
      image = flip;
  }
  /** transposes matrix
    * switch height and width
    * x -> y, and y -> x
    * fill in the image
    */
  public void transpose(){
      String transposed ="";
      int temp;
      for(int j = 0; j < width; j++){
	  for(int i = 0; i <height; i++){
	      transposed += getPixel(j, i); 
	  }
      }
      image = transposed;
      temp = height;
      height = width;
      width = temp;
  }
  
  public void fill(int x, int y, char c){
      int pos = position(x, y);
      char original = getPixel(x, y);
      
      if(pos >= 0 && pos < image.length()){
	  setPixel(x, y, c);
      
	  if(position((x-1),y) >= 0 && getPixel((x-1), y) == original){
	    fill((x-1), y, c);
	  }
	  if(position((x+1), y) < image.length() && getPixel((x+1), y)== original){
	    fill((x+1), y, c);
	  }
	  if(position(x, (y-1)) >=0 && getPixel(x, (y-1)) == original){
	    fill(x, (y-1), c);
	   }
	  if(position(x, (y+1)) < image.length() && getPixel(x, (y+1)) == original){
	    fill(x, (y+1), c);
	  }
      }
      
  }
  //return true, iff image is a palindrom, symmetric
  public boolean isSymmetricH(){
      boolean symmetric = false;
      String check ="", check2 = "";
      int counter = 1, from = 0;
      
      while(counter <= height){
	  check = image.substring(from, width*counter);
	  check2 = "";
	  from = (width*counter);
	  counter++;
	  
	      for(int j = (width-1); j >= 0; j--){
		  check2 += check.charAt(j);
		  }
		  if(check.equals(check2)){
		      symmetric = true;
		  }else{
		      symmetric = false;
		      return symmetric;
		  }
	      
	  
      }
      
      return symmetric;
  }
  	/**
   * Returns value of pixel on x and y
   * @param   x   x-cooardinate
   * @param   y   y-cooardinate
   * @return  char  character at this pointer
   */
  private char getPixel(int x, int y){
      
      return image.charAt(x+(y*width));
      
  }
  	/**
   * @param   x   x-cooardinate
   * @param   y   y-cooardinate
   * @param   c   char you want to set at this point
   */
  private void setPixel(int x, int y, char c){
      String temp ="";
      int z = (x+(y*width));
      temp += image.substring(0, z);
      temp += c;
      temp += image.substring((z+1));
      image = temp;
  }
  private int position(int x, int y){
	int position = x + (width * y);
	return position;
  }
}