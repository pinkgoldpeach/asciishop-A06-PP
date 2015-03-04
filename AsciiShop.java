    import java.util.Scanner;
    /**
 * Das Programm erstellt im ersten Schritt ein leeres Bild,
 * auf dem anschliessend unterschiedliche Operationen ausgefuehrt werden koennen.
 * Neben dem Laden von Bilddaten und dem Transponieren, ist auch das Zeichnen
 * von Linien, sowie das Ersetzen von Zeichen und das Loeschen des gesamten
 * Bildinhaltes moeglich. Mit Hilfe eines eigenen Befehls laesst sich das
 * aktuelle Bild jederzeit ausgeben
 */
    public class AsciiShop{
      public static void main(String[] args){
	  
	  Scanner sc = new Scanner(System.in); 
	  int width, height;
	  boolean mismatch = false, failed = false, unknown = false;
	    
	      
	      //create image, every image has to start with create
	      if(sc.hasNext() && sc.next().equals("create") && sc.hasNextInt()){
		width = sc.nextInt();
		if(sc.hasNextInt()){
		    height = sc.nextInt();
		    if(width > 0 && height > 0){
			AsciiImage image = new AsciiImage(height, width);
			AsciiStack stack = new AsciiStack(3);
			
			
			while(sc.hasNext() && mismatch == false && failed == false && unknown == false){
			  String command = sc.next();
			  //read image as long input != eof
			  if(command.equals("load") && sc.hasNext()){
	
			    stack.push(new AsciiImage(image));
			    String eof = sc.next();
			    String temp = new String();
			    int counter = 0;
			    boolean read = true;
			    
			     while(sc.hasNext() && read == true){
				temp = sc.next();
				  if(temp.equals(eof)){
				    read = false;
				  }
				  if(temp.length() == width){
				    for(int i = 0; i < width; i++){
				      char charAt = temp.charAt(i);
				      image.setPixel(i, counter, charAt);
				    }
				    counter++;
				  }
				  if(read == true && temp.length()!=width){
				  mismatch = true;
				  }
				  
				  if(temp.equals(eof) && counter != height){
				  mismatch = true;
				  }
			     
			     }
			     
			  }
			      //draw line
			     else if(command.equals("line")){
				if(sc.hasNextInt()){
				  int x0 = sc.nextInt();
				  if(sc.hasNextInt()){
				    int y0 = sc.nextInt();
				    if(sc.hasNextInt()){
				      int x1 = sc.nextInt();
				      if(sc.hasNextInt()){
					int y1 = sc.nextInt();
					if(sc.hasNext()){
					//check if sizes are right
					  if(x0 >= 0 && x0 < width && x1 >= 0 && x1 < width){
					     if(y0 >= 0 && y0 < height && y1 >= 0 && y1 < height){
						stack.push(new AsciiImage(image));
						 char c = sc.next().charAt(0);
						 image.drawLine(x0, y0, x1, y1, c);
					      }else{
						  mismatch = true;
					      }
					  }else{
					      mismatch = true;
					  }
					}else if(!sc.hasNext() || sc.hasNext() && sc.next().length() != 1){
					    mismatch = true;
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
				}else{
				    mismatch = true;
				}
			      }
			     else if(command.equals("print")){
				System.out.println(image.toString());
			      }
			     else if(command.equals("clear")){
				stack.push(new AsciiImage(image));
				image.clear();
			      }
			      //get center of a certain char
			      else if(command.equals("centroid")){
				if(sc.hasNext()){
				    char c = sc.next().charAt(0);
				    AsciiPoint aP = image.getCentroid(c);
				    if(aP != null){
					System.out.println(aP.toString());
				    }else{
					System.out.println("null");
				    }
				 }else if(!sc.hasNext() || sc.hasNext() && sc.next().length() != 1){
				    mismatch = true;
				 }
			      }
			      //replace every '.' right next to char with char
			     else if(command.equals("grow")){
				if(sc.hasNext()){
				    stack.push(new AsciiImage(image));
				    char rep = sc.next().charAt(0);
				    image.growRegion(rep);
				}else if(!sc.hasNext() || sc.hasNext() && sc.next().length() != 1){
				    mismatch = true;
				 }
			      }else if(command.equals("straighten")){
			      	String check = "";
			      	if(sc.hasNext()){
			      		check = sc.next();

			      		if(check.length() == 1){
			      		stack.push(new AsciiImage(image));
			      		image.straightenRegion(check.charAt(0));
			      		}else{
			      			mismatch = true;
			      		}
			      	}else{
			      		mismatch = true;
			      	}
			      	
			      }
			      //floodfill
			     else if(command.equals("fill")){
				  if(sc.hasNextInt()){
				 
				      stack.push(new AsciiImage(image));
				      int x = sc.nextInt();
				      if(sc.hasNextInt()){
					  int y = sc.nextInt();
					  if(sc.hasNext()){
					      char c = sc.next().charAt(0);
						//check if sizes are right
						if(x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()){
						  image.fill(x, y, c);
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
			      }//transposes image/matrix
			     else if(command.equals("transpose")){
				  stack.push(new AsciiImage(image));
				  image.transpose();
			      }//replaces every oldchar with newchar
			     else if(command.equals("replace")){
				  if(sc.hasNext()){
				      stack.push(new AsciiImage(image));
				      char oldChar = sc.next().charAt(0);
				      if(sc.hasNext()){
					  char newChar = sc.next().charAt(0);
					  image.replace(oldChar, newChar);
				      }else if(!sc.hasNext() || sc.hasNext() && sc.next().length() != 1){
					  mismatch = true;
				      }
				  }else if(!sc.hasNext() || sc.hasNext() && sc.next().length() != 1){
				      mismatch = true;
				  }
			      }//undos a command, retrieves previous image from stack 
			     else if(command.equals("undo")){
				  if(stack.size() == 0){
				      System.out.println("STACK EMPTY");
				  }else{
				      image = stack.pop();
				      //System.out.println(image);
				      System.out.println("STACK USAGE "+stack.size() +"/" + stack.capacity());
				  }
			      }//command create is only allowed once
			      else if(command.equals("create")){
				  unknown = true;
				  //no other commands are known
			      }else{
				  unknown = true;
			      }
			      
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
	if(mismatch == true){
	    System.out.println("INPUT MISMATCH");
	}
	if(unknown == true){
	    System.out.println("UNKNOWN COMMAND");
	}
	if(failed == true){
	    System.out.println("OPERATION FAILED");
	}

    }

 }