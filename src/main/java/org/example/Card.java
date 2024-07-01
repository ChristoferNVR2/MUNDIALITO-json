package org.example;

public class Card extends Event {
    private String cardType;  // 'yellow' or 'red'

    public Card(Match match, int minute, Player2 player, String cardType) {
        super(match, minute, player);
        this.cardType = cardType;
    }

    @Override
    public String getInfo() {
        return cardType.substring(0, 1).toUpperCase() + cardType.substring(1) + " card for " + getPlayer().getName() + " at " + getMinute() + " minute in match " + getMatch().getTeam1().getName() + " vs " + getMatch().getTeam2().getName();
    }

    public String getCardType() {
        return cardType;
    }
}
