package com.unicamp.mc322.javai_final.cards;

public abstract class HeroCardModel extends MinionCardModel {

	protected HeroCardModel(String unlocalizedName, String unlocalizedDescription, int manaCost, int baseDamage, int baseHealth) {
		super(unlocalizedName, unlocalizedDescription, manaCost, baseDamage, baseHealth);
	}
	
	public void levelUp(Card c) {
		
	}
	
	public abstract int getLeveldUpDamage();
	
	public abstract int getLeveldUpHealth();
	
	@Override
	public boolean isHero() {
		return true;
	}
}
