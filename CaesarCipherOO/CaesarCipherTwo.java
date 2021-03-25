
public class CaesarCipherTwo {
    private String alphabet ;
    private String ALPHABET ;
    private String shiftedAlphabet1 ;
    private String shiftedALPHABET1 ;
    private String shiftedAlphabet2 ;
    private String shiftedALPHABET2 ;
    private int mainKey1 ;
    private int mainKey2 ;
    public CaesarCipherTwo(int key1,int key2){
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1)+alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2)+alphabet.substring(0,key2);
        ALPHABET = alphabet.toUpperCase();
        shiftedALPHABET1 = shiftedAlphabet1.toUpperCase();
        shiftedALPHABET2 = shiftedAlphabet2.toUpperCase();
        mainKey1 = key1;
        mainKey2 = key2;
    }
    public String encrypt(String input){
        StringBuilder string = new StringBuilder(input);
        for(int i=0;i<input.length();i++){
            char curCh = string.charAt(i);
            if(Character.isLowerCase(curCh) ==true){
                int index = alphabet.indexOf(curCh);
                if(index != -1){
                    if((i+1)%2==0){
                        char shCh = shiftedAlphabet2.charAt(index);
                        string.setCharAt(i,shCh);
                    }
                    else{
                        char shCh = shiftedAlphabet1.charAt(index);
                        string.setCharAt(i,shCh);
                    }
                }
            }
            else{
                int index = ALPHABET.indexOf(curCh);
                if(index != -1){
                    if((i+1)%2==0){
                        char shCh = shiftedALPHABET2.charAt(index);
                        string.setCharAt(i,shCh);
                    }
                    else{
                        char shCh = shiftedALPHABET1.charAt(index);
                        string.setCharAt(i,shCh);
                    }
                }
            }
        }
        return string.toString();
    }
    public String decrypt(String input){
        CaesarCipherTwo cc = new CaesarCipherTwo(26-mainKey1,26-mainKey2);
        return cc.encrypt(input);
    }
}
