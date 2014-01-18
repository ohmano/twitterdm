package com.mracz.twitterdm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

import twitter4j.DirectMessage;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import hu.meza.aao.Actor;

public class TwitterUser extends Actor{
	
	private String name="";
	private Long userId =null;
	private String ConsumerKey="";
	private String ConsumerSecret="";
	private String AccessTokenKey = "";
	private String AccessTokenSecret= "";
	private Twitter twitterInstance=null;
	
	
	TwitterUser(String actorLabel){
		this.name=actorLabel;
		readCredentials();
		createTwitterinstance();
		
	}

	private void createTwitterinstance(){
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(ConsumerKey)
		  .setOAuthConsumerSecret(ConsumerSecret)
		  .setOAuthAccessToken(AccessTokenKey)
		  .setOAuthAccessTokenSecret(AccessTokenSecret);
		TwitterFactory tf = new TwitterFactory(cb.build());
		this.twitterInstance = tf.getInstance();
	}
	private void readCredentials(){
		this.ConsumerKey=readCredential("ConsumerKey");
		this.ConsumerSecret=readCredential("ConsumerSecret");
		this.AccessTokenKey=readCredential("AccessTokenKey");
		this.AccessTokenSecret=readCredential("AccessTokenSecret");
	}
	
	
	private String readCredential(String credential){
		
		BufferedReader br = null;
		try {
 
			String currentLine;
 
			br = new BufferedReader(new FileReader(new File ("twitterdm.credentials")));
 
			while ((currentLine = br.readLine()) != null) {
				if (currentLine.contains(this.name) && currentLine.contains(credential)){
					credential = currentLine.split("=")[1]; 
					}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return credential;
	}
	
	public Twitter getTwitterInstance(){
		return twitterInstance;
	}
	public Long getId() {
		try {
			userId= twitterInstance.getId();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
		return userId;
	}
	
	public void createFriendship(Long ID){
		try {
			twitterInstance.createFriendship(ID);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
	
	public void destroyFriendship(Long ID){
		try {
			twitterInstance.destroyFriendship(ID);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
	
	public void destroyAllFriendship(){
  	  	long lCursor = -1;
		long[] friendsIDs = null;
		try {
			friendsIDs = (twitterInstance.getFriendsIDs( lCursor)).getIDs();
			for (long i : friendsIDs)
			{
				destroyFriendship(i);
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
	
	public void cleanInbox(){
		try {
			List<DirectMessage> messages = twitterInstance.getDirectMessages();
			for(DirectMessage message : messages){
				twitterInstance.destroyDirectMessage(message.getId());
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean isMessageRecieved(String messageText){
		Boolean recieved = false;
		
		try {
			List<DirectMessage> messages = twitterInstance.getDirectMessages();
			for(DirectMessage message : messages){
				if ((message.getText()).equals(messageText)){recieved = true;}
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
		return recieved;
	}
	
}
