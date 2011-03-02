/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.useric.euchre;

/**
 *
 * @author Eric
 */
class round {

    deck Deck = new deck();
    player[] player = new player[4];
    // This will be difficult - make it last
    trick trick;
    int tricksWon;
    team calledTrump;
    team teamNS;
    team teamEW;

    // constructor
    round (player playerArray[], team TeamNS, team TeamEW) {
        for (int i = 0; i<4; i++) {
            player[i] = playerArray[i];
        }
        teamNS = TeamNS;
        teamEW = TeamEW;
    }

    int incrementPlayer(int leader) {
        leader = leader+1;
        if(leader>3) {
            leader = leader-4;
        }
        return leader;
    }

    int playRound(int dealer) {
//        Deck.initializeDeck();
        int leader = incrementPlayer(dealer);
        int ally = incrementPlayer(incrementPlayer(leader));
        int goingAlonePartner = 5;
//        if(dealer<0) {
//            dealer = dealer+4;
//        }
        int temp = leader;
        int temp2 = ally;
        card flippedCard = Deck.deal(player);
        String selectedTrump = flippedCard.getSuit();
        int action = -5;  //NOTE: uninitialize this variable
        // deal and go around pick up top card or pass
        // also don't forget to order cards!!
        boolean trumpSelected = false;
        int c = 0;
        flippedCard.setTrump(selectedTrump);
        {  //Print the flipped card
        int i = flippedCard.getRank();
        String rank;
        if(i == 0) {
            rank = "Nine";
        } else if(i == 1) {
            rank = "Ten";
        } else if(i == 2) {
            rank = "Jack";
        } else if(i == 3) {
            rank = "Queen";
        } else if(i == 4) {
            rank = "King";
        } else {
            rank = "Ace";
        }
        System.out.println("Flipped card is ...");
        System.out.print(rank);
        System.out.print(", " + flippedCard.getSuit());
        System.out.println();
        }
        for(int a=0; a<4; a++) {
            player[a].myHand.setTrump(selectedTrump);
            // order the hand of the player
            player[a].myHand.order();
        }
        while(trumpSelected == false && c < 4) {
            // see if the player wants to select the flippedCard as trump
            // -1 is pass, 0 is pick up, 1 is go alone
            action = player[temp].pickupCard(flippedCard, dealer, temp2);
            // if a player does not pass, set trumpSelected to true;
            if(action > -1) {
                trumpSelected = true;
                calledTrump = player[temp].getTeam();
                player[temp].Team.setCalledTrump(true);
                System.out.println("Player " + temp + "called trump.");
                break;
            }
            c++;
            // go to the next person
            temp = incrementPlayer(temp);
            temp2 = incrementPlayer(temp2);
        }
        // already did this above
//        for(int i=0; i<4; i++) {
//            player[i].myHand.setTrump(selectedTrump);
//        }
        if(action > -1) {
            // make dealer pick up card and get rid of another
            player[dealer].discardCard(flippedCard);
            player[dealer].myHand.order();
        }
        if(action > 0) {
            // set partner to as going alone
            temp = incrementPlayer(incrementPlayer(temp));
            goingAlonePartner = temp;
            player[temp].setPartnerGoingAlone(true);
            temp = incrementPlayer(incrementPlayer(temp));
            System.out.println("Player " + temp + " is going alone.");
        }
        // If trump was not initially selected, get rid of trump and re-sort
        if(trumpSelected == false) {       
        // get rid of trump, re-sort
            for(int s=0; s<4; s++) {
                // order the hand of the player
                player[s].myHand.setTrump("");
                player[s].myHand.order();
            }
        }
        // second round, where suit is selected
        c = 0;
        while(trumpSelected == false && c < 3) {
            player[temp].myHand.order();
            // see if the person wants to select trump
            box box1 = player[temp].selectTrump(false, flippedCard.getSuit());
            selectedTrump = box1.getString();
            if(selectedTrump.equals("")) {
                trumpSelected = false;
                // go to the next person
                temp = incrementPlayer(temp);
            } else {
                trumpSelected = true;
                calledTrump = player[temp].getTeam();
                player[temp].Team.setCalledTrump(true);
                System.out.println("Player " + temp + " called trump.");
                System.out.println("Trump is " + selectedTrump);
                break;
            }
            if (box1.getGoingAlone()) {
                temp = incrementPlayer(incrementPlayer(temp));
                goingAlonePartner = temp;
                player[temp].setPartnerGoingAlone(true);
                temp = incrementPlayer(incrementPlayer(temp));
                System.out.println("Player " + temp + " is going alone.");
            }
            c++;
        }

        if(trumpSelected == false) {
            box box1 = player[temp].selectTrump(true, flippedCard.getSuit());
            selectedTrump = box1.getString();
            calledTrump = player[temp].getTeam();
            player[temp].Team.setCalledTrump(true);
            System.out.println("Player " + temp + "is stuck.");
            if(box1.getGoingAlone()) {
                temp = incrementPlayer(incrementPlayer(temp));
                goingAlonePartner = temp;
                player[temp].setPartnerGoingAlone(true);
                temp = incrementPlayer(incrementPlayer(temp));
                System.out.println("Player " + temp + " is going alone.");
            }
            System.out.println("Trump is " + selectedTrump);
        }
        for(int i=0; i<4; i++) {
            player[i].myHand.setTrump(selectedTrump);
        }
        for(int a=0; a<4; a++) {
            System.out.print("player ");
            System.out.print(a);
            System.out.print("'s cards:");
            System.out.println("");
            for(int b=0; b<5; b++) {
                int i = player[a].myHand.cardsInHand.get(b).getRank();
                String rank;
                if(i == 0) {
                    rank = "Nine";
                } else if(i == 1) {
                    rank = "Ten";
                } else if(i == 2) {
                    rank = "Jack";
                } else if(i == 3) {
                    rank = "Queen";
                } else if(i == 4) {
                    rank = "King";
                } else {
                    rank = "Ace";
                }
                System.out.print(rank);
                System.out.print(", " + player[a].myHand.cardsInHand.get(b).getSuit());
                System.out.println();
            }
            System.out.println();
        }
//        System.out.println("Player ");
//        System.out.print(" called trump.");
        int z = 5; // some number that does not correspond with a player
        // this number is returned as the leader of the next trick
        
        // need to play trick 5 times over
        trick = new trick(player);
        for(int a=0; a<5; a++) {
            if(goingAlonePartner < 5) {
                z = trick.playTrick(leader, goingAlonePartner);
                if (player[z].getTeam() == calledTrump) {
                    tricksWon++;
                }
                trick.resetTrick();
                // reset the value of the cards in each Player's hands
                for(int b=0; b<4; b++) {
                    for(int d=0; d<player[b].myHand.cardsInHand.size(); d++) {
                        player[b].myHand.cardsInHand.get(d).resetValue();
                    }
                }
            } else {
                z = trick.playTrick(leader);
                if (player[z].getTeam() == calledTrump) {
                    tricksWon++;
                }
                trick.resetTrick();
                // reset the value of the cards in each Player's hands
                for(int b=0; b<4; b++) {
                    for(int d=0; d<player[b].myHand.cardsInHand.size(); d++) {
                        player[b].myHand.cardsInHand.get(d).resetValue();
                    }
                }
            }
        }
        goingAlonePartner = 5;
        System.out.println("Result:");
        // Scoring
        if(tricksWon > 2) {
            // the team that won gets a point
            calledTrump.increaseScore(1);
            System.out.println("Won at least 3 tricks.");
        }
        if(tricksWon == 5) {
            // the team that won gets an additional point
            // if one team member is going alone, add an additional 2 points
            calledTrump.increaseScore(1);
            System.out.println("Won 5 tricks.");
            if(calledTrump == teamNS) {
                if(player[0].partnerGoingAlone() || player[2].partnerGoingAlone()) {
                    calledTrump.increaseScore(2);
                    System.out.println("Won 5 tricks going alone.");
                }
            } else {
                if(player[1].partnerGoingAlone() || player[3].partnerGoingAlone()) {
                    calledTrump.increaseScore(2);
                    System.out.println("Won 5 tricks going alone.");
                }
            }
//            if(calledTrump.Player[0].partnerGoingAlone() == true || calledTrump.Player[1].partnerGoingAlone() == true) {
//                calledTrump.increaseScore(2);
//            }
        }
        if(tricksWon < 3) {
            // the calling team loses two points
            // calledTrump.decreaseScore(2);
            if(calledTrump == teamNS) {
                if(player[0].partnerGoingAlone() || player[2].partnerGoingAlone()) {
                    player[0].Team.increaseScore(2);
                    System.out.println("Euchred.");
                }
            } else {
                if(player[1].partnerGoingAlone() || player[3].partnerGoingAlone()) {
                    player[1].Team.increaseScore(2);
                    System.out.println("Euchred.");
                }
            }
        }
        calledTrump.setCalledTrump(false);
        for(int b=0; b<4; b++) {
            player[b].myHand.cardsInHand.clear();
        }
        return incrementPlayer(dealer);
    }
}
