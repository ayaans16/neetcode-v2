# LeetCode in Java: Notes for NeetCode Blind 75 and the Google OA
Written assuming you know nothing about competitive-style Java or DSA patterns.

---

## Part 1: How a LeetCode Java Solution Is Shaped

You never write `main`. LeetCode gives you a class and one method to fill in:

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // your code
        return new int[]{};
    }
}
```

- Inputs come in as parameters, and you `return` the answer.
- Helper methods go inside `Solution` (make them `private`).
- Google OAs are usually on a different platform (CodeSignal, HackerRank, or Google's own). There you may need to read input yourself. See Part 6.

### Imports you will constantly use
```java
import java.util.*;          // covers List, Map, Set, Queue, Deque, PriorityQueue, Arrays, Collections
```
On LeetCode, `java.util.*` is usually pre-imported. In other platforms, add it yourself.

---

## Part 2: Java Basics for Problem Solving

### Types
| Type | Notes |
|---|---|
| `int` | 32-bit, max about 2.1 billion (`Integer.MAX_VALUE`) |
| `long` | 64-bit. Use for sums or products that can overflow. Write literals as `1L` |
| `char` | single character, `'a'`. It is also a number: `'a' + 1` is 98 |
| `boolean` | `true` / `false` |
| `double` | decimals |
| `String` | immutable text (see below) |

**Overflow is the #1 silent bug.** If `a + b` could exceed about 2.1 billion, use `long`.
Midpoint: write `mid = lo + (hi - lo) / 2`, never `(lo + hi) / 2`.

### Arrays
```java
int[] a = new int[5];              // [0,0,0,0,0]
int[] b = {1, 2, 3};
int n = b.length;                  // NOTE: .length, no parentheses
int[][] grid = new int[3][4];      // 3 rows, 4 cols
int cols = grid[0].length;
Arrays.fill(a, -1);
Arrays.sort(a);                    // in place, ascending
Arrays.toString(a);                // for printing
Arrays.copyOf(a, a.length);        // copy
Arrays.copyOfRange(a, 1, 3);       // indices 1..2
```

### Strings (immutable, so you cannot change a character in place)
```java
String s = "hello";
s.length();                        // .length() WITH parentheses for Strings
s.charAt(0);                       // 'h'
s.substring(1, 3);                 // "el" (end index exclusive)
s.equals("hello");                 // NEVER use == for strings
s.toCharArray();                   // char[] you can modify
String.valueOf(charArray);         // char[] back to String
s.indexOf('l');                    // -1 if missing
s.split(" ");                      // String[]
s.toLowerCase();
Character.isLetterOrDigit(c);
Character.isDigit(c);
Character.isLetter(c);
c - 'a'                            // 0..25 index for lowercase letters
```

**StringBuilder** is for building strings in loops. `+=` in a loop is O(n²).
```java
StringBuilder sb = new StringBuilder();
sb.append('x');
sb.append("abc");
sb.reverse();
sb.insert(0, 'y');
sb.deleteCharAt(sb.length() - 1);
sb.toString();
```

### Loops
```java
for (int i = 0; i < n; i++) { }
for (int x : nums) { }             // for-each
while (cond) { }
```

### Integer vs int (boxing gotcha)
Collections hold objects (`Integer`), not primitives.
```java
List<Integer> list = new ArrayList<>();      // OK
List<int> bad;                               // does NOT compile
```
Comparing two `Integer` objects with `==` can be wrong for values above 127. Use `.equals()`:
```java
if (list.get(0).equals(list.get(1))) { }
```

---

## Part 3: The Collections You Need (the whole toolkit)

### ArrayList: resizable array
```java
List<Integer> list = new ArrayList<>();
list.add(5);
list.add(0, 9);                    // insert at index (O(n))
list.get(i);
list.set(i, 7);
list.remove(list.size() - 1);      // remove last, O(1)
list.size();
list.contains(5);                  // O(n)
```
**Trap:** `list.remove(1)` removes at *index* 1. To remove the *value* 1, use `list.remove(Integer.valueOf(1))`.

### HashMap: key to value, O(1) average
```java
Map<Integer, Integer> map = new HashMap<>();
map.put(k, v);
map.get(k);                        // null if missing
map.getOrDefault(k, 0);
map.containsKey(k);
map.put(k, map.getOrDefault(k, 0) + 1);   // COUNTING pattern
map.merge(k, 1, Integer::sum);            // same, shorter
map.computeIfAbsent(k, x -> new ArrayList<>()).add(v);   // GROUPING pattern
for (Map.Entry<Integer,Integer> e : map.entrySet()) { e.getKey(); e.getValue(); }
map.remove(k);
```

### HashSet: uniqueness and O(1) lookup
```java
Set<Integer> set = new HashSet<>();
set.add(x);                        // returns false if already present
set.contains(x);
set.remove(x);
```

### Stack / Queue / Deque
Use `ArrayDeque` for all of them (the old `Stack` class is slow and discouraged).
```java
Deque<Integer> stack = new ArrayDeque<>();
stack.push(x);  stack.pop();  stack.peek();   // top of stack
stack.isEmpty();

