import java.util.*;
import edu.duke.*;
public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slice = new StringBuilder();
        for(int i=whichSlice;i<message.length();i+=totalSlices){
            char ch = message.charAt(i);
            slice.append(ch);
        }
        return slice.toString();
    }
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i=0;i<klength;i++){
            String st = sliceString(encrypted,i,klength);
            int key1 = cc.getKey(st);
            key[i] = key1;
        }
        return key;
    }
    public void breakVigenere () {
        FileResource file = new FileResource();
        String encrypted = file.asString();
        HashMap<String,HashSet<String>> dictionaries = new HashMap<String,HashSet<String>>();
        String[] languages = {"Danish","Dutch","English","French","German","Italian","Portuguese","Spanish"};
        for(String s : languages){
            FileResource dictionaryFile = new FileResource("dictionaries/"+s);
            HashSet<String> dictionary = readDictionary(dictionaryFile);
            dictionaries.put(s, dictionary);
        }
        breakForAllLanguages(encrypted, dictionaries);
    }
    public HashSet<String> readDictionary(FileResource file){
        HashSet<String> hash = new HashSet<String>();
        for(String s : file.lines()){
            s = s.toLowerCase();
            hash.add(s);
            
        }
        return hash;
    }
    public int countWords(String message,HashSet<String> dictionary){
        String[] words = message.split("\\W+");
        int realWords = 0;
        for(String s : words){
            s = s.toLowerCase();
            if(dictionary.contains(s)){
                realWords ++;
            }
        }
        return realWords;
    }
    public String breakForLanguage(String encrypted,HashSet<String> dictionary){
        int maxWords = 0;
        String realMessage = new String();
        char mostCommon = mostCommonCharIn(dictionary);
        for(int i=1;i<100;i++){
            int[] key = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String message = vc.decrypt(encrypted);
            int realWords = countWords(message, dictionary);
            if(realWords > maxWords){
                maxWords = realWords;
                realMessage = message;
            }
        }
        return realMessage;
    }
    public char mostCommonCharIn(HashSet<String> dictionary){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int max = 0;
        int[] Char = new int[26];
        char mostChar = 'a';
        for(String s : dictionary){
            s = s.toLowerCase();
            for(int i=0;i<s.length();i++){
                char ch = s.charAt(i);
                int index = alphabet.indexOf(ch);
                if(index != -1){
                    Char[index]++;
                }
                
            }
        }
        for(int i=0;i<Char.length;i++){
            if(Char[i]>max){
                max = Char[i];
                mostChar = alphabet.charAt(i);
            }
        }
        return mostChar;
    }
    public void breakForAllLanguages(String encrypted,HashMap<String,HashSet<String>> languages){
        String decrypted = new String();
        String language = new String();
        int max = 0;
        for(String s : languages.keySet()){
            HashSet<String> dictionary = languages.get(s);
            int words = countWords(encrypted, dictionary);
            if(words > max){
                max = words;
                decrypted = breakForLanguage(encrypted, dictionary);
                language = s;
            }
        }
        /*I can decrypt the message but I can't get the original language*/
        System.out.println("The original language\t"+language+"\n"+decrypted);
    }
}
