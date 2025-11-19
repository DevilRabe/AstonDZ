package Module1.Task1;

import java.util.ArrayList;
public class Main {

    public static void main(String[] args)
    {
        // Тест MyArrayList
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println("List: " + list);

        list.remove(Integer.valueOf(20));
        System.out.println("After remove: " + list);

        MyArrayList<Integer> other = new MyArrayList<>();
        other.add(40);
        other.add(50);
        list.addAll(other);
        System.out.println("After addAll: " + list);

        // Тест MyHashSet
        MyHashSet<String> set = new MyHashSet<>();
        set.add("apple");
        set.add("banana");
        set.add("apple");
        System.out.println("Set: " + set);

        set.remove("banana");
        System.out.println("After remove: " + set);

        System.out.println("Contains apple? " + set.contains("apple"));

        ArrayList<String> lis = new ArrayList<>();

    }
}