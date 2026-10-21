
class Solution {
    public int minOperations(String s) {
        int n = s.length();
        int min = 'z', max = 'a';
        int minCount = 0, maxCount = 0;

        for (int i = 0; i < n; i++) {
            min = Math.min(min, s.charAt(i));
            max = Math.max(max, s.charAt(i));
        }

        boolean sorted = true;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                sorted = false;
                break;
            }
        }

        if (sorted) return 0;
        if (n == 2) return -1;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == min) minCount++;
            if (s.charAt(i) == max) maxCount++;
        }

        char first = s.charAt(0);
        char last = s.charAt(n - 1);

        if (first == min || last == max) return 1;

        if (first == max && last == min
                && minCount == 1 && maxCount == 1) {
            return 3;
        }

        return 2;
    }
}
