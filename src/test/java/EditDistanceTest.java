
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class EditDistanceTest {

    EditDistance ed = new EditDistance();

    @Test
    void referenceValues() {
        check("kitten", "sitting", 3);
        check("flaw", "lawn", 2);
        check("intention", "execution", 5);
        check("", "abc", 3);
        check("abc", "", 3);
        check("abc", "abc", 0);
        check("", "", 0);
    }

    void check(String a, String b, int expected) {
        assertEquals(expected, ed.recursive(a, b));
        assertEquals(expected, ed.table(a, b));
        assertEquals(expected, ed.twoRows(a, b));
    }

    @Test
    void randomTests() {
        Random random = new Random(42);

        for (int t = 0; t < 100; t++) {
            String a = generate(random);
            String b = generate(random);

            int r = ed.recursive(a, b);
            int d = ed.table(a, b);
            int two = ed.twoRows(a, b);

            assertEquals(r, d);
            assertEquals(d, two);
        }
    }

    String generate(Random random) {
        int n = random.nextInt(5);
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < n; i++) {
            s.append((char) ('a' + random.nextInt(3)));
        }

        return s.toString();
    }
}
