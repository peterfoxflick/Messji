package com.messji.messji.Deals;

import com.messji.messji.Database;
import com.messji.messji.Message;

import java.util.Calendar;

public class DailyDeal {
    private Message message;
    private String title;
    private String description;
    private String imageEmoji;

    protected DailyDeal(Message message) {
        this.message = message;
    }

    public static DailyDeal getTodaysDeal(Message message) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

//        return new TuesdayDeal(message);

        switch (day) {
            case Calendar.MONDAY:
                return new MondayDeal(message);
            case Calendar.TUESDAY:
                return new TuesdayDeal(message);
            case Calendar.WEDNESDAY:
                return new WednesdayDeal(message);
            case Calendar.THURSDAY:
                return null;
            case Calendar.FRIDAY:
                return null;
            case Calendar.SATURDAY:
                return null;
            case Calendar.SUNDAY:
                return null;
            default:
                return new DailyDeal(message);
        }
    }

    public String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected void setImageEmoji(String imageEmoji) {
        this.imageEmoji = imageEmoji;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public String getImageEmoji() {
        return imageEmoji;
    }

    public boolean outgoingMessage(int conversationId) {
        if (canSend()) {
            subtractCharCount(getLength());
            Database.addMessage(message, conversationId);
            return true;
        } else {
            return false;
        }
    }

    public void incomingMessage() {

    }

    protected void subtractCharCount(int bonus) {
        Database db = new Database();
        db.addCount(bonus * (-1));

    }

    protected int getCharCount(String searchText) {
        return message.getText().split(searchText, -1).length - 1;
    }

    protected int getLength() {
        return message.getText().length();
    }

    public boolean canSend() {
        return Database.getCharCount() >= getLength();
    }
}
