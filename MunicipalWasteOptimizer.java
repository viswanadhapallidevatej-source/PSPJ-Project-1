import java.util.*;

public class MunicipalWasteOptimizer {

    static class Bin {
        int id;
        String location, type;
        double fill, distance;

        Bin(int id, String location, String type,
            double fill, double distance) {
            this.id = id;
            this.location = location;
            this.type = type;
            this.fill = fill;
            this.distance = distance;
        }

        // Calculate collection priority
        double priority() {
            int urgency;

            if (type.equalsIgnoreCase("Organic"))
                urgency = 30;
            else if (type.equalsIgnoreCase("Recyclable"))
                urgency = 20;
            else
                urgency = 10;

            return fill + urgency - distance;
        }

        public String toString() {
            return "Bin " + id +
                   " | " + location +
                   " | " + type +
                   " | Fill: " + fill + "%" +
                   " | Priority: " +
                   String.format("%.1f", priority());
        }
    }

    // Display all bins
    static void displayBins(ArrayList<Bin> bins) {
        System.out.println("\n--- WASTE BIN DETAILS ---");

        for (Bin b : bins)
            System.out.println(b);
    }

    // Find the most urgent bin
    static Bin highestPriority(ArrayList<Bin> bins) {

        Bin best = bins.get(0);

        for (Bin b : bins) {
            if (b.priority() > best.priority())
                best = b;
        }

        return best;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Bin> bins = new ArrayList<>();

        // Sample municipal waste data
        bins.add(new Bin(
                101, "Kukatpally",
                "Organic", 90, 5));

        bins.add(new Bin(
                102, "Miyapur",
                "Recyclable", 70, 8));

        bins.add(new Bin(
                103, "Bachupally",
                "General", 55, 12));

        bins.add(new Bin(
                104, "Moosapet",
                "Organic", 85, 6));

        int choice;

        do {

            System.out.println("\n================================");
            System.out.println(" MUNICIPAL WASTE OPTIMISER");
            System.out.println("================================");
            System.out.println("1. Display all bins");
            System.out.println("2. Find priority bin");
            System.out.println("3. Add new bin");
            System.out.println("4. Show critical bins");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    displayBins(bins);
                    break;

                case 2:

                    Bin urgent = highestPriority(bins);

                    System.out.println(
                            "\nHighest Priority Bin:");
                    System.out.println(urgent);

                    break;

                case 3:

                    System.out.print("Enter Bin ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Location: ");
                    String location = sc.nextLine();

                    System.out.print(
                            "Enter Waste Type: ");
                    String type = sc.nextLine();

                    System.out.print(
                            "Enter Fill Percentage: ");
                    double fill = sc.nextDouble();

                    System.out.print(
                            "Enter Distance from Depot: ");
                    double distance = sc.nextDouble();

                    if (fill >= 0 && fill <= 100) {

                        bins.add(new Bin(
                                id, location, type,
                                fill, distance));

                        System.out.println(
                                "Bin added successfully.");

                    } else {
                        System.out.println(
                                "Invalid fill percentage.");
                    }

                    break;

                case 4:

                    System.out.println(
                            "\n--- CRITICAL BINS ---");

                    for (Bin b : bins) {

                        if (b.fill >= 80) {
                            System.out.println(
                                    "URGENT -> " + b);
                        }
                    }

                    break;

                case 5:

                    System.out.println(
                            "Thank you for using the system.");

                    break;

                default:

                    System.out.println(
                            "Invalid choice.");
            }

        } while (choice != 5);

        sc.close();
    }
}

