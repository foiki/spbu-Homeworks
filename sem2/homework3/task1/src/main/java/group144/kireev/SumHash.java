package group144.kireev;

/** Class realizes Sum HashFunction */
public class SumHash implements HashFunction {
    private int mod = 480;

    /**
     * @param string to calculate hash
     * @return Polynomial Hash of string
     */
    @Override
    public int hash(String string) {
        int result = 0;
        for (int i = 0; i < string.length(); i++) {
            result += string.charAt(i);
            result = result % mod;
        }
        return result;
    }

    @Override
    public int getMod() {
        return mod;
    }
}
