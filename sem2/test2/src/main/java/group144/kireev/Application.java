package group144.kireev;

import java.util.Arrays;
import java.util.LinkedList;

/** Class realizes application that can add array of arrays of string to the SortedSet */
public class Application {

    /**
     * Method adds array of arrays of string to the SortedSet
     * @param elements Strings to add to SortedSet
     */
    public static void runApplication(String[] elements) {
        SortedSet set = new SortedSet();
        for (String string : elements) {
            String[] words = string.split(" ");
            set.add(new LinkedList<>(Arrays.asList(words)));
        }
        set.printSet();
    }
}
