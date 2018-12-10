package com.messji.messji.Deals;

public abstract class DailyDeal {
    private String message;
    private String description;
    private String imageEmoji;

    public DailyDeal(String message) {
        this.message = message;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected void setImageEmoji(String imageEmoji) {
        this.imageEmoji = imageEmoji;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public String getImageEmoji() {
        return imageEmoji;
    }

    public void outgoingMessage() {

    }

    public void incomingMessage() {

    }

    protected void updateCharCount(int bonus) {

    }

    protected int getCharCount(String searchText) {
        return 0;
    }

    protected int getLength() {
        return message.length();
    }

    public boolean canSend() {
        return true;
    }
}
