import edu.duke.FileResource;
public class CaesarCipher {
    public String encrypt(String str,int key){
        StringBuilder string = new StringBuilder(str);
        String alpha ="abcdefghijklmnopqrstuvwxyz";
        String shiftedAlpha = alpha.substring(key)+alpha.substring(0,key);
        String Alpha = alpha.toUpperCase();
        String ShiftedAlpha = shiftedAlpha.toUpperCase();
        for(int i=0;i<str.length();i++){
            char curCh = string.charAt(i);
            if(Character.isLowerCase(curCh) ==true){
                int index = alpha.indexOf(curCh);
                if(index != -1){
                    char shCh = shiftedAlpha.charAt(index);
                    string.setCharAt(i,shCh);
                }
            }
            else{
                int index = Alpha.indexOf(curCh);
                if(index != -1){
                    char shCh = ShiftedAlpha.charAt(index);
                    string.setCharAt(i,shCh);
                }
            }
        }
        return string.toString();
    }
    public String encryptTwoKeys(String str,int key1,int key2){
        StringBuilder string = new StringBuilder(str);
        String alpha ="abcdefghijklmnopqrstuvwxyz";
        String shiftedAlpha1 = alpha.substring(key1)+alpha.substring(0,key1);
        String shiftedAlpha2 = alpha.substring(key2)+alpha.substring(0,key2);
        String Alpha = alpha.toUpperCase();
        String ShiftedAlpha1 = shiftedAlpha1.toUpperCase();
        String ShiftedAlpha2 = shiftedAlpha2.toUpperCase();
        for(int i=0;i<str.length();i++){
            char curCh = string.charAt(i);
            if(Character.isLowerCase(curCh) ==true){
                int index = alpha.indexOf(curCh);
                if(index != -1){
                    if((i+1)%2==0){
                        char shCh = shiftedAlpha2.charAt(index);
                        string.setCharAt(i,shCh);
                    }
                    else{
                        char shCh = shiftedAlpha1.charAt(index);
                        string.setCharAt(i,shCh);
                    }
                }
            }
            else{
                int index = Alpha.indexOf(curCh);
                if(index != -1){
                    if((i+1)%2==0){
                        char shCh = ShiftedAlpha2.charAt(index);
                        string.setCharAt(i,shCh);
                    }
                    else{
                        char shCh = ShiftedAlpha1.charAt(index);
                        string.setCharAt(i,shCh);
                    }
                }
            }
        }
        return string.toString();
    }
    public void testEncrypt(){
        FileResource fr = new FileResource();
        String str = fr.asString();
        int key = 15;
        String encrypted = encrypt(str,key);
        System.out.println("Key is "+ key + "\n" + encrypted);
    }
    public void testEncryptTwoKeys(){
        String mes = "Top ncmy qkff vi vguv vbg ycpx";
        System.out.println(encryptTwoKeys(mes,24,6));
    }
}
