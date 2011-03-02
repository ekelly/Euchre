/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.useric.euchre;

/**
 *
 * @author Eric
 */
class team {
    // instance variables
    int score;
    int tricksWon;
    boolean calledTrump;
    player[] Player = new player[2];  //just references

    // accessor methods
    void increaseScore(int value) {
        score = score+value;
        if (score > 9) {
            // set game over to be true? How can I access that?
            // notify everyone (through gui) that team __ won!
        }
    }
    void decreaseScore(int value) {
        score = score-value;
    }
}
