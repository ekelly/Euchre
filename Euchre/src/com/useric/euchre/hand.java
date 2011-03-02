/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.useric.euchre;

import java.util.ArrayList;

/**
 *
 * @author Eric
 */
class hand {
    // instance variables
    ArrayList<card> cardsInHand = new ArrayList();

    // methods
    void order() {
        // 5 suits, one of which is trump
        // implement Comparable, overwrite compareTo(card c)
        // compare by value (if trump, value cards like so, ...
        // ... if not trump, value cards like so, then sort)
        // each suit has own rank
    }
    card selectCard() {
        card selectedCard;
        // remove card from hand
        // if computer then ___ else return card clicked from gui
        // take selected card imput and remove it from hand and on board (gui)
        // also detects if card played is following suit
        // (should it be in own method?)
        return selectedCard;
    }
    // measures ability to follow suit (is person forced to follow suit)
    boolean followSuit(String suit) {
        boolean bool = false;
        // if String suit is in the hand, then return true
        return bool;
    }

    int totalValue() {
        int x = 0;
        for(int index = 0; index < cardsInHand.size(); index++) {
            x = x + cardsInHand.get(index).value;
        }
        return x;
    }
}
