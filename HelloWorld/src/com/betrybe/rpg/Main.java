package rpg;

public class Main {
  public static void main(String[] args) {
    Warrior warrior = new Warrior("Warrior", 100, "Sword");
    warrior.printStats();
    warrior.attack();
  }
}
