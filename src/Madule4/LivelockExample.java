package Madule4;

public class LivelockExample {
    static class Spoon {
        private final String name;
        public Spoon(String name) { this.name = name; }
        public String getName() { return name; }
    }

    static class HungryPerson implements Runnable {
        private String name;
        private Spoon mySpoon;
        private Spoon partnersSpoon;

        public HungryPerson(String name, Spoon mySpoon, Spoon partnersSpoon) {
            this.name = name;
            this.mySpoon = mySpoon;
            this.partnersSpoon = partnersSpoon;
        }

        public void eatWith(Spoon spoon, Spoon otherSpoon) {
            while (mySpoon != spoon) {
                System.out.println(name + ": передаю ложку");
                mySpoon = spoon;
                spoon = otherSpoon;
                otherSpoon = mySpoon;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(name + ": ем!");
        }

        @Override
        public void run() {
            eatWith(mySpoon, partnersSpoon);
        }
    }

    public static void main(String[] args) {
        Spoon spoon1 = new Spoon("Ложка 1");
        Spoon spoon2 = new Spoon("Ложка 2");

        HungryPerson person1 = new HungryPerson("Елена", spoon1, spoon2);
        HungryPerson person2 = new HungryPerson("Сергей", spoon2, spoon1);

        new Thread(person1).start();
        new Thread(person2).start();
    }
}
