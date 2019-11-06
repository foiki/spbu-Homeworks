package group144.kireev;

import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class NetworkTest {
    Computer firstComputer = new Computer(OS.WINDOWS);
    Computer secondComputer = new Computer(OS.MACOS);
    Computer thirdComputer = new Computer(OS.LINUX);
    Computer fourthComputer = new Computer(OS.WINDOWS);
    Computer fifthComputer = new Computer(OS.MACOS);
    Virus virus = new Virus();
    List<Computer> computers = new LinkedList<>();

    private void initializeComputers() {
        computers.add(firstComputer);
        computers.add(secondComputer);
        computers.add(thirdComputer);
        computers.add(fourthComputer);
        computers.add(fifthComputer);
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
        Network network = new Network(computers, virus);
        network.infectFirstComputer(2);
        assertTrue(network.getCouldBeInfectedComputers().isEmpty());
        network.infectFirstComputer(0);
        List<Computer> expected = new LinkedList<>();
        expected.add(secondComputer);
        expected.add(fourthComputer);
        expected.add(fifthComputer);
        assertEquals(expected, network.getCouldBeInfectedComputers());
    }
}