
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource file = new FileResource(filename);
         for(String line : file.lines()){
             LogEntry entry = WebLogParser.parseEntry(line);
             records.add(entry);
            }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> IPs = new ArrayList<String>();
         for(LogEntry s : records){
             String IP = s.getIpAddress();
             if(!IPs.contains(IP)){IPs.add(IP);}
            }
            return IPs.size();
     }
     
     public void printAllHigherThanNum(int num){
         for(LogEntry entry : records){
             int status = entry.getStatusCode();
             if(status >= num){
                 System.out.println("This entry has status higher than "+num+"\n"+entry);
                }
            }
     }   
     
     public ArrayList<String> uniqueIPVisitsOnDay(String day){
         ArrayList<String> IPs = new ArrayList<String>();
         for(LogEntry entry : records){
             String d = entry.getAccessTime().toString().substring(4,10);
             if(d.contains(day) && !IPs.contains(entry.getIpAddress())){
                 IPs.add(entry.getIpAddress());
                }
            }
            return IPs;
        }
        
        private ArrayList<String> IPVisitsOnDay(String day){
         ArrayList<String> IPs = new ArrayList<String>();
         for(LogEntry entry : records){
             String d = entry.getAccessTime().toString().substring(4,10);
             if(d.contains(day)){
                 IPs.add(entry.getIpAddress());
                }
            }
            return IPs;
        }
        
        private ArrayList<String> getIPsForDays(String day){
         ArrayList<String> IPs = new ArrayList<String>();
         for(LogEntry entry : records){
             String d = entry.getAccessTime().toString();
             if(d.contains(day)){
                 IPs.add(entry.getIpAddress());
                }
            }
            return IPs;
        }
     
        public int countUniqueIPsInRange(int low,int high){
            ArrayList<String> IPs = new ArrayList<String>();
            for(LogEntry entry : records){
             int status = entry.getStatusCode();
             if(status >= low && status <= high){
                 if(!IPs.contains(entry.getIpAddress())){
                     IPs.add(entry.getIpAddress());
                    }
                }
            }
            return IPs.size();
        }
        
        public HashMap<String,Integer> countVisitsPerIP(){
            HashMap<String,Integer> IPs = new HashMap<String,Integer>();
            for(LogEntry entry : records){
                if(!IPs.containsKey(entry.getIpAddress())){
                    IPs.put(entry.getIpAddress(),1);
                }
                else{
                    IPs.put(entry.getIpAddress(),IPs.get(entry.getIpAddress())+1);
                }
            }
            return IPs;
        }
        
        public int mostNumberVisitsByIP(HashMap<String,Integer> map){
            int max = 0;
            for(String s : map.keySet()){
                if(map.get(s)>=max){
                    max = map.get(s);
                }
            }
            return max;
        }
        
        public ArrayList<String> IPsMostVisits(HashMap<String,Integer> map){
            int max = mostNumberVisitsByIP(map);
            ArrayList<String> IPs = new ArrayList<String>();
            for(String s : map.keySet()){
                if(map.get(s) == max && !IPs.contains(s)){
                    IPs.add(s);
                }
            }
            return IPs;
        }
        
        public HashMap<String,ArrayList<String>> IPsForDays(){
            HashMap<String,ArrayList<String>> IPDa = new HashMap<String,ArrayList<String>>();
            for(LogEntry entry : records){
                String day = entry.getAccessTime().toString().substring(4,10);
                ArrayList<String> list = getIPsForDays(day);
                if(!IPDa.containsKey(day)){
                    IPDa.put(day,list);
                }
            }
            return IPDa;
        }
        
        public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> map){
            map = IPsForDays();
            String day = null;
            int max = 0;
            for(String s : map.keySet()){
                if(map.get(s).size()>max){
                    max = map.get(s).size();
                    day = s;
                }
            }
            return day;
        }
        
        public ArrayList<String> IPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> map,String day){
            map = IPsForDays();
            ArrayList<String> ips = IPVisitsOnDay(day);
            HashMap<String,Integer> IPs = new HashMap<String,Integer>();
            for(int i=0;i<ips.size();i++){
                String ip = ips.get(i);
                if(!IPs.containsKey(ip)){
                    IPs.put(ip,1);
                }
                else{
                    IPs.put(ip,IPs.get(ip)+1);
                }
                    }
            return IPsMostVisits(IPs);
        }
        
}