Queue<Integer> q = new ArrayDeque<>();
q.offer(x);  q.poll();  q.peek();             // FIFO (BFS)

Deque<Integer> dq = new ArrayDeque<>();
dq.offerFirst(x); dq.offerLast(x);
dq.pollFirst();   dq.pollLast();
dq.peekFirst();   dq.peekLast();
```
`ArrayDeque` does not allow `null`.

### PriorityQueue: heap (min-heap by default)
```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);   // custom
pq.offer(x); pq.poll(); pq.peek(); pq.size();
```
For a comparator with large numbers, prefer `Integer.compare(a[0], b[0])` over subtraction, which can overflow.

### TreeMap / TreeSet: sorted, O(log n)
```java
TreeMap<Integer,Integer> tm = new TreeMap<>();
tm.firstKey(); tm.lastKey();
tm.floorKey(x);      // greatest key <= x
tm.ceilingKey(x);    // smallest key >= x
```

### Sorting with a comparator
```java
Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));   // int[][]
Collections.sort(list);
list.sort((a, b) -> b - a);                                       // descending
Integer[] boxed = ...; Arrays.sort(boxed, Collections.reverseOrder());
// int[] CANNOT take a comparator; you must box it or sort ascending and reverse.
```

### Converting between types
```java
int[] arr = list.stream().mapToInt(i -> i).toArray();     // List<Integer> to int[]
List<Integer> l = new ArrayList<>(); for (int x : arr) l.add(x);
new ArrayList<>(set);                                      // Set to List
res.toArray(new int[res.size()][]);                        // List<int[]> to int[][]
```

### Returning List<List<Integer>>
```java
List<List<Integer>> res = new ArrayList<>();
res.add(new ArrayList<>(current));     // COPY, otherwise later mutation corrupts it
res.add(Arrays.asList(1, 2, 3));
```

---

## Part 4: Big-O Cheat Sheet

| n (input size) | Aim for |
|---|---|
| up to about 10-20 | O(2ⁿ), O(n!), brute-force backtracking |
| up to about 500 | O(n³) |
| up to about 5,000 | O(n²) |
| up to about 100,000 | O(n log n) or O(n) |
| up to about 10⁶ or more | O(n) or O(log n) |

Read the constraints first. They tell you what complexity is expected.

| Operation | Cost |
|---|---|
| Array index | O(1) |
| HashMap/HashSet get/put | O(1) average |
| ArrayList add at end | O(1) amortized |
| ArrayList insert/remove at front | O(n) |
| Sort | O(n log n) |
| Heap push/pop | O(log n) |
| TreeMap ops | O(log n) |

---

## Part 5: The Blind 75 by Pattern
Learn the pattern, not the problem. Each template is followed by the problems it solves.

### Pattern 1: Hash Map / Hash Set
**Idea:** trade memory for speed. Remember what you have seen.

```java
// Two Sum
public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> seen = new HashMap<>();   // value -> index
    for (int i = 0; i < nums.length; i++) {
        int need = target - nums[i];
        if (seen.containsKey(need)) return new int[]{seen.get(need), i};
        seen.put(nums[i], i);
    }
    return new int[]{};
}
```
```java
// Contains Duplicate
public boolean containsDuplicate(int[] nums) {
    Set<Integer> s = new HashSet<>();
    for (int x : nums) if (!s.add(x)) return true;
    return false;
}
```
**Problems:** Two Sum, Contains Duplicate, Valid Anagram (count letters with `int[26]`), Group Anagrams (key = sorted string), Longest Consecutive Sequence (put all in a set, only start counting from numbers with no `x-1`).

### Pattern 2: Two Pointers
**Idea:** two indices moving toward each other or in the same direction. It usually needs sorted input or a symmetric structure.

```java
// Valid Palindrome
public boolean isPalindrome(String s) {
    int l = 0, r = s.length() - 1;
    while (l < r) {
        while (l < r && !Character.isLetterOrDigit(s.charAt(l))) l++;
        while (l < r && !Character.isLetterOrDigit(s.charAt(r))) r--;
        if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) return false;
        l++; r--;
    }
    return true;
}
```
**Problems:** Valid Palindrome, 3Sum (sort, fix one number, two-pointer the rest, skip duplicates), Container With Most Water (move the shorter side inward), Trapping Rain Water.

### Pattern 3: Sliding Window
**Idea:** keep a window `[l, r]`. Expand `r`; shrink `l` while the window is invalid. It is used for "longest/shortest substring or subarray such that..."

```java
// Longest Substring Without Repeating Characters
public int lengthOfLongestSubstring(String s) {
    Set<Character> window = new HashSet<>();
    int l = 0, best = 0;
    for (int r = 0; r < s.length(); r++) {
        while (window.contains(s.charAt(r))) {
            window.remove(s.charAt(l));
            l++;
        }
        window.add(s.charAt(r));
        best = Math.max(best, r - l + 1);
    }
    return best;
}
```
**Problems:** Best Time to Buy and Sell Stock (track min so far), Longest Substring Without Repeating, Longest Repeating Character Replacement, Minimum Window Substring, Permutation in String.

### Pattern 4: Stack
**Idea:** the last thing opened is the first thing closed. It is also used for "next greater element" (monotonic stack).

```java
// Valid Parentheses
public boolean isValid(String s) {
    Deque<Character> st = new ArrayDeque<>();
    for (char c : s.toCharArray()) {
        if (c == '(' || c == '[' || c == '{') st.push(c);
        else {
            if (st.isEmpty()) return false;
            char open = st.pop();
            if ((c == ')' && open != '(') || (c == ']' && open != '[') || (c == '}' && open != '{')) return false;
        }
    }
    return st.isEmpty();
}
```
**Problems:** Valid Parentheses, Min Stack, Evaluate Reverse Polish Notation, Daily Temperatures, Car Fleet, Largest Rectangle in Histogram.

### Pattern 5: Binary Search
**Idea:** halve the search space each step. It works on sorted data or on any yes/no condition that flips once ("binary search on the answer").

```java
public int search(int[] nums, int target) {
    int lo = 0, hi = nums.length - 1;
    while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;
        if (nums[mid] == target) return mid;
        else if (nums[mid] < target) lo = mid + 1;
        else hi = mid - 1;
    }
    return -1;
}
```
**Problems:** Binary Search, Search in Rotated Sorted Array, Find Minimum in Rotated Sorted Array, Koko Eating Bananas (search the answer), Time Based Key-Value Store, Median of Two Sorted Arrays (hard).

### Pattern 6: Linked List
Node definition (given by LeetCode):
```java
class ListNode { int val; ListNode next; ListNode(int x) { val = x; } }
```
**Core techniques:**
- **Dummy head:** `ListNode dummy = new ListNode(0); ListNode tail = dummy;` avoids head special-cases. Return `dummy.next`.
- **Reverse:**
```java
public ListNode reverseList(ListNode head) {
    ListNode prev = null, cur = head;
    while (cur != null) {
        ListNode next = cur.next;
        cur.next = prev;
        prev = cur;
        cur = next;
    }
    return prev;
}
```
- **Fast/slow pointers:** find the middle, or detect a cycle (`fast` moves 2, `slow` moves 1; they meet if there is a cycle).

**Problems:** Reverse Linked List, Merge Two Sorted Lists, Linked List Cycle, Reorder List (find middle, reverse second half, merge), Remove Nth Node From End (fast pointer starts n ahead), Merge K Sorted Lists (min-heap), LRU Cache (HashMap + doubly linked list).

### Pattern 7: Trees (recursion)
```java
class TreeNode { int val; TreeNode left, right; TreeNode(int x) { val = x; } }
```
**DFS template:** solve for the children, then combine.
```java
public int maxDepth(TreeNode root) {
    if (root == null) return 0;                           // base case FIRST
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
}
```
**BFS (level order) template:**
```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) return res;
    Queue<TreeNode> q = new ArrayDeque<>();
    q.offer(root);
    while (!q.isEmpty()) {
        int size = q.size();                              // freeze level size
        List<Integer> level = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TreeNode n = q.poll();
            level.add(n.val);
            if (n.left != null) q.offer(n.left);
            if (n.right != null) q.offer(n.right);
        }
        res.add(level);
    }
    return res;
}
```
**BST validation:** pass bounds down. Use `long` bounds or `Integer` (null) to avoid edge cases at `Integer.MIN_VALUE`.
```java
boolean valid(TreeNode n, long lo, long hi) {
    if (n == null) return true;
    if (n.val <= lo || n.val >= hi) return false;
    return valid(n.left, lo, n.val) && valid(n.right, n.val, hi);
}
```
**Problems:** Invert Binary Tree, Max Depth, Same Tree, Subtree of Another Tree, LCA of BST/Binary Tree, Level Order Traversal, Validate BST, Kth Smallest in BST (inorder), Construct Tree from Preorder+Inorder, Binary Tree Maximum Path Sum (hard; use a global max), Serialize/Deserialize (hard), Diameter of Binary Tree, Balanced Binary Tree.

### Pattern 8: Heap / Priority Queue
**Idea:** you need repeated access to the smallest or largest element. "Top K" means a **min-heap of size K**.

```java
// Kth Largest Element
public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> heap = new PriorityQueue<>();  // min-heap
    for (int x : nums) {
        heap.offer(x);
        if (heap.size() > k) heap.poll();                 // evict smallest
    }
    return heap.peek();
}
```
**Problems:** Kth Largest, Top K Frequent Elements (map counts, then heap), Find Median from Data Stream (max-heap for the low half plus min-heap for the high half), Merge K Sorted Lists, Task Scheduler.

### Pattern 9: Backtracking
**Idea:** try a choice, recurse, undo the choice. It is used for "all combinations, subsets, permutations".

```java
// Subsets
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    backtrack(nums, 0, new ArrayList<>(), res);
    return res;
}
private void backtrack(int[] nums, int start, List<Integer> cur, List<List<Integer>> res) {
    res.add(new ArrayList<>(cur));                 // copy!
    for (int i = start; i < nums.length; i++) {
        cur.add(nums[i]);                          // choose
        backtrack(nums, i + 1, cur, res);          // explore
        cur.remove(cur.size() - 1);                // un-choose
    }
}
```
- Combination Sum: recurse with `i` (reuse allowed) instead of `i + 1`.
- Duplicates in input: sort first, then `if (i > start && nums[i] == nums[i-1]) continue;`.

**Problems:** Subsets, Combination Sum, Permutations, Subsets II, Word Search (grid DFS), N-Queens.

### Pattern 10: Graphs (BFS/DFS on grids and adjacency lists)
**Grid DFS (Number of Islands):**
```java
public int numIslands(char[][] grid) {
    int count = 0;
    for (int r = 0; r < grid.length; r++)
        for (int c = 0; c < grid[0].length; c++)
            if (grid[r][c] == '1') { dfs(grid, r, c); count++; }
    return count;
}
private void dfs(char[][] g, int r, int c) {
    if (r < 0 || c < 0 || r >= g.length || c >= g[0].length || g[r][c] != '1') return;
    g[r][c] = '0';                                   // mark visited
    dfs(g, r + 1, c); dfs(g, r - 1, c); dfs(g, r, c + 1); dfs(g, r, c - 1);
}
```
**Four directions trick:**
```java
int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
for (int[] d : dirs) { int nr = r + d[0], nc = c + d[1]; }
```
**Building an adjacency list:**
```java
List<List<Integer>> adj = new ArrayList<>();
for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
for (int[] e : edges) { adj.get(e[0]).add(e[1]); adj.get(e[1]).add(e[0]); }  // undirected
```
**Shortest path in an unweighted graph or grid = BFS.**
**Topological sort (Course Schedule):** compute in-degrees, BFS from in-degree 0, count nodes processed. If the count is not `n`, there is a cycle.
**Union-Find** (Number of Connected Components, Graph Valid Tree, Redundant Connection):
```java
int[] parent = new int[n];
for (int i = 0; i < n; i++) parent[i] = i;
int find(int x) { return parent[x] == x ? x : (parent[x] = find(parent[x])); }
boolean union(int a, int b) {
    int ra = find(a), rb = find(b);
    if (ra == rb) return false;                       // already connected (cycle)
    parent[ra] = rb; return true;
}
```
**Problems:** Number of Islands, Clone Graph, Pacific Atlantic Water Flow, Course Schedule I/II, Number of Connected Components, Graph Valid Tree, Longest Consecutive Sequence, Alien Dictionary (hard), Max Area of Island, Rotting Oranges.

### Pattern 11: Dynamic Programming
**Idea:** the answer to a big problem is built from answers to smaller overlapping subproblems. Steps:
1. Define `dp[i]` in plain English.
2. Write the recurrence.
3. Set base cases.
4. Choose the fill order.
5. Optimize space if possible.

```java
// Climbing Stairs: dp[i] = dp[i-1] + dp[i-2]
public int climbStairs(int n) {
    if (n <= 2) return n;
    int a = 1, b = 2;
    for (int i = 3; i <= n; i++) { int c = a + b; a = b; b = c; }
    return b;
}
```
```java
// Coin Change: dp[a] = min coins to make amount a
public int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1);        // "infinity"
    dp[0] = 0;
    for (int a = 1; a <= amount; a++)
        for (int c : coins)
            if (c <= a) dp[a] = Math.min(dp[a], dp[a - c] + 1);
    return dp[amount] > amount ? -1 : dp[amount];
}
```
**Kinds of DP in Blind 75:**
- **1D linear:** Climbing Stairs, House Robber I/II, Decode Ways, Maximum Subarray (Kadane), Maximum Product Subarray, Word Break, Longest Increasing Subsequence.
- **Knapsack-ish:** Coin Change, Coin Change II, Partition Equal Subset Sum.
- **2D grid / string:** Unique Paths, Longest Common Subsequence.

**Kadane's (Maximum Subarray):**
```java
int best = nums[0], cur = nums[0];
for (int i = 1; i < nums.length; i++) {
    cur = Math.max(nums[i], cur + nums[i]);
    best = Math.max(best, cur);
}
```

### Pattern 12: Greedy
**Idea:** take the locally best choice and it turns out globally correct. Proofs are hard, so recognize the problems.
**Problems:** Jump Game (track the farthest reachable index), Jump Game II, Maximum Subarray, Gas Station.

### Pattern 13: Intervals
**Idea:** sort by start, then sweep.
```java
public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
    List<int[]> res = new ArrayList<>();
    for (int[] cur : intervals) {
        if (res.isEmpty() || res.get(res.size() - 1)[1] < cur[0]) res.add(cur);
        else res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], cur[1]);
    }
    return res.toArray(new int[res.size()][]);
}
```
**Problems:** Merge Intervals, Insert Interval, Non-overlapping Intervals, Meeting Rooms I/II (II: sort starts and ends separately, or use a min-heap of end times).

### Pattern 14: Bit Manipulation and Math
```java
n & 1              // lowest bit
n >> 1             // divide by 2 (arithmetic, keeps sign)
n >>> 1            // unsigned right shift (use for "Number of 1 Bits")
n & (n - 1)        // clears the lowest set bit
a ^ a == 0         // XOR cancels duplicates (Single Number)
```
**Problems:** Single Number, Number of 1 Bits, Counting Bits, Reverse Bits, Missing Number, Sum of Two Integers.
**Matrix problems:** Rotate Image (transpose, then reverse each row), Spiral Matrix (four moving boundaries), Set Matrix Zeroes.

### Pattern 15: Prefix / Arrays tricks
- **Product of Array Except Self:** left-pass prefix products, then right-pass suffix products. No division.
- **Prefix sum:** `pre[i+1] = pre[i] + nums[i]`, so range sum `[l, r] = pre[r+1] - pre[l]`.
- **Encode/Decode Strings:** length prefix like `4#abcd`.

