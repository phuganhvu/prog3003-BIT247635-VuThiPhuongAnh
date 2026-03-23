package ex1;

public class GenericMethodDemo {
    public static <E> void printArray(E[] inputArray) {
        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }

    public static void main(String args[]) {
        Integer[] intArray = { 1, 2, 3, 4, 5 };
        String[] stringArray = { "Hello", "World" };

        System.out.println("Mảng Integer bao gồm:");
        printArray(intArray);

        System.out.println("\nMảng String bao gồm:");
        printArray(stringArray);
    }
}
