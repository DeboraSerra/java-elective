package com.betrybe.rpg;

public class Main {

  public static void main(String[] args) {
    Warrior player = new Warrior();
    player.setName("Aragorn");
    player.setRace("Humano");

    System.out.println(player.getName() + " é da raça " + player.getRace());
    player.attack();

    Mage mage = new Mage();
    mage.setName("Gandalf");
    mage.setRace("Humano");
    mage.setMagic("Light");
    mage.attack();
  }
}
