package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.MinionCardModel;

public class VanguardModel extends MinionCardModel {

	VanguardModel() {
		super("card.vanguard.name", "card.vanguard.desc", 4, 3, 3);
	}

	@Override
	public void onSummon(Card c) {
		// +1/+1 para todos os seguidores aliados em campo (campeoes nao inclusos)
		//TODO: Funciona para as cartas que JA estao no campo, ou para as cartas que serao baixadas
		// depois dessa tambem? 
		for(int i = 0; i < 6; i++) {
			if(c.getOwner().getFieldCards()[i] != null) {
				Card fc = c.getOwner().getFieldCards()[i];
				fc.setDamage(fc.getDamage() + 1);
				fc.setHealth(fc.getHealth() + 1);
			}
		}
	}
}
