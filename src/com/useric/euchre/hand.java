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
class hand {
    // instance variables
    ArrayList<card> cardsInHand = new ArrayList<card>();
//    int cValue = 0;
//    int sValue = 0;
//    int dValue = 0;
//    int hValue = 0;

    // methods
    void order() {
        // 5 suits, one of which is trump (for next version)
        // compare by value (if trump, value cards like so, ...
        // ... if not trump, value cards like so, then sort)
        // each suit has own rank
        Collections.sort(cardsInHand);
    }
    card selectCard(int winner, int ally, card highestCard, String suitLed, boolean computer) {
        card selectedCard = new card("string", 10);  // make uninitialized
        if(computer == true) {
            if(winner == ally) {
                // play lowest card in that suit
                if(followSuit(suitLed) == true) {
                    card c = getLowestCard(suitLed);
                    //removeCard(c);
                    return c;
                } else {
                    card c = getLowestCard();
                    //removeCard(c);
                    return c;
                }
            } else {
                if(followSuit(suitLed) == true) {
                    // check to see if highest card beats card
                    if(highestCard.getValue() < getHighestCard(suitLed).getValue()) {
                        // play highest card
                        card c = getHighestCard(suitLed);
                        //removeCard(c);
                        return c;
                    } else {
                        // if you can't beat it, play the lowest card
                        card c = getLowestCard(suitLed);
                        //removeCard(c);
                        return c;
                    }
                } else {
                    if(cardsInHand.isEmpty()) {
                        int x = 1;
                    }
                    if(cardsInHand.get(0).getValue() > getHighestCard().getValue()) {
                        card c = getHighestCard();
                        //removeCard(c);
                        return c;
                    } else {
                        card c = getLowestCard();
                        //removeCard(c);
                        return c;
                    }
                }
            }
        } else {
            // return card clicked from gui
            return selectedCard; // GET RID OF THIS!!!
        }
        // remove card from hand
        // if computer then ___ else return card clicked from gui
        // take selected card imput and remove it from hand and on board (gui)
        // also detects if card played is following suit
        // (should it be in own method?)
    }

    card selectCard(boolean computer) {
        card selectedCard = new card("suit", 10);  // make uninitialized
        if(computer == true) {
                card c = getHighestNonTrumpCard();
                removeCard(c);
                return c;
            } else {
                // TODO: gui selection
                return selectedCard;
            }
        // remove card from hand
        // if computer then ___ else return card clicked from gui
        // take selected card imput and remove it from hand and on board (gui)
        // also detects if card played is following suit
        // (should it be in own method?)
    }
    // measures ability to follow suit (is person forced to follow suit)
    boolean followSuit(String suit) {
        ArrayList<String> suits = new ArrayList<String>();
        //make an arraylist full of suits
        for(int i=0; i<cardsInHand.size(); i++) {
            suits.add(cardsInHand.get(i).getSuit());
        }
        // if String suit is in the hand, then return true
        if(suits.contains(suit)) {
            return true;
        } else {
            return false;
        }
    }

    // for humans: make sure that the selected card is following suit
    boolean followSuit(String suit, card selectedCard) {
        if (selectedCard.getSuit().equals(suit)) {
            return true;
        } else {
            return false;
        }
    }

    void setTrump(String newTrump) {
//        for(int i=0; i<cardsInHand.size(); i++) {
//            cardsInHand.get(i).setTrump(newTrump);
//        }
        for(int i=0; i<cardsInHand.size(); i++) {
            cardsInHand.get(i).resetSuit();
            cardsInHand.get(i).setTrump(newTrump);
        }
    }

    card getHighestCard() {
        card Card = cardsInHand.get((cardsInHand.size()-1));  //NOTE:size minus one?
//        cardsInHand.remove((cardsInHand.size()-1));
        return Card;
    }

    card getHighestCard(String suit) {
        card Card = null; // should never be null as this is only called after followSuit() = true
        for(int i=(cardsInHand.size()-1); i>=0; i--) {
            if(cardsInHand.get(i).getSuit().equals(suit)) {
                Card = cardsInHand.get(i);
//                cardsInHand.remove(i);
                break;
            }
        }
        return Card;
    }

    card getLowestCard() {
        card Card = cardsInHand.get(0);
//        cardsInHand.remove(0);
        //TODO: resize the arrayList?  or does it do it automatically?
        // trimToSize()?
        return Card;
    }

    card getLowestCard(String suit) {
        card Card = null; // should never be null as this is only called after followSuit() = true
        for(int i = 0; i<cardsInHand.size(); i++) {
            if(cardsInHand.get(i).getSuit().equals(suit)) {
                Card = cardsInHand.get(i);
//                cardsInHand.remove(i);
                break;
            }
        }
        return Card;
    }

