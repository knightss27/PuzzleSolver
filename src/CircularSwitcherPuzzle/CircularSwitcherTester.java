package CircularSwitcherPuzzle;

import PuzzleInterfaces.Search;
import PuzzleInterfaces.State;
import SearchAlgorithms.IDAStarSearch;
import SearchAlgorithms.IDDepthFirstSearch;
import SearchUtils.Solution;
import SearchUtils.Solver;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CircularSwitcherTester {
    public static void main(String[] args) {
//        generateTestCases();

        testSwitcher("switcher-testcases2.txt", new IDAStarSearch());
    }

    static void testSwitcher(String filename, Search searcher) {
        double totalTime = 0.0;
        int numTests = 0;
        int previousPathLength = -1;
        List<String> inputLines = readFile(filename);

        for (String inputLine : inputLines) {
            String[] fields = inputLine.split(" ");
            int randomizedPathLength = Integer.parseInt(fields[0]);
            String[] startStateArrayStrings = fields[1].split(",");

            int[] startStateArray = new int[20];
            for (int i = 0; i < 20; i++) {
                startStateArray[i] = Integer.parseInt(startStateArrayStrings[i]);
            }

            State startState = new CircularSwitcherState(startStateArray);

            long startTime = System.nanoTime();
            Solution solution = searcher.search(startState);
            long endTime = System.nanoTime();
            double duration = (endTime - startTime) / 1e6;

            System.out.println("Solved for path of length " + randomizedPathLength + " in " + (int) duration + ", actual length " + solution.getPathLength());

            if (previousPathLength != -1 && previousPathLength != randomizedPathLength) {
                displayAverageTime(searcher, previousPathLength, totalTime, numTests);
                totalTime = 0.0;
                numTests = 0;
            }

            totalTime += duration;
            numTests += 1;
            previousPathLength = randomizedPathLength;
        }
        displayAverageTime(searcher, previousPathLength, totalTime, numTests);
    }

    static List<String> readFile(String filename) {
        try {
            Path filepath = FileSystems.getDefault().getPath(filename);
            return Files.readAllLines(filepath);
        } catch (IOException ioexception) {
            throw new RuntimeException("Ack!  We had a problem reading the file: " + ioexception);
        }
    }

    static void displayAverageTime(Search searcher, int pathLength, double totalTime, int numTests) {
        double avgTime = totalTime / numTests;
        System.out.printf("Average time to solve puzzles using %s with path length %2d is %8.1f ms based on %2d test cases\n",
                searcher.toString(), pathLength, avgTime, numTests);
    }

    static void generateTestCases() {
        StringBuilder output = new StringBuilder();
        for (int depth = 1; depth <= 20; depth++) {
            for (int i = 0; i < 10; i++) {
                // get the randomized state
                CircularSwitcherState state = new CircularSwitcherState();
                state.randomize(depth);

                // add it to the output
                StringBuilder stateArray = new StringBuilder();
                for (int j = 0; j < state.state.length; j++) {
                    stateArray.append(state.state[j]);
                    if (j < state.state.length-1) {
                        stateArray.append(",");
                    }
                }
                output.append(depth).append(" ").append(stateArray).append("\n");
            }
        }
        System.out.println(output);
    }
}
