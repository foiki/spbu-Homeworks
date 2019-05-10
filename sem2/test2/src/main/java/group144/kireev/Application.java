package group144.kireev;

import java.util.LinkedList;

public class Application {
    public static void runApplication(LinkedList<LinkedList<String>> elements) {
        SortedSet set = new SortedSet();
        for (LinkedList<String> currentList : elements) {
            set.add(currentList);
        }
        set.printSet();
    }
}
