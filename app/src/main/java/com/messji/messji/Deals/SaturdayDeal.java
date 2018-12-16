package com.messji.messji.Deals;

import com.messji.messji.Message;

public class SaturdayDeal extends DailyDeal {

    protected SaturdayDeal(Message message) {
        super(message);

        setTitle("Stealing Saturday");

        setDescription("The crime is loose! Sending a special emoji will steal 10 characteres from the contact you send it to!");

        // This is the emoji 💰
        setImageEmoji("\uD83D\uDCB0");
    }

    @Override
    public void incomingMessage() {
        int bags = getCharCount(getImageEmoji());
        if (bags > 0)
        {
            //Future Note: cap the emoji limit
            subtractCharCount(10);
        }

    }

    @Override
    protected int getLength() {
        int bags = getCharCount(getImageEmoji());
        int points = 0;

        if (bags > 0){
            points = 10;
        }

        return getMessage().getText().length() - points;
    }
}
