package group144.kireev;

import java.util.Random;

public class Virus {
    private final double WINDOWS_INFECTION_PROBABILITY = 0.5;
    private final double LINUX_INFECTION_PROBABILITY = 0.3;
    private final double MACOS_INFECTION_PROBABILITY = 0.12;
    private Random random = new Random();

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

    private boolean tryToInfectWindows() {
        return random.nextDouble() < WINDOWS_INFECTION_PROBABILITY;
    }

    private boolean tryToInfectLinux() {
        return random.nextDouble() < LINUX_INFECTION_PROBABILITY;
    }

    private boolean tryToInfectMacOs() {
        return random.nextDouble() < MACOS_INFECTION_PROBABILITY;
    }
}
