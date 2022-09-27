package testing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

 
public class ListTestResultTable {

    private final static String heading;
     
    private final static String lineDelimiter;
     
    private final static String row;
     
    private final static Map<String, BiConsumer<List<Integer>, Integer>> methodToLambda;
     
    private final static List<String> listOfMethods;

    static {
        heading = String.format("|%15s|%15s|%15s|%15s|%n", "Called method", "Number of calls", "ArrayList", "LinkedList");
        lineDelimiter = "=".repeat(65) + "\n";
        row = "|%15s|%15s|%12.2f ms|%12.2f ms|%n";
        listOfMethods = List.of("Add", "Get", "Remove");
        methodToLambda = Map.of(
                "Add", (list, index) -> list.add(100),
                "Get", (list, index) -> list.get(index),
                "Remove", (list, index) -> list.remove(list.size() - 1)
        );
    }

     
    public static void print(int... numbersOfMethodsCalls) {
        StringBuilder table = new StringBuilder();
        ListCollectionTester tester = new ListCollectionTester();

        double linkedListExecutionTime, arrayListExecutionTime;

        table.append(lineDelimiter);
        table.append(heading);
        for (int numberOfMethodsCalls : numbersOfMethodsCalls) {
            table.append(lineDelimiter);

            List<Integer> linkedList = new LinkedList<>();
            List<Integer> arrayList = new ArrayList<>();

            for (String method : listOfMethods) {
                linkedListExecutionTime = tester.getExecutionTime(methodToLambda.get(method), linkedList, numberOfMethodsCalls);
                arrayListExecutionTime = tester.getExecutionTime(methodToLambda.get(method), arrayList, numberOfMethodsCalls);
                table.append(String.format(row, method, numberOfMethodsCalls, arrayListExecutionTime, linkedListExecutionTime));
            }
        }

        System.out.println(table);
    }
}
