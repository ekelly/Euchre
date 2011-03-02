/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.useric.euchre;

/**
 *
 * @author Eric
 */
class card implements Comparable {
    // instance variables
    private int rank; // (0-5) for (9-A [no jack of opposite])
    private String suit; // actual suit
    String originalSuit;
    // private String orderSuit; // 5 suits for ordering (regular + trump)
    static String trump;
    int value;
    boolean isTrump;

    // constructor - pass values to create deck
    card(String s, int initializedRank) {
        suit = s;
        originalSuit = s;
        rank = initializedRank;
        value = rank;
    }
    int getRank() {
        return rank;
    }
    public int compareTo(Object o) {
        // comparison here.
        card c = (card) o;
        if (c.getValue() > value) {
            return -1;
        }
        if (c.getValue() == value) {
            return 0;
        } else {
            return 1;
        }
    }

    //accessor method for orderSuit
////    void orderSuit(String newSuit) {
////        suit = newSuit;
////    }

    int getValue() {
        return value;
    }

    String getSuit() {
        return suit;
    }

    boolean isTrump() {
        return isTrump;
    }
    
    void setTrump(String newTrump) {
        trump = newTrump; // TODO: comment out later
        if(newTrump.equals("")) {
            isTrump = false;
        }
        if(suit.equals(newTrump)) {
            isTrump = true;
        }
        if(rank == 2) {
            if(trump.equals("clubs") && suit.equals("spades")) {
                isTrump = true;
            } else if(trump.equals("spades") && suit.equals("clubs")) {
                isTrump = true;
            } else if(trump.equals("hearts") && suit.equals("diamonds")) {
                isTrump = true;
            } else if(trump.equals("diamonds") && suit.equals("hearts")) {
                isTrump = true;
            }
            suit = trump;
        }
        resetValue();
        resetSuit();
        setTrumpValue();
    }

    void setTrickValue(String suitLed) {
        // if following suit (excepting trump)
        if(isTrump() == false) {
            if(suit.equals(suitLed)) {
                value = value+10;
            }
        }
    }
    void setTrumpValue() {
        // if a card is trump, increase its value by 20
        if(isTrump() == true) {
            value = rank+20;
            if(rank == 2) {
                // if a card is trump, but off suit (Jack) increase again by 4
                if(!trump.equals(suit)) {
                    value = value+4;
                } else {
                    // if a card is in suit but a jack, increase it again by 5
                    value = value+5;
                }
            }
        }
    }

    void resetValue() {
        if(isTrump() == false) {
            value = rank;
        }
    }

    void resetSuit() {
        if(isTrump() == false) {
            suit = originalSuit;
        }
    }
}
