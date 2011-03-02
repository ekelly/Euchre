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
    // card flippedCard; unneeded ... I think

    // methods
    card deal(player players[]) {
        //call shuffle(), give five cards to every player, then flip top card
        shuffle();
        for (int i=0; i<4; i++) {
            for (int a=0; a<5; a++) {
                players[i].myHand.cardsInHand.add(Cards.get(0));
                Cards.remove(0);
            }
        }
        return Cards.get(0);// flip top card
    }
    void shuffle() {
        Collections.shuffle(Cards);
    }
    
    public deck() {
        // create deck of 24 cards
        String[] s = {"clubs", "diamonds", "spades", "hearts"};
        int a, b;
        for(a=0; a<4; a++){
            for(b=0; b<6; b++) {
            Cards.add(new card(s[a], b));
            }
        }
    }

    //TODO: MAKE THIS A CONSTRUCTOR
//    void initializeDeck() {
//        // create deck of 24 cards
//        String[] s = {"clubs", "diamonds", "spades", "hearts"};
//        int a, b;
//        for(a=0; a<4; a++){
//            for(b=0; b<6; b++) {
//            Cards.add(new card(s[a], b));
//            }
//        }
//    }
}
