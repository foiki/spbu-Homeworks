package group144.kireev;

/** Class realizes Sum HashFunction */
public class SumHash implements HashFunction {

    /**
     * @param string to calculate hash
     * @return Polynomial Hash of string
     */
    @Override
    public int hash(String string) {
        int result = 0;
        for (int i = 0; i < string.length(); i++) {
            result += string.charAt(i);
            result = result % Integer.MAX_VALUE;
        }
        return result;
    }
}
