import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BettelmannTest {
    static HashMap<String, Integer> cardMap = new HashMap<String, Integer>();
    static{
        for(int i = 0; i < Card.nCards; i++){
            Card c = new Card(i);
            cardMap.put(c.toString(), i);
        }
    }
    private Card string2Card(String input){
        return new Card(cardMap.get(input));
    }

    private Card[] string2cards(String input){
        input = input.substring(1, input.length()-1);

        if(input.equals("")){
            return new Card[0];
        }

        String[] splited = input.split(", ");

        Card[] cards = new Card[splited.length];

        for(int i = 0; i < splited.length; i++){
            cards[i] = string2Card(splited[i]);
        }

        return cards;
    }

    private void addCardsToPile(Deque<Card> pile, Card[] cards){
        for(int i =  cards.length - 1; i >= 0; i--){
            pile.push(cards[i]);
        }
    }

    private Bettelmann initGame(String player1, String player2){
        Card[] p1 = string2cards(player1);
        Card[] p2 = string2cards(player2);

        Bettelmann game = new Bettelmann();
        addCardsToPile(game.getClosedPile1(), p1);
        addCardsToPile(game.getClosedPile2(), p2);

        return game;
    }

    private void checkResult(Bettelmann game, String p1, String p2, int winner){
        String  ap1 = game.getClosedPile1() + "";
        String  ap2 = game.getClosedPile2() + "";


        assertEquals(p1, ap1);
        assertEquals(p2, ap2);
    }

    @Test
    void game1(){
        Bettelmann game = initGame(
                "[♥D, ♠10, ♠K, ♣A, ♥K, ♦K, ♥7, ♦D, ♦A, ♦9, ♠B, ♠9, ♦8, ♣10, ♣8, ♥10]",
                "[♥9, ♦B, ♣K, ♦7, ♦10, ♠D, ♠8, ♣B, ♣D, ♥A, ♥8, ♣7, ♠7, ♠A, ♥B, ♣9]");


        game.playRound();

        checkResult(game,
                "[♠10, ♠K, ♣A, ♥K, ♦K, ♥7, ♦D, ♦A, ♦9, ♠B, ♠9, ♦8, ♣10, ♣8, ♥10, ♥D, ♥9]",
                "[♦B, ♣K, ♦7, ♦10, ♠D, ♠8, ♣B, ♣D, ♥A, ♥8, ♣7, ♠7, ♠A, ♥B, ♣9]",
                -1);
    }

    @Test
    void game2(){
        Bettelmann game = initGame(
                "[♥D, ♠10, ♠K, ♣A, ♥K, ♦K, ♥7, ♦D, ♦A, ♦9, ♠B, ♠9, ♦8, ♣10, ♣8, ♥10]",
                "[♥9, ♦B, ♣K, ♦7, ♦10, ♠D, ♠8, ♣B, ♣D, ♥A, ♥8, ♣7, ♠7, ♠A, ♥B, ♣9]");

        game.playRound();

        checkResult(game,
                "[♠10, ♠K, ♣A, ♥K, ♦K, ♥7, ♦D, ♦A, ♦9, ♠B, ♠9, ♦8, ♣10, ♣8, ♥10, ♥D, ♥9]",
                "[♦B, ♣K, ♦7, ♦10, ♠D, ♠8, ♣B, ♣D, ♥A, ♥8, ♣7, ♠7, ♠A, ♥B, ♣9]", -1);
    }

    @Test
    void game3() {
        Bettelmann game = initGame("[♦7, ♣K]", "[♦A, ♥7]");

        game.playRound();

        checkResult(game,"[♣K]","[♥7, ♦A, ♦7]", -1);
    }

    @Test
    void game4() {
        Bettelmann game = initGame(
                "[♠D, ♠B, ♦B, ♠10, ♦10, ♣9, ♦9, ♥K, ♠8, ♣K, ♦8, ♦D, ♦A, ♠7, ♣A, ♦7]",
                "[♣D, ♣B, ♥B, ♣10, ♥10, ♥D, ♥9, ♦K, ♣8, ♠K, ♥8, ♠9, ♥A, ♣7, ♠A, ♥7]");

        game.playRound();

        checkResult(game,
                "[♦9, ♥K, ♠8, ♣K, ♦8, ♦D, ♦A, ♠7, ♣A, ♦7]",
                "[♥9, ♦K, ♣8, ♠K, ♥8, ♠9, ♥A, ♣7, ♠A, ♥7, ♣D, ♣B, ♥B, ♣10, ♥10, ♥D, ♠D, ♠B, ♦B, ♠10, ♦10, ♣9]", -1);
    }

    @Test
    void game5(){
        Bettelmann game = initGame(
                "[♣K]",
                "[♥7, ♦A, ♦7]");

        game.playRound();

        checkResult(game,
                "[♣K, ♥7]",
                "[♦A, ♦7]", 2);
    }

    @Test
    void game6(){
        Bettelmann game = initGame(
                "[♦8]",
                "[♥8, ♠8, ♣7, ♣8, ♦7, ♣A, ♣K, ♦A, ♣9, ♠D, ♠10, ♠K, ♦D, ♥10, ♠A, ♦10, ♥K, ♣D, ♥B, ♠B, ♣10, ♦K, ♠9, ♥D, ♣B, ♦B, ♦9, ♥7, ♥A, ♠7, ♥9]");

        game.playRound();

        checkResult(game,
                "[]",
                "[♠8, ♣7, ♣8, ♦7, ♣A, ♣K, ♦A, ♣9, ♠D, ♠10, ♠K, ♦D, ♥10, ♠A, ♦10, ♥K, ♣D, ♥B, ♠B, ♣10, ♦K, ♠9, ♥D, ♣B, ♦B, ♦9, ♥7, ♥A, ♠7, ♥9]", -1);
    }

    @Test
    void testWinP1(){
        Bettelmann game = initGame(
                "[♥9, ♣10, ♠7, ♦K, ♦7, ♥A, ♣7, ♦D, ♦B, ♦A, ♥K, ♥B, ♦9, ♠B, ♠10, ♥10, ♣8, ♦10, ♥7, ♠K, ♣D, ♥D, ♣9, ♠D, ♠9, ♠8, ♠A, ♦8, ♣K, ♣A, ♣B]",
                "[♥8]");
        game.playRound();
        assertEquals(1, game.getWinner());
    }

    @Test
    void testDraw(){
        Bettelmann game = initGame(
                "[♥7, ♥B, ♣7, ♣B, ♥8, ♥D, ♣8, ♣D, ♥9, ♥K, ♣9, ♣K, ♥10, ♥A, ♣10, ♣A]",
                "[♦7, ♦B, ♠7, ♠B, ♦8, ♦D, ♠8, ♠D, ♦9, ♦K, ♠9, ♠K, ♦10, ♦A, ♠10, ♠A]");
        game.playRound();
        assertEquals(0, game.getWinner());
    }

    @Test
    void testWinP2(){
        Bettelmann game = initGame("[♣7]",
                "[♣B, ♣D, ♥9, ♠B, ♠8, ♦10, ♦7, ♣A, ♦K, ♠9, ♣8, ♠D, ♦B, ♣9, ♦8, ♥A, ♦D, ♠10, ♦9, ♥D, ♥10, ♠A, ♥8, ♠7, ♣K, ♥B, ♥7, ♠K, ♣10, ♦A, ♥K]");
        game.playRound();

        // Check, that cards get placed properly even if the round ended the game
        checkResult(game,
                "[]",
                "[♣D, ♥9, ♠B, ♠8, ♦10, ♦7, ♣A, ♦K, ♠9, ♣8, ♠D, ♦B, ♣9, ♦8, ♥A, ♦D, ♠10, ♦9, ♥D, ♥10, ♠A, ♥8, ♠7, ♣K, ♥B, ♥7, ♠K, ♣10, ♦A, ♥K, ♣B, ♣7]",
                2);
    }
}