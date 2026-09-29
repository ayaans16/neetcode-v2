class Solution {
    public int maxProfit(int[] prices) {
        int price = prices[0]; // initial element, this becomes smallest element
        int profit = 0; // initialize profit

        // iterate through prices
        for (int i = 1; i < prices.length; i++) {
            // if current index is the new min, update it
            if (price > prices[i]) {
                price = prices[i];
            } else {
                // if not, check if the current index - current min gives a greater profit, if yes, update it
                if (prices[i] - price > profit) {
                    profit = prices[i] - price;
                }
            }
        }
        return profit;
    }
}

