package main.java.me.valid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author valid
 * @date 06/12/2025
 */
public class DayThree {
    public static void main(String[] args) {
        String[] input;
        try {
            Path path = Path.of("src/main/resources/DayThree.txt");
            input = Files.readString(path).split("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        partOne(input);
        partTwo(input);
    }

    public static void partOne(String[] input) {
        int joltage = 0;

        for (String line : input) {
            int length = line.length();
            int max = 0;

            int[] rightMax = new int[length];
            rightMax[length - 1] = -1; // last digit has no right digit
            int currentMax = -1;

            for (int i = length - 1; i >= 0; i--) {
                rightMax[i] = currentMax;
                int digit = line.charAt(i) - '0';
                if (digit > currentMax) {
                    currentMax = digit;
                }
            }

            for (int i = 0; i < length; i++) {
                int first = line.charAt(i) - '0'; // current digit
                int second = rightMax[i]; // max digit to the right

                // check if there is no digit to the right
                if (second == -1) {
                    continue;
                }

                int jolt = first * 10 + second;
                if (jolt > max) {
                    max = jolt;
                }
            }

            joltage += max;
        }

        System.out.println("Part 1 Joltage: " + joltage);
    }

    public static void partTwo(String[] input) {
        long joltage = 0;

        for (String line : input) {
            line = line.trim();
            int length = line.length();
            int maxChars = 12;

            if (length <= maxChars) {
                joltage += Long.parseLong(line);
                continue;
            }

            StringBuilder sb = new StringBuilder(12);

            int i = 0;
            while (maxChars > 0) {
                int endIndex = length - maxChars;

                if (endIndex < i) {
                    endIndex = i;
                }

                char best = '0';
                int bestIndex = i;

                for (int j = i; j <= endIndex; j++) {
                    char c = line.charAt(j);
                    if (c > best) {
                        best = c;
                        bestIndex = j;

                        if (best == '9') {
                            break;
                        }
                    }
                }

                sb.append(best);
                i = bestIndex + 1;
                maxChars--;
            }

            joltage += Long.parseLong(sb.toString());
        }

        System.out.println("Part 2 Joltage: " + joltage);
    }
}
