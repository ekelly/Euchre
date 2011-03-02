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
    boolean partnerGoingAlone = false;
    team Team;

    // methods
    card selectCard(int winner, int ally, card highestCard, String suitLed) {
        return myHand.selectCard(winner, ally, highestCard, suitLed, computer);
    }
    card selectCard() {
        return myHand.selectCard(computer);
    }
    void setPartnerGoingAlone(boolean b) {
        partnerGoingAlone = b;
    }
    boolean partnerGoingAlone() {
        return partnerGoingAlone;
    }
    team getTeam() {
        return Team;
    }

    int incrementPlayer(int leader) {
        leader = leader+1;
        if(leader>3) {
            leader = leader-4;
        }
        return leader;
    }

    int pickupCard(card dealerPickup, int dealer, int ally) {
        //TODO: pickupCard()
        // return 0 if picking up card, 1 if going alone, -1 if pass

        // should CPU tell dealer to pick up card?
        // only if value of cards exceeds (trump = 72 ~~ 3 highest)
        // differs based on who the dealer is, what card to pick up, what's in
        // your hand!!!
        int value = myHand.getTrumpValue();
        if (value >= 78) {
            return 1;
        } else {
            if(dealer == ally || dealer == incrementPlayer(incrementPlayer(ally))) {
                if(value >= 48) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                if(dealerPickup.getValue() <= 24) {
                    if(value >= 76) {
                        return 0;
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            }
        } // change!!!!
    }
    box selectTrump(boolean dealerStuck, String notSuit) {
        // if dealer is stuck then force the selection of trump
        // in the prompt, disallow notSuit

        // should the dealer choose trump?
        // or what should he choose for trump?
        // or is this a player thing?
        // where a popup or some visual question asks and a player chooses?
        //TODO: Can't go alone under this setup
        box box1 = new box();
        if(computer) {
            String bestSuit = myHand.bestHand(notSuit);
            box1.setString(bestSuit);
            int bestValue = myHand.bestHandValue(bestSuit);
            if(dealerStuck == true) {

                return box1;
            } else {
                if(bestValue >= 78) {
                    // somehow go alone???
                    box1.setGoingAlone(true);
                    return box1;
                } else if(bestValue >= 53) { // the top two cards
                    return box1;
                } else {
                    box1.setString(""); // pass
                    return box1;
                }
            }
        } else {
            // TODO: get from GUI
            box1.setString("");
            return box1;
        }
    }
    void discardCard(card pickedUpCard) {
        myHand.cardsInHand.remove(0);
        myHand.cardsInHand.add(pickedUpCard);
        myHand.order();
    }
    void orderCards() {
        myHand.order();
    }

    public player(boolean bool, team t) {
        computer = bool;
        Team = t;
        myHand = new hand();
    }
}
