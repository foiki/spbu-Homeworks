package group144.kireev;

import java.util.LinkedList;
import java.util.List;

/** Describes a computer. */
public class Computer {
    private OS os;
    private List<Computer> connectedComputers;
    private boolean isInfected = false;
    private boolean isInTheCouldBeInfectedList = false;

    public Computer (OS os) {
        this.os = os;
        this.connectedComputers = new LinkedList<>();
    }

    public boolean isInfected() {
        return isInfected;
    }

    /**
     * @param virus Virus tries to infect the computer.
     * @return if the try was successful.
     */
    public boolean tryToInfect(Virus virus) {
        if (isInfected) {
            return true;
        }
        if (virus.tryToInfect(os)) {
            isInfected = true;
            return true;
        }
        return false;
    }

    public void setInfected() {
        isInfected = true;
    }

    public void setInTheCouldBeInfectedList(boolean value) {
        isInTheCouldBeInfectedList = value;
    }

    public boolean getIsInTheCouldBeInfectedList() {
        return isInTheCouldBeInfectedList;
    }

    /**
     * Add new computer to the list with connected computers.
     * @param computer to add.
     */
    public void addConnected(Computer computer) {
        connectedComputers.add(computer);
    }

    public List<Computer> getConnectedComputers() {
        return connectedComputers;
    }

    public OS getOs() {
        return os;
    }
}
