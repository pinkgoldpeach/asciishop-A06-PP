import java.util.ArrayList;

public class AsciiImage{
    
    int width;
    int height;
    char[][] image;
    
    /**height = rows
    * width = columns
    */
    
    public AsciiImage(int height, int width){
	
	this.width = width;
	this.height = height;
	image = new char[height][width];
	clear();
    
    }
    /**copy-constructor, copies first object and makes another one
    * it's important that there's no refernce between the two objects
    */
    public AsciiImage(AsciiImage img){
	this.height = img.getHeight();
	this.width = img.getWidth();
	
	this.image = new char[height][width];
	getImage(img.image);
    }
   /**clears the image, returns only '.'
   * i is running through rows
   * j is running through columns
   */
    public void clear(){
	for(int i = 0; i < height; i++){
	  for(int j= 0; j < width; j++){ 
	   
	      image[i][j] = '.';
	  }
	}
    }
    /**loads an image into the object AsciiImage
    * length = width = columns, size = height=rows;
    */
    public void load(char charAt, int size, int length){		
	image[size][length] = charAt;	

    }
   /**draws lines
   * start at x0,y0 draw line with char c till endpoint x1,y1
   * DDA Algorithm
   */
    public void drawLine(int x0, int y0, int x1, int y1, char c){
	image[y0][x0] = c;
	double dx = x1 - x0;
	double dy = y1 - y0;
	int y = y0;
	int x = x0;
	float ty = y0, tx=x0;
	if(Math.abs(dy) <= Math.abs(dx) && dx >=0){
	  double ky = dy/dx;
	  while(x < x1){
	    x++;
	    ty += ky;
	    y = Math.round(ty);
	    setPixel(x, y, c);
	  }
	}else if(Math.abs(dy) <= Math.abs(dx) && dx <0){
	  double ky = dy/dx;
	  while(x > x1){
	    x--;
	    ty -= ky;
	    y = Math.round(ty);
	    setPixel(x, y, c);
	  }
	}else if(Math.abs(dy) > Math.abs(dx) && dy >= 0){
	  double kx = dx/dy;
	  while(y < y1){
	    y++;
	    tx += kx;
	    x = Math.round(tx);
	    setPixel(x, y, c);
	  }
	}else if(Math.abs(dy) > Math.abs(dx) && dy < 0){
	  double kx = dx/dy;
	  while(y > y1){
	  y--;
	  tx -= kx;
	  x = Math.round(tx);
	  setPixel(x, y, c);
	}
      }
    }
    
