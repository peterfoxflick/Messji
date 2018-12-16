package com.messji.messji.deals;

import android.util.Log;

import com.messji.messji.Database;
import com.messji.messji.Message;
import com.vdurmont.emoji.EmojiParser;

public class FridayDeal extends DailyDeal {

    protected FridayDeal(Message message) {
        super(message);

        setTitle("Freaky Friday");

        setDescription("Its a strange day, this random emoji is the one you have to send to get extra points!");

        //This is removed for demo purposes
        //int r = new Random().nextInt(5);
        int r = Database.getFridayR();

        String emoji = "";
        switch (r) {
            case 0:
                emoji = "\uD83D\uDE02"; //😂 laughing/smile cry emoji
                break;
            case 1:
                emoji = "\uD83D\uDC8E"; //💎 gem stone
                break;
            case 2:
                emoji = "\uD83C\uDFC7"; //🏇 horse Race
                break;
            case 3:
                emoji = "\uD83C\uDF85"; //🎅 santa
                break;
            case 4:
                emoji = "\uD83D\uDC88"; //💈 barber pole
                break;

        }

        Log.i("FridayDeal", "Emoji is: " + emoji + " " + r);
        setImageEmoji(emoji);
    }


    @Override
    protected int getLength() {
        int length = EmojiParser.removeAllEmojis(getMessage().getText()).length();
        int santas = getCharCount(getImageEmoji());
        int points = santas * 10;
        return length - points;
    }
}
