import java.util.Arrays;

public class StringOperations {

    public static void stringConnection() {
        String a = "Java";
        String b = "forever";
        System.out.println(a + " " + b);
        System.out.println(a.concat(" ").concat(b));
        System.out.println(new StringBuilder(a).append(" ").append(b));
        System.out.println(new StringBuffer(a).append(" ").append(b));
    }

    public static void initials(String s) {

        String[] parts = s.split(" ");
        String part1 = parts[0];
        String part2 = parts[1];
        String part3 = parts[2];
        System.out.print(part1.charAt(0));
        System.out.print(part2.charAt(0));
        System.out.println(part3.charAt(0));
    }

    public static void anagram(String a, String b) {

        char[] arrA = a.replaceAll("[^a-zA-Zа-яА-Я]", "").toLowerCase().toCharArray();
        char[] arrB = b.replaceAll("[^a-zA-Zа-яА-Я]", "").toLowerCase().toCharArray();
        Arrays.sort(arrA);
        Arrays.sort(arrB);
        if (String.valueOf(arrA).equals(String.valueOf(arrB))) {
            System.out.println("Entered words is anagram.");
        } else {
            System.out.println("Entered words is not anagram.");
        }
    }

    public static void main(String[] args) {
        stringConnection();
        initials("Кондратьев Эдуард Александрович");
        anagram("Полковник", "клоповник !");
    }
}