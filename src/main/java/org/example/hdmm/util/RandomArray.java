package org.example.hdmm.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomArray {




    public static void main(String[] args) {
        int min = 1;
        int max = 10;
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        int[] randomNumbers = getRandomNumbers(min, max, 5,set);

        System.out.println("Random numbers: ");
        for (int number : randomNumbers) {
            System.out.print(number + " ");
        }
    }

    public static int[] getRandomNumbers(int min, int max, int count) {
        if (max - min + 1 < count) {
            throw new IllegalArgumentException("Khoảng không đủ lớn để sinh " + count + " số khác nhau");
        }

        Random random = new Random();
        int[] numbers = new int[count];
        Set<Integer> uniqueNumbers = new HashSet<>();

        int index = 0;
        while (uniqueNumbers.size() < count) {
            int num = random.nextInt(max - min + 1) + min;
            if (uniqueNumbers.add(num)) {
                numbers[index++] = num;
            }
        }

        return numbers;
    }
    public static int[] getRandomNumbers(int min, int max, int count, Set<Integer>  uniqueNumbers) {
        int total = uniqueNumbers.size();
        if (max - min + 1 < count) {
            throw new IllegalArgumentException("Khoảng không đủ lớn để sinh " + count + " số khác nhau");
        }

        Random random = new Random();
        int[] numbers = new int[count];


        int index = 0;
        while (uniqueNumbers.size() < count+total) {
            int num = random.nextInt(max - min + 1) + min;
            if (uniqueNumbers.add(num)) {
                numbers[index++] = num;
            }
        }

        return numbers;
    }


}
