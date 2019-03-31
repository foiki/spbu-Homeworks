package group144.kireev;

public class PolynomialHash implements HashFunction {
    private int mod = 512;

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
