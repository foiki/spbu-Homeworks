package group144.kireev;

import java.io.*;
import java.util.HashMap;

/** Trie that stores strings */
public class Trie implements Serializable {
    private Vertex root;

    /** Empty constructor for Trie */
    public Trie() {
        root = new Vertex();
    }

    /**
     * @return number of elements in Trie
     */
    public int size() {
        return root.startWith;
    }

    /**
     * Adding element to the Trie
     * @param element to add to Trie
     * @return if element would be added
     */
    public boolean add(String element) {
        if (element == null || contains(element)) {
            return false;
        }
        Vertex current = root;
        int position = 0;
        while (position < element.length()) {
            if (!current.children.containsKey(element.charAt(position))) {
               current.children.put(element.charAt(position), new Vertex(current.text + element.charAt(position)));
            }
            current = current.children.get(element.charAt(position));
            ++current.startWith;
            if (current.text.equals(element) && !current.isWord) {
                current.isWord = true;
                return true;
            }
            ++position;
        }
        return false;
    }

    /**
     * @param element to check for contains
     * @return if trie contains the element
     */
    public boolean contains(String element) {
        if (element == null) {
            return false;
        }
        int position = 0;
        Vertex current = root;
        while (position < element.length()) {
            if (!current.children.containsKey(element.charAt(position))) {
                return false;
            } else {
                current = current.children.get(element.charAt(position));
                ++position;
            }
        }
        return current.isWord;
    }

    /**
     * Removing element from the Trie
     * @param element to add to Trie
     * @return if element would be removed
     */
    public boolean remove(String element) {
        if (!contains(element)) {
            return false;
        }
        Vertex current = root;
        --root.startWith;
        for (int i = 0; i < element.length(); ++i) {
            Vertex parent = current;
            current = current.children.get(element.charAt(i));
            --current.startWith;
            if (current.startWith == 0) {
                parent.children.remove(element.charAt(i));
                return true;
            }
        }
        current.isWord = false;
        return true;
    }

    /**
     * @param prefix to find
     * @return number of words starts with prefix
     */
    public int howManyStartWithPrefix(String prefix) {
        if (prefix == null) {
            return 0;
        }
        Vertex current = root;
        for (int i = 0; i < prefix.length(); ++i) {
            if (current.children.containsKey(prefix.charAt(i))) {
                return 0;
            } else {
                current = current.children.get(prefix.charAt(i));
            }
        }
        return current.startWith;
    }

    /**
     * @param out stream to write Trie in
     * @throws IOException when we can't write there
     */
    public void serialize(OutputStream out) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(out);
        outputStream.writeObject(this);
        outputStream.close();
    }

    /**
     * @param in stream to take Trie
     * @throws IOException when we can't read
     * @throws ClassNotFoundException when we cant find class which serialized in this stream
     */
    public void deserialize(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(in);
        Trie newTrie = (Trie) inputStream.readObject();
        this.root = newTrie.root;
    }

    /**
     * Element
     * text - values of all previous edges.
     * children - hashMap of existing edges.
     * isWord - true when values of all previous edges form a word, false otherwise.
     * startWith - amount of the words, which starts with text as prefix.
     */
    private class Vertex {
        private String text;
        private HashMap<Character, Vertex> children;
        boolean isWord;
        int startWith;

        Vertex() {
            text = "";
            children = new HashMap<>();
            startWith = 0;
            isWord = false;
        }

        Vertex(String text) {
            this();
            this.text = text;
        }
    }
}
