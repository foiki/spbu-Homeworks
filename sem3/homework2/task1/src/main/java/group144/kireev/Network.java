package group144.kireev;

import java.util.LinkedList;
import java.util.List;

public class Network {
    private List<Computer> computers;
    private List<Computer> couldBeInfected;
    private Virus virus;

    public Network(List<Computer> computers, Virus virus) {
        this.computers = computers;
        this.virus = virus;
    }

    public void infectFirstComputer(int numberOfComputer) {
        Computer firstInfectedComputer = computers.get(numberOfComputer);
        firstInfectedComputer.setInfected();
        couldBeInfected.addAll(updateCouldBeInfectedComputers(firstInfectedComputer));
    }

    private List<Computer> updateCouldBeInfectedComputers(Computer computer) {
        List<Computer> result = new LinkedList<>();
        for (Computer connected : computer.getConnectedComputers()) {
            if (!connected.hasInfectedConnectedComputer() && !connected.isInfected()) {
                connected.updateHasInfectedConnected();
                result.add(connected);
            }
        }
        return result;
    }
}
