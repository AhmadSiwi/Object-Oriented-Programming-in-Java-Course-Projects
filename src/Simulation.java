import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Simulation {
    private ArrayList<Item> items;
    private ArrayList<Rocket> rocketU1;
    private ArrayList<Rocket> rocketU2;
    private boolean hasLanded = true;
    private File phase1 = new File("phase-1.txt");
    private File phase2 = new File("phase-2.txt");

    public Simulation() {
    }

    public ArrayList<Item> loadItems(int phase) throws FileNotFoundException {

        if (phase == 1) {
            System.out.println("Loading phase 1");
            loadPerPhaseItems(phase1);
            System.out.println("Done loading phase 1");
        } else {
            System.out.println("Loading phase 2");
            loadPerPhaseItems(phase2);
            System.out.println("Done loading phase 2\n");
        }

        return items;
    }

    public void loadPerPhaseItems(File file) throws FileNotFoundException {
        items = new ArrayList<>();
        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNextLine()) {
            String string = fileScanner.nextLine();
            String[] separated = string.split("=");

            Item item = new Item(separated[0], Integer.parseInt(separated[1]));
            items.add(item);
        }
    }

    public ArrayList<Rocket> loadU1(List<Item> itemsU1) {
        System.out.println("loading U1...");
        rocketU1 = new ArrayList<>();
        Rocket rocket = new U1();
        Iterator iterator = itemsU1.iterator();

        while (iterator.hasNext()) {
            Item item = (Item) iterator.next();
            if (rocket.canCarry(item)) {
                rocket.carry(item);
            } else {
                rocketU1.add(rocket);
                rocket = new U1();
                System.out.println("New U1 rocket created");
                rocket.carry(item);
            }
            if (!iterator.hasNext()) {
                rocketU1.add(rocket);
            }
        }

        return rocketU1;
    }

    public ArrayList<Rocket> loadU2(ArrayList<Item> itemsU2) {
        System.out.println("loading U2");
        rocketU2 = new ArrayList<>();
        Rocket rocket = new U2();
        Iterator iterator = itemsU2.iterator();

        while (iterator.hasNext()) {
            Item item = (Item) iterator.next();
            if (rocket.canCarry(item)) {
                rocket.carry(item);
            } else {
                rocketU2.add(rocket);
                rocket = new U2();
                System.out.println("New U2 rocket created");
                rocket.carry(item);
            }
            if (!iterator.hasNext()) {
                rocketU2.add(rocket);
            }
        }

        return rocketU2;
    }

    public void runSimulation(ArrayList<Rocket> rockets, int i) {

        for (Rocket rocket : rockets) {

            while (!rocket.launch()) {
                launchSimulation(i);
            }

            while (!rocket.land()) {
                while (!rocket.launch()) {
                    launchSimulation(i);
                }
                landSimulation(i);
            }

        }
    }

    public void launchSimulation(int i) {

        if (i == 1) {
            int counter1 = U1.getRocketU1Counter();
            counter1++;
            U1.setRocketU1Counter(counter1);

        } else {
            int counter1 = U2.getRocketU2Counter();
            counter1++;
            U2.setRocketU2Counter(counter1);

        }
    }

    public void landSimulation(int i) {
        if (i == 1) {
            int counter = U1.getRocketU1Counter();
            counter++;
            U1.setRocketU1Counter(counter);

        } else {
            int counter = U2.getRocketU2Counter();
            counter++;
            U2.setRocketU2Counter(counter);
        }
        hasLanded = false;
    }
}
