package com.messji.messji.Deals;

import com.messji.messji.Message;

public class ThursdayDeal extends DailyDeal {

    protected ThursdayDeal(Message message) {
        super(message);

        setTitle("Throwback Thursday");

        setDescription("Sending an old-school emoji (such as ':)') gives you 10 additional characters");

        // This is the emoji :)
        setImageEmoji(":)");
    }

    @Override
    protected int getLength() {
        int emojis = getCharCount(":)");
        emojis += getCharCount(":(");
        emojis += getCharCount("XD");
        emojis += getCharCount(":P");
        int partyEmoji = getCharCount("*<(:D)");
        int points = emojis * 10;
        points += ((partyEmoji > 0) ? 25 : 0);
        return getMessage().getText().length() - points;
    }
}
