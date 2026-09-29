class Solution {
    public int getSum(int a, int b) {
        while (b != 0) {
            // and operator and left shift
            int carry = (a & b) << 1;
            // xor operator
            a = a ^ b;
            b = carry;
        }
        return a;
    }
}
