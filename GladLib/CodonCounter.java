import java.util.HashMap;
import edu.duke.FileResource;
public class CodonCounter{
    private HashMap<String,Integer> codonMap;
    public CodonCounter(){
        codonMap = new HashMap<String,Integer>();
    }
    private void buildCodonMap(int start,String dna){
        codonMap.clear();
        int codons = (dna.length()-start)/3;
        int rounds = 0;
        while(rounds < codons){
            String codon = dna.substring(start,start+3);
            if(Character.isLetter(codon.charAt(2))){
                if(!codonMap.containsKey(codon)){
                    codonMap.put(codon,1);
                }
                else{
                    codonMap.put(codon,codonMap.get(codon)+1);
                }
            }
            start += 3;
            rounds ++;
        }
    }
    private String getMostCommonCodon(){
        int max = 0;
        String maxCodon = new String();
        for(String s : codonMap.keySet()){
            if(codonMap.get(s)>max){
                max = codonMap.get(s);
                maxCodon = s;
            }
        }
        return maxCodon;
    }
    private void printCodonCounts(int start,int end){
        for(String s : codonMap.keySet()){
            if(codonMap.get(s)>=start && codonMap.get(s)<=end){
                    System.out.println("\nThe codon "+s+" was counted "+codonMap.get(s)+" times");
            }
        }
    }
    public void tester(){
        FileResource file = new FileResource();
        String dna = file.asString();
        dna = dna.toUpperCase();
        buildCodonMap(0,dna);
        String commonCodon = getMostCommonCodon();
        System.out.println("Total number of unique codons in the 0 frame "+codonMap.size()+"\nThe most common codon is "+commonCodon+" it counted "+codonMap.get(commonCodon)+" times");
        printCodonCounts(1,5);
        buildCodonMap(1,dna);
        commonCodon = getMostCommonCodon();
        System.out.println("Total number of unique codons in the 1 frame "+codonMap.size()+"\nThe most common codon is "+commonCodon+" it counted "+codonMap.get(commonCodon)+" times");
        printCodonCounts(1,5);
        buildCodonMap(2,dna);
        commonCodon = getMostCommonCodon();
        System.out.println("Total number of unique codons in the 2 frame "+codonMap.size()+"\nThe most common codon is "+commonCodon+" it counted "+codonMap.get(commonCodon)+" times");
        printCodonCounts(1,5);
    }
}