import java.util.Random;

public class U2 extends Rocket {
    private static int rocketU2Counter;
    private final int maxCargo = 11000;
    private static final int cost = 120000000;
    private final int weight = 18000;
    private double chanceLaunched;
    private double chanceLanded;

    public U2() {
        this(0);
    }

    public U2(int lastWeight) {
        setMaxCargo(maxCargo);
        setCost(cost);
        setWeight(weight);
        setCurrentWeight(lastWeight);

        rocketU2Counter++;
        System.out.println("------------------------------------");
    }


    public static int getRocketU2Counter() {
        return rocketU2Counter;
    }

    public static void setRocketU2Counter(int rocketU2Counter) {
        U2.rocketU2Counter = rocketU2Counter;
    }

    @Override
    public boolean launch() {
        System.out.println("\nLAUNCHING...");
        Random randomNumber = new Random();
        double rand = randomNumber.nextDouble();
        chanceLaunched = 0.04 * (getTotalWeight() / getMaxCargo());

        System.out.println("Cargo: " + getCurrentWeight());
        if (chanceLaunched >= rand) {
            System.out.println("U2 Exploded!");
            System.out.println("...Sending another rocket because it exploded...");
            return false;
        } else {
            System.out.println("U2 launched successfully!");
            return true;
        }
    }

    @Override
    public boolean land() {
        System.out.println("\nLANDING...");
        Random randomNumber = new Random();
        double rand = randomNumber.nextDouble();
        chanceLanded = 0.08 * (getTotalWeight() / maxCargo);

        if (chanceLanded >= rand) {
            System.out.println("U2 Crashed!!");
            System.out.println("...Sending another rocket because it crashed...");
            return false;
        } else {
            System.out.println("U2 landed successfully!");
            return true;
        }
    }

    public static long getTotalCost() {
        return (long) getRocketU2Counter() * cost;
    }
}
