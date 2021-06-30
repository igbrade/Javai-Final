package com.unicamp.mc322.javai_final.gamestate;

import java.util.List;

import com.unicamp.mc322.javai_final.cards.Card;

public class RoundEndState extends GameState {
	private List<Integer> defendSelection;
	private List<Integer> attackSelection;
  	
	protected RoundEndState(GameStateManager manager, List<Integer> defendSelection, List<Integer> attackSelection) {
		super(manager);
		this.defendSelection = defendSelection;
		this.attackSelection = attackSelection;
	}

	@Override
	public void onStateLoad() {
		for(int i = 0;i < attackSelection.size();i++) {
			Card attacking = getManager().getCurrentPlayer().getFieldCards()[attackSelection.get(i)];
			if(defendSelection.get(i) == -1) {
				getManager().getOpponentPlayer().takeNexusDamage(attacking.getDamage());
				return;
			}
			
			Card defending = getManager().getOpponentPlayer().getFieldCards()[defendSelection.get(i)];
			
			defending.takeDamage(attacking.getDamage());
			attacking.takeDamage(defending.getDamage());
			
			if(attacking.getHealth() <= 0) {
				attacking.onDeath();
				attacking = null;
			}
			if(defending.getHealth() <= 0) {
				defending.onDeath();
				defending = null;
			}
		}
	}
}