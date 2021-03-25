import edu.duke.FileResource;
public class CaesarBreaker {
    public int[] countLetters(String encr){
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
    public String decrypt(String encr){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encr);
        int max = maxIndex(freqs);
        int dkey = max-4;
        if(max < 4){dkey = 26-(4-max);}
        return cc.encrypt(encr,26-dkey);
    }
    public String halfOfString(String mess,int start){
        StringBuilder str = new StringBuilder();
        for(int i=start;i<mess.length();i+=2){
            str.append(mess.charAt(i));
        }
        return str.toString();
    }
    public int getKey(String str){
        int[] freqs = countLetters(str);
        int max = maxIndex(freqs);
        int dkey = max-4;
        if(max < 4){dkey = 26-(4-max);}
        return dkey;
    }
    public String decryptTwoKeys(String encr){
        CaesarCipher cc = new CaesarCipher();
        String str1 = halfOfString(encr,0);
        String str2 = halfOfString(encr,1);
        int key1 = getKey(str1);
        int key2 = getKey(str2);
        System.out.println("Key1 is\t"+key1+"\tKey2 is\t"+key2);
        return cc.encryptTwoKeys(encr,26-key1,26-key2);
    }
    public void testBreaker(){
        FileResource file = new FileResource();
        String str = file.asString();
        System.out.println(decryptTwoKeys(str));
    }
}
