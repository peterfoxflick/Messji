package com.messji.messji.deals;

import com.messji.messji.Message;
import com.vdurmont.emoji.EmojiParser;

/**
 * @author Henrique Tedeschi
 */
public class SaturdayDeal extends DailyDeal {


    /**
     * SaturdayDeal Constructor
     *
     * @param message
     */
    protected SaturdayDeal(Message message) {
        super(message);

        setTitle("Stealing Saturday");

        setDescription("The crime is loose! Sending a special emoji will steal 10 characteres from the contact you send it to!");

        // This is the emoji 💰
        setImageEmoji("\uD83D\uDCB0");
    }

    /**
     * Will check if the incoming message has a bag of money (💰) emoji. If so, will subtract 10 chars from the user who received the message
     */
    @Override
    public void incomingMessage() {
        int bags = getCharCount(getImageEmoji());
        if (bags > 0) {
            //Future Note: cap the emoji limit
            subtractCharCount(10);
        }

    }


    /**
     * @return Returns the size of the message (disregarding the emojis) subtracted by 10
     */
    @Override
    protected int getLength() {
        int length = EmojiParser.removeAllEmojis(getMessage().getText()).length();
        int bags = getCharCount(getImageEmoji());
        int points = 0;

        if (bags > 0) {
            points = 10;
        }

        return length - points;
    }
}
