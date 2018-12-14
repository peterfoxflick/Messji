package com.messji.messji.Deals;

import android.util.Log;

import com.messji.messji.Database;
import com.messji.messji.Message;
import com.vdurmont.emoji.EmojiParser;

import java.util.Calendar;

/**
 * @author Henrique Tedeschi
 */
public class DailyDeal {
    private Message message;
    private String title;
    private String description;
    private String imageEmoji;


    /**
     * Constructor for a DailyDeal
     * @param message
     */
    protected DailyDeal(Message message) {
        setMessage(message);
    }

    /**
     * Will get the current system date and select a deal to return.
     * @param message
     * @return Returns a Daily Deal. The Daily deals are: MondayDeal, TuesdayDeal, WednesdayDeal, ThursdayDeal, FridayDeal, SaturdayDeal, and SundayDeal.
     */
    public static DailyDeal getTodaysDeal(Message message) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        // NOTICE: THIS IS HARDCODED FOR TESTING PURPOSES!
//        return new FridayDeal(message);

        switch (day) {
            case Calendar.MONDAY:
                return new MondayDeal(message);
            case Calendar.TUESDAY:
                return new TuesdayDeal(message);
            case Calendar.WEDNESDAY:
                return new WednesdayDeal(message);
            case Calendar.THURSDAY:
                 return new ThursdayDeal(message);
            case Calendar.FRIDAY:
                 return new FridayDeal(message);
            case Calendar.SATURDAY:
                 return new SaturdayDeal(message);
            case Calendar.SUNDAY:
                 return new SundayDeal(message);
            default:
                return new DailyDeal(message);
        }
    }


    /**
     * @return Returns the daily deal's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the daily deal's title
     * @param title
     */
    protected void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the daily deal's description
     * @param description
     */
    protected void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the daily deal's emoji
     * @param imageEmoji
     */
    protected void setImageEmoji(String imageEmoji) {
        this.imageEmoji = imageEmoji;
    }

    public Message getMessage() {
        return message;
    }

    /**
     * Sets a message. If message is null, return a new message with text "FAKE"
     * This was first made for testing purposes.
     * @param message
     */
    public void setMessage(Message message) {
        if(message != null) {
            this.message = message;

        } else {
            this.message = new Message("FAKE", 1);
        }
    }

    /**
     * @return Returns the daily deal's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the daily deal's emoji
     * @return
     */
    public String getImageEmoji() {
        return imageEmoji;
    }

    /**
     * Checks if user has enough chars left to send a message
     * @param conversationId
     * @return True: message sent | False: message not sent
     */
    public boolean outgoingMessage(int conversationId) {
        if (canSend()) {
            subtractCharCount(getLength());
            Database.addMessage(message, conversationId);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the incoming message has emojis and if it is in the deal of the day.
     * This function is always overriden by the Daily Deals to work according to the deal assigned.
     */
    public void incomingMessage() {

    }

    /**
     * Will subtract an amount of chars from the user.
     * To add chars, pass the parameter as a negative Integer.
     * @param bonus
     */
    protected void subtractCharCount(int bonus) {
        Database db = new Database();
        db.addCount(bonus * (-1));
    }

    /**
     * @param searchText
     * @return Returns the number of times a {@link String} is found
     */
    protected int getCharCount(String searchText) {
        Log.d("getCharCount:", "searchText is: " + searchText);
        return message.getText().split(searchText, -1).length - 1;
    }

    /**
     * @return Returns the length of a message disregarding number of emojis
     */
    protected int getLength() {
        return EmojiParser.removeAllEmojis(message.getText()).length();
    }

    /**
     * @return True: the user can send a message | False: the user cannot send a message
     */
    public boolean canSend() {
        return Database.getCharCount() >= getLength() && getMessage().getText().length() > 0;
    }
}
