import edu.duke.FileResource;
import edu.duke.URLResource;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class GladLibMap {
    private HashMap<String,ArrayList<String>> myMap;
    private ArrayList<String> usedList;
    private ArrayList<String> usedCategories;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String,ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        usedList = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String,ArrayList<String>>();
        initializeFromSource(source);
        usedList = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] label = {"adjective","noun","color","country","fruit","name","animal","timeframe","verb"};
        for(String s : label){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s,list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if(usedCategories.indexOf(label) == -1){
            usedCategories.add(label);
        }
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while(usedList.indexOf(sub) != -1){
            sub = getSubstitute(w.substring(first+1,last));
          }
        if(usedList.indexOf(sub)== -1){
            usedList.add(sub);
        }
        return prefix+sub+suffix;
    }
    
    private int totalWordsInMap(){
        int total = 0;
        for(String s : myMap.keySet()){
            total += myMap.get(s).size();
        }
        return total;
    }
    
    private int totalWordsConsidered(){
        int total = 0;
        for(String s : usedCategories){
            total += myMap.get(s).size();
        }
        return total;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
        int totalWords = totalWordsInMap();
        int totalUsed = totalWordsConsidered();
        System.out.println("\nWe replaced "+usedList.size()+" words\nTotal words can be used "+totalWords+" words\nTotal words of labels found in this story "+totalUsed+" words");
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        System.out.println("\n");
        usedList.clear();
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
    }
    


}
