package org.example;


public class PokerHands {
    public Hand player1, player2;
    private int numOfCards = 5;

    public PokerHands(String content) {
        String black = content.substring(content.indexOf(":") + 1, content.indexOf("White:"));
        String white = content.substring(content.lastIndexOf(":") + 1);
        String[] cardsOfBlack = black.trim().split(" ");
        String[] cardsOfWhite = white.trim().split(" ");
        player1 = new Hand(cardsOfBlack);
        player2 = new Hand(cardsOfWhite);
    }

    public void judgeHands() {
        getCombination(player1);
        getCombination(player2);
    }

    private void getCombination(Hand player) {
        if (hasStraightFlush(player)) {
            player.setCombination(8);
            int temp = player.getValue(4) == 14 && player.getValue(3) == 5 ? 5 : player.getValue(4);
            player.setHighCard(temp);
        } else if (hasFourOfAKind(player)) {
            player.setCombination(7);
            player.setHighCard(player.getValue(1));
        } else if (hasFullHouse(player)) {
            player.setCombination(6);
            player.setHighCard(player.getValue(2));
        } else if (hasFlush(player)) {
            player.setCombination(5);
            player.setHighCard(4);
        } else if (hasStraight(player)) {
            player.setCombination(4);
            int temp = player.getValue(4) == 14 && player.getValue(3) == 5 ? 5 : player.getValue(4);
            player.setHighCard(temp);
        } else if (hasThreeOfKind(player)) {
            player.setCombination(3);
            player.setHighCard(player.getValue(2));
        } else if (hasTwoPair(player)) {
            player.setCombination(2);
            player.setHighCard(player.getValue(3));
        } else if (hasPair(player)) {
            player.setCombination(1);
            if (isPair(player.getCard(0), player.getCard(1)) || isPair(player.getCard(2), player.getCard(1)))
                player.setHighCard(player.getValue(1));
            else
                player.setHighCard(player.getValue(3));
        } else
            player.setHighCard(player.getValue(4));
    }

    private boolean hasStraightFlush(Hand player) {
        return hasFlush(player) && hasStraight(player);
    }

    private boolean hasFourOfAKind(Hand player) {
        for (int i = 0; i < numOfCards - 3; i++) {
            if (isThreeOfAKind(player.getCard(i), player.getCard(i + 1), player.getCard(i + 2))
                    && isPair(player.getCard(i + 2), player.getCard(i + 3)))
                return true;
        }
        return false;
    }

    private boolean hasFullHouse(Hand player) {
        return (isPair(player.getCard(0), player.getCard(1)) &&
                isThreeOfAKind(player.getCard(2), player.getCard(3), player.getCard(4))) ||
                (isPair(player.getCard(3), player.getCard(4)) &&
                        isThreeOfAKind(player.getCard(0), player.getCard(1), player.getCard(2)));
    }

    private boolean hasFlush(Hand player) {
        for (int i = 0; i < numOfCards - 1; i++) {
            if (player.getSuit(i) != player.getSuit(i + 1))
                return false;
        }
        return true;
    }

    private boolean hasStraight(Hand player) {
        for (int i = numOfCards - 1; i > 0; i--) {
            int one = player.getValue(i);
            int two = player.getValue(i - 1);
            if (one == 14 && two == 5)
                continue;
            if (one - two != 1)
                return false;
        }
        return true;
    }

    private boolean hasThreeOfKind(Hand player) {
        for (int i = 0; i < numOfCards - 2; i++) {
            if (isThreeOfAKind(player.getCard(i), player.getCard(i + 1), player.getCard(i + 2)))
                return true;
        }
        return false;
    }

    private boolean isThreeOfAKind(Card card1, Card card2, Card card3) {
        return card1.getValue() == card2.getValue() && card2.getValue() == card3.getValue();
    }

    private boolean hasTwoPair(Hand player) {
        for (int i = 0; i < numOfCards - 3; i++) {
            if (isPair(player.getCard(i), player.getCard(i + 1))) {
                for (int j = i + 2; j < numOfCards - 1; j++) {
                    if (isPair(player.getCard(j), player.getCard(j + 1)))
                        return true;
                }
            }
        }
        return false;
    }

    private boolean hasPair(Hand player) {
        for (int i = 0; i < numOfCards - 1; i++) {
            if (isPair(player.getCard(i), player.getCard(i + 1)))
                return true;
        }
        return false;
    }

    private boolean isPair(Card card1, Card card2) {
        return card1.getValue() == card2.getValue();
    }

    public String getWinner() {
        if (player1.getCombination() == player2.getCombination()) {
            if (player1.getHighCard() == player2.getHighCard())
                return resolveTie();
            else
                return player1.getHighCard() > player2.getHighCard() ? "Black wins. - with high card: " + player1.getHighCard() : "White wins. - with high card: " + player2.getHighCard();
        } else
            return player1.getCombination() > player2.getCombination() ? "Black wins. - with high card: " + player1.getCombination() : "White wins. - with high card: " + player2.getCombination();
    }

    private String resolveTie() {
        for (int i = numOfCards - 1; i >= 0; i--) {
            if (player1.getValue(i) == player2.getValue(i))
                continue;
            return player1.getValue(i) > player2.getValue(i) ? "Black wins. - with high card: " + player1.getValue(i) : "White wins. - with high card: " + player2.getValue(i);
        }
        return "Tie.";
    }

}


