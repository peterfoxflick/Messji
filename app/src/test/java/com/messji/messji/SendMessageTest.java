package com.messji.messji;

import com.messji.messji.ConversationPackage.Conversation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SendMessageTest {
    @Test
    public void sendMessageTest() {
        //Check that messageID matches convo ID
        // create message
        Message m = null;
        m.setMessage_id(10);

        //create convo
        Conversation c = null;

        //send message to covno
        c.addMessage(m.getMessage_id());

        //assert convo listID contains messadeID
//        assertEquals(true, true);
        assertEquals(c.getMessages(), m.getMessage_id());
    }
}
