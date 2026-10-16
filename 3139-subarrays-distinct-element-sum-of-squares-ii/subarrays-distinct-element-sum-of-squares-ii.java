class Solution {
    static final long MOD = 1_000_000_007L;

    int n;
    long[] sum, sq, lazy;

    void build(int node, int l, int r) {
        if (l == r) return;

        int mid = (l + r) / 2;
        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);
    }

    void apply(int node, int l, int r, long val) {
        long len = r - l + 1L;

        sq[node] = (sq[node]
                + 2 * val % MOD * sum[node] % MOD
                + len % MOD * val % MOD * val % MOD) % MOD;

        sum[node] = (sum[node] + len * val) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    void push(int node, int l, int r) {
        if (lazy[node] == 0 || l == r) return;

        int mid = (l + r) / 2;
        apply(node * 2, l, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, r, lazy[node]);
        lazy[node] = 0;
    }

    void update(int node, int l, int r,
                int ql, int qr, long val) {
        if (ql > r || qr < l) return;

        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }

        push(node, l, r);

        int mid = (l + r) / 2;
        update(node * 2, l, mid, ql, qr, val);
        update(node * 2 + 1, mid + 1, r, ql, qr, val);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        java.util.HashMap<Integer, Integer> last =
                new java.util.HashMap<>();

        long ans = 0;

        for (int i = 0; i < n; i++) {
            Integer prev = last.get(nums[i]);

            int start = (prev == null) ? 0 : prev + 1;

            update(1, 0, n - 1, start, i, 1);

            last.put(nums[i], i);
            ans = (ans + sq[1]) % MOD;
        }

        return (int) ans;
    }
}