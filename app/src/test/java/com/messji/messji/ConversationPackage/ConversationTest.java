package com.messji.messji.ConversationPackage;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ConversationTest {

    private Conversation _conversation;
    private int _idConversation;
    private Integer _idMessage;
    private String _title;
    private String _message;
    private List<Integer> _messagesList ;
    private List<Integer> _messages;


    @Before
    public void initialize() {
        _conversation = new Conversation();
        _idConversation = 1;
        _title = "This is the title";
        _idMessage = 99;
        _message = "This is a message";
        _messagesList = Arrays.asList(1, 2, 3, 4, 5, 6);
        _messages = new ArrayList<>(_messagesList);


    }

    @Test
    public void getId() {
        _conversation.setId(_idConversation);
        assertEquals(_idConversation, _conversation.getId());
    }

    @Test
    public void setId() {
        _conversation.setId(_idConversation);
        assertNotNull(_conversation.getId());
    }

    @Test
    public void getMessages() {
        _conversation.setMessages(_messages);
        assertEquals(_messages, _conversation.getMessages());
    }

    @Test
    public void setMessages() {
        _conversation.setMessages(_messages);
        assertNotNull(_conversation.getMessages());
    }

    @Test
    public void getTitle() {
        _conversation.setTitle(_title);
        assertEquals(_title, _conversation.getTitle());
    }

    @Test
    public void setTitle() {
        _conversation.setTitle(_title);
        assertNotNull(_conversation.getTitle());
    }

    @Test
    public void addMessage() {
        _conversation.setMessages(_messages);
        _conversation.addMessage(_idMessage);
        assertEquals(_idMessage.toString(), _conversation.getMessages().get(_conversation.getMessages().size() - 1).toString());
    }

    @Test
    public void getLastMessage() {
        _conversation.setMessages(_messages);
        assertNotNull(_conversation.getLastMessage());
    }
}