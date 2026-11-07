
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class StringSearchTest {

    StringSearch search = new StringSearch();

    @Test
    void testLPS() {
        assertArrayEquals(
                new int[]{0, 1, 2, 0, 1, 2, 3, 3},
                search.lps("AAACAAAA")
        );

        assertArrayEquals(
                new int[]{0, 0, 1, 2, 0, 1, 2, 3, 4},
                search.lps("ABABCABAB")
        );
    }

    @Test
    void testSearch() {
        check("ABABDABACDABABCABAB",
                "ABABCABAB", List.of(10));

        check("AAAAA", "AA", List.of(0, 1, 2, 3));

        check("ABABABA", "ABA", List.of(0, 2, 4));

        check("abcdef", "xyz", List.of());
    }

    void check(String text, String pattern,
               List<Integer> expected) {

        assertEquals(expected, search.naive(text, pattern));
        assertEquals(expected, search.kmp(text, pattern));
    }

    @Test
    void randomTests() {
        Random random = new Random(42);

        for (int t = 0; t < 100; t++) {
            String text = generate(random, 20);
            String pattern = generate(random, 3);

            assertEquals(
                    search.naive(text, pattern),
                    search.kmp(text, pattern)
            );
        }
    }

    String generate(Random random, int n) {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < n; i++) {
            s.append((char) ('A' + random.nextInt(3)));
        }

        return s.toString();
    }

    @Test
    void emptyPattern() {
        assertThrows(IllegalArgumentException.class,
                () -> search.naive("ABC", ""));

        assertThrows(IllegalArgumentException.class,
                () -> search.kmp("ABC", ""));
    }

    @Test
    void longerPattern() {
        check("AB", "ABCDE", List.of());
    }
}
