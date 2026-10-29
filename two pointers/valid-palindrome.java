class Solution {
    public boolean isPalindrome(String s) {
        // regex to remove symbols, only keep letters + make it lowercase
        String clean = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // reverse the cleaned string and bring it back as a string
        String dup = new StringBuilder(clean).reverse().toString();

        // simple comparison
        if (clean.equals(dup)) {
            return true;
        }

        return false;
    }
}
