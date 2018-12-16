package com.messji.messji.deals;

import com.messji.messji.Message;
import com.vdurmont.emoji.EmojiParser;

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
        int length = EmojiParser.removeAllEmojis(getMessage().getText()).length();
        int emojis = getCharCount(":"); // temporary fix, needs to regex filter for the whole ":)" emoji
        //int partyEmoji = getCharCount("*<(:D)");
        int points = emojis * 10;
        //points += ((partyEmoji > 0) ? 25 : 0);
        return length - points;
    }
}
