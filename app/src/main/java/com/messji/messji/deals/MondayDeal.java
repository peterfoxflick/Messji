package com.messji.messji.deals;

import com.messji.messji.Message;

/**
 * @author Henrique Tedeschi
 */
public class MondayDeal extends DailyDeal {

    /**
     * MondayDeal Constructor
     * Needs a {@Link Message}
     *
     * @param message
     */
    protected MondayDeal(Message message) {
        super(message);
        setTitle("Hate Mail Monday");

        setDescription("Everyone hates Mondays, so we gave you an excuse not to text anyone. Every message counts as double today!");

        // This is the angry emoji 😠
        setImageEmoji("\uD83D\uDE20");
    }

    /**
     * @return Returns the number of chars in the message doubled (*2).
     */
    @Override
    protected int getLength() {
        return super.getLength() * 2;
    }

}
