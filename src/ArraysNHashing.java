import java.util.*;

public class ArraysNHashing {
    /*
     *
     *
     *
     * todo:
     * */


//    main method
    public static void main(String[] args){
//        containsDuplicate(new int[]{1,2,3,1});
//        isAnagram("anagram", "nagaram");
//        System.out.println(containsNearbyAlmostDuplicate(new int[]{1,5,9,1,5,9}, 2, 3));
//        System.out.println(groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
//        System.out.println(findAnagrams("cbaebabacd", "abc"));
//        Arrays.stream(twoSum(new int[]{3,2,4}, 6)).forEach(i->System.out.println(i));
//        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
//        System.out.println(threeSumClosest(new int[]{-1,2,1,-4}, 1));
        System.out.println(threeSumSmaller(new int[]{3,1,0,-2}, 4));
    }

    /*
    217. Contains Duplicate
    https://leetcode.com/problems/contains-duplicate/
    * Given an integer array nums, return true if any value appears at least twice
    * in the array, and return false if every element is distinct.
    todo: set
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
    * return true if there are two distinct indices i and j in the array such that
    * abs(nums[i] - nums[j]) <= t
    * and abs(i - j) <= k.
    todo: treeset, pick a value consider it as i and j using celling and floor
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
    todo: check and uncheck from the start
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
    * todo: string to unique hash
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
    * todo: sliding window (insert)
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
    todo: hashMap key value
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
    * todo: two pointer
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
    *todo: two pointer hi-lo, difference
    */
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

    /*
    * 259. 3Sum Smaller
    *https://leetcode.com/problems/3sum-smaller/
    *Given an array of n integers nums and an integer target,
    * find the number of index triplets i, j, k with 0 <= i < j < k < n
    * that satisfy the condition nums[i] + nums[j] + nums[k] < target.
    * todo: 2 pointer
    * */
    public static int threeSumSmaller(int[] nums, int target) {
        int result = 0;
        if (nums.length < 3) return result;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i++) {
            int lo = i+1;
            int hi = nums.length-1;
            while(lo < hi){
                int sum = nums[i]+nums[lo]+nums[hi];
                if(sum < target){
                    result += hi-lo;
                    ++lo;
                }
                else {
                    --hi;
                }
            }
        }

