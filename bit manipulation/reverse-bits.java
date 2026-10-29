class Solution {
    public int reverseBits(int n) {
        String bit = String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0');
        String reversed = new StringBuilder(bit).reverse().toString();
        int newbit = Integer.parseInt(reversed, 2);

        return newbit;
    }
}
