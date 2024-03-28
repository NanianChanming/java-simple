package com.nanian.simple.entity;

import java.util.*;

/**
 * @Author Caoheng
 * @Date 2023/5/9 10:25
 * @Description
 */
public class NearestNumber {
    public static void main(String[] args) {
        int[] source = {33, 23, 49};
        int[] target = {94, 25, 83};

        int[][] diff = new int[source.length][target.length];

        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < target.length; j++) {
                diff[i][j] = Math.abs(source[i] - target[j]);
            }
        }

        List<Integer> usedTargets = new ArrayList<>();

        for (int i = 0; i < source.length; i++) {
            int minDiff = Integer.MAX_VALUE;
            int minTarget = -1;

            for (int j = 0; j < target.length; j++) {
                if (!usedTargets.contains(j) && diff[i][j] < minDiff) {
                    minDiff = diff[i][j];
                    minTarget = j;
                }
            }
            usedTargets.add(minTarget);
            System.out.println("Source " + source[i] + " matches target " + target[minTarget] + " with diff " + minDiff);
        }

    }

    public static int minNumber(int source, List<Integer> list) {
        return list.stream().min(Comparator.comparing(t -> t - source)).get();
    }

    public static void randomNumber() {
        Random random = new Random();
        List<Integer> sources = Arrays.asList(random.nextInt(100), random.nextInt(100), random.nextInt(100));
        List<Integer> targets = Arrays.asList(random.nextInt(100), random.nextInt(100), random.nextInt(100));

        System.out.println(sources);
        System.out.println(targets);

        Map<Integer, Integer> nearestTargets = new HashMap<>();
        Set<Integer> unmatchedTargets = new HashSet<>(targets);

        while (!unmatchedTargets.isEmpty()) {
            for (int source : sources) {
                int nearestTarget = unmatchedTargets.stream().min(Comparator.comparingInt(target -> Math.abs(target - source))).orElse(-1);
                // 分别与其余参数比较
                if (nearestTarget >= 0) {
                    nearestTargets.put(source, nearestTarget); // 记录最近的目标数字
                    unmatchedTargets.remove(nearestTarget); // 从未匹配集合中移除已匹配的目标数字
                }
            }
        }

        for (Map.Entry<Integer, Integer> entry : nearestTargets.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
