class Solution {
    public int minMeetingRooms(int[][] intervals) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // sort the interval by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // base case: empty 2d array
        if (intervals.length == 0) {
            return 0;
        }

        // base case: only 1 array inside the 2d array
        if (intervals.length == 1) {
            return 1;
        }

        // initialize the heap with the first end time of the sorted 2d array
        heap.offer(intervals[0][1]);
        
        // iterate through the array and start at 1
        for (int i = 1; i < intervals.length; i++) {
            // if (1, 0), (2, 0) ... is >= the first/smallest element of the heap, remove it
            // because it can be reused
            if (intervals[i][0] >= heap.peek()) {
                heap.poll();
            }
            
            // add back the end time to the queue
            heap.offer(intervals[i][1]);
        }

        // return size of heap which will consist of the min # of meeting rooms required
        return heap.size();
    }
}
