class Solution {
    public int findJudge(int n, int[][] trust) {
        // 1-indexed array
        // index is the person, value of index is their trust level
        int[] score = new int[n + 1];

        // access individual scores: score[t[0]] and score[t[1]]
        // for each array inside the 2d array:
        for (int[] t : trust) {
            // access the first element of the array
            score[t[0]]--;
            // access the second element of the array
            score[t[1]]++;
        }

        // for each person in the town
        for (int i = 1; i <= n; i++) {
            // if the score of the person = the # of people in the town - 1 (self)
            if (score[i] == n - 1) {
                // return the judge
                return i;
            }
        }
        // no judge
        return -1;
    }
}
