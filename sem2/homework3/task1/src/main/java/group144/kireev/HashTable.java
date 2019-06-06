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
        bucket = new ArrayList<>(mod);
        for (int i = 0; i < mod; ++i) {
            bucket.add(new LinkedList<>());
        }
    }

    /**
     * @return does the element belong to the table
     */
    public boolean exist(String element) {
        return bucket.get(hashFunction.hash(element, mod)).contains(element);
    }

    /**
     * @param element to add unique element to the table
     */
    public void add(String element) {
        int hash = hashFunction.hash(element, mod);
        if (exist(element)) {
            return;
        }
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

    /** Adding all elements from list to the table
     */
    private void addAll(ArrayList<LinkedList<String>> oldBucket) {
        for (LinkedList<String> list : oldBucket) {
            for (String element : list) {
                add(element);
            }
        }
    }

    /**
     * Method updates Mod of HashTable
     * increases it in two times if the LoadFactor reached upper threshold
     * decreases it int two times if the LoadFactor reached lower threshold
     */
    private void updateMod() {
        ArrayList<LinkedList<String>> oldBucket = bucket;
        if (loadFactor() > higherLoadFactor) {
            mod *= 2;
        } else {
            mod /= 2;
        }
        this.bucket = new ArrayList<>(mod);
        for (int i = 0; i < mod; ++i) {
            bucket.add(new LinkedList<>());
        }
        addAll(oldBucket);
    }

    /**
     * @param element to remove from table
     * @throws ElementDoesNotExist if the element does not belong to the table
     */
    public void remove(String element) throws ElementDoesNotExist {
        if (!exist(element)) {
            throw new ElementDoesNotExist("Element " + element + " not found!");
        }
        if (bucket.get(hashFunction.hash(element, mod)).size() > 1) {
            --numberOfConflicts;
        } else {
            --cellsUsed;
        }
        --uniqueWordsAdded;
        bucket.get(hashFunction.hash(element, mod)).remove(element);
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
        ArrayList<LinkedList<String>> oldBucket = bucket;
        hashFunction = newHash;
        bucket = new ArrayList<>(mod);
        for (int i = 0; i < mod; ++i) {
            this.bucket.add(new LinkedList<>());
        }
        addAll(oldBucket);
        updateMod();
    }
}
