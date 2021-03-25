import edu.duke.FileResource;
public class TestCaesarCipher {
    private int[] countLetters(String encr){
        int[] letters = new int[26];
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        for(int i=0;i<encr.length();i++){
            char ch = Character.toLowerCase(encr.charAt(i));
            int index = alpha.indexOf(ch);
            if(index != -1){
                letters[index]++;
            }
        }
        return letters;
    }
    public int maxIndex(int[] values){
        int max = 0;
        int index = 0;
        for(int i=0;i<values.length;i++){
            if(values[i]>max){
                max = values[i];
                index = i;
            }
        }
        return index;
    }
    public String breakCaesarCipher(String input){
        int[] freqs = countLetters(input);
        int max = maxIndex(freqs);
        int dkey = max-4;
        if(max < 4){dkey = 26-(4-max);}
        CaesarCipher cc = new CaesarCipher(dkey);
        return cc.decrypt(input);
    }
    public void simpleTests(){
        FileResource file = new FileResource();
        String string = file.asString();
        System.out.println(string);
        CaesarCipher cc = new CaesarCipher(15);
        String encr = cc.encrypt(string);
        System.out.println(encr);
        String decr = cc.decrypt(encr);
        System.out.println(decr);
        String breakCC = breakCaesarCipher(encr);
        System.out.println("Breaked "+breakCC);
    }
}
