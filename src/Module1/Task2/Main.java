package Module1.Task2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class Main {

    public static class Student {
        private List<Book> books;
        private String name;

        public Student(String name, List<Book> books) {
            this.books = books;
            this.name = name;
        }

        public List<Book> getBooks() {
            return books;
        }

        @Override
        public String toString() {
            return "Student{books=" + books + '}';
        }
    }

    static List<Student> students = Arrays.asList(
            new Student("Alex", Arrays.asList(
                    new Module1.Task2.Book("Java 8", 400, 2014),
                    new Module1.Task2.Book("Clean Code", 464, 2008),
                    new Module1.Task2.Book("Effective Java", 416, 2018),
                    new Module1.Task2.Book("Design Patterns", 395, 1994),
                    new Module1.Task2.Book("Refactoring", 450, 2018)
            )),
            new Student("Nick", Arrays.asList(
                    new Module1.Task2.Book("Java 8", 400, 2014),
                    new Module1.Task2.Book("Spring in Action", 500, 2020),
                    new Module1.Task2.Book("Head First Java", 600, 2003),
                    new Module1.Task2.Book("Algorithms", 700, 2011),
                    new Module1.Task2.Book("Programming Pearls", 300, 1999)
            )),
            new Student("Lucius", Arrays.asList(
                    new Module1.Task2.Book("Java 8", 480, 2016),
                    new Module1.Task2.Book("Clear code", 780, 2020),
                    new Module1.Task2.Book("Bible  Java", 900, 1991),
                    new Module1.Task2.Book("Programming Pearls", 300, 1999),
                    new Module1.Task2.Book("Code review", 367, 1989)
            ))
    );

    public static void main(String[] args)
    {

        Optional<Integer> result = students.stream()
                .peek(System.out::println)
                .flatMap(student -> student.getBooks().stream())
                .sorted(Comparator.comparingInt(Book::getPages))
                .distinct()
                .filter(book -> book.getYear() > 2000)
                .limit(3)
                .map(Book::getYear)
                .findFirst();

        System.out.println(
                result.isPresent()
                        ? "Год выпуска найденной книги: " + result.get()
                        : "Такая книга отсутствует"
        );
    }
}