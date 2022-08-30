import java.util.*;

public class ArraysNHashing {
//    main method
    public static void main(String[] args){
//        containsDuplicate(new int[]{1,2,3,1});
//        isAnagram("anagram", "nagaram");
//        System.out.println(containsNearbyAlmostDuplicate(new int[]{1,5,9,1,5,9}, 2, 3));
//        System.out.println(groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
//        System.out.println(findAnagrams("cbaebabacd", "abc"));
//        Arrays.stream(twoSum(new int[]{3,2,4}, 6)).forEach(i->System.out.println(i));
//        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
        System.out.println(threeSumClosest(new int[]{-1,2,1,-4}, 1));
    }

    /*
    217. Contains Duplicate
    https://leetcode.com/problems/contains-duplicate/
    * Given an integer array nums, return true if any value appears at least twice
    * in the array, and return false if every element is distinct.
    * */

    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i: nums) {
            if (set.contains(i))
                return true;
            set.add(i);
        }
        return false;
    }

    /*
    220. Contains Duplicate III
    * https://leetcode.com/problems/contains-duplicate-iii/
    * Given an integer array nums and two integers k and t,
    * return true if there are two distinct indices i and j in the array such that abs(nums[i] - nums[j]) <= t
    * and abs(i - j) <= k.
    * */
//    TLE
    public static boolean containsNearbyAlmostDuplicateTLE(int[] nums, int k, int t) {
//        first pick
        for (int i = 0; i < nums.length; i++) {
//            second pick
            for (int j = Math.max(i-k, 0); j < i; j++) {
                if (Math.abs(nums[i]-nums[j]) <= t) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
//            smallest successor
            Integer suc = treeSet.ceiling(nums[i]);
            if (suc != null && suc <= t+nums[i]){
                return true;
            }
//            biggest predecessor
            Integer pred = treeSet.floor(nums[i]);
            if (pred != null && pred >= nums[i]-t){
                return true;
            }
//            addition
            treeSet.add(nums[i]);
            if (treeSet.size() > k) {
                treeSet.remove(nums[i-k]);
            }
        }
        return false;
    }
    /*
    242. Valid Anagram
    https://leetcode.com/problems/valid-anagram/
    * Given two strings s and t, return true if t is an anagram of s,
    * and false otherwise.
    * */
    public static boolean isAnagram(String s, String t){
        if (s.length() != t.length()) return false;

        int[] store = new int[26];

        for (int i = 0; i < s.length(); i++) {
            store[s.charAt(i) - 'a']++;
            store[t.charAt(i) - 'a']--;
        }

        for (int n : store) {
            if (n!= 0)
                return false;
        }

        return true;
    }

    /*
    * 49. Group Anagrams
    * https://leetcode.com/problems/group-anagrams/
    * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
    * */
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> stringListMap = new HashMap<>();
        for (String s: strs) {
            int[] alph = new int[26];
            Arrays.fill(alph, 0);
            for (char c: s.toCharArray()) {
                ++alph[c-'a'];
            }
            StringBuilder stringBuilder = new StringBuilder("");
            for (int i = 0; i < alph.length; i++) {
                stringBuilder.append("#");
                stringBuilder.append(alph[i]);
            }
            String key = stringBuilder.toString();
            if (!stringListMap.containsKey(key)){
                stringListMap.put(key, new ArrayList<>());
            }
            stringListMap.get(key).add(s);
        }
        return new ArrayList<>(stringListMap.values());
    }

    /*
    *438. Find All Anagrams in a String
    *https://leetcode.com/problems/find-all-anagrams-in-a-string/
    *Given two strings s and p, return an array of all the start indices of p's anagrams in s.
    * You may return the answer in any order.
    * */
    public static List<Integer> findAnagrams(String s, String p) {
        if (s.length()<p.length()){
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        int[] charsS = new int[26];
        int[] charsP = new int[26];
        for (char c: p.toCharArray()) {
            ++charsP[c-'a'];
        }
        for (int i = 0; i < s.length(); i++) {
            ++charsS[s.charAt(i)-'a'];

            if (i >= p.length()){
                --charsS[s.charAt(i-p.length())-'a'];
            }

            if (Arrays.equals(charsS, charsP)){
                result.add(i-p.length()+1);
            }
        }
        return result;
    }
    /*
    1. Two Sum
    https://leetcode.com/problems/two-sum/
    Given an array of integers nums and an integer target,
    return indices of the two numbers such that they add up to target
    */
    public static int[] twoSum(int[] nums, int target){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])){
                return new int[]{map.get(nums[i]) , i};
            }
            map.put(target-nums[i], i);
        }
        return null;
    }

    /*
    * 15. 3Sum
    * https://leetcode.com/problems/3sum/
    * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
    * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
    * Notice that the solution set must not contain duplicate triplets.
    * */
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            if (i == 0 || nums[i-1] != nums[i]){
                twoSum(nums, i, result);
            }
        }
        return result;
    }

    private static void twoSum(int[] nums, int first, List<List<Integer>> result) {
        int loSecond = first+1;
        int hiThird = nums.length-1;
        while (loSecond < hiThird){
            int sum = nums[first]+nums[loSecond]+nums[hiThird];
            if (sum < 0){
                ++loSecond;
            }
            else if(sum > 0){
                --hiThird;
            }
            else{
                result.add(Arrays.asList(nums[first], nums[loSecond++], nums[hiThird--]));
                while (loSecond < hiThird && nums[loSecond] == nums[loSecond-1]){
                    ++loSecond;
                }
            }
        }
    }

    /*
    16. 3Sum Closest
    *https://leetcode.com/problems/3sum-closest/
    *Given an integer array nums of length n and an integer target,
    find three integers in nums such that the sum is closest to target.
    Return the sum of the three integers.
    * */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int lo = i + 1;
            int hi = nums.length - 1;
            int sum;
            while (lo < hi) {
                sum = nums[i] + nums[lo] + nums[hi];
                int newDiff = target - sum;
//                keep track the minimum difference
                if (Math.abs(newDiff) < Math.abs(diff)) {
                    diff = newDiff;
                }
//                change hi and lo based on the comparison between sum and target
                if (sum < target) {
                    ++lo;
                } else {
                    --hi;
                }
            }
        }
        return target - diff;
    }
}
