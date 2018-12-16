package com.messji.messji.deals;

import com.messji.messji.Database;
import com.messji.messji.Message;


import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class DailyDealTests {

    private DailyDeal todaysDeal = DailyDeal.getTodaysDeal(new Message("\uD83C\uDF2E Message", 0, 0, true));

    @Test
    public void getTodaysDeal() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.MONDAY:
                assertTrue(todaysDeal instanceof MondayDeal);
                break;
            case Calendar.TUESDAY:
                assertTrue(todaysDeal instanceof TuesdayDeal);
                break;
            case Calendar.WEDNESDAY:
                assertTrue(todaysDeal instanceof WednesdayDeal);
                break;
            case Calendar.THURSDAY:
                assertTrue(todaysDeal instanceof ThursdayDeal);
                break;
            case Calendar.FRIDAY:
                assertTrue(todaysDeal instanceof FridayDeal);
                break;
            case Calendar.SATURDAY:
                assertTrue(todaysDeal instanceof SaturdayDeal);
                break;
            case Calendar.SUNDAY:
                assertTrue(todaysDeal instanceof SundayDeal);
                break;
            default:
                assertTrue(false);
                break;
        }
    }

    @Test
    public void getTitle() {
        assertNotNull(todaysDeal.getTitle());
    }

    @Test
    public void setTitle() {
        DailyDeal dd = new MondayDeal(null);
        String title = "Title of the deal";
        dd.setTitle(title);
        assertEquals(title, dd.getTitle());
    }

    @Test
    public void setDescription() {
        DailyDeal dd = new MondayDeal(null);
        String description = "Description of the deal";
        dd.setDescription(description);
        assertEquals(description, dd.getDescription());
    }

    @Test
    public void setImageEmoji() {
        DailyDeal dd = new MondayDeal(null);
        String imageEmoji = "\uD83C\uDF2E";
        dd.setImageEmoji(imageEmoji);
        assertEquals(imageEmoji, dd.getImageEmoji());
    }

    @Test
    public void getDescription() {
        assertNotNull(todaysDeal.getDescription());
    }

    @Test
    public void getImageEmoji() {
        assertNotNull(todaysDeal.getImageEmoji());
    }

    @Test
    public void subtractCharCount() {
        Database db = new Database();
        DailyDeal dd = new DailyDeal(null);

        int before = db.getCharCount();
        int after;
        int subtract = 10;

        dd.subtractCharCount(subtract);

        after = db.getCharCount();

        assertTrue(before > after);
    }

    @Test
    public void getCharCount() {
        assertTrue(todaysDeal.getCharCount("\uD83C\uDF2E") == 1);
    }

    @Test
    public void getLength() {
        assertNotNull(todaysDeal.getLength());
    }

    @Test
    public void canSend() {
        assertTrue(todaysDeal.canSend());
    }
}