### Pattern 16: Tries
```java
class TrieNode { TrieNode[] kids = new TrieNode[26]; boolean end; }
// insert: walk chars, create nodes as needed, mark end at last node
// search: walk chars, return node != null && node.end
```
**Problems:** Implement Trie, Design Add and Search Words, Word Search II.

---

## Part 6: Google Online Assessment Tips

### What to expect
- Usually **2 problems in about 60-90 minutes** (format varies by role and year, so check your invite email).
- Difficulty is typically **LeetCode medium to hard**, with emphasis on graphs, DP, arrays/strings, binary search, and heaps.
- Hidden test cases check for **both correctness and time complexity**. A brute force that passes the samples will often time out.
- New grad and intern OAs often use a platform like CodeSignal or HackerRank. Read the platform's rules in the invite.

### Input handling (if there is no LeetCode-style function)
```java
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(st.nextToken());
        System.out.println(solve(a));
    }
}
```
`BufferedReader` is much faster than `Scanner` for big inputs. Use `StringBuilder` and print once at the end for large outputs.

### Strategy during the test
1. **Read the constraints first**, then choose the complexity target (Part 4).
2. **Do small examples by hand** and confirm you understand the problem.
3. **State the brute force**, then ask: which pattern removes the wasted work?
4. **Edge cases before submitting:** empty input, a single element, all equal, negatives, max-size values, overflow (use `long`).
5. **Get a working solution first**, then optimize if time remains. A correct O(n²) beats an unfinished O(n).
6. **Test with your own cases**, not just the sample.
7. **Budget time.** Do not sink 50 minutes into one problem. Submit partial credit where the platform allows it.
8. Unless told otherwise, assume **no external help**. Follow the invite's rules about tabs, notes, and AI tools, since violations can void your result.

