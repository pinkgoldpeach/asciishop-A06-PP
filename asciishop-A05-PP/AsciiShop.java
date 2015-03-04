import java.util.Scanner;

/**
* Das Programm erstellt im ersten Schritt ein leeres Bild,
* auf dem anschliessend unterschiedliche Operationen ausgefuehrt werden koennen.
* Neben dem Laden von Bilddaten und dem Transponieren, ist auch das Zeichnen
* von Linien, sowie das Ersetzen von Zeichen und das Loeschen des gesamten
* Bildinhaltes moeglich. Mit Hilfe eines eigenen Befehls (print) laesst sich das
* aktuelle Bild jederzeit ausgeben.
*/

public class AsciiShop{
	
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		int length, size;
		boolean error = false, unknown = false, failed = false;
		//create image, every image has to start with create
		if(sc.hasNext() && sc.next().equals("create") && sc.hasNextInt()){
			length = sc.nextInt();
			
			if(length > 0 && sc.hasNextInt()){
				size = sc.nextInt();
				if(size > 0){
					AsciiImage image = new AsciiImage(length, size);
					
					while(sc.hasNext() && error == false && unknown == false && failed == false){
						String command = sc.next();
						//read image as long input != eof
						if(command.equals("load") && sc.hasNext()){
							String eof = sc.next();
							String temp = new String();
							int counterH = 0;
							char newC;
							boolean run = true;
							while(sc.hasNext() && run == true){
								temp = sc.next();
								if(temp.equals(eof)){
									run = false;
									if( counterH != size){
										error = true;
									}									
								}else if(temp.length() == length){
									for(int i = 0; i < temp.length(); i++){
										newC = temp.charAt(i);
										image.load(counterH, i, newC);
									}
									counterH ++;
								}else{ 
									error = true;
								}
								
							}//replaces every oldchar with newchar
						}else if(command.equals("replace")){
							if(sc.hasNext()){
								String str = sc.next();
								if(str.length() == 1){
									char oldChar = str.charAt(0);
									if(sc.hasNext()){
										String st = sc.next();
										if(st.length() == 1){
											char newChar = st.charAt(0);
											image.replace(oldChar, newChar);
										}else{
											error = true;
										}
										
									}else{
										error = true;
									}
								}else{
									error = true;
								}
							}else{
								error = true;
							}
							//returns only '.' in image	
						}else if(command.equals("clear")){
							image.clear();
						}else if(command.equals("print")){
							System.out.println(image.toString());
							//transposes image/matrix	
						}else if(command.equals("transpose")){
							image.transpose();
							//floodfill
						}else if(command.equals("fill")){
							if(sc.hasNextInt()){
								int x = sc.nextInt();
								if(x >= 0 && x < length){
									if(sc.hasNextInt()){
										int y = sc.nextInt();
										if(y >= 0 && y < size){
											if(sc.hasNext()){
												char c = sc.next().charAt(0);
												image.fill(x, y, c);
											}else if(!sc.hasNext() || sc.next().length() != 1){
												error = true;
											}
										}else{
											failed = true;
										}
									}else{
										error = true;
									}
								}else{
									failed = true;
								}
							}else{
								error = true;
							}
							//draws line from x0,y0 to x1,y1 with char c
						}else if(command.equals("line")){
							if(sc.hasNextInt()){
								int x1 = sc.nextInt();
								if(x1 >= 0 && x1 < length && sc.hasNextInt()){
									int y1 = sc.nextInt();
									if(y1 >= 0 && y1 < size && sc.hasNextInt()){
										int x2 = sc.nextInt();
										if( x2 >= 0 && x2 < length && sc.hasNextInt()){
											int y2 = sc.nextInt();
											if(y2 >= 0 && y2 < size && sc.hasNext()){
												String str = sc.next();
												if(str.length() == 1){
													char ch = str.charAt(0);
													image.drawLine(x1, y1, x2, y2, ch);
												}else{
													error = true;
												}
											}else{
												error = true;
											}
										}else{
											error = true;
										}
									}else{
										error = true;
									}
								}else{
									error = true;
								}
							}else{
								error = true;
							}
							//command create only once allowed
						}else if(command.equals("create")){
							unknown = true;
							//no other commands knowns
						}else{
							unknown = true;
						}
					}
				}else{
					error = true;
				}
			}else{
				error = true;	
			}
			
			
		}else{
			error = true;
		}
		if( error == true){
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
