
import edu.duke.FileResource;


public class VigenereTester {
    public  void testSliceString(){
        VigenereBreaker vb = new VigenereBreaker();
        String string = vb.sliceString("abcdefghijklm", 4, 5);
        System.out.println(string);
    }
    public  void testTryKeyLength(){
        FileResource file = new FileResource();
        String message = file.asString();
        VigenereBreaker vb = new VigenereBreaker();
        int[] key = vb.tryKeyLength(message, 38, 'e');
        for(int n : key){
            System.out.println(n);
        }
    }
    public  void testBreakVigenere(){
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }
}
