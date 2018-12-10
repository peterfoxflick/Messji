package com.messji.messji.Deals;

import com.messji.messji.Database;

public class MondayDeal extends DailyDeal{

    public MondayDeal(String message) {
        super(message);
        setDescription("Hate Mail Monday");

        // This is the angry emoji 😠
        setImageEmoji("\uD83D\uDE20");
    }

    @Override
    public void outgoingMessage() {
        updateCharCount(getLength() * (-1));
    }

    @Override
    protected int getLength() {
        return super.getLength() * 2;
    }

    @Override
    public boolean canSend() {
        return Database.getCharCount() > getLength();
    }
}
