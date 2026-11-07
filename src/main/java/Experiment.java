
import java.io.File;
import java.io.PrintWriter;
import java.util.Random;

public class Experiment {

    static final int RUNS = 5;
    static final long SEED = 42;

    public static void main(String[] args) throws Exception {

        File folder = new File("results");
        folder.mkdirs();

        PrintWriter writer = new PrintWriter(
                new File(folder, "results.csv"));

        writer.println("algorithm,input,n,run,time_ms,operations");

        // Experiment 1: Edit Distance
        int[] editSizes = {2, 4, 6, 8, 10};

        for (int n : editSizes) {
            for (String type : new String[]{"random", "different"}) {

                Random random = new Random(SEED + n);
                String a, b;

                if (type.equals("random")) {
                    a = generate(random, n, "abcd");
                    b = generate(random, n, "abcd");
                } else {
                    a = "a".repeat(n);
                    b = "b".repeat(n);
                }

                for (String algorithm :
                        new String[]{"Recursive", "Table", "TwoRows"}) {

                    for (int run = 1; run <= RUNS; run++) {

                        EditDistance ed = new EditDistance();

                        long start = System.nanoTime();

                        if (algorithm.equals("Recursive")) {
                            ed.recursive(a, b);
                        } else if (algorithm.equals("Table")) {
                            ed.table(a, b);
                        } else {
                            ed.twoRows(a, b);
                        }

                        long end = System.nanoTime();
                        double time = (end - start) / 1_000_000.0;

                        save(writer, algorithm, type, n,
                                run, time, ed.operations);
                    }
                }
            }
        }

        // Experiment 2: String Search
        int[] searchSizes = {1000, 4000, 8000, 16000, 32000};

        for (int n : searchSizes) {
            for (String type : new String[]{"random", "worst"}) {

                Random random = new Random(SEED + n);
                String text, pattern;

                if (type.equals("random")) {
                    text = generate(random, n, "abcd");
                    pattern = "abcab";
                } else {
                    text = "a".repeat(n);
                    pattern = "a".repeat(19) + "b";
                }

                for (String algorithm :
                        new String[]{"Naive", "KMP"}) {

                    for (int run = 1; run <= RUNS; run++) {

                        StringSearch search = new StringSearch();

                        long start = System.nanoTime();

                        if (algorithm.equals("Naive")) {
                            search.naive(text, pattern);
                        } else {
                            search.kmp(text, pattern);
                        }

                        long end = System.nanoTime();
                        double time = (end - start) / 1_000_000.0;

                        save(writer, algorithm, type, n,
                                run, time, search.operations);
                    }
                }
            }
        }

        writer.close();

        System.out.println("Experiment completed!");
        System.out.println("Results saved to results/results.csv");
    }

    // Generate reproducible random strings
    static String generate(Random random, int n, String alphabet) {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int index = random.nextInt(alphabet.length());
            s.append(alphabet.charAt(index));
        }

        return s.toString();
    }

    static void save(PrintWriter writer, String algorithm,
                     String type, int n, int run,
                     double time, long operations) {

        writer.printf(java.util.Locale.US,
                "%s,%s,%d,%d,%.6f,%d%n",
                algorithm, type, n, run, time, operations);
    }
}
