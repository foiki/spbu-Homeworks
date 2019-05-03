package group144.kireev;

/** Interface realizes HashFunction */
public interface HashFunction {
    int hash(String string);
    int getMod();
}
