package group144.kireev;

import java.util.LinkedList;
import java.util.List;

public class Network {
    private List<Computer> computers;
    private List<Computer> couldBeInfected = new LinkedList<>();
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

    /**
     * Modelling the step of infection in this network
     */
    public void modelStep() {
        List<Computer> newInfectedComputers = new LinkedList<>();
        List<Computer> newProbablyInfectedComputers = new LinkedList<>();
        for (Computer computer : couldBeInfected) {
            if (computer.tryToInfect(virus)) {
                newInfectedComputers.add(computer);
                newProbablyInfectedComputers.addAll(updateCouldBeInfectedComputers(computer));
            }
        }
        couldBeInfected.removeAll(newInfectedComputers);
        couldBeInfected.addAll(newProbablyInfectedComputers);
    }

    public String getStatus() {
        int position = 0;
        StringBuilder result = new StringBuilder();
        for (Computer computer : computers) {
            result.append(position++).append(" computer(").append(computer.getOs()).append(") ");
            if (computer.isInfected()) {
                result.append("is infected");
            } else {
                result.append("is not infected");
            }
        }
        return result.toString();
    }

    public List<Computer> getCouldBeInfectedComputers() {
        return couldBeInfected;
    }
}
