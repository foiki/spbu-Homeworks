package group144.kireev;

/** Interface describes HashFunction */
public interface HashFunction {

    /**
     * Method that computes the hash
     * @param number element whose hash will be computed
     * @return hash of the element
     */
    int hash(String string);
}
