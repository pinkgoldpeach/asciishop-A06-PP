import java.util.ArrayList;
import java.util.HashMap;

public class Histogram{
	
	public static AsciiImage getHistogram(AsciiImage img){
		String charset = img.getCharset();
		String histCharset =charset;
		HashMap<Character, Double> characters = new HashMap<Character, Double>();
		if(histCharset.indexOf('.') == -1){
			histCharset += ".";
		}
		if(histCharset.indexOf('#')== -1){
			histCharset = "#"+histCharset;
		}
		histCharset = "0123456789"+histCharset;
		
		int width = charset.length()+3;
		int i = 0;
		AsciiImage hist = new AsciiImage(width, 16, histCharset);
		ArrayList<AsciiPoint> size = new ArrayList<AsciiPoint>();
		double max = 0;
		double total = img.getWidth() *img.getHeight();
		
		/** put the charset in the histogram
		*/
		for(int x = 3; x < width; x++){
			hist.setPixel(x, 15, charset.charAt(i));
			i++;
		}
		
		/** put all the characters with their occurrences in a map
		* character = key, occurrence = value
		*/
		for(char c : charset.toCharArray()){
			size = img.getPointList(c);
			double occ = (size.size()/total)*100;
			characters.put(c, occ);
			max = (max > occ) ? max : occ;
		}

		/** write the percentages in the histogram.
		* percentage of the most common character = highest value in histogram (equal to 100% in histogram)
		*/
		for(int j = 0; j < 16; j+=2) {

			String number = String.format("%3d", Math.round(max*(15-j)/15));
			for(int k = 0; k < 3; k++)
				hist.setPixel(k, j, number.charAt(k));
		}

		/** mark the occurrences of all the characters with '#'
		* max = 100% (all lines, occurrence of the most common char)
		* 15 = all lines (last line = charset)
		* divide it by max (all lines) multiply with 15 to get the line-number where you start.
		* 
		*/
			
		int x = 3;
		for(int j = 0; j < charset.length(); j++){
			Character c = charset.charAt(j);
			int occurrence = (int)(Math.ceil(characters.get(c)));
				for(int y = (int)(occurrence*15/max); y > 0; y-- ){
					hist.setPixel(x, 15-y, '#');
				}
				x++;
		}

		return hist;
	}
	
}
