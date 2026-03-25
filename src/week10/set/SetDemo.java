package week10.set;

import java.util.function.Function;
import java.util.function.Predicate;

class EvenPredicate implements Predicate<Integer> {
    public boolean test(Integer value) {
        return (int)value % 2 == 0;
    }
}

class Over50Predicate implements Predicate<Integer> {
    public boolean test(Integer value) {
        return value > 50;
    }
}

class MapTimes10 implements Function<Integer, Integer> {
    public Integer apply(Integer value) {
        return value * 10;
    }
}

class MapMod10 implements Function<Integer, Integer> {
    public Integer apply(Integer value) {
        return value % 10;
    }
}

public class SetDemo {
    private static void showSet(Set set, String setName) {
        System.out.printf("%s: ", setName);
        for (Integer element : set) {
            System.out.print(element.toString() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] setAElements = { 95, 64, 19, 67, -24, 90 };
        int[] setBElements = { 67, 90, 67, 42, -84 };

        Set setA = new Set();
        for (int element : setAElements) {
            setA.add(element);
        }
        Set setB = new Set();
        for (int element : setBElements) {
            setB.add(element);
        }

        // Display the 2 sets
        showSet(setA, "Set A");
        showSet(setB, "Set B");

        // Perform union, intersection, and difference of 2 sets
        showSet(setA.union(setB), "A union B");
        showSet(setA.intersection(setB), "A intersect B");
        showSet(setA.difference(setB), "A \\ B");
        showSet(setB.difference(setA), "B \\ A");

        // Perform various filter operations
        EvenPredicate evenPredicate = new EvenPredicate();
        showSet(setA.filter(evenPredicate), "Set A filtered for evens");
        showSet(setB.filter(evenPredicate), "Set B filtered for evens");
        Over50Predicate over50Predicate = new Over50Predicate();
        showSet(setA.filter(over50Predicate), "Set A filtered for elements > 50");
        showSet(setB.filter(over50Predicate), "Set B filtered for elements > 50");

        // Perform various map operations
        MapTimes10 mapTimes10 = new MapTimes10();
        showSet(setA.map(mapTimes10), "Set A mapped * 10");
        showSet(setB.map(mapTimes10), "Set B mapped * 10");
        MapMod10 mapMod10 = new MapMod10();
        showSet(setA.map(mapMod10), "Set A mapped % 10");
        showSet(setB.map(mapMod10), "Set B mapped % 10");
    }
}
