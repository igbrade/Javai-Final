package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.MinionCardModel;
import com.unicamp.mc322.javai_final.gamestate.GameStateManager;
import com.unicamp.mc322.javai_final.gamestate.InputListener;
import com.unicamp.mc322.javai_final.gamestate.SummonState;

public class TianaModel extends MinionCardModel {
	TianaModel() {
		super("card.tiana.name", "card.tiana.desc", 8, 7, 7);
	}
	
	@Override
	public void onDraw(Card card) {
		// Uma das cartas sumonadas ataca o nexus inimigo
		GameStateManager manager = GameStateManager.getInstance();
		
		SummonState g = (SummonState)manager.getCurrentState();
		g.addListener(new InputListener() {
			@Override
			public boolean onInput(String input) {
				Card c = null;
				boolean possibleSelection = false;
				for(int i = 0;i < 6;i++) {
					if(manager.getCurrentPlayer().getFieldCards()[i] != null) {
						possibleSelection = true;							
					}
				}
				if(possibleSelection == false) {
					System.err.println("Nao existe carta para aplicar o Efeito de Tiana");
					return true;
				}
				
				if(manager.getCurrentPlayer().isAI()) {
					int maxDamageCard = 0;
					for(int i = 0;i < manager.getCurrentPlayer().getFieldCards().length;i++) {
						if(manager.getCurrentPlayer().getFieldCards()[i] != null && manager.getCurrentPlayer().getFieldCards()[i].getDamage() > maxDamageCard) {
							c = manager.getCurrentPlayer().getFieldCards()[i];
							maxDamageCard = c.getDamage();
						}
					}
				} else {
					if(input.equals("done")) {
						System.err.println("Clique em alguma carta do campo para aplicar o alvo certeiro");
						return false;
					}
					String[] s = input.split(" ");
					if(Integer.parseInt(s[2]) != manager.getCurrentPlayerIndex()) {
						System.err.println("Selecione somente suas coisas");
						return false;
					} else if(s[0].equals("hand")) {
						System.err.println("Selecione alguma carta do campo");
						return false;
					} else {
						int index = Integer.parseInt(s[1]);
						if(manager.getCurrentPlayer().getFieldCards()[index] == null) {
							System.err.println("Campo vazio");
							return false;
						} else {
							c = manager.getCurrentPlayer().getFieldCards()[index];							
						}
					}
				}
				
				manager.getOpponentPlayer().takeNexusDamage(c.getDamage());
				return true;
			}
		});
	}
}
