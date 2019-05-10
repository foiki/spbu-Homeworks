package group144.kireev;

import java.util.LinkedList;

/** Class realizes application that can add array of arrays of string to the SortedSet */
public class Application {

    /**
     * Method adds array of arrays of string to the SortedSet
     * @param elements Strings to add to SortedSet
     */
    public static void runApplication(LinkedList<LinkedList<String>> elements) {
        SortedSet set = new SortedSet();
        for (LinkedList<String> currentList : elements) {
            set.add(currentList);
        }
        set.printSet();
    }
}
