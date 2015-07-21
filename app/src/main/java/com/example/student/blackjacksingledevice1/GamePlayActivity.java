package com.example.student.blackjacksingledevice1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Scanner;


public class GamePlayActivity extends ActionBarActivity {

    ArrayList<String> namesAL = new ArrayList<>();
    LinearLayout myLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        myLL = (LinearLayout) findViewById(R.id.myLinearLayout);
        namesAL = getIntent().getStringArrayListExtra("namesArray");
        startGame(namesAL);
    }

    public void startGame(ArrayList<String> playersNames) {
        boolean[] deck = new boolean[52];
        int amountOfPlayers = playersNames.size();

        playersNames.add("Dealer");

        int[][] players = new int[amountOfPlayers + 1][12];

        //populating the players array with the value -1
        for (int i = 0; i < players.length; i++)
            populateDeck(players[i]);

        //dealing cards to all the players
        for (int i = 0; i < players.length; i++)
            dealCards(deck, players[i]);

        for (int i = 0; i < players.length - 1; i++){
            TextView tv = new TextView(this);
            tv.setText("\nIt is " + playersNames.get(i) + "'s turn.");
            myLL.addView(tv);
            playersTurn(i, players, deck, playersNames);
        }

        dealersTurn(players, deck, playersNames);

    }
    public void playersTurn(int i, int[][] players,boolean[] deck, ArrayList<String> playersName) {
        //printCards(players, i, playersName);
        //need to put this if statement in the startGame method
        if(isBlackJack(players[i])){
            TextView textView = new TextView(this);
            textView.setText(playersName.get(i) + ", you have BlackJack!! You Win!");
            myLL.addView(textView);
            return;
        }
        promptUser(i, players, deck, playersName);

    }
    public static void promptUser(int currentPlayer, int[][] players, boolean[] deck, ArrayList<String> playersNames) {
        //Scanner input = new Scanner(System.in);
        boolean isHit = true;
        do{
            int sumOfCards = countCards(players[currentPlayer]);
            if(sumOfCards >  21){
                //Toast.makeText(GamePlayActivity.this, playersNames[currentPlayer]  + ", you Busted. Your turn is over; You lost ", Toast.LENGTH_LONG).show();
                break;
            }
           // System.out.print(playersNames.get(currentPlayer) +", your cards count up to "
                    //+ sumOfCards +" Would you like to Hit or Stick?";
            if(isHit)
            {
                hit(deck, players[currentPlayer]);
                //Toast("You were dealt a " + getCardFaceValueText(players, currentPlayer, getPlayersFirstEmptyCardIndex(players[currentPlayer])-1));  //need to add here if its a 10,11,12 that its king queen or jack
            }
            isHit = false;
        } while(isHit);
        // input.close();

    }
    public static int hit(boolean[] deck, int[] whosTurn) {
        int x = (int)(Math.random() * 52);
        while(deck[x] == true) {
            x = (int)(Math.random() * 52);
        }
        deck[x] = true;
        whosTurn[getPlayersFirstEmptyCardIndex(whosTurn)] = x;
        return x;
    }
    private static int getPlayersFirstEmptyCardIndex(int[] whosTurn) {
        int i;
        for(i= 0; i < whosTurn.length; i++) {
            if (whosTurn[i] == -1) {
                break;
            }
        }
        return i;
    }
    public static int countCards(int[] cards) {
        int sum = 0;
        boolean aceFound=false;
        int c = 0;  //changed to 0
        for (int i = 0; i < cards.length && cards[i] != -1 ; i++ ) {//the loop doesn't need to go through the whole array
            c = cards[i] % 13; //divide to get card number, but it's 1 off (lower because of 0 index)
            if (c >= 0 && c < 9)
                sum += c + 1;
            if (c >= 9 && c <= 12)
                sum += 10;
            if (c == 0)
                aceFound=true;
        }
        if (aceFound)
            sum+= (isSumLessThanEleven(sum)?10:0);//After summing all cards, add ten if there are any aces with room to be high
        return sum;
    }
    private static boolean isSumLessThanEleven(int sum) {
        if (sum < 11)
            return true;
        else
            return false;
    }
    public static boolean isBlackJack(int[] playerHand){
        boolean oneTenValueCard=false;
        boolean oneAceCard=false;
        for(int i = 0; i < 2; i++) {
            if (playerHand[i]%13==0)
                oneAceCard=true;
            else if(playerHand[i]%13>=9)
                oneTenValueCard=true;
        }
        if(oneAceCard&&oneTenValueCard)
            return true;//blackjack can only happen where there are 2 cards and not more.
        return false;
    }
    public static void populateDeck(int[] playerDeck) {
        for( int i = 0;i < playerDeck.length; i++){
            playerDeck[i] = -1;
        }
    }
    public static void dealCards(boolean[] deck, int[] playerhand) {
        int count = 0;
        while(count < 2) {
            int deal = (int)(Math.random() * 52);
            while (deck[deal])
                deal = (int)(Math.random() * 52);
            deck[deal] = true;
            playerhand[count] = deal;
            ++count;
        }
    }
    public void dealersTurn(int [][] players, boolean [] deck, ArrayList<String> playersNames){
        int dSum = countCards(players[players.length - 1]);
        boolean isHit;

        String output = "\nIt is the dealer's turn. \nThe dealers cards are: ";
        for (int i = 0; i < 2; i++)
            output += "\n" + (getCardFaceValueText(players, players.length -1, i));

        TextView tv = new TextView(this);
        tv.setText(output); //added this to print dealers first 2 cards
        myLL.addView(tv);
        //!!check for blackjack here!
        do{
            isHit = false;
            if (dSum < 17){
                hit(deck, players[players.length - 1]);
                dSum = countCards(players[players.length - 1]);
               // System.out.println("To see what the dealer has dealt, press enter!");
                //Scanner input = new Scanner(System.in);
                //input.nextLine();
               // System.out.println("The dealer was dealt a "
                 //       + getCardFaceValueText(players, players.length - 1, getPlayersFirstEmptyCardIndex(players[players.length - 1])-1) + " your cards now"
                   //     + " count up to " + dSum);
                isHit = true;
            }else if(isThereOneAceHigh(players[players.length - 1], dSum) && dSum == 17){ // this is what the method is expecting... the isThereOneAce method is also expecting to be passed in something
                hit(deck, players[players.length - 1]);
                dSum = countCards(players[players.length - 1]);
                //System.out.println("To see what the dealer has dealt, press enter!");
                //Scanner input = new Scanner(System.in);
                //input.nextLine();
               // System.out.println("The dealer was dealt a "
                 //       + getCardFaceValueText(players, players.length - 1, getPlayersFirstEmptyCardIndex(players[players.length - 1])-1) + " your cards now"
                   //     + " count up to " + dSum);
                isHit = true;
            }else{   //stick
                //System.out.println("\nAll players cards will be displayed:");
               // printCards(players, players.length - 1, playersNames);
                if(dSum > 21){
                    //System.out.println("Dealer busted with a total of " + dSum );
                    for(int i = 0; i < players.length - 1; i++){
                        if(countCards(players[i]) <= 21) {//checks if the player is not busted (used to be in the isPlayerNotBusted method)
                            //System.out.println(playersNames.get(i) + ": won!");
                        } else{
                            //System.out.println(playersNames.get(i) + ": lost.");
                        }
                    }
                }else
                    for(int i = 0; i < players.length - 1; i++){
                        if(countCards(players[i]) <= 21){//checks if the player is not busted
                            if(countCards(players[i]) > dSum) {
                                // System.out.println(playersNames.get(i) + ": won!");
                            }else if(countCards(players[i]) == dSum) {
                                //System.out.println(playersNames.get(i) + " and the Dealer -- PUSH, DRAW");
                            }else {//player has less than dealerSum
                                //System.out.println(playersNames.get(i) + " lost.");
                            }
                        }else {
                           // System.out.println(playersNames.get(i) + " lost.");
                        }
                    }
            }
        }while(isHit);
    }
    public static boolean isThereOneAceHigh(int [] dealer, int dealersSum){
        int sum = 0;
        boolean aceFound = false;
        for (int i = 0; i < dealer.length && dealer[i] != -1 ; i++ ) {//the loop doesn't need to go through the whole array
            int c = dealer[i] % 13; //divide to get card number, but it's 1 off (lower because of 0 index)
            if (c >= 0 && c < 9)
                sum += c + 1;
            else if (c >= 9 && c <= 12)
                sum += 10;
            if (c == 0) {
                aceFound=true;
                if(isSumLessThanEleven(sum)) //this method used to be called aceIsHigh(int sum)
                    return true;
                else
                    return false;
            }
        }
        return aceFound;
    }
    private static String getCardFaceValueText(int[][] playersHands, int player, int card) {
        int cardNumber;
        int suit;
        String cardFaceValue="";
        cardNumber = (playersHands[player][card] % 13) + 1;
        switch (cardNumber) {
            case 1: cardFaceValue="Ace of "; break;
            case 11: cardFaceValue="Jack of "; break;
            case 12: cardFaceValue="Queen of "; break;
            case 13: cardFaceValue="King of "; break;
            default: cardFaceValue= cardNumber + " of "; break;
        }
        suit = playersHands[player][card] / 13;
        switch(suit) {
            case 0: cardFaceValue+="Spades"; break;
            case 1: cardFaceValue+="Diamonds"; break;
            case 2: cardFaceValue+="Clubs"; break;
            case 3: cardFaceValue+="Hearts"; break;
            default: cardFaceValue+="Unknown suit"; break;
        }
        return cardFaceValue;
    }
}