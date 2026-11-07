
public class EditDistance {

    public long operations = 0;

    // State: distance between prefixes of a and b.
    // Transition: insert, delete or replace a character.
    // Base case: if one string is empty, return the other length.
    public int recursive(String a, String b) {
        operations++;
        int n = a.length();
        int m = b.length();

        if (n == 0) return m;
        if (m == 0) return n;

        if (a.charAt(n - 1) == b.charAt(m - 1)) {
            return recursive(a.substring(0, n - 1),
                    b.substring(0, m - 1));
        }

        int insert = recursive(a, b.substring(0, m - 1));
        int delete = recursive(a.substring(0, n - 1), b);
        int replace = recursive(a.substring(0, n - 1),
                b.substring(0, m - 1));

        return 1 + Math.min(insert, Math.min(delete, replace));
    }

    // State: dp[i][j] stores distance between two prefixes.
    // Transition: minimum of insert, delete and replace.
    // Base case: dp[i][0] = i and dp[0][j] = j.
    public int table(String a, String b) {
        int n = a.length();
        int m = b.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) dp[i][0] = i;
        for (int j = 0; j <= m; j++) dp[0][j] = j;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                operations++;

                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j],
                            Math.min(dp[i][j - 1], dp[i - 1][j - 1])
                    );
                }
            }
        }
        return dp[n][m];
    }

    // State: previous and current rows of the DP table.
    // Transition: same as the full DP table.
    // Base case: first row and first column represent empty strings.
    public int twoRows(String a, String b) {
        if (b.length() > a.length()) {
            String temp = a;
            a = b;
            b = temp;
        }

        int n = a.length();
        int m = b.length();

        int[] prev = new int[m + 1];
        int[] curr = new int[m + 1];

        for (int j = 0; j <= m; j++) prev[j] = j;

        for (int i = 1; i <= n; i++) {
            curr[0] = i;

            for (int j = 1; j <= m; j++) {
                operations++;

                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    curr[j] = prev[j - 1];
                } else {
                    curr[j] = 1 + Math.min(
                            prev[j],
                            Math.min(curr[j - 1], prev[j - 1])
                    );
                }
            }

            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[m];
    }

    public int distance(String a, String b) {
        return table(a, b);
    }
}