        return result;
    }
    /*
    * 18. 4Sum
    *https://leetcode.com/problems/4sum/
    *Given an array nums of n integers,
    * return an array of all the unique quadruplets
    * [nums[a], nums[b], nums[c], nums[d]] such that:
    0 <= a, b, c, d < n
    a, b, c, and d are distinct.
    nums[a] + nums[b] + nums[c] + nums[d] == target
    You may return the answer in any order.
    * todo: two point hashset, boundary checking
    * */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }

    public List<List<Integer>> kSum(int[] nums, long target, int start, int k) {
        List<List<Integer>> result = new ArrayList<>();

        // If we have run out of numbers to add, return res.
        if (start == nums.length) {
            return result;
        }

        // There are k remaining values to add to the sum. The
        // average of these values is at least target / k.
        long average_value = target / k;

        // We cannot obtain a sum of target if the smallest value
        // in nums is greater than target / k or if the largest
        // value in nums is smaller than target / k.
        if  (nums[start] > average_value || average_value > nums[nums.length - 1]) {
            return result;
        }

        if (k == 2) {
            return twoSum(nums, target, start);
        }

        for (int i = start; i < nums.length; ++i) {
            if (i == start || nums[i - 1] != nums[i]) {
                for (List<Integer> subset : kSum(nums, target - nums[i], i + 1, k - 1)) {
                    result.add(new ArrayList<>(Arrays.asList(nums[i])));
                    result.get(result.size() - 1).addAll(subset);
                }
            }
        }

        return result;
    }

    public List<List<Integer>> twoSum(int[] nums, long target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Long> s = new HashSet<>();

        for (int i = start; i < nums.length; ++i) {
            if (res.isEmpty() || res.get(res.size() - 1).get(1) != nums[i]) {
                if (s.contains(target - nums[i])) {
                    res.add(Arrays.asList((int)target - nums[i], nums[i]));
                }
            }
            s.add((long)nums[i]);
        }

        return res;
    }

    /*
     *167. Two Sum II - Input Array Is Sorted
     *https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
     *Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
     *  find two numbers such that they add up to a specific target number
     * todo: two pointer
     * */
    public int[] twoSum2(int[] numbers, int target) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];

            if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else if (sum < target) {
                ++low;
            } else {
                --high;
            }
        }
        // In case there is no solution, return {-1, -1}.
        return new int[]{-1, -1};
    }
    /*
     *560. Subarray Sum Equals K
     *https://leetcode.com/problems/subarray-sum-equals-k/
     *Given an array of integers nums and an integer k,
     * return the total number of subarrays whose sum equals to k.
     * todo: cumulative sum, two pointer
     * */
    public static int subArraySum(int[] nums, int k){
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        int result = 0;
        int[] sum = new int[nums.length+1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i-1]+nums[i-1]; // cumulative sum
        }

        for (int one = 0; one < nums.length; one++) {
            for (int two = one+1; two <=nums.length ; two++) {
                if (sum[two] - sum[one] == k) ++result;
            }

        }

        return result;
    }
    /*
     *1480. Running Sum of 1d Array
     *https://leetcode.com/problems/running-sum-of-1d-array/?envType=study-plan&id=level-1
     *Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
     Return the running sum of nums.
     * todo: cumulative sum
     * */
    public static int[] runningSum(int[] nums){
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i-1];
        }
        return nums;
    }
    /*
     *724. Find Pivot Index
     *https://leetcode.com/problems/find-pivot-index/
     *Given an array of integers nums, calculate the pivot index of this array.

The pivot index is the index where the sum of all the numbers strictly to the
* left of the index is equal to the sum of all the numbers strictly to the index's right.
     * todo: prefix sum or cumulative sum
     * */
    public static int pivotIndex(int[] nums){
        int sum = 0, leftSum = 0;
        for (int num: nums) {
            sum+=num;
        }
        for (int i = 0; i < nums.length; i++) {
            if (leftSum == sum - nums[i]- leftSum) return i;
            leftSum+= nums[i];
        }
        return -1;
    }
    /*
     *1679. Max Number of K-Sum Pairs
     *https://leetcode.com/problems/max-number-of-k-sum-pairs/
     *You are given an integer array nums and an integer k.
    In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
    Return the maximum number of operations you can perform on the array.
     * todo: hashmap one pass
     * */
    public int maxOperations(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            int complement = k - current;
            if (map.getOrDefault(complement, 0) > 0) {
                map.put(complement, map.get(complement) - 1);
                count++;
            } else {
                map.put(current, map.getOrDefault(current, 0) + 1);
            }
        }
        return count;
    }

    /*
     *2023. Number of Pairs of Strings With Concatenation Equal to Target
     *https://leetcode.com/problems/number-of-pairs-of-strings-with-concatenation-equal-to-target/
     *Given an array of digit strings nums and a digit string target,
     * return the number of pairs of indices (i, j) (where i != j) such that the concatenation of nums[i] + nums[j] equals target
     * todo: hashmap
     * */
    public static int numOfPairs(String[] nums, String target) {
        int res = 0;
        Map<String, Integer> map = new HashMap<>();
        for (String num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (String num : nums) {
            if (num.length() >= target.length()) continue;
            String str = target.substring(num.length());
            if (target.startsWith(num) && map.containsKey(str)) {
                if (!num.equals(str)) {
                    res += map.get(str);
                } else {
                    res += map.get(str) - 1;
                }
            }
        }
        return res;
    }

    /*
    * 2374. Node With Highest Edge Score
    * https://leetcode.com/problems/node-with-highest-edge-score/
    *Check online for description
    * todo: hashmap
    * */
    public static int edgeScore(int[] edges){
        Map<Integer, Integer> scores = new HashMap<>();
        int highestScoreNode = 0;
        for (int i = 0; i < edges.length; i++) {
            scores.put(edges[i], scores.getOrDefault(edges[i], 0) + i);
            int lastScore = scores.get(edges[i]), highestScore = scores.getOrDefault(highestScoreNode, 0);
            if (lastScore > highestScore)
                highestScoreNode = edges[i];
            else if (lastScore == highestScore)
                highestScoreNode = Math.min(edges[i], highestScoreNode);
        }
        return highestScoreNode;
    }
}


