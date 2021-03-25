public class CaesarCipher {
    private String alphabet ;
    private String shiftedAlphabet ;
    private String ALPHABET ;
    private String shiftedALPHABET ;
    private int mainKey ;
    public CaesarCipher(int key){
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key)+alphabet.substring(0,key);
        ALPHABET = alphabet.toUpperCase();
        shiftedALPHABET = shiftedAlphabet.toUpperCase();
        mainKey = key ;
    }
    public String encrypt(String input){
        StringBuilder string = new StringBuilder(input);
        for(int i=0;i<string.length();i++){
            char curCh = string.charAt(i);
            if(Character.isLowerCase(curCh)==true){
                int index = alphabet.indexOf(curCh);
                if(index != -1){
                    char shCh = shiftedAlphabet.charAt(index);
                    string.setCharAt(i,shCh);
                }
            }
            else {
                int Index = ALPHABET.indexOf(curCh);
                if(Index != -1){
                    char ShCh = shiftedALPHABET.charAt(Index);
                    string.setCharAt(i,ShCh);
                }
            }
        }
        return string.toString();
    }
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        return cc.encrypt(input);
    }
}
