package Poker.GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

import pokerBase.Card;
import pokerBase.Deck;
import pokerBase.Hand;
import pokerBase.Player;
import pokerEnums.eGame;

public class PlayGame {
	private eGame gme;
	private ArrayList<Player> players = new ArrayList<Player>();

	public PlayGame(eGame gme) {
		this.gme = gme;
	}

	public eGame GetGame() {
		return this.gme;
	}

	public void AddPlayer(Player p) {
		players.add(p);
	}

	public void run() {

		playHand();

	}
	
	
	public void winner() {
		
		
	JLabel winner = new JLabel("Winner!");
	}
	
	public void playHand() {
		switch (gme) {

		case FiveStudTwoJoker: {

			// Set a new deck d
			Deck d = new Deck(4);
			
			// Reset each player's hand
			for (Player p : players) {
				p.resetHand();
			}

			// Create a hand for each player
			for (Player p : players) {
				Hand h = new Hand();
				for (int i = 0; i < 5; i++) {
					Card c = d.drawFromDeck();
					h.AddCardToHand(c);
				}
				h.HandleJokerWilds();
				p.SetHand(h);
			}

			// Player has the hand, call the playerUpdated method to set the
			// screen
			for (Player p : players) {
				p.getClient().playerUpdated(p);
				p.getClient().playerActed(p);
			}
			
		}
		case DeucesWild: {
			// Set a new deck d
			Deck d = new Deck(4);
							
			// Reset each player's hand
			for (Player p : players) {
				p.resetHand();
			}	
			// Create a hand for each player
			for (Player p : players) {
				Hand h = new Hand();
				for (int i = 0; i < 2; i++) {
					Card c = d.drawFromDeck();
					h.AddCardToHand(c);
				}
				h.HandleJokerWilds();
				p.SetHand(h);
				}
			// Player has the hand, call the playerUpdated method to set the
			// screen
			for (Player p : players) {
				p.getClient().playerUpdated(p);
				p.getClient().playerActed(p);
			}
		}
		
		case FiveStud:{
			// Set a new deck d
			Deck d = new Deck(4);
							
			// Reset each player's hand
			for (Player p : players) {
				p.resetHand();
			}	
			// Create a hand for each player
			for (Player p : players) {
				Hand h = new Hand();
				for (int i = 0; i < 5; i++) {
					Card c = d.drawFromDeck();
					h.AddCardToHand(c);
				}
				p.SetHand(h);
				}
			// Player has the hand, call the playerUpdated method to set the
			// screen
			for (Player p : players) {
				p.getClient().playerUpdated(p);
				p.getClient().playerActed(p);
			}			
		}
		
		case SevenDraw: {
			// Set a new deck d
			Deck d = new Deck(4);
							
			// Reset each player's hand
			for (Player p : players) {
				p.resetHand();
			}	
			// Create a hand for each player
			for (Player p : players) {
				Hand h = new Hand();
				for (int i = 0; i < 8; i++) {
					Card c = d.drawFromDeck();
					h.AddCardToHand(c);
				}
				p.SetHand(h);
				}
			// Player has the hand, call the playerUpdated method to set the
			// screen
			for (Player p : players) {
				p.getClient().playerUpdated(p);
				p.getClient().playerActed(p);
			}	
		}
		
		case TexasHoldEm:{
			// Set a new deck d
			Deck d = new Deck(4);
							
			// Reset each player's hand
			for (Player p : players) {
				p.resetHand();
			}	
			// Create a hand for each player
			for (Player p : players) {
				Hand h = new Hand();
				for (int i = 0; i < 2; i++) {
					Card c = d.drawFromDeck();
					h.AddCardToHand(c);
				}
				h.HandleJokerWilds();
				p.SetHand(h);
				}
			// Player has the hand, call the playerUpdated method to set the
			// screen
			for (Player p : players) {
				p.getClient().playerActed(p);
			}		
		}
		
		case Omaha:{
			// Set a new deck d
			Deck d = new Deck(4);
							
			// Reset each player's hand
			for (Player p : players) {
				p.resetHand();
			}	
			// Create a hand for each player
			for (Player p : players) {
				Hand h = new Hand();
				for (int i = 0; i < 5; i++) {
					Card c = d.drawFromDeck();
					h.AddCardToHand(c);
				}
				p.SetHand(h);
				}
			// Player has the hand, call the playerUpdated method to set the
			// screen
			for (Player p : players) {
				p.getClient().playerUpdated(p);
				p.getClient().playerActed(p);
			}}}			
		}		

	}		
