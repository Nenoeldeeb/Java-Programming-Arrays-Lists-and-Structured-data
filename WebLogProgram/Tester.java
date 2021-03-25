
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testCountUniqueIPs(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.printAll();
        int count = la.countUniqueIPs();
        System.out.println("Unique IPs are "+count);
        la.printAllHigherThanNum(400);
        int range = la.countUniqueIPsInRange(200,299);
        System.out.println("Unique IPs in range are "+range);
    }
    
    public void testCountUniqueIPVistsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> unique = la.uniqueIPVisitsOnDay("Sep 27");
        System.out.println("Unique IPs in the day "+unique.size());
    }
    
    public void visitsByIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> map = la.countVisitsPerIP();
        int max = la.mostNumberVisitsByIP(map);
        ArrayList<String> IPs = la.IPsMostVisits(map);
        HashMap<String,ArrayList<String>> IPDa = la.IPsForDays();
        HashMap<String,ArrayList<String>> ip = new HashMap<String,ArrayList<String>>();
        String day = la.dayWithMostIPVisits(ip);
        System.out.println("Most number  "+IPs);
        for(String s : IPDa.keySet()){
            System.out.println(s+" "+IPDa.get(s));
    }
    }
    
    public void testIPsVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> map = null;
        ArrayList<String> IPs = la.IPsWithMostVisitsOnDay(map,"Sep 30");
        for(String s : IPs){
            System.out.println(s);
        }
    }
    
}
