class Solution {
    public int reverse(int x) {
        long n = x;
        int sign = 1;
        if (n < 0) {
            sign = -1;
            n = -n;
        }

        long newNum = 0;
        while (n > 0) {
            newNum = newNum * 10 + n % 10;
            n /= 10;
        }

        long result = newNum * sign;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
            return 0;

        return (int) result;
    }
}   