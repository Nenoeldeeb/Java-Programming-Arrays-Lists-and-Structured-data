import java.util.ArrayList;
import edu.duke.FileResource;
public class WordFrequencies {
    private ArrayList<String> myWords ;
    private ArrayList<Integer> myFreqs ;
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    private void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource file = new FileResource();
        for(String word : file.words()){
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if(index == -1){
                    myWords.add(word);
                    myFreqs.add(1);
                }
                else {myFreqs.set(index,myFreqs.get(index)+1);}
        }
    }
    public void tester(){
        findUnique();
        for(int i=0;i<myWords.size();i++){
            if(myFreqs.get(i)==1){System.out.println(myFreqs.get(i)+" Time\t"+myWords.get(i));}
            else{System.out.println(myFreqs.get(i)+" Times\t"+myWords.get(i));}
        }
        System.out.println("The number of unique words is\t"+myWords.size());
        System.out.println("The most common word is "+myWords.get(findIndexOfMax())+" It appeares "+myFreqs.get(findIndexOfMax()));
    }
    private int findIndexOfMax(){
        int value = 0;
        int index = 0;
        for(int i=0;i<myFreqs.size();i++){
            if(myFreqs.get(i)>value){
                value = myFreqs.get(i);
                index = i;
            }
        }
        return index;
    }
}
