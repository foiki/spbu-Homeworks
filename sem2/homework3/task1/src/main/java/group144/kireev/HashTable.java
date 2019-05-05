package group144.kireev;

import java.util.ArrayList;
import java.util.LinkedList;

/** Class realizes HashTable that stores Strings */
public class HashTable {
    private ArrayList<LinkedList<String>> bucket;
    private HashFunction hashFunction;
    private int mod = 512;
    private int cellsUsed = 0;
    private int uniqueWordsAdded = 0;
    private int numberOfConflicts = 0;
    private double higherLoadFactor = 0.15;
    private double lowerLoadFactor = 0.01;

    /**
     * HashTable.
     *
     * @param hashFunction, mod of hashFunction is also mod of hashTable.
     */
    HashTable(HashFunction hashFunction) {
        this.hashFunction = hashFunction;
        this.bucket = new ArrayList<>(mod);
        for (int i = 0; i < mod; ++i) {
            this.bucket.add(new LinkedList<>());
        }
    }

    /**
     * Method calculating hash with current mod
     * @param element to calculate hash
     * @return hash of element
     */
    private int hash(String element) {
        return hashFunction.hash(element) % mod;
    }

    /**
     * @return does the element belong to the table
     */
    public boolean exist(String element) {
        return bucket.get(hash(element)).contains(element);
    }

    /**
     * @param element to add to table
     */
    public void add(String element) {
        int hash = hash(element);
        if (!exist(element)) {
            if (bucket.get(hash).size() > 0) {
                ++numberOfConflicts;
            } else {
                ++cellsUsed;
            }
            ++uniqueWordsAdded;
            bucket.get(hash).add(element);
            if (loadFactor() > higherLoadFactor) {
                updateMod();
            }
        }
    }

    /**
     * @return list with all elements of table
     */
    private LinkedList<String> getAllElements() {
        LinkedList<String> elements = new LinkedList<>();
        for (int i = 0; i < mod; ++i) {
            elements.addAll(bucket.get(i));
            bucket.remove(i);
            bucket.add(i, new LinkedList<>());
        }
        return elements;
    }

    /**
     * Method updates Mod of HashTable
     * increases it in two times if the LoadFactor reached upper threshold
     * decreases it int two times if the LoadFactor reached lower threshold
     */
    private void updateMod() {
        LinkedList<String> elements = getAllElements();
        if (loadFactor() > higherLoadFactor) {
            mod *= 2;
        } else {
            mod /= 2;
        }
        for (String element : elements) {
            add(element);
        }
    }

    /**
     * @param element to remove from table
     * @throws ElementDoesNotExist if the element does not belong to the table
     */
    public void remove(String element) throws ElementDoesNotExist {
        if (!exist(element)) {
            throw new ElementDoesNotExist("Element " + element + " not found!");
        }
        if (bucket.get(hash(element)).size() > 1) {
            --numberOfConflicts;
        } else {
            --cellsUsed;
        }
        --uniqueWordsAdded;
        bucket.get(hash(element)).remove(element);
        if (loadFactor() < lowerLoadFactor) {
            updateMod();
        }
    }

    /**
     * Prints statistic of HashTable: number of cells, load factor, number of conflicts,
     * maximum length of list in conflict cells and number of unique words in table
     */
    public void printStat() {
        System.out.println("Number of cells: " + mod);
        System.out.println("Load factor: " + loadFactor());
        System.out.println("Number of conflicts: " + numberOfConflicts);
        System.out.println("Maximum length of list in conflict cells: " + maxLengthOfConflictCells());
        System.out.println("Unique words added: " + uniqueWordsAdded);
    }

    /**
     * @return loadFactor of HashTable
     */
    public double loadFactor() {
        return (double) cellsUsed / mod;
    }

    /**
     * @return max length of list in conflict cells
     */
    public int maxLengthOfConflictCells() {
        int result = 0;
        for (LinkedList list : bucket) {
            result = Integer.max(result, list.size());
        }
        return result;
    }

    /**
     * Method changing HashFunction of HashTable
     * @param newHash on which to change
     */
    public void changeHashFunction(HashFunction newHash) {
        LinkedList<String> elements = getAllElements();
        hashFunction = newHash;
        for (String element : elements) {
            add(element);
        }
        updateMod();
    }
}