### Pattern-recognition cheat sheet
| You see... | Try... |
|---|---|
| "subarray / substring, longest or shortest" | Sliding window or prefix sums |
| "sorted array" or "find in O(log n)" | Binary search |
| "top K" or "k-th largest" | Heap |
| "all combinations / permutations / subsets" | Backtracking |
| "min/max/count ways" with overlapping subproblems | DP |
| "shortest path, fewest steps" | BFS |
| "connected components, grouping" | DFS or Union-Find |
| "dependencies / ordering" | Topological sort |
| "matching brackets / nested" | Stack |
| "next greater / smaller" | Monotonic stack |
| "have I seen this before?" | HashSet / HashMap |
| "intervals / meetings" | Sort by start, sweep |
| Given tree | Recursion (DFS) or BFS by level |

---

## Part 7: Common Java Bugs on LeetCode

| Bug | Fix |
|---|---|
| `==` on Strings or Integers | Use `.equals()` |
| Integer overflow | Use `long`; `lo + (hi-lo)/2` |
| `list.remove(int)` vs `remove(Object)` | `remove(Integer.valueOf(x))` |
| Adding `cur` (not a copy) to results | `res.add(new ArrayList<>(cur))` |
| `arr.length` vs `str.length()` vs `list.size()` | Array field, String method, List method |
| Comparator with subtraction | Use `Integer.compare` |
| `NullPointerException` from `map.get()` unboxing | Use `getOrDefault` |
| Modifying a collection while iterating it | Iterate a copy or collect changes first |
| StackOverflow on deep recursion | Convert to iteration with an explicit stack |
| Forgetting base cases in recursion | Write the base case first, always |

