package group144.kireev;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NetworkTest {
    Computer firstComputer = new Computer(OS.WINDOWS);
    Computer secondComputer = new Computer(OS.MACOS);
    Computer thirdComputer = new Computer(OS.LINUX);
    Computer fourthComputer = new Computer(OS.WINDOWS);
    Computer fifthComputer = new Computer(OS.MACOS);
    List<Computer> computers = new LinkedList<>();

    private void initializeComputers() {
        computers.add(firstComputer);
        computers.add(secondComputer);
        computers.add(thirdComputer);
        computers.add(fourthComputer);
        computers.add(fifthComputer);
    }

    private void getConnections() throws IOException {
        int computerNumber = computers.size();
        BufferedReader in = new BufferedReader(new FileReader("src/test/resources/input.txt"));
        boolean[][] connections = new boolean[computerNumber][computerNumber];
        for (int i = 0; i < computerNumber; ++i) {
            String line = in.readLine();
            String[] lineElements = line.split(" ");
            for (int j = 0; j < computerNumber; ++j) {
                if (lineElements[j].equals("1")) {
                    connections[i][j] = true;
                } else {
                    connections[i][j] = false;
                }
            }
        }
        Network.addConnections(computers, connections);
    }

    @Test
    public void infectFirstComputerTest() {
        initializeComputers();
        assertFalse(fifthComputer.isInfected());
        assertFalse(secondComputer.isInfected());
        Network network = new Network(computers, new Virus());
        network.infectFirstComputer(1); //Numbers starting with 0
        assertFalse(computers.get(0).isInfected());
        assertTrue(computers.get(1).isInfected());
    }

    @Test
    public void updateCouldBeInfectedTest() {
        initializeComputers();
        firstComputer.addConnected(secondComputer);
        firstComputer.addConnected(fourthComputer);
        firstComputer.addConnected(fifthComputer);
        Network network = new Network(computers, new Virus());
        network.infectFirstComputer(2);
        assertTrue(network.getCouldBeInfectedComputers().isEmpty());
        network.infectFirstComputer(0);
        List<Computer> expected = new LinkedList<>();
        expected.add(secondComputer);
        expected.add(fourthComputer);
        expected.add(fifthComputer);
        assertEquals(expected, network.getCouldBeInfectedComputers());
    }

    // 0 1 0 1 1
    // 1 0 0 1 0
    // 0 0 0 1 1
    // 1 1 1 0 0
    // 1 0 1 0 0
    @Test
    public void getStatusCorrectnessTest() throws IOException {
        initializeComputers();
        getConnections();
        Network network = new Network(computers, new Virus());
        network.infectFirstComputer(0);
        assertEquals(network.getStatus(), "0 computer(WINDOWS) is infected\n" +
                                                "1 computer(MACOS) is not infected\n" +
                                                "2 computer(LINUX) is not infected\n" +
                                                "3 computer(WINDOWS) is not infected\n" +
                                                "4 computer(MACOS) is not infected\n");
    }

    @Test
    public void infectCorrectnessTest() throws IOException {
        initializeComputers();
        getConnections();
        Virus virus = new Virus();
        virus.setMacosInfectionProbability(1.0);
        virus.setWindowsInfectionProbability(0.0);
        virus.setLinuxInfectionProbability(1.0);
        Network network = new Network(computers, virus);
        network.infectFirstComputer(0);
        network.modelStep();
        assertEquals(network.getStatus(), "0 computer(WINDOWS) is infected\n" +
                                                "1 computer(MACOS) is infected\n" +
                                                "2 computer(LINUX) is not infected\n" +
                                                "3 computer(WINDOWS) is not infected\n" +
                                                "4 computer(MACOS) is infected\n");
        network.modelStep();
        assertEquals(network.getStatus(), "0 computer(WINDOWS) is infected\n" +
                                                "1 computer(MACOS) is infected\n" +
                                                "2 computer(LINUX) is infected\n" +
                                                "3 computer(WINDOWS) is not infected\n" +
                                                "4 computer(MACOS) is infected\n");
    }
}