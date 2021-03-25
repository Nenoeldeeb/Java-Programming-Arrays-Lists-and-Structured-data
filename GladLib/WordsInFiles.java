import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import edu.duke.FileResource;
import edu.duke.DirectoryResource;
public class WordsInFiles {
    private HashMap<String,ArrayList<String>> wordsMap;
    public WordsInFiles(){
        wordsMap = new HashMap<String,ArrayList<String>>();
    }
    private void addWordsFromFile(File f){
        FileResource file = new FileResource(f);
        for(String word : file.words()){
            if(!wordsMap.containsKey(word)){
                ArrayList<String> fileName = new ArrayList<String>();
                fileName.add(f.getName());
                wordsMap.put(word,fileName);
            }
            else{
                if(!wordsMap.get(word).contains(f.getName())){
                    ArrayList<String> fileName = wordsMap.get(word);
                    fileName.add(f.getName());
                    wordsMap.put(word,fileName);
                }
            }
        }
    }
    private void buildWordFileMap(){
        DirectoryResource dir = new DirectoryResource();
        wordsMap.clear();
        for(File f : dir.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    private int maxNumber(){
        int max = 0;
        for(String s : wordsMap.keySet()){
            if(wordsMap.get(s).size()>max){
                max = wordsMap.get(s).size();
            }
        }
        return max;
    }
    private ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> result = new ArrayList<String>();
        for(String s : wordsMap.keySet()){
            if(wordsMap.get(s).size() == number){
                if(!result.contains(s)){
                    result.add(s);
 
                }           }
        }
        return result;
    }
    private void printFilesIn(String word){
        for(int i=0;i<wordsMap.get(word).size();i++){
            System.out.println(word+"\t"+wordsMap.get(word).get(i));
        }
    }
    public void tester(){
        buildWordFileMap();
        int max = maxNumber();
        ArrayList<String> filesMax = wordsInNumFiles(4);
        printFilesIn("tree");
        System.out.println(filesMax.size());
        for(int i=0;i<filesMax.size();i++){
            printFilesIn(filesMax.get(i));
        }
        for(String s : wordsMap.keySet()){
            System.out.println(s);
            for(int i=0;i<wordsMap.get(s).size();i++){
                System.out.println("\nIt appears in\t"+wordsMap.get(s).get(i)+"\n");
            }
        }
    }
}
