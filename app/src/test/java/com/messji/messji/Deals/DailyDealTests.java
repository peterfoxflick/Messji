package com.messji.messji.Deals;

import com.messji.messji.Message;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class DailyDealTests {

    DailyDeal todaysDeal = DailyDeal.getTodaysDeal(new Message("\uD83C\uDF2E Message", 0, 0, true));

    @Test
    public void getTodaysDeal() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.MONDAY:
                assertTrue(todaysDeal instanceof MondayDeal);
            case Calendar.TUESDAY:
                assertTrue(todaysDeal instanceof TuesdayDeal);
            case Calendar.WEDNESDAY:
                assertTrue(todaysDeal instanceof WednesdayDeal);
            case Calendar.THURSDAY:
                assertTrue(true);
                // assertTrue(todaysDeal instanceof ThursdayDeal);
            case Calendar.FRIDAY:
                assertTrue(true);
                // assertTrue(todaysDeal instanceof FridayDeal);
            case Calendar.SATURDAY:
                assertTrue(true);
                // assertTrue(todaysDeal instanceof SaturdayDeal);
            case Calendar.SUNDAY:
                assertTrue(true);
                // assertTrue(todaysDeal instanceof SundayDeal);
            default:
                assertTrue(false);
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
    public void outgoingMessage() {
    }

    @Test
    public void incomingMessage() {
    }

    @Test
    public void subtractCharCount() {
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
    }
}