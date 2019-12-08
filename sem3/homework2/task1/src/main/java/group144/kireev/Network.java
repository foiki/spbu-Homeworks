package group144.kireev;

import java.util.LinkedList;
import java.util.List;

/** Describes local network. */
public class Network {
    private List<Computer> computers;
    private List<Computer> couldBeInfected = new LinkedList<>();
    private Virus virus;

    public Network(List<Computer> computers, Virus virus) {
        this.computers = computers;
        this.virus = virus;
    }

    /**
     * @param computers list of computers in network to add connections between them.
     * @param connections matrix of connections between computers.
     */
    public static void addConnections(List<Computer> computers, boolean[][] connections) {
        for (int i = 0; i < connections.length; ++i) {
            for (int j = i; j < connections.length; ++j) {
                if (connections[i][j]) {
                    Computer firstComputer = computers.get(i);
                    Computer secondComputer = computers.get(j);
                    firstComputer.addConnected(secondComputer);
                    secondComputer.addConnected(firstComputer);
                }
            }
        }
    }

    /**
     * Infects first computer in the network.
     * @param numberOfComputer to infect.
     */
    public void infectFirstComputer(int numberOfComputer) {
        Computer firstInfectedComputer = computers.get(numberOfComputer);
        firstInfectedComputer.setInfected();
        couldBeInfected.addAll(updateCouldBeInfectedComputers(firstInfectedComputer));
    }

    /**
     * @param computer infected computer that could infect connected in future steps.
     * @return List of computers connected to the infected.
     */
    private List<Computer> updateCouldBeInfectedComputers(Computer computer) {
        List<Computer> result = new LinkedList<>();
        for (Computer connected : computer.getConnectedComputers()) {
            if (!connected.isInfected() && !connected.getIsInTheCouldBeInfectedList()) {
                result.add(connected);
                connected.setInTheCouldBeInfectedList(true);
            }
        }
        return result;
    }

    /**
     * Modelling the step of infection in this network.
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

    /**
     * @return String representation of the network status.
     */
    public String getStatus() {
        int position = 0;
        StringBuilder result = new StringBuilder();
        for (Computer computer : computers) {
            result.append(position++).append(" computer(").append(computer.getOs()).append(") ");
            if (computer.isInfected()) {
                result.append("is infected\n");
            } else {
                result.append("is not infected\n");
            }
        }
        return result.toString();
    }

    public List<Computer> getCouldBeInfectedComputers() {
        return couldBeInfected;
    }
}
