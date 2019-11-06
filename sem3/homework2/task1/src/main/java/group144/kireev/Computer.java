package group144.kireev;

import java.util.LinkedList;
import java.util.List;

public class Computer {
    private OS os;
    private List<Computer> connectedComputers;
    private boolean isInfected = false;
    private boolean hasInfectedConnectedComputer = false;

    public Computer (OS os) {
        this.os = os;
        this.connectedComputers = new LinkedList<>();
    }

    public boolean isInfected() {
        return isInfected;
    }

    public boolean tryToInfect(Virus virus) {
        if (isInfected) {
            return true;
        }
        if (virus.tryToInfect(os)) {
            isInfected = true;
            return true;
        }
        return true;
    }

    public void setInfected() {
        isInfected = true;
    }

    public void addConnected(Computer computer) {
        connectedComputers.add(computer);
    }

    public List<Computer> getConnectedComputers() {
        return connectedComputers;
    }

    public void updateHasInfectedConnected() {
        hasInfectedConnectedComputer = true;
    }

    public boolean hasInfectedConnectedComputer() {
        return hasInfectedConnectedComputer;
    }
}
