package main.java.me.valid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DayOne {
    public static void main(String[] args) {
        String[] input;
        try {
            Path path = Path.of("src/main/resources/DayOne.txt");
            input = Files.readString(path).split("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        partOne(input);
        partTwo(input);
    }

    public static void partOne(String[] input) {
        int dial = 50;
        int password = 0;

        for (String line : input) {
            line = line.trim();
            if (line.isEmpty()) continue;

            char dir = line.charAt(0);
            int rotation = Integer.parseInt(line.substring(1));

            if (dir == 'R') {
                dial = (dial + rotation) % 100;
            } else {
                dial = (dial - (rotation % 100) + 100) % 100;
            }

            if (dial == 0) {
                password++;
            }
        }

        System.out.println("Part 1 Password: " + password);
    }

    public static void partTwo(String[] input) {
        int password = 0;
        int dial = 50;

        for (String line : input) {
            line = line.trim();
            if (line.isEmpty()) continue;

            char dir = line.charAt(0);
            int rotation = Integer.parseInt(line.substring(1));

            password += Math.floorDiv(rotation, 100);

            if (dir == 'R') {
                if (dial + (rotation % 100) > 100) {
                    password++;
                }

                dial = (dial + rotation) % 100;
            } else {
                if (dial - (rotation % 100) < 0 && dial != 0) {
                    password++;
                }

                dial -= rotation;

                if (dial < 0) {
                    dial = 100 - (Math.abs(dial) % 100);

                    if (dial == 100) {
                        dial = 0;
                    }
                }
            }

            if (dial == 0) {
                password++;
            }
        }

        System.out.println("Part 2 Password: " + password);
    }
}