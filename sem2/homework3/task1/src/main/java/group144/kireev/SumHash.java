package group144.kireev;

public class SumHash implements HashFunction {
    private int mod = 512;

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
