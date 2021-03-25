import edu.duke.FileResource;
public class TestCaesarCipherTwo {
    private String halfOfString(String mess,int start){
        StringBuilder string = new StringBuilder();
        for(int i=start;i<mess.length();i+=2){
            string.append(mess.charAt(i));
        }
        return string.toString();
    }
    private int[] countLetters(String input){
        int[] letters = new int[26];
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        for(int i=0;i<input.length();i++){
            char ch = Character.toLowerCase(input.charAt(i));
            int index = alpha.indexOf(ch);
            if(index != -1){
                letters[index]++;
            }
        }
        return letters;
    }
    private int maxIndex(int[] values){
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
    private int getKey(String input){
        int[] freqs = countLetters(input);
        int max = maxIndex(freqs);
        int dKey = max-4;
        if(max <4){dKey = 26-(max-4);}
        return dKey;
    }
    private String breakCaesarCipher(String input){
        String part1 = halfOfString(input,0);
        String part2 = halfOfString(input,1);
        int key1 = getKey(part1);
        int key2 = getKey(part2);
        System.out.println("part1 "+part1+key1+"\npart2"+part2+key2);
        CaesarCipherTwo cc = new CaesarCipherTwo(key1,key2);
        return cc.decrypt(input);
    }
    public void simpleTests(){
        FileResource file = new FileResource();
        String string = file.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(14,24);
        System.out.println(string);
        String encr = cc.encrypt(string);
        System.out.println(encr);
        String decr = cc.decrypt(encr);
        System.out.println(decr);
        String breakCC = breakCaesarCipher(encr);
        System.out.println("Breaked "+breakCC);
    }
}
