package com.messji.messji.Deals;

import com.messji.messji.Message;
import com.vdurmont.emoji.EmojiParser;

public class SundayDeal extends DailyDeal {

    protected SundayDeal(Message message) {
        super(message);

        setTitle("Superbowl Sunday");

        setDescription("Sending and receiving a football emoji results in 10 additional points");

        // This is the emoji 🏈
        setImageEmoji("\uD83C\uDFC8");
    }

    @Override
    public void incomingMessage() {
        int balls = getCharCount(getImageEmoji());
        int points = balls * 10;

        //Future Note: cap the emoji limit
        subtractCharCount(points * (-1));
    }

    @Override
    protected int getLength() {
        int length =  EmojiParser.removeAllEmojis(getMessage().getText()).length();
        int balls = getCharCount(getImageEmoji());
        int points = balls * 10;
        return length - points;
    }
}
