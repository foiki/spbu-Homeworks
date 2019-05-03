package group144.kireev;

import java.util.ArrayList;
import java.util.LinkedList;

/** Class realizes HashTable that stores Strings */
public class HashTable {
    private ArrayList<LinkedList<String>> bucket;
    private HashFunction hashFunction;
    private int cellsUsed = 0;
    private int uniqueWordsAdded = 0;
    private int numberOfConflicts = 0;

    /**
     * HashTable.
     *
     * @param hashFunction, mod of hashFunction is also mod of hashTable.
     */
    HashTable(HashFunction hashFunction) {
        this.hashFunction = hashFunction;
        this.bucket = new ArrayList<>(hashFunction.getMod());
        for (int i = 0; i < hashFunction.getMod(); ++i) {
            this.bucket.add(new LinkedList<>());
        }
    }

    /**
     * @return does the element belong to the table
     */
    public boolean exist(String element) {
        return bucket.get(hashFunction.hash(element)).contains(element);
    }

    /**
     * @param element to add to table
     */
    public void add(String element) {
        int hash = hashFunction.hash(element);
        if (!exist(element)) {
            if (bucket.get(hash).size() > 0) {
                ++numberOfConflicts;
            } else {
                ++cellsUsed;
            }
            ++uniqueWordsAdded;
            bucket.get(hash).add(element);
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
        if (bucket.get(hashFunction.hash(element)).size() > 1) {
            --numberOfConflicts;
        } else {
            --cellsUsed;
        }
        --uniqueWordsAdded;
        bucket.get(hashFunction.hash(element)).remove(element);
    }

    /**
     * Print statistic of HashTable: number of cells, load factor, number of conflicts,
     * maximum length of list in conflict cells and number of unique words in table
     */
    public void printStat() {
        System.out.println("Number of cells: " + getNumberOfCells());
        System.out.println("Load factor: " + getLoadFactor());
        System.out.println("Number of conflicts: " + numberOfConflicts);
        System.out.println("Maximum length of list in conflict cells: " + getMaxLengthOfConflictCells());
        System.out.println("Unique words added: " + uniqueWordsAdded);
    }

    public int getNumberOfAddedWords() { return uniqueWordsAdded; }

    public int getNumberOfCells() {
        return hashFunction.getMod();
    }

    public double getLoadFactor() {
        return (double) cellsUsed / getNumberOfCells();
    }

    /**
     * @return max length of list in conflict cells
     */
    public int getMaxLengthOfConflictCells() {
        int result = 0;
        for (LinkedList list : bucket) {
            result = Integer.max(result, list.size());
        }
        return result;
    }

    /**
     * Method changing HashFunction of HashTable
     * @param table to change HashFunction
     * @param newHash on which to change
     * @return HashTable with changed HashFunction
     */
    public static HashTable changeHashFunction(HashTable table, HashFunction newHash) {
        if (newHash.getClass().equals(table.hashFunction.getClass())) {
            return table;
        }
        HashTable newHashTable = new HashTable(newHash);
        for (int i = 0; i < table.hashFunction.getMod(); ++i) {
            for (String element : table.bucket.get(i)) {
                newHashTable.add(element);
            }
        }
        return newHashTable;
    }
}