---

## Part 8: A Study Plan for the Blind 75

Work through them in pattern order, not random order:

1. **Week 1:** Arrays and hashing, two pointers, stack
2. **Week 2:** Sliding window, binary search, linked list
3. **Week 3:** Trees, heap, backtracking
4. **Week 4:** Graphs, 1-D DP, intervals
5. **Week 5:** 2-D DP, greedy, bit manipulation, then redo problems you failed

**How to practice each problem:**
1. Attempt for 20-25 minutes with no help.
2. If stuck, read only the *hint or approach*, not the code, and try again.
3. Code it yourself in Java without copying.
4. Write down the **pattern and the trick** in one line.
5. **Redo it in a few days** without looking. Spaced repetition is what makes it stick.
6. Track everything: problem, pattern, time, difficulty, mistakes.

Aim to be able to explain *why* the pattern applies. That is the skill an OA tests.

---

## Part 9: Quick Reference

```java
Math.max(a, b);  Math.min(a, b);  Math.abs(x);  Math.pow(2, 10);  Math.sqrt(x);
Integer.MAX_VALUE;  Integer.MIN_VALUE;  Long.MAX_VALUE;
Integer.parseInt("42");  Integer.toString(42);  Integer.toBinaryString(5);
Integer.bitCount(n);
Collections.reverse(list);  Collections.swap(list, i, j);  Collections.max(list);
Arrays.asList(1, 2, 3);
List.of(1, 2, 3);                              // immutable
int[] copy = arr.clone();
Arrays.stream(arr).sum();  Arrays.stream(arr).max().getAsInt();
```