    card getHighestNonTrumpCard() {
        card Card = new card("suit", 10);
        // get highest non-trump card
        for(int i=cardsInHand.size()-1; i>=0; i--) {
            if(cardsInHand.get(i).isTrump() == false) {
                Card = cardsInHand.get(i);
//                cardsInHand.remove(i);
                break;
            }
        }
//        if(Card.equals(null)) {
        if(Card.getSuit().equals("suit")) {
            Card = cardsInHand.get(0);
//            cardsInHand.remove(0);
        }
        return Card;
    }

    int totalValue() {
        int x = 0;
        for(int index = 0; index < cardsInHand.size(); index++) {
            x = x + cardsInHand.get(index).value;
        }
        return x;
    }

//    int getValue(String suitLed) {
//        return getHighestCard(suitLed).getValue();
//    }

    void removeCard(card c) {
        cardsInHand.remove(c);
    }
//
//    void setHvalue() {
//        ArrayList<card> h = new ArrayList();
//        //make an arraylist full of suits
//        if(followSuit("hearts")) {
//            for(int i=0; i<cardsInHand.size(); i++) {
//                if(cardsInHand.get(i).getSuit().equals("hearts")) {
//                    h.add(cardsInHand.get(i));
//                }
//            }
//        }
//        for(int index = 0; index < h.size(); index++) {
//            hValue = hValue + h.get(index).value;
//        }
//    }
//
//    void setDvalue() {
//        ArrayList<card> d = new ArrayList();
//        //make an arraylist full of suits
//        if(followSuit("diamonds")) {
//            for(int i=0; i<cardsInHand.size(); i++) {
//                if(cardsInHand.get(i).getSuit().equals("diamonds")) {
//                    d.add(cardsInHand.get(i));
//                }
//            }
//        }
//        for(int index = 0; index < d.size(); index++) {
//            dValue = dValue + d.get(index).value;
//        }
//    }
//
//    void setCvalue() {
//        ArrayList<card> c = new ArrayList();
//        //make an arraylist full of suits
//        if(followSuit("clubs")) {
//            for(int i=0; i<cardsInHand.size(); i++) {
//                if(cardsInHand.get(i).getSuit().equals("clubs")) {
//                    c.add(cardsInHand.get(i));
//                }
//            }
//        }
//        for(int index = 0; index < c.size(); index++) {
//            cValue = cValue + c.get(index).value;
//        }
//    }
//
//    void setSvalue() {
//        ArrayList<card> s = new ArrayList();
//        //make an arraylist full of suits
//        if(followSuit("spades")) {
//            for(int i=0; i<cardsInHand.size(); i++) {
//                if(cardsInHand.get(i).getSuit().equals("spades")) {
//                    s.add(cardsInHand.get(i));
//                }
//            }
//        }
//        for(int index = 0; index < s.size(); index++) {
//            sValue = sValue + s.get(index).value;
//        }
//    }
//
//    int getSvalue() {
//        return sValue;
//    }
//
//    int getHvalue() {
//        return hValue;
//    }
//
//    int getDvalue() {
//        return dValue;
//    }
//
//    int getCvalue() {
//        return cValue;
//    }

    int getTrumpValue() {
        int value = 0;
        for(int i=0; i<cardsInHand.size(); i++) {
            if(cardsInHand.get(i).isTrump()) {
                value = value+cardsInHand.get(i).getValue();
            }
        }
        return value;
    }

    int bestHandValue(String suit) {
        int value = 0;
        setTrump(suit);
        for(int i=0; i<cardsInHand.size(); i++) {
            if(cardsInHand.get(i).isTrump()) {
                value = value+cardsInHand.get(i).getValue();
            }
        }
        setTrump("");
        return value;
    }

    String bestHand(String notSuit) {
        int hearts = 0;
        int clubs = 0;
        int diamonds = 0;
        int spades = 0;
        if(!"hearts".equals(notSuit)) {
            setTrump("hearts");
            hearts = getTrumpValue();
        }
        if(!"clubs".equals(notSuit)) {
            setTrump("clubs");
            clubs = getTrumpValue();
        }
        if(!"diamonds".equals(notSuit)) {
            setTrump("diamonds");
            diamonds = getTrumpValue();
        }
        if(!"spades".equals(notSuit)) {
            setTrump("spades");
            spades = getTrumpValue();
        }
        setTrump("");
        if(hearts >= clubs) {
            if(hearts >= diamonds) {
                if(hearts >= spades) {
                    return "hearts";
                } else {
                    return "spades";
                }
            } else {
                if(diamonds >= spades) {
                    return "diamonds";
                } else {
                    return "spades";
                }
            }
        } else {
            if(clubs >= diamonds) {
                    if(clubs >= spades) {
                        return "clubs";
                    } else {
                        return "spades";
                    }
                } else {
                    if(diamonds >= spades) {
                        return "diamonds";
                    } else {
                        return "spades";
                    }
                }
        }
    }
}
