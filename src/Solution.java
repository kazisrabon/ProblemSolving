import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.*;

public class Solution {

    public ListNode deleteDuplicates(ListNode head) {
        if(head == null)
            return null;
        ListNode slow = head, fast = head;

        while(fast != null)
        {
            while(fast != null && slow.val == fast.val)
                fast = fast.next;
            slow.next = fast;
            slow = fast;
        }
        return head;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;

        else if (p == null)
            return false;

        else if (q == null)
            return false;

        else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right) && p.val == q.val;

        }
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;

        else{
            return isSymmentricChild(root.left, root.right);
        }
    }

    public boolean isSymmentricChild(TreeNode p, TreeNode q){
        if (p == null && q == null)
            return true;

        else if (p == null)
            return false;

        else if (q == null)
            return false;

        else {
            return isSymmentricChild(p.left, q.right) && isSymmentricChild(p.right, q.left) && p.val == q.val;

        }
    }

    public int maxDepth(TreeNode root) {
        int depth = 0;
        if (root != null) {
            depth += 1;
            depth = getMaxDepth(root.left, root.right, depth);

        }
        return depth;
    }

    public int getMaxDepth(TreeNode p, TreeNode q, int depth){
        if (p == null && q == null)
            return depth;

        else if (p == null)
            return getMaxDepth(q.left, q.right, depth+1);

        else if (q == null)
            return getMaxDepth(p.left, p.right, depth+1);

        else {
            int leftDepth = getMaxDepth(p.left, p.right, depth+1);
            int rightDepth = getMaxDepth(q.left, q.right, depth+1);
            if( leftDepth > rightDepth )
                return leftDepth;
            else return rightDepth;


        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> listList = new ArrayList<>();

        if (root != null){
            Queue<TreeNode> treeNodeQueue = new LinkedList<>();
            treeNodeQueue.offer(root);
            while (!treeNodeQueue.isEmpty()){
                int size = treeNodeQueue.size();
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode newRoot = treeNodeQueue.poll();
                    list.add(newRoot.val);

                    if (newRoot.left != null)
                        treeNodeQueue.offer(newRoot.left);

                    if (newRoot.right != null)
                        treeNodeQueue.offer(newRoot.right);
                }
                listList.add(list);
            }
            Collections.reverse(listList);
        }

        return listList;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0)
            return null;
        else if (nums.length == 1)
            return new TreeNode(nums[0]);

        else
            return sortedArrayToBST(nums, 0, nums.length-1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end)
            return null;

        int mid = (start + end) / 2;
        TreeNode treeNode = new TreeNode(nums[mid]);
        treeNode.left = sortedArrayToBST(nums, start, mid-1);
        treeNode.right = sortedArrayToBST(nums, mid+1, end);

        return treeNode;
    }

    public boolean isBalanced(TreeNode root) {
        int leftHeight;
        int rightHeight;

        if (root == null)
            return true;

        leftHeight = height(root.left);
        rightHeight = height(root.right);

        return (abs(leftHeight - rightHeight)) <= 1
                && isBalanced(root.left)
                && isBalanced(root.right);
    }

    private int height(TreeNode treeNode) {
        if (treeNode == null)
            return 0;

        return 1 + max(height(treeNode.left), height(treeNode.right));
    }

    public int minDepth(TreeNode root) {
        // Corner case. Should never be hit unless the code is
        // called on root = NULL
        if (root == null)
            return 0;

        // Base case : Leaf Node. This accounts for height = 1.
        if (root.left == null && root.right == null)
            return 1;

        // If left subtree is NULL, recur for right subtree
        if (root.left == null)
            return minDepth(root.right) + 1;

        // If right subtree is NULL, recur for left subtree
        if (root.right == null)
            return minDepth(root.left) + 1;

        return min(minDepth(root.left),
                minDepth(root.right)) + 1;
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;

        else
            return hasPathSum2(root.left, targetSum - root.val)
                    || hasPathSum2(root.right, targetSum - root.val);

    }

    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null && targetSum == 0)
            return true;

        else if (root == null)
            return false;

        else
            return hasPathSum2(root.left, targetSum - root.val)
                    || hasPathSum2(root.right, targetSum - root.val);
    }

    public boolean hasPathSum3(TreeNode root, int targetSum) {
        if(root==null)
            return false;
        targetSum = targetSum-root.val;


        if(root.left == null & root.right==null &&targetSum==0) return true;
        boolean left = hasPathSum3(root.left,targetSum);
        boolean right = hasPathSum3(root.right, targetSum);

        return left || right;

    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> listList = new ArrayList<>();

        for (int i = 0; i < numRows ; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i ; j++) {
                if (0 < j && j < i){
                    list.add(listList.get(i-1).get(j-1)+listList.get(i-1).get(j));
                }
                else {
                    list.add(1);
                }
            }
            listList.add(list);
        }

        return listList;
    }

    public List<Integer> getRow(int rowIndex) {
        return generate(rowIndex+1).get(rowIndex);
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;

        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();

        Arrays.sort(sChar);
        Arrays.sort(tChar);

        for (int i = 0; i < s.length(); i++) {
            if (sChar[i] != tChar[i])
                return false;
        }
        return true;
    }

    public int maxProfit(int[] prices) {
        int minimumPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minimumPrice)
                minimumPrice = prices[i];
            maxProfit = max(maxProfit, prices[i] - minimumPrice);
        }
        return maxProfit;
