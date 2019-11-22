package group144.kireev;

import java.util.List;
import java.util.Random;

/** Implements random with generation specified values. */
public class Randomizer extends Random {
    private List<Double> values;
    private int currentPosition = 0;

    public Randomizer(List<Double> values) {
        this.values = values;
    }

    @Override
    public double nextDouble() {
        return values.get(currentPosition++ % values.size());
    }
}
