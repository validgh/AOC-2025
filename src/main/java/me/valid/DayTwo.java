package main.java.me.valid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DayTwo {
    public static void main(String[] args) {
        String[] input;
        try {
            Path path = Path.of("src/main/resources/DayTwo.txt");
            input = Files.readString(path).split(",");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        partOne(input);
        partTwo(input);
    }

    public static void partOne(String[] input) {
        long sum = 0;

        for (String line : input) {
            String[] range = line.trim().split("-");
            long start = Long.parseLong(range[0]);
            long end = Long.parseLong(range[1]);

            for (long i = start; i <= end; i++) {
                int middle = String.valueOf(i).length() / 2;
                if (middle == 0) {
                    continue;
                }

                String first = String.valueOf(i).substring(0, middle);
                String second = String.valueOf(i).substring(middle);

                if (first.charAt(0) == '0') {
                    continue;
                }

                if (first.equals(second)) {
                    sum += i;
                }
            }
        }

        System.out.println("Part 1 Sum: " + sum);
    }

    public static void partTwo(String[] input) {
        long sum = 0;

        for (String line : input) {
            String[] range = line.trim().split("-");
            long start = Long.parseLong(range[0]);
            long end = Long.parseLong(range[1]);

            for (long i = start; i <= end; i++) {
                boolean alreadyAdded = false;
                String numString = String.valueOf(i);
                int length = numString.length();

                for (int j = 1; j <= length / 2; j++) {
                    if (length % j != 0) {
                        continue;
                    }

                    String pattern = numString.substring(0, j);
                    int repeat = length / j;

                    StringBuilder repeated = new StringBuilder();
                    repeated.append(pattern.repeat(repeat));

                    if (repeated.toString().equals(numString)) {
                        if (alreadyAdded) {
                            break;
                        }
                        sum += i;
                        alreadyAdded = true;
                    }
                }
            }
        }

        System.out.println("Part 2 Sum: " + sum);
    }
}
