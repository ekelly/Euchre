/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.useric.euchre;

/**
 *
 * @author Eric
 */
class card {
    // instance variables
    private int rank; // (0-5) for (9-J [no jack of opposite])
    // COME BACK TO THIS!!!
    private String suit; // actual suit
    private String orderSuit; // 5 suits for ordering (regular + trump)
    String trump;
    int value;

    // constructor - pass values to create deck
    card(String s, int initializedRank) {
        suit = s;
        orderSuit = suit;
        rank = initializedRank;
    }

    //accessor method for orderSuit
    void orderSuit(String newSuit) {
        suit = newSuit;
    }

    void setTrump(String newTrump) {
        trump = newTrump;
    }

    void setValue(int number) {

        // This is probably wrong.  I'm just trying to make sure that the value
        // is only increased while the card is trump.  Remember, trump shifts
        // multiple times.  The value has to be able to return to normal. I
        // suppose I can just use a negative number.  Also, be sure to make the
        // jack of opposing suit go up by 20, and the suited jack go up by 21!
        while(!trump.equals(suit)) {
            value = value+number;
        }
    }

}
