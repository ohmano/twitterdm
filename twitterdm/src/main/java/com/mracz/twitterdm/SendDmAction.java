package com.mracz.twitterdm;

import java.util.Random;

import twitter4j.DirectMessage;
import twitter4j.TwitterException;
import hu.meza.aao.Action;

public class SendDmAction implements Action {
	
	private String messageText="";
	private DirectMessage message=null;
	private String response="";
	private TwitterUser performingUser=null;
    private TwitterUser targetUser=null;

    SendDmAction(TwitterUser performingUser, TwitterUser targetUser) {
    	messageText = "Hello I'm "+performingUser.getLabel()+"! Check this out : "+ new Random().nextInt(1000)+"";
    	this.performingUser = performingUser;
    	this.targetUser = targetUser;
        
    }

    public void execute() {
        try {
        	message =performingUser.getTwitterInstance().sendDirectMessage(targetUser.getId(), messageText);
			response = message.getText();
		} catch (TwitterException e) {
			response = e.getErrorMessage();
		}
    }

    public String getMessageText() {
        return messageText;
    }

    public String getResponse() {
        return response;
    }
    
    public SendDmAction copyOf() {
		return new SendDmAction(performingUser,targetUser);
	}
}
