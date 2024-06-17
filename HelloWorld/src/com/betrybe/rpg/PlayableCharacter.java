package rpg;

public class PlayableCharacter {
  protected String name;
  protected int health;
  protected boolean isAlive;

  public PlayableCharacter(String name, int health) {
    this.name = name;
    this.health = health;
    this.isAlive = true;
  }
}
