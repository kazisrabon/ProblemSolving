import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Assignments {

    public static void main(String[] args) throws IOException {
        Assignments assignments = new Assignments();
//        assignments.table(5);
//        assignments.star(5);
//        assignments.star2(5);
//        assignments.read("abc.txt");
//        System.out.println(String.valueOf(assignments.palyndrom("abcdcba")));
//        assignments.stringExp("AAA:BBB:CCC:DDD");
//        assignments.sum(new int[]{1,2,3,4,5}, 5);
//        System.out.println(assignments.lSearch(new int[]{2, 10, 20, 17, 6, 5}, 10) ? "FOUND" : "NOT FOUND");
//        int[] inputs = new int[]{2, 10, 20, 17, 6, 5};
//        Arrays.sort(inputs);
//        System.out.println(assignments.bSearch(inputs, 0, inputs.length-1, 10));

        HashMap<Integer, String> hm = new HashMap<>();

        // enter data into hashmap
        hm.put(98, "Math");
        hm.put(85, "Data Structure");
        hm.put(91, "Database");
        hm.put(95, "Java");
        hm.put(79, "Operating System");
        hm.put(80, "Networking");
        Map<Integer, String> hm1 = assignments.sortedHashMap(hm);
        for (Map.Entry<Integer, String> en : hm1.entrySet()) {
            System.out.println("Key = " + en.getKey() +
                    ", Value = " + en.getValue());
        }

    }

    private void table(int n){
        System.out.println(n+" * 1 = "+ n);
        System.out.println(n+" * 2 = "+ n*2);
        System.out.println(n+" * 3 = "+ n*3);
        System.out.println(n+" * 4 = "+ n*4);
        System.out.println(n+" * 5 = "+ n*5);
        System.out.println(n+" * 6 = "+ n*6);
        System.out.println(n+" * 7 = "+ n*7);
        System.out.println(n+" * 8 = "+ n*8);
        System.out.println(n+" * 9 = "+ n*9);
        System.out.println(n+" * 10 = "+ n*10);
    }

    private void star(int n){
        if (n > 0){
            String s = "*";
            for (int j = 1; j <= n ; j++) {
                System.out.println(s);
                s = s+"*";
            }
        }
    }

    private void star2(int n){
        for (int i = 0; i < n; i++) {
            for (int j = n - i; j > 1; j--)
                System.out.print(" ");
            for (int j = 0; j <= i; j++)
                System.out.print("* ");
            System.out.println();
        }
    }

    private void read(String fileName) throws IOException {
        String line, word = "";
        int count = 0, maxCount = 0;
        ArrayList<String> words = new ArrayList<String>();

        FileReader file = new FileReader(fileName);
        BufferedReader br = new BufferedReader(file);

        while((line = br.readLine()) != null) {
            String string[] = line.toLowerCase().split("([,.\\s]+) ");
            for(String s : string){
                words.add(s);
            }
        }

        for(int i = 0; i < words.size(); i++){
            count = 1;
            for(int j = i+1; j < words.size(); j++){
                if(words.get(i).equals(words.get(j))){
                    count++;
                }
            }
            if(count > maxCount){
                maxCount = count;
                word = words.get(i);
            }
        }

        System.out.println("Most repeated word: " + word);
        br.close();
    }

    private boolean palyndrom(String s){
        char[] characters = s.toCharArray();
        int lo = 0;
        int hi = characters.length -1;
        while (lo <= hi){
            if (characters[lo] != characters[hi]) return false;
            lo++;
            hi--;
        }
        return true;
    }

    private void stringExp(String s){
        String[] strings = s.split(":");
        for (String str: strings) {
            System.out.println(str);
        }
    }
    private void sum(int[] ints, int sum){
        Arrays.sort(ints);
        int lo = 0, hi = ints.length-1;
        while( lo < hi){
            if(ints[lo]+ints[hi] == sum){
                System.out.println(ints[lo]+", "+ints[hi]);
            }
            if (ints[lo]+ints[hi] > sum)
                hi--;
            else lo++;
        }
    }

//    Assignment 12
//    Linear search
    private boolean lSearch(int inputs[], int value){
        System.out.println(11/2);
        if (inputs.length > 0){
            for (int i = 0; i < inputs.length; i++)
                if (inputs[i] == value) return true;
        }
        return false;
    }

//    Assignment 12
//    Binary search
    private int bSearch(int inputs[], int lo, int hi, int value){
        if(hi >= lo){
            int mid = lo+(hi - lo)/2;
            if (inputs[mid] == value)
                return mid;
            if (inputs[mid] > value)
                return bSearch(inputs, lo, mid -1, value);
            return bSearch(inputs, mid+1, hi, value);
        }
        return -1;
    }

//    Assignment 13
    private HashMap<Integer, String> sortedHashMap(HashMap<Integer, String> map){
//        create a list with the hashmap
        List<Map.Entry<Integer, String>> list =
                new LinkedList<Map.Entry<Integer, String>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, String>>() {
                    @Override
                    public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
                        return (o1.getValue()).compareTo(o2.getValue());
                    }
                }
        );
        HashMap<Integer, String> hashMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, String> entry : list) {
            hashMap.put(entry.getKey(), entry.getValue());
        }

        return hashMap;
    }

}
