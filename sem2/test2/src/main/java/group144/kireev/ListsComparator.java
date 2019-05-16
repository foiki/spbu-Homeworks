package group144.kireev;

import java.util.LinkedList;

/** Interface describes comparator for LinkedLists */
public interface ListsComparator {

    /**
     * Method comparing two elements
     * @param first element to compare
     * @param second element to compare
     * @return -1, zero, or 1 as the first argument is less than, equal to, or greater than the second
     */
    int compare(LinkedList<String> first, LinkedList<String> second);
}