
public class Main {
    public static void main(String[] args) {

        EditDistance ed = new EditDistance();

        System.out.println("=== EDIT DISTANCE ===");

        System.out.println("Recursive: " +
                ed.recursive("kitten", "sitting"));

        System.out.println("Table: " +
                ed.table("kitten", "sitting"));

        System.out.println("Two Rows: " +
                ed.twoRows("kitten", "sitting"));

        System.out.println("Operations: " + ed.operations);

        System.out.println();

        StringSearch search = new StringSearch();

        System.out.println("=== STRING SEARCH ===");

        System.out.println("Naive: " +
                search.naive("ABABABA", "ABA"));

        System.out.println("KMP: " +
                search.kmp("ABABABA", "ABA"));

        System.out.println("Search Operations: " +
                search.operations);
    }
}
