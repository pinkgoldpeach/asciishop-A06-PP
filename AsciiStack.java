public class AsciiStack{
   int size;
    AsciiImage[] stack;
    AsciiImage image;
    
    public AsciiStack(int increment){
	size = increment;
	stack = new AsciiImage[increment];
	
    }
    //returns stack capacity
    public int capacity(){
    
	return stack.length;
    }
    
    //returns true if stack at position 1 empty
    public boolean empty(){			
	boolean empty = false;
    
	if(stack[0] == null){
	    empty = true;
	}
    
	return empty;
    }
    
    /**pops first Image from Stack
    * if empty return null
    * if there's an image, return it
    * move rest of the images in the stack one up
    * resize stackif it is bigger than the original size and there are more empty spaces than the original size
    */
    public AsciiImage pop(){
	
	if(empty() == true){
	      return null;
	}else{
	    image = stack[0];
	}
	//move images up
	for(int i = 0; i < capacity() -1; i++){
	    stack[i] = stack[i+1];
	}
	    stack[capacity()-1] = null;
	    	
	//move images up & resize stack 
	if(capacity() > size && emptySpace() > size){
	    AsciiImage[] stack2 = new AsciiImage[capacity()-size];
		   for(int i = 0; i < stack2.length; i++){
			   stack2[i] = stack[i];
		  }
		this.stack = stack2;
	}

	return image;
    }
    
    public AsciiImage peek(){
	if(empty() == false){
	    image = stack[0];
	}else{
	    return null;
	}
	return image;
    }
    /**LIFO Stack
    * if stack on position 1 empty, save image there
    * if stack too small, add memory, move every image one down and put new one on top
    * if position 1 is full and stack is big enough, move all the images one down and save image on position 1
    * size = used memory of stack, capacity = stack.length
    */
    public void push(AsciiImage img){
	  if(empty() == true){
	      stack[0] = img;
	  }else if(size() == capacity()){
	      AsciiImage[] stack2 = new AsciiImage[stack.length+size];
	      stack2[0] = img;
	      for(int i = 0; i < stack.length; i++){
		    stack2[i+1] = stack[i]; 
		}
	      this.stack = stack2;
	    //size = used elements of stack  
	  }else if(size() < capacity()){	
	      moveDown();
	      stack[0] = img;
	  }
	  
	  
    }
    //moves every asciiImage down
   public void moveDown(){
	for(int k = stack.length-2; k >= 0; k--){
	    stack[k+1] = stack[k];
	}
   }
   
   //returns how much memory of the stack is used
   public int size(){
    
    return capacity()-emptySpace();
    }
    
    //returns how much empty memory is in the stack
    public int emptySpace(){
	  int counter = 0;
	  for(int i = 0; i < stack.length; i++){
	      if(stack[i] == null){
		    counter++;
	      }
	  }
	  return counter;
    }



}