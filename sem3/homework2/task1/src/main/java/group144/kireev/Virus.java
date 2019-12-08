package group144.kireev;

import java.util.Random;

/** Implements computer virus. */
public class Virus {
    private double windowsInfectionProbability = 0.5;
    private double linuxInfectionProbability = 0.3;
    private double macosInfectionProbability = 0.12;
    private Random random = null;

    public Virus (Random randomizer) {
        random = randomizer;
    }

    /**
     * @param os System that the virus should try to infect.
     * @return if the try was successful.
     */
    public boolean tryToInfect(OS os) {
        switch (os) {
            case WINDOWS:
                return tryToInfectWindows();
            case LINUX:
                return tryToInfectLinux();
            case MACOS:
                return tryToInfectMacOs();
            default:
                return false;

        }
    }

    public void setWindowsInfectionProbability(double value) {
        windowsInfectionProbability = value;
    }

    public void setLinuxInfectionProbability(double value) {
        linuxInfectionProbability = value;
    }

    public void setMacosInfectionProbability(double value) {
        macosInfectionProbability = value;
    }

    /**
     * @return if random try to infect Windows was successful.
     */
    private boolean tryToInfectWindows() {
        return random.nextDouble() < windowsInfectionProbability;
    }

    /**
     * @return if random try to infect Linux was successful.
     */
    private boolean tryToInfectLinux() {
        return random.nextDouble() < linuxInfectionProbability;
    }

    /**
     * @return if random try to infect MacOs was successful.
     */
    private boolean tryToInfectMacOs() {
        return random.nextDouble() < macosInfectionProbability;
    }
}
