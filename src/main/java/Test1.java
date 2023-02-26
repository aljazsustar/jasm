import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Test1 {

    public static Stop[][] MEMO = new Stop[100][100];
    public static Stop[] stopsGlobal = null;
    public static Stop[] shortestPath = null;
    public static int minPrice = Integer.MAX_VALUE;

    public static void cheapest(int index, Stop[] stations, int totalDistance, int gasLeft, int totalGas, int price) {

        /*if (stations[index] != null ) {
            if (MEMO[stations[index].id] != null) {
                shortestPath = MEMO[stations[index].id];

            } else {
                if (arraySum(stations) < arraySum(MEMO[stations[index].id])) {
                    MEMO[stations[index].id] = stations;
                } else if (arraySum(stations) == arraySum(MEMO[stations[index].id]) && stations.length < MEMO[stations[index].id].length) {
                    MEMO[stations[index].id] = stations;
                }
            }
        }*/

        if (gasLeft < 0) {
            return;
        }

        if (price > minPrice) {
            return;
        }

        if (index == stopsGlobal.length - 1) {

            if (gasLeft - totalDistance < 0) return;
            if (price < minPrice) {
                shortestPath = new Stop[stations.length];
                for (int i = 0; i < stations.length; i++) {
                    shortestPath[i] = stations[i];
                }
                minPrice = price;
                return;
            } else if (price == minPrice) {
                if (shortestPath != null && stations.length < shortestPath.length) {
                    shortestPath = stations;
                }
                return;
            }
            return;
        }
        stations[index] = null;
        cheapest(index + 1, stations, totalDistance - stopsGlobal[index + 1].distanceFromLast, gasLeft - stopsGlobal[index + 1].distanceFromLast, totalGas, price);
        stations[index] = stopsGlobal[index];
        int temp = price + (stopsGlobal[index].price * (totalGas - gasLeft));
        stations[index].amountRefilled = (stopsGlobal[index].price * (totalGas - gasLeft));
        cheapest(index + 1, stations, totalDistance - stopsGlobal[index + 1].distanceFromLast, totalGas - stopsGlobal[index + 1].distanceFromLast, totalGas, temp);
    }

    public static int arraySum(Stop[] stops) {
        int res = 0;
        for (int i = 0; i < stops.length; i++) {
            if (stops[i] != null)
                res += stops[i].price * stops[i].amountRefilled;
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        String[] lines = readFile(args[0]);
        parse(lines);
        int[] params = parseParams(args[0]);
        Stop[] subset = new Stop[stopsGlobal.length];

        cheapest(0, subset, params[0], params[1], params[1], 0);
        write(args[1], shortestPath);

    }

    public static String[] readFile(String path) throws IOException {

        File file = new File(path);
        Scanner sc = new Scanner(file);

        int linesNo = Integer.parseInt(sc.nextLine().split(",")[2]);
        String[] lines = new String[linesNo];

        for (int i = 0; i < linesNo; i++) {
            lines[i] = sc.nextLine();
        }

        sc.close();

        return lines;
    }

    public static void write(String path, Stop[] values) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(path));
        boolean isFirst = true;
        for (int i = 0; i < values.length; i++) {
            if (values[i] != null && values[i].id > -1) {
                if (isFirst) {
                    isFirst = false;
                    writer.print(values[i].id);
                } else
                    writer.print("," + values[i].id);
            }
        }
        writer.close();
    }

    public static void parse(String[] fileContents) {

        Stop[] stops = new Stop[fileContents.length + 2];

        stops[0] = new Stop();

        for (int i = 1; i <= fileContents.length; i++) {
            stops[i] = new Stop(fileContents[i - 1]);
        }
        stops[stops.length - 1] = new Stop();
        // printArray(stops);
        stopsGlobal = stops;
    }

    public static int[] parseParams(String path) throws IOException {

        File file = new File(path);
        Scanner sc = new Scanner(file);

        String rawLine = sc.nextLine();
        sc.close();

        int[] val = new int[3];
        String[] split = rawLine.split(",");
        val[0] = Integer.parseInt(split[0]);
        val[1] = Integer.parseInt(split[1]);
        val[2] = Integer.parseInt(split[2]);
        return val;
    }
}

class Stop {

    public int id;
    public int distanceFromLast;
    public int price;
    public int amountRefilled;

    Stop() {
        this.id = -1;
        this.distanceFromLast = 0;
        this.price = 0;
        this.amountRefilled = 0;
    }

    Stop(String value) {
        this.id = Integer.parseInt(value.split(":")[0]);
        this.distanceFromLast = Integer.parseInt(value.split(":")[1].split(",")[0]);
        this.price = Integer.parseInt(value.split(":")[1].split(",")[1]);
        this.amountRefilled = 0;
    }
}