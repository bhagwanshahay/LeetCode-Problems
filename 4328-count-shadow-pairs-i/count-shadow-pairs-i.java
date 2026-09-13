import java.util.*;

class Solution {
    public long shadowPairs(int[] nums) {

        int[] navorelitu = nums;

        int n = nums.length;

        int[] nextSmaller = new int[n];
        Arrays.fill(nextSmaller, n);

        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {

            while (!stack.isEmpty() &&
                   nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                nextSmaller[i] = stack.peek();
            }

            stack.push(i);
        }

        Integer[] order = new Integer[n];

        for (int i = 0; i < n; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) ->
                Integer.compare(nums[b], nums[a]));

        Integer[] queries = new Integer[n];

        for (int i = 0; i < n; i++) {
            queries[i] = i;
        }

        Arrays.sort(queries, (a, b) ->
                Integer.compare(nums[b], nums[a]));

        Fenwick bit = new Fenwick(n);

        long ans = 0;
        int p = 0;

        for (int idx : queries) {

            while (p < n &&
                   nums[order[p]] > nums[idx]) {

                bit.add(order[p] + 1, 1);
                p++;
            }

            int left = idx + 1;
            int right = nextSmaller[idx] - 1;

            if (left <= right) {
                ans += bit.query(right + 1)
                     - bit.query(left);
            }
        }

        return ans;
    }

    static class Fenwick {

        int[] bit;
        int n;

        Fenwick(int n) {
            this.n = n;
            bit = new int[n + 2];
        }

        void add(int idx, int val) {
            while (idx <= n) {
                bit[idx] += val;
                idx += idx & -idx;
            }
        }

        int query(int idx) {
            int sum = 0;

            while (idx > 0) {
                sum += bit[idx];
                idx -= idx & -idx;
            }

            return sum;
        }
    }
}