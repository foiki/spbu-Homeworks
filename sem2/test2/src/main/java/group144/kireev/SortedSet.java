package group144.kireev;

import java.util.LinkedList;

/** Class realizes SortedSet collects LinkedLists of Strings */
public class SortedSet implements ListsComparator {
    private LinkedList<LinkedList<String>> elements = new LinkedList<>();

    /**
     * @return if SortedSet is empty
     */
    public boolean isEmpty() {
        return elements.size() == 0;
    }

    /**
     * @param element to add to SortedSet in increasing order
     */
    public void add(LinkedList<String> element) {
        if (isEmpty()) {
            elements.add(0, element);
            return;
        }
        int currentIndex = 0;
        LinkedList<String> current = elements.get(currentIndex);
        while (currentIndex < elements.size() && compare(element, current) > 0) {
            ++currentIndex;
            current = elements.get(currentIndex);
        }
        elements.add(currentIndex, element);
    }

    /**
     * Method comparing two elements
     * @param first element to compare
     * @param second element to compare
     * @return -1, zero, or 1 as the first argument is less than, equal to, or greater than the second
     */
    @Override
    public int compare(LinkedList<String> first, LinkedList<String> second) {
        if (first.size() > second.size()) {
            return 1;
        }
        if (first.size() < second.size()) {
            return -1;
        }
        return 0;
    }

    /** Method prints SortedSet to the console */
    public void printSet() {
        for (LinkedList<String> currentList : elements) {
            printList(currentList);
        }
    }

    /** Method prints LinkedList to the console */
    private void printList(LinkedList<String> list) {
        for (String string : list) {
            System.out.print(string + " ");
        }
    }
}
