package group144.kireev;

/** Class realizes Polynomial HashFunction */
public class PolynomialHash implements HashFunction {
    private int mod = 512;

    /**
     * @param string to calculate hash
     * @return Polynomial Hash of string
     */
    @Override
    public int hash(String string) {
        int prime = 13;
        int result = 0;
        for (int i = 0; i < string.length(); ++i) {
            result = (result + prime * string.charAt(i)) % mod;
        }
        return result;
    }

    @Override
    public int getMod() {
        return mod;
    }
}
