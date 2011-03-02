/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.useric.euchre;

/**
 *
 * @author Eric
 */
class player {
    // instance variables
    boolean trickWinner;
    hand myHand;
    card cardPlayed;
    boolean computer;
    boolean partnerGoingAlone;
    int team;

    // methods
    void selectCard() {
        myHand.selectCard();
    }
    void pickupCard(card dealerPickup, int dealer) {
        // should CPU tell dealer to pick up card?
        // only if value of cards exceeds (trump = 72 + 10, nontrump)
        // differs based on who the dealer is, what card to pick up, what's in
        // your hand!!!
        if (myHand.totalValue() > 80) {
            // tell dealer to pick up
        }
    }
    void selectTrump() {
        // should the dealer choose trump?
        // or what should he choose for trump?
        // or is this a player thing?
        // where a popup or some visual question asks and a player chooses?
    }
    void orderCards() {
        myHand.order();
    }

    public player(int playerNumber) {
        if(playerNumber%2 == 0) {
            team = 1;
        } else {
            team = 2;
        }
    }
}
