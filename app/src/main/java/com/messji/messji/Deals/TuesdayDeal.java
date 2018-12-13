package com.messji.messji.Deals;

import com.messji.messji.Message;
import com.vdurmont.emoji.EmojiParser;

public class TuesdayDeal extends DailyDeal {
    protected TuesdayDeal(Message message) {
        super(message);

        setTitle("Taco Tuesday");

        setDescription("Mande ou receba um taco para ganhar 5 caracteres! (Get/Send a taco and get 5 more characters!)");

        // This is the taco emoji 🌮
        setImageEmoji("\uD83C\uDF2E");
    }

    @Override
    public void incomingMessage() {
        int tacos = getCharCount(getImageEmoji());
        int points = tacos * 5;

        //Future Note: cap taco limit.... everyone gets a taco
        subtractCharCount(points * (-1));
    }

    @Override
    protected int getLength() {
        int length =  EmojiParser.removeAllEmojis(getMessage().getText()).length();
        int tacos = getCharCount(getImageEmoji());
        int points = tacos * 5;
        return length - points;
    }
}
