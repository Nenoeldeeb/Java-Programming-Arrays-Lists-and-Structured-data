import java.util.ArrayList;
import edu.duke.FileResource;
public class CharactersInPlay {
    private ArrayList<String> names ;
    private ArrayList<Integer> times ;
    public CharactersInPlay(){
        names = new ArrayList<String>();
        times = new ArrayList<Integer>();
    }
    private void update(String person){
        int index = names.indexOf(person);
        if(index == -1){
            names.add(person);
            times.add(1);
        }
        else {times.set(index,times.get(index)+1);}
    }
    private void findAllCharacters(){
        names.clear();
        times.clear();
        FileResource file = new FileResource();
        for(String line : file.lines()){
            int index = line.indexOf(".");
            if(index != -1){update(line.substring(0,index));}
        }
    }
    private void charactersWithNumParts(int num1,int num2){
        for(int i=0;i<times.size();i++){
            int number = times.get(i);
            if(number >= num1 && number <= num2){System.out.println(names.get(i));}
        }
    }
    public void tester(){
        findAllCharacters();
        System.out.println("These are the names of the play characters");
        for(int i=0;i<times.size();i++){
            if(times.get(i) >= 20){System.out.println(names.get(i)+" "+times.get(i)+" times");}
        }
        charactersWithNumParts(10,15);
    }
}
