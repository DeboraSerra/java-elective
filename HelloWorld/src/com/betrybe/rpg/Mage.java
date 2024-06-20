package com.betrybe.rpg;

public class Mage extends PlayableCharacter {

  public String getMagic() {
    return magic;
  }

  public void setMagic(String magic) {
    this.magic = magic;
  }

  private  String magic;

  @Override
  public void attack(){
    System.out.println("%s lançou a magia %s".formatted(getName(), magic));
  }
}
