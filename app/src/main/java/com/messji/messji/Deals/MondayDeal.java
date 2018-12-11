package com.messji.messji.Deals;

import com.messji.messji.Message;

public class MondayDeal extends DailyDeal {

    protected MondayDeal(Message message) {
        super(message);
        setTitle("Hate Mail Monday");

        setDescription("Everyone hates Mondays, so we gave you an excuse not to text anyone. Every message counts as double today!");

        // This is the angry emoji 😠
        setImageEmoji("\uD83D\uDE20");
    }

    @Override
    protected int getLength() {
        return super.getLength() * 2;
    }

}