//        int min=Integer.MAX_VALUE;
//        int ans=0;
//        for(int i=0;i<prices.length;++i){
//            if(prices[i]<min){
//                min=prices[i];
//            }
//            ans=Math.max(ans,prices[i]-min);//always update our profit to maximum
//        }
//        return ans;
    }

    public int maxProfit2(int[] prices) {
        if (prices.length == 1)
            return 0;
        else {
            int maxProfit = 0;
            for (int i = 0; i < prices.length-1; i++) {
                for (int j = i+1; j < prices.length; j++) {
                    if (maxProfit < (prices[j] - prices[i]))
                        maxProfit = prices[j] - prices[i];
                }
            }
            return maxProfit;
        }
    }

    public boolean isLeapYear(int year){
        if (year % 4 == 0 ){
            if (year % 100 == 0 ){
                return year % 400 == 0;
            }
            else return true;
        }
        else return false;

    }

    public boolean AnagramPalindrome(String word) {
        char[] ch = word.toCharArray();
        List<Character> stack = new ArrayList<>();
        for (char c : ch) {
            if(stack.contains(c)){
                stack.remove(c);
            }
            else{
                stack.add(c);
            }
        }
        return stack.size() <= 1;

    }

    public static ArrayList<Integer> unique(int[] arr) {
        Set<Integer> integerSet = new HashSet<Integer>();
        for (int i: arr) {
            integerSet.add(i);
        }
        integerSet.forEach(System.out::println);
        ArrayList<Integer> list = new ArrayList<>(integerSet);
        // YOUR WORK HERE

        return list;
    }

    public static HashMap<String, Integer> wordCount(String sentence) {
        // YOUR WORK HERE
        String[] words = sentence.replaceAll("[^a-zA-Z ]", "").toLowerCase().trim().split(" ");
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        if (words.length == 0){
            hashMap.clear();
            return hashMap;
        }

        for (String s: words) {
            if (hashMap.containsKey(s))
                hashMap.put(s, hashMap.get(s)+1);
            else hashMap.put(s, 1);
        }
        return hashMap;
    }

    public static ArrayList<Integer> mergeList(List<Integer> a, List<Integer> b){
        ArrayList<Integer> result = new ArrayList<> ();
        int size = 0, l = 0, r = 0;

        while(size < a.size()+b.size()){

            if(l >= a.size()){
                if(r < b.size()){
                    result.add(b.get(r));
                    size++;
                    r++;
                }
            }
            else if(r >= b.size()){
                if(l < a.size()){
                    result.add(a.get(l));
                    size++;
                    l++;
                }
            }

            else if(a.get(l) <= b.get(r)){
                result.add(a.get(l));
                size++;
                l++;
            }

            else if(a.get(l) > b.get(r)){
                result.add(b.get(r));
                size++;
                r++;
            }
        }

        return result;

    }

    public static String characterMode(String string) {
        // YOUR WORK HERE
        string = string.replaceAll("[^a-zA-Z]", "").toLowerCase(Locale.ROOT);
        char[] chars = string.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c: chars) {
            if (map.containsKey(c)){
                map.put(c, map.get(c)+1);
            }
            else
                map.put(c, 1);
        }
        int maxVal = Collections.max(map.values());
        StringBuilder sb = new StringBuilder();
        for (char c: map.keySet()) {
            if (map.get(c) == maxVal)
                sb.append(c);
        }

        return sb.toString();
    }

    public static int sortDigits(int n) {
        // YOUR WORK HERE
        char[] chars = (n+"").replaceAll("0", "").toCharArray();
        int l = chars.length;
        ArrayList<Integer> ints = new ArrayList<>();
        for (char aChar : chars) ints.add(Integer.parseInt(aChar + ""));
        Collections.sort(ints);
        StringBuilder sb = new StringBuilder();
        for (int i: ints) sb.append(i);
        return Integer.parseInt(sb.toString());
    }

    public static ArrayList<Integer> getDuplicates(int[] arr) {
        // YOUR WORK HERE
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            if (map.containsKey(key)) map.put(key, map.get(key)+1);
            else map.put(key, 1);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int key: map.keySet()) {
            if (map.get(key) > 1)
                list.add(key);
        }

        return list;
    }

    public static boolean anagramPair(String string1, String string2) {
        // YOUR WORK HERE
        if (string1.length() != string2.length()) return false;
        
        char[] chars1 = string1.toLowerCase().toCharArray();
        char[] chars2 = string2.toLowerCase().toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < string1.length(); i++) {

            if (map.containsKey(chars1[i])) map.put(chars1[i], map.get(chars1[i]+1));
            else map.put(chars1[i], 1);

            if (map.containsKey(chars2[i])) map.put(chars2[i], map.get(chars2[i]-1));
            else map.put(chars2[i], -1);
        }

        return map.values().stream().allMatch(Objects::isNull);
    }

    public static int numberOfOnes(int[] input) {
        // YOUR WORK HERE
        if (input.length == 0)
            return 0;
        if (input[0] == 1) return input.length;
        if (input[input.length-1] == 0) return 0;
        int left = 0, right = input.length, pos = 0, index = input.length/2;
        while (index > left && index < right){
            if (input[index] == 1){
                pos = index;
                right = index;
                if (index == left) break;
                index = (index + left) / 2;
            }
            else {
                left = index;
                if (index == right) break;
                index = (index + right)/2;
            }
        }
       return  (pos > 0)?  input.length - pos :  0;
    }
    public static int binarySearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int mid;
        while(start <= end) {
            mid = (int) floor((start + end) >> 1);
            if(arr[mid] == target) { return mid; }
            if(target < arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static int numberOfOnes2(int[] arr) {
        int start=0;
        int end=arr.length-1;
        int mid=0;
        int indexval=-1;
        while(start<=end) {
            mid=(int)Math.floor((start + end) >> 1);
            if(arr[mid]==1) {
                indexval=mid;
                end=mid-1;
            }else {
                start=mid+1;
            }
        }

        return indexval!=-1?arr.length-indexval:0;
    }

    public static int compute(int m, int n) {
        // YOUR WORK HERE
        if (m == 0 && n == 0) return 1;
        if (m < 0 || n < 0) return 0;
        int left = compute(m-1, n);
        int right = compute(m, n-1);

        return left+right;
    }

    public static ArrayList<String> list = new ArrayList<>();
    public static String string;

    public static List<String> compute(String str) {
        // YOUR WORK HERE
        string = str;
        findCombos("", 0);
        return list;
    }

    public static void findCombos(String s, int i){
        if (i == string.length()){
            list.add(s);
            return;
        }
        char cChar = string.charAt(i);
        findCombos(s, i+1);
        findCombos(s+cChar, i+1);
    }

    public static int closestValue(int[] arr, int target) {
        // YOUR WORK HERE
        int start = 0;
        int end = arr.length - 1;
        int mid;
        int temp = 0, diff = Integer.MAX_VALUE;
        while(start <= end) {
            mid = (int) floor((start + end) >> 1);
            if(arr[mid] == target) { return arr[mid]; }
            if(target < arr[mid]) {
                if (diff > Math.abs(arr[mid] - target))
                    temp = arr[mid];
                end = mid - 1;
            } else {
                if (diff > Math.abs(arr[mid] - target))
                    temp = arr[mid];
                start = mid + 1;
            }
        }
        return temp;
    }

    public static Double squareRoot(Double number) {
        // YOUR WORK HERE
        double t;
        double squareroot = number / 2;
        do
        {
            t = squareroot;
            squareroot = (t + (number / t)) / 2;
        }
        while ((t - squareroot) >= 0.000001);
        String s = String.format("%.6f", squareroot);
        return Double.parseDouble(s);
    }

    public static int greaterValues(int[] a, int target) {
        // YOUR WORK HERE

        HashSet<Integer> set = new HashSet<>(Arrays.stream(a).boxed().collect(Collectors.toList()));
        System.out.println(set);
        int[] arr = new int[set.size()];
        int pos = 0;
        for (int i:set) {
            arr[pos] = i;
        }
        Arrays.sort(arr);
        Arrays.stream(arr).forEach(System.out::println);
        int start = 0;
        int end = arr.length - 1;
        int mid;
        int temp = 0, diff = Integer.MAX_VALUE;
        int pos2 = -1;
        while(start <= end) {
            mid = (int) floor((start + end) >> 1);
            if(arr[mid] == target) {
                pos2 = mid;
                break;
            }
            if(target < arr[mid]) {
                if (diff > Math.abs(arr[mid] - target))
                    pos2 = mid;
                end = mid - 1;
            } else {
                if (diff > Math.abs(arr[mid] - target))
                    pos2 = mid;
                start = mid + 1;
            }
        }
        System.out.println(pos2);


        return -1;
    }

    public static String minWindow(String s, String r){
        if(s == "" || r == "") return "";
        if (r.length() == 1) return s.contains(r) ? r : "";
        String result = "";
        Map<Character, Integer> map= new HashMap<>();
        populateMap(map, r);
        System.out.println(map);
        int left = 0, right = 0, min = Integer.MAX_VALUE;
        int l = r.length();
        int secondPos = 0;
        int flag = 0;
//        int size = 0;
        char c;
        for (int i = 0; i < s.length() - r.length() + 1; i = secondPos) {
            c = s.charAt(i);
            if (isImportant(r, c)){
                left = i;
//                size++;
                map.put(c, map.get(c)-1);

                for (int j = i+1; hasValue(map); j++) {
                    if (j >= s.length()) break;
                    c = s.charAt(j);
                    if(isImportant(r, c)){
                        flag++;
                        if (flag == 1)
                            secondPos = j;
                        if (map.get(c) > 0) {
                            right = j;
                            map.put(c, map.get(c) - 1);
                        }
                    }
                }
                if(!hasValue(map)) {
                    flag = 0;
                    int i1 = right - left;
                    if (i1 < min) {
                        min = i1;
                        result = s.substring(left, right + 1);
                    }
                    populateMap(map, r);
                    right =0;
                }
                else break;
            }
            else secondPos++;
        }
        return result;
    }

    private static boolean hasValue(Map<Character, Integer> map) {
        for (char c: map.keySet()) {
            if (map.get(c) > 0) return true;
        }
        return false;
    }

    private static void populateMap(Map<Character, Integer> map, String r) {
        for (char c: r.toCharArray()) {
            if (map.containsKey(c)) map.put(c, map.get(c)+1);
            else map.put(c, 1);
        }
    }

    public static boolean isImportant(String r, char c){
        for (char c1:
             r.toCharArray()) {
            if (c1 == c) return true;
        }
        return false;
    }

    public static void main(String[] args){
        Solution solution = new Solution();
//        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(minWindow("BBA", "BA"));
//        System.out.println(minWindow("AA", "A"));
//        System.out.println(minWindow("AB", "AB"));
//        System.out.println(minWindow("AB", ""));
//        System.out.println(greaterValues(new int[] {1, 10, 22, 59, 67, 72, 100}, 0));
//        System.out.println(squareRoot(98.0));
//        System.out.println(closestValue(new int[]{1, 2, 3, 5, 5, 7, 9, 10, 11}, 6));
//        int output = FCProblems.sortDigits(8970);
//        ArrayList<Integer> output = getDuplicates(new int[] {3, 2, 3, 2, 3, 3, 4});
//        System.out.println(anagramPair("racecarp", "aaccrreq"));
//        System.out.println(numberOfOnes2(new int[]{1,1,1,1}));
//        System.out.println(compute(1, 1));
//        List<String> result = compute("abcdefg");
//        List<String> answer = Arrays.asList("","g","f","fg","e","eg","ef","efg","d",
//                "dg","df","dfg","de","deg","def","defg","c","cg","cf","cfg","ce","ceg",
//                "cef","cefg","cd","cdg","cdf","cdfg","cde","cdeg","cdef","cdefg","b","bg",
//                "bf","bfg","be","beg","bef","befg","bd","bdg","bdf","bdfg","bde","bdeg",
//                "bdef","bdefg","bc","bcg","bcf","bcfg","bce","bceg","bcef","bcefg","bcd",
//                "bcdg","bcdf","bcdfg","bcde","bcdeg","bcdef","bcdefg","a","ag","af","afg",
//                "ae","aeg","aef","aefg","ad","adg","adf","adfg","ade","adeg","adef",
//                "adefg","ac","acg","acf","acfg","ace","aceg","acef","acefg","acd","acdg",
//                "acdf","acdfg","acde","acdeg","acdef","acdefg","ab","abg","abf","abfg",
//                "abe","abeg","abef","abefg","abd","abdg","abdf","abdfg","abde","abdeg",
//                "abdef","abdefg","abc","abcg","abcf","abcfg","abce","abceg","abcef",
//                "abcefg","abcd","abcdg","abcdf","abcdfg","abcde","abcdeg","abcdef","abcdefg");
//
//        Collections.sort(result);
//        Collections.sort(answer);
//
//        System.out.println(result.equals(answer));


//        mergeList(Arrays.stream(new int[]{1, 2, 4}).boxed().collect(Collectors.toList()),
//                Arrays.stream(new int[]{1, 2, 3}).boxed().collect(Collectors.toList()))
//                .forEach(System.out::println);
//        unique(new int[]{1, 2, 4, 4, 5, 6});
//        HashMap<String, Integer> output = FCProblems.wordCount("");
//        for (String s:
//             output.keySet()) {
//            System.out.println(s+" "+output.get(s));
//        }
//        solution.AnagramPalindrome("abba");

//        TreeNode one = new TreeNode(1);
//        TreeNode two = new TreeNode(2);
//        TreeNode three = new TreeNode(3);
//        TreeNode four = new TreeNode(4);
//        TreeNode t1 = new TreeNode(1, two, one);
//        TreeNode t2 = new TreeNode(1, one, two);
//        TreeNode t3 = new TreeNode(1, two, three);
//        TreeNode t4 = new TreeNode(1, two, three);
//        TreeNode t5 = new TreeNode(1, two, null);
//        TreeNode t6 = new TreeNode(1, null, two);
//        TreeNode p1 = new TreeNode(2, three, four);
//        TreeNode p2 = new TreeNode(2, four, three);
//        TreeNode p3 = new TreeNode( 1, p1, p2);
//        if (solution.isSameTree(t5, t6))
//            System.out.println("same");
//        else System.out.println("not same");
//        if (solution.isSymmetric(p3))
//            System.out.println("symmetric");
//        else System.out.println("not symmetric");
//        System.out.println(solution.maxDepth(p3));
//        if (solution.isBalanced(p3)){
//            System.out.println("true");
//        }
//        else System.out.println("false");

//        fizzbuzz(100);

//        int[] ints = new int[]{ 1,9,6,9,1,7,1,1,5,9,9,9 };
//        System.out.println(solution.maxProfit3(ints));
//        if (solution.isPalindrome("0P")){
//            System.out.println("true");
//        }
//        else System.out.println("false");
//
//        System.out.println(solution.singleNumber(new int[]{}));

//        MinStack minStack = new MinStack();
//        minStack.push(-2);
//        minStack.push(0);
//        minStack.push(-3);
//        System.out.println(minStack.getMin()); // return -3
//        minStack.pop();
//        System.out.println(minStack.top());    // return 0
//        System.out.println(minStack.getMin()); // return -2
//        List<String> strings = new ArrayList<>();
//        strings.add("RGRGRGRGLGR");
//        strings.add("RGRGRGRGLG");
//        for (String s : solution.problem2(strings)) {
//            System.out.println(s);
//        }
//        System.out.println("good "+solution.findPasswordStrength2("good"));
//        System.out.println("test "+solution.findPasswordStrength2("test"));
//        System.out.println("abc "+solution.findPasswordStrength2("abc"));
//        int a = 12%10;
//        a = 12/ 10;
//        System.out.println(a);
    }

    private long findPasswordStrength1(String password) {
        int n = password.length();
        long result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String s = password.substring(i, j);
                result += s.chars().distinct().count();
            }
        }
        return result;
    }

    private long findPasswordStrength2(String s) {
        int ans = 0;
        int count = 0;
        int[] lastCount = new int[26];
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        for (int i = 0; i < s.length(); ++i) {
            final int c = s.charAt(i) - 'a';
            final int currentCount = i - lastSeen[c];
            count = count - lastCount[c] + currentCount;
            lastCount[c] = currentCount;
            lastSeen[c] = i;
            ans += count;
        }

        return ans;
    }

    private long findPasswordStrength3(String password) {
        int res = 0;
        if (password == null || password.length() == 0)
            return res;
        int[] showLastPosition = new int[128];
        int[] contribution = new int[128];
        int cur = 0;
        for (int i = 0; i < password.length(); i++) {
            char x = password.charAt(i);
            cur -= contribution[x]; // removing contribution of last occurence of ith char
            contribution[x] = (i - (showLastPosition[x] - 1)); // reset the contribution of ith char
            cur += contribution[x]; // add the contribution of ith char
            showLastPosition[x] = i + 1; // update the last occurence of ith char
            res += cur;
        }
        return res;
    }

    public int maxProfit3(int[] prices) {

        int buyPrice = Integer.MAX_VALUE, sellPrice = -1, profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < buyPrice) {
                if (sellPrice > -1){
                    profit = profit + sellPrice - buyPrice;
                    sellPrice = -1;
                }
                buyPrice = prices[i];
            }
            else if (prices[i] > buyPrice && prices[i] >= sellPrice){
                sellPrice = prices[i];
                if (i == prices.length-1)
                    profit = profit + sellPrice -buyPrice;
            }
            else if(prices[i] < sellPrice){
                profit = profit + sellPrice - buyPrice;
                buyPrice = prices[i];
                sellPrice = -1;
            }
        }

        return profit;
    }

    private static void fizzbuzz(int i) {
        for (int j = 1; j <= i; j++) {
            if (j % 3 == 0 && j % 5 == 0) System.out.println("fizzbuzz");
            else if (j % 3 == 0) System.out.println("fizz");
            else if (j % 5 == 0) System.out.println("buzz");
            else System.out.println(j);
        }
    }
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int low = 0;
        int high = s.length()-1;

        while(low <= high){
            char low_char = s.charAt(low);
            char high_char = s.charAt(high);

            if (!((low_char >= 'a' && low_char <= 'z')
                    ||(low_char >= '0' && low_char <= '9')))
                low++;
            else if (!((high_char >= 'a' && high_char <= 'z')
                    ||(high_char >= '0' && high_char <= '9')))
                high--;
            else if(low_char == high_char){
                low++;
                high--;
            }
            else return false;

        }
        return true;
    }


    //    Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public boolean hasCycle(ListNode head) {
        ListNode node = head;
        if (node == null) return false;
        ListNode node1 = node.next;
        while (node1 != null){
            if (node1.next == null) return false;
            if (node == node1) return true;
            node = node.next;
            node1 = node1.next.next;
        }
        return false;
    }

//    Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int singleNumber(int[] nums) {
        int a = 0;
        for (int i : nums) {
            a ^= i;
        }
        return a;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        List<ListNode> listNodes = new ArrayList<>();
        while (headA != null){
            listNodes.add(headA);
            headA = headA.next;
        }
        while (headB != null){
            if (listNodes.contains(headB)) return headB;
            headB = headB.next;
        }
        return null;
    }

    public List<String> problem2(List<String> inputs){

        List<String> strings = new ArrayList<>();
        for (String s: inputs) {
            String s1 = s+s+s+s;
            int x = 0, y = 0, dir = 0;

            for (int i = 0; i < s1.length(); i++) {
                char c = s1.charAt(i);
                if (c == 'R')
                    dir = (dir+1)%4;
                else if (c == 'L')
                    dir = (4+dir-1)%4;
                else {
                    if (dir == 0){
                        y++;
                    }
                    else if (dir == 1){
                        x++;
                    }
                    else if (dir == 2){
                        y--;
                    }
                    else {
                        x--;
                    }
                }
            }
            if (x == 0 && y == 0)
                strings.add("YES");
            else strings.add("NO");
        }
        return strings;
    }

}
