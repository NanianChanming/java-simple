package com.nanian.simple.entity;

import com.nanian.simple.utils.DistanceCalculatorUtil;

import java.util.*;

/**
 * @Author Caoheng
 * @Date 2023/5/9 9:53
 * @Description
 */
public class Location {
    private String name;
    private double latitude;
    private double longitude;

    public Location(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        List<Location> locations = Arrays.asList(
                new Location("A", 39.9, 116.4),
                new Location("B", 31.2, 121.4),
                new Location("C", 22.3, 113.9),
                new Location("D", 25.0, 102.7),
                new Location("E", 28.2, 112.9),
                new Location("F", 36.6, 101.7)
        );

        Map<Location, Location> nearestTargets = new HashMap<>();
        Set<Location> unmatchedTargets = new HashSet<>(locations.subList(3, 6)); // 目标地点的集合，初始时为未匹配状态
        for (Location source : locations.subList(0, 3)) { // 对每个原始地点进行计算
            Location nearestTarget = unmatchedTargets.stream()
                    .min(Comparator.comparingDouble(target -> DistanceCalculatorUtil.distance(source.getLatitude(), source.getLongitude(), target.getLatitude(), target.getLongitude())))
                    .orElse(null);
            nearestTargets.put(source, nearestTarget); // 记录最近的目标地点
            if (nearestTarget != null) {
                unmatchedTargets.remove(nearestTarget); // 从未匹配集合中移除已匹配的目标地点
            }
        }

        while (!unmatchedTargets.isEmpty()) { // 重复匹配未匹配的目标地点
            Location nearestSource = unmatchedTargets.stream()
                    .map(target -> {
                        Map.Entry<Location, Location> entry = nearestTargets.entrySet().stream()
                                .filter(e -> Objects.equals(e.getValue(), target))
                                .findFirst()
                                .orElse(null);
                        return entry != null ? entry.getKey() : null; // 找到距离最近的未匹配的原始地点
                    })
                    .filter(Objects::nonNull)
                    .min(Comparator.comparingDouble(source -> DistanceCalculatorUtil.distance(source.getLatitude(), source.getLongitude(), source.getLatitude(), source.getLongitude())))
                    .orElse(null);
            if (nearestSource == null) {
                break; // 如果没有未匹配的原始地点，结束循环
            }
            Location nearestTarget = unmatchedTargets.stream()
                    .min(Comparator.comparingDouble(target -> DistanceCalculatorUtil.distance(nearestSource.getLatitude(), nearestSource.getLongitude(), target.getLatitude(), target.getLongitude())))
                    .orElse(null);
            nearestTargets.put(nearestSource, nearestTarget); // 记录最近的目标地点
            unmatchedTargets.remove(nearestTarget); // 从未匹配集合中移除已匹配的目标地点
        }
        System.out.println(nearestTargets);
    }
}