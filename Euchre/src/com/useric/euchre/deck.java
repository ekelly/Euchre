/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.useric.euchre;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Eric
 */
class deck {
    // instance variables
    ArrayList<card> Cards = new ArrayList<card>(24);

    // methods
    void deal(player playerArray[]) {
        //call shuffle(), give five cards to every player, then flip top card
        shuffle();
        // for each player
        for (int i=0; i<4; i++) {
            // Deal five cards
            for (int j=0; j<5; j++) {
                playerArray[i].myHand.cardsInHand.add(Cards.get(0));
                Cards.remove(0);
            }
        }
    }
    void shuffle() {
        Collections.shuffle(Cards);
    }
    void initializeDeck() {
        // create deck of 24 cards
        String[] s = {"clubs", "diamonds", "spades", "hearts"};
        int a, b;
        for(a=0; a>3; a++){
            for(b=0; b>6; b++) {
            Cards.add(new card(s[a], b));
            }
        }
    }
}
