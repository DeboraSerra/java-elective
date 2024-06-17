package rpg;

public class Warrior extends PlayableCharacter {
  private String weapon;

  public Warrior(String name, int health, String weapon) {
    super(name, health);
    this.weapon = weapon;
  }

  public void attack() {
    if (this.isAlive) {
      System.out.println(this.name + " attacks");
    }
  }

  public void printStats() {
    System.out.println("Name: " + this.name);
    System.out.println("Health: " + this.health);
    System.out.println("Weapon: " + this.weapon);
  }
}
