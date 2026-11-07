
import java.util.ArrayList;
import java.util.List;

public class StringSearch {

    public long operations = 0;

    // Naive search checks every possible position
    public List<Integer> naive(String text, String pattern) {
        if (pattern.isEmpty()) {
            throw new IllegalArgumentException("Empty pattern");
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            int j = 0;

            while (j < pattern.length()) {
                operations++;

                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
                j++;
            }

            if (j == pattern.length()) {
                result.add(i);
            }
        }

        return result;
    }

    // LPS stores longest proper prefix which is also suffix
    public int[] lps(String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0;
        int i = 1;

        while (i < pattern.length()) {
            operations++;

            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else if (len != 0) {
                len = lps[len - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }

        return lps;
    }

    // KMP uses LPS to avoid unnecessary comparisons
    public List<Integer> kmp(String text, String pattern) {
        if (pattern.isEmpty()) {
            throw new IllegalArgumentException("Empty pattern");
        }

        List<Integer> result = new ArrayList<>();

        if (pattern.length() > text.length()) {
            return result;
        }

        int[] prefix = lps(pattern);
        int i = 0;
        int j = 0;

        while (i < text.length()) {
            operations++;

            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else if (j != 0) {
                j = prefix[j - 1];
            } else {
                i++;
            }

            if (j == pattern.length()) {
                result.add(i - j);
                j = prefix[j - 1];
            }
        }

        return result;
    }
}

