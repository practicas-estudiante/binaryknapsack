import Model.Knapsack;
import Solvers.ConstructiveMethods.GreedyConstructive;
import Solvers.ConstructiveMethods.RandomConstructive;
import Solvers.Exact.DynamicProgramming;
import Solvers.Metaheuristic.LocalSearch.SwapLocalSearch;
import Solvers.Metaheuristic.LocalSearch.SwapLocalSearchAddition;
import Solvers.Metaheuristic.VNS.Skewed.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SkewedExp {
    public static String path = "resources/instances_01_KP/large_scale";

    public static void main(String[] args) {
        //executeDynamicProgramming();
        //executeConstructive();
       // executeConstructiveLSBest();
        //executeConstructiveLSFirst();
        executeSequentialVNSTimedmethods();
    }

    public static void executeSequentialVNSTimedmethods() {
        var files = readFilesNames(path);

        var BNVS = new ArrayList<String[]>();
        BNVS.add(new String[]{"Instance", "Value", "Time"});
        files.stream().forEach(file -> {
            System.out.println(
                    "Executing BVNS for file: " + file
            );
            var lines = readFile(path + "/" + file);
            var knapsack = createKnapsack(lines);
            var result = executeBVNSSeqTimed(knapsack);
            var resultString = new String[]{file, String.valueOf(result[0]), String.valueOf(result[1])};
            BNVS.add(resultString);
        });

        writeListOfArraysToCSV(BNVS, "resultBVNSSkewed.csv");






        var RVNS = new ArrayList<String[]>();
        RVNS.add(new String[]{"Instance", "Value", "Time"});
        files.stream().forEach(file -> {
            System.out.println(
                    "Executing RVNS for file: " + file
            );
            var lines = readFile(path + "/" + file);
            var knapsack = createKnapsack(lines);
            var result = executeRVNSSeqTimed(knapsack);
            var resultString = new String[]{file, String.valueOf(result[0]), String.valueOf(result[1])};
            RVNS.add(resultString);
        });

        writeListOfArraysToCSV(RVNS, "resultRVNSSkewed.csv");







        var VND = new ArrayList<String[]>();
        VND.add(new String[]{"Instance", "Value", "Time"});
        files.stream().forEach(file -> {
            System.out.println(
                    "Executing VND for file: " + file
            );
            var lines = readFile(path + "/" + file);
            var knapsack = createKnapsack(lines);
            var result = executeVNDSeqTimed(knapsack);
            var resultString = new String[]{file, String.valueOf(result[0]), String.valueOf(result[1])};
            VND.add(resultString);
        });

        writeListOfArraysToCSV(VND, "resultVNDSkewed.csv");







        var GVNS = new ArrayList<String[]>();
        GVNS.add(new String[]{"Instance", "Value", "Time"});
        files.stream().forEach(file -> {
            System.out.println(
                    "Executing GVNS for file: " + file
            );
            var lines = readFile(path + "/" + file);
            var knapsack = createKnapsack(lines);
            var result = executeGVNSSeqTimed(knapsack);
            var resultString = new String[]{file, String.valueOf(result[0]), String.valueOf(result[1])};
            GVNS.add(resultString);
        });

        writeListOfArraysToCSV(GVNS, "resultGVNSSkewed.csv");
    }
    public static void executeDynamicProgramming() {
        var files = readFilesNames(path);

        var results = new ArrayList<String[]>();
        results.add(new String[]{"Instance", "Value", "Time"});
        files.stream().forEach(file -> {
            System.out.println(
                    "Executing Dynamic Programming for file: " + file
            );
            var lines = readFile(path + "/" + file);
            var knapsack = createKnapsack(lines);
            var result = executeDynamicProgrammingTimed(knapsack);
            var resultString = new String[]{file, String.valueOf(result[0]), String.valueOf(result[1])};
            results.add(resultString);
        });

        writeListOfArraysToCSV(results, "resultsDP.csv");
    }

    public static void executeConstructive() {
        var files = readFilesNames(path);

        var results = new ArrayList<String[]>();
        results.add(new String[]{"Instance", "Value", "Time"});
        files.stream().forEach(file -> {
            System.out.println(
                    "Executing constructive for file: " + file
            );
            var lines = readFile(path + "/" + file);
            var knapsack = createKnapsack(lines);
            var result = executeConstructiveTimed(knapsack);
            var resultString = new String[]{file, String.valueOf(result[0]), String.valueOf(result[1])};
            results.add(resultString);
        });

        writeListOfArraysToCSV(results, "resultsConsGood.csv");
    }

    public static void executeConstructiveLSBest() {
        var files = readFilesNames(path);

        var results = new ArrayList<String[]>();
        results.add(new String[]{"Instance", "Value", "Time"});
        files.stream().forEach(file -> {
            System.out.println(
                    "Executing constructive and swap localSearch best for file: " + file
            );
            var lines = readFile(path + "/" + file);
            var knapsack = createKnapsack(lines);
            var result = executeConstructiveLSTimedBest(knapsack);
            var resultString = new String[]{file, String.valueOf(result[0]), String.valueOf(result[1])};
            results.add(resultString);
        });

        writeListOfArraysToCSV(results, "resultsLSBest1.csv");
    }

    public static void executeConstructiveLSFirst() {
        var files = readFilesNames(path);

        var results = new ArrayList<String[]>();
        results.add(new String[]{"Instance", "Value", "Time"});
        files.stream().forEach(file -> {
            System.out.println(
                    "Executing constructive and swap localSearch first for file: " + file
            );
            var lines = readFile(path + "/" + file);
            var knapsack = createKnapsack(lines);
            var result = executeConstructiveLSTimedFirst(knapsack);
            var resultString = new String[]{file, String.valueOf(result[0]), String.valueOf(result[1])};
            results.add(resultString);
        });

        writeListOfArraysToCSV(results, "resultsLSFirst1.csv");
    }

    public static List<String> readFilesNames(String path) {
        List<String> filesNames = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(file -> filesNames.add(file.getFileName().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filesNames;
    }

    public static List<String> readFile(String Path) {
        List<String> lines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(Path))) {
            stream.forEach(lines::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static Knapsack createKnapsack(List<String> lines) {
        int maxWeight = Integer.parseInt(lines.get(0).split(" ")[1]);
        int[] values = new int[lines.size() - 1];
        int[] weights = new int[lines.size() - 1];
        for (int i = 1; i < lines.size(); i++) {
            String[] line = lines.get(i).split(" ");
            values[i - 1] = Integer.parseInt(line[0]);
            weights[i - 1] = Integer.parseInt(line[1]);
        }
        return new Knapsack(maxWeight, weights, values);
    }

    public static long[] executeDynamicProgrammingTimed(Knapsack knapsack) {
        long startTime = System.currentTimeMillis();
        DynamicProgramming.execute(knapsack);
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        return new long[]{knapsack.getValue(), duration};
    }

    public static long[] executeConstructiveTimed(Knapsack knapsack) {
        long startTime = System.currentTimeMillis();
        GreedyConstructive.createNewSolution(knapsack);
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        return new long[]{knapsack.getValue(), duration};
    }

    public static long[] executeConstructiveLSTimedBest(Knapsack knapsack) {
        long startTime = System.currentTimeMillis();
        RandomConstructive.createNewBadSolution(knapsack);
        SwapLocalSearchAddition.localSearchBestImprovement(knapsack);
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        return new long[]{knapsack.getValue(), duration};
    }

    public static long[] executeConstructiveLSTimedFirst(Knapsack knapsack) {
        long startTime = System.currentTimeMillis();
        RandomConstructive.createNewBadSolution(knapsack);
        SwapLocalSearch.localSearchFirstImprovement(knapsack);
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        return new long[]{knapsack.getValue(), duration};
    }


    public static long[] executeBVNSSeqTimed(Knapsack knapsack) {
        long startTime = System.currentTimeMillis();
        GreedyConstructive.createNewSolution(knapsack);
        new BVNS().execute(knapsack);
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        return new long[]{knapsack.getValue(), duration};
    }

    public static long[] executeVNDSeqTimed(Knapsack knapsack) {
        long startTime = System.currentTimeMillis();
        GreedyConstructive.createNewSolution(knapsack);
        new VND().execute(knapsack);
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        return new long[]{knapsack.getValue(), duration};
    }

    public static long[] executeRVNSSeqTimed(Knapsack knapsack) {
        long startTime = System.currentTimeMillis();
        GreedyConstructive.createNewSolution(knapsack);
        new RVNS().execute(knapsack);
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        return new long[]{knapsack.getValue(), duration};
    }

    public static long[] executeGVNSSeqTimed(Knapsack knapsack) {
        long startTime = System.currentTimeMillis();
        GreedyConstructive.createNewSolution(knapsack);
        new GVNS().execute(knapsack);
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        return new long[]{knapsack.getValue(), duration};
    }
    public static void writeListOfArraysToCSV(List<String[]> data, String path) {
        try {
            FileWriter csvWriter = new FileWriter(path);
            for (String[] row : data) {
                csvWriter.append(String.join(",", row));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}