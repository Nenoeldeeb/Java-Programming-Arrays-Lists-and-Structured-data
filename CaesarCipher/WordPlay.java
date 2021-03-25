
public class WordPlay {
    public boolean isVowel(char ch){
        boolean res = false;
        if(ch=='a'||ch=='A'||ch=='e'||ch=='E'||ch=='i'||ch=='I'||ch=='o'||ch=='O'||ch=='u'||ch=='U'){res = true;}
        return res;
    }
    public String replaceVowels(String str,char ch){
        StringBuilder string = new StringBuilder(str);
        for(int i=0;i<str.length();i++){
            if(isVowel(string.charAt(i)) == true){string.setCharAt(i,ch);}
        }
        return string.toString();
    }
    public String emphasize(String str,char ch){
        StringBuilder string = new StringBuilder(str);
        for(int i=0;i<str.length();i++){
            if(string.charAt(i)==ch){
                if((i+1)%2==0){string.setCharAt(i,'+');}
                else{string.setCharAt(i,'*');}
            }
        }
        return string.toString();
    }
    public void testIsVowel(){
        String phrase = "I'm a good person";
        for(int i=0;i<phrase.length();i++){
            char ch = phrase.charAt(i);
            boolean test = isVowel(ch);
            System.out.println(ch+" "+test);
        }
    }
    public void testReplaceVowels(){
        String str = "Hello World";
        System.out.println(str+" "+replaceVowels(str,'*'));
    }
    public void testEmphasize(){
        String str = "dna ctgaaa-aaaagt ataa";
        System.out.println(emphasize(str,'a'));
    }
}
