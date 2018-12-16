package com.messji.messji.deals;

import com.messji.messji.Database;
import com.messji.messji.Message;

public class WednesdayDeal extends DailyDeal {

    protected WednesdayDeal(Message message) {
        super(message);

        setTitle("Hump Day");

        setDescription("Getting a camel gets you a 10% bonus");

        // This is the camel emoji 🐪
        setImageEmoji("\uD83D\uDC2A");

        getLength();
    }

    @Override
    public void incomingMessage() {
        int camel = getCharCount(getImageEmoji());
        int twoHumpCamel = getCharCount("\uD83D\uDC2B");

        if ((twoHumpCamel + camel) > 0) {
            int bonus = Database.getCharCount() / 10;
            //Add a 10 percent of what their current count is
            subtractCharCount(bonus * (-1));
        }
    }
}
