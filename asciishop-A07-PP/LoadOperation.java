/** transforms the input string into an image object
*/

public class LoadOperation{

    private String data;

    public LoadOperation(String data){
        this.data = data;
    }
/** remove \n from string, set every char at the right position in the image
* throw exception if there are too many or not enough chars, or if the char is invalid (not in charset)
*/
    public AsciiImage execute(AsciiImage img) throws OperationException{
        int height = img.getHeight();
        int width = img.getWidth();
        String charset = img.getCharset();
        AsciiImage image = new AsciiImage(img);
        data = data.replaceAll("\n", "");
        //String[] lines = data.split("\n");
        if(checkData(charset)==false|| height*width != data.length()){
            throw new OperationException("OPERATION FAILED");
        }else{
            int x = 0;
            int y = 0;
            for(int i = 0; i < data.length(); i++){
                if((i % width) == 0 && i != 0){
                    y++;
                    x = 0;
                }
                if(y < height) {
                    image.setPixel(x, y, data.charAt(i));
                    x++;
                }




            }
        }
        return image;
     }
     /** @param check = unique chars of data string 
     * compare check to charset of image
     * @param charsExist = false, if there's a char in check, that isn't in charset
     * return false if charset or check = null, or charExist == true
     */
    private boolean checkData(String charset){
        String check = new String();
        boolean ok = false;
        for(int i = 0; i < data.length(); i++){
            if(check.indexOf(data.charAt(i)) == -1){
                check += data.charAt(i);
            }
        }
        boolean charsExist = true;
        for(int i = 0; i < check.length(); i++){
            if(charset.indexOf(check.charAt(i)) == -1){
                charsExist = false;
                break;
            }
        }

        if(charset.length() == 0 ||!charsExist){
            ok = false;
        }else{
            ok = true;
        }
        return ok;
    }
}