    /**determines center of the char c
    */
    public AsciiPoint getCentroid(char c){
    
	char temp;
	int  x=0, y=0;
	double counter=0;
	for(int i = 0; i < height; i++){
	    for(int j = 0; j < width; j++){
		temp = image[i][j];
		if(temp == c){
		  x += j;
		  y += i;
		   counter++;
		}
	    }
	}
	if(counter != 0){
	    x = (int)Math.round(x/counter);
	    y = (int)Math.round(y/counter);
	    AsciiPoint aP = new AsciiPoint(x, y);
    
	    return aP;
	}else{
	    return null;
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
    public char getPixel(int x, int y){
	char pixel = image[y][x];
	return pixel;
    }
    /**
   * @param   point   Asciipoint with x and y cooardinates
   * @return  char  character at this point
  */
    public char getPixel(AsciiPoint p){
	int x = p.getX();
	int y = p.getY();
	return getPixel(x, y);
    }
    
 
      /**
   * Returns an ArrayList with all asciipoints of a certain char
   * @param   c   character of pixels we have to find
   * @return  array  ArrayList with all Pixels
  */
    public ArrayList<AsciiPoint> getPointList(char c){
	ArrayList<AsciiPoint> asciiList = new ArrayList<AsciiPoint>();
	AsciiPoint point;
	for(int y = 0; y < height; y++){
	  for(int x = 0; x < width; x++){
	      if(image[y][x] == c){
	      point = new AsciiPoint(x, y);
	      asciiList.add(point);
	      }
	  }
	}
    
	return asciiList;
    }
    
    
    public int getWidth(){
    
	return width;
    }
    
    //returns image, for copy constructor
    public void getImage(char[][] image){
    for(int i = 0; i < getHeight(); i++){
	      for(int j = 0; j < getWidth(); j++){
		    this.image[i][j]=image[i][j];
	      }
	}
    }
    //replaces the neighbours, with c if the neighbour = '.'
    public void replaceNeighbour(AsciiPoint point, char c){
        int x = point.getX();
        int y = point.getY();
        
	if((x-1) >= 0 && getPixel(x-1, y) == '.'){
	      setPixel(x-1, y, c);
	}
	if((x+1) < width && getPixel(x+1, y) == '.'){
	      setPixel(x+1, y, c);
	}
	if((y-1) >= 0 && getPixel(x, y-1) == '.'){
	      setPixel(x, y-1, c);
	}
	if((y+1) < height && getPixel(x, y+1) == '.'){
	      setPixel(x, y+1, c);
	}
    
    }
    //replaces the neighbours of a char c, with c, if they are empty '.'
    public void growRegion(char c){
	
	for(AsciiPoint point : getPointList(c)){
	replaceNeighbour(point, c);
	}
    }
    /**
   * Looks for the char you want to replace and replaces it
   * @param   oldChar   char you want to replace
   * @param   newChar   char you want to place instead
  */
    public void replace(char oldChar, char newChar){
	for(AsciiPoint point : getPointList(oldChar)){
	    setPixel(point, newChar);
	}
	
    
    }
    /**
   * @param   x   x-cooardinate
   * @param   y   y-cooardinate
   * @param   c   char you want to set at this point
  */
    public void setPixel(int x, int y, char c){
	image[y][x] = c;
    }
    /**
   * @param   point  Asciipoint with x and y cooardinates
   * @param   c   char you want to set at this point
  */
    public void setPixel(AsciiPoint p, char c){
	int x = p.getX();
	int y = p.getY();
	setPixel(x, y, c);
    }
    
    public String toString(){
	String imageString = new String();
	for(int i = 0; i < height; i++){			//läuft durch zeilen
	    for(int j=0; j < width; j++){
		imageString += image[i][j];
		if(j != 0 && j % (width -1) == 0){
		  imageString += "\n";
		}
	    }
	  }
    
    return imageString;
    
    }
    /** transposes matrix
    * switch height and width
    * x -> y, and y -> x
    * fill in the image
    */
    public void transpose(){
	  int tempHeight = height;
	  char[][] tempArr = new char[width][height];
	  for(int i = 0; i < height; i++){
		for(int j = 0; j < width; j++){
		      tempArr[j][i] = image[i][j];
		}
	  }
	  height = width;
	  width = tempHeight;
	  image = tempArr;
	  
    
    }
    /**
   * Replaces the char on (x,y) with c and also
   * replaces it on top,bottom,right and left
   * floodfill algorithm
   * @param   x   x-cooardinate
   * @param   y   y-cooardinate
   * @return  c   new char you want to replace
   */
    public void fill(int x, int y, char c){
	  char oldChar = image[y][x];
	  if(oldChar != c){
		setPixel(x, y, c);
	  }
	  if((x-1) >= 0 && x-1 < width && getPixel(x-1, y) == oldChar){
	  fill(x-1, y, c);
	  }
	  if((x+1) >= 0 && (x+1) < width && getPixel(x+1, y) == oldChar){
	  fill(x+1, y, c);
	  }
	  if((y-1) >= 0 && (y-1) < height && getPixel(x, y-1) == oldChar){
	  fill(x, y-1, c);
	  }
	  if((y+1) >= 0 && (y+1) < height && getPixel(x, y+1) == oldChar){
	  fill(x, y+1, c);
	  }
    }
    /** Das Bild soll durch Entfernen dünner Linien bzw. einzelner Störpixel geglättet werden. 
    * Dafür werden alle Pixel des Zeichens c auf ‘.’ gesetzt, 
    * die einen oder keinen Nachbarpixel mit dem Zeichen c besitzen. 
    * Hierbei werden vier Nachbarpixel betrachtet. 
    * allein im Bild liegende oder an Flächen angrenzende einzelne Punkte und Linien mit einer Breite 
    * von einem Pixel werden entfernt.
	* 
    * checking a list of asciipoints with char c, if you replace one, done = false, start over, to check if there
    * are new 'lonely' pixels now.
    */

    public void straightenRegion(char c){
    	boolean done = true;
    	int counter = 0;
    	int x = 0, y = 0;
    	do{
    		done = true;
    	for(AsciiPoint point : getPointList(c)){	
    		x = point.getX();
    		y = point.getY();
    		if((x-1) >= 0 && x-1 < width && getPixel(x-1, y) == c){
	  			counter++;
	  		}
	  		if((x+1) >= 0 && (x+1) < width && getPixel(x+1, y) == c){
	  			counter++;
	  		}
	  		if((y-1) >= 0 && (y-1) < height && getPixel(x, y-1) == c){
	  			counter++;
	  		}
	  		if((y+1) >= 0 && (y+1) < height && getPixel(x, y+1) == c){
	  			counter++;
	  		}

	  		if(counter <= 1){
	  			setPixel(point, '.');
	  			done = false;
	  		}
	  		counter = 0;
		}


		}while(done == false);  	
    }
    

}