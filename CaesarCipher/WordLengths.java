import edu.duke.FileResource;
public class WordLengths {
    public void countWordLengths(FileResource file,int[] count){
        for(String word : file.words()){
            int wordLength = word.length();
            for(int i=0;i<wordLength;i++){
                char ch = word.charAt(i);
                if(i==0 || i==wordLength-1){
                    if(!Character.isLetter(ch)){wordLength--;}
                }
            }
            count[wordLength]++;
            System.out.println("Word length \t"+wordLength+"\t Number of words\t"+count[wordLength]);
        }
    }
    public int indexOfMax(int[] values){
        int max = 0;
        int index = 0;
        for(int i=0;i<values.length;i++){
            if(values[i]>max){
                max = values[i];
                index = i;
            }
        }
        return index;
    }
    public void testCountWordLengths(){
        FileResource file = new FileResource();
        int[] count = new int[31];
        countWordLengths(file,count);
        int index = indexOfMax(count);
        System.out.println("The max index is\t"+ index);
    }
}
