package com.mracz.twitterdm;

import org.junit.Assert;

import hu.meza.aao.ActorManager;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TwitterDMSteps {
	
	private ActorManager actorManager;

    public TwitterDMSteps(ActorManager actorManager) {
        this.actorManager = actorManager;
    }
    
	@Given("^([^/s]+), ([^/s]+) are on twitter$")
	public void are_on_twitter(String actor1Label, String actor2Label){
		TwitterUser user1 = new TwitterUser(actor1Label);
		TwitterUser user2 = new TwitterUser(actor2Label);
		
		actorManager.addActor(actor1Label, user1);
		actorManager.addActor(actor2Label, user2);
	}

	@Given("^([^/s]+) is a follower of ([^/s]+)$")
	public void is_follower_of(String actor1Label, String actor2Label) {
	    TwitterUser performingUser = (TwitterUser) actorManager.getActor(actor1Label);
	    TwitterUser targetUser = (TwitterUser) actorManager.getActor(actor2Label);
	    
	    performingUser.createFriendship(targetUser.getId());
	}
	
	@Given("^([^/s]+) is NOT following ([^/s]+)$")
	public void is_NOT_follower_of(String actor1Label, String actor2Label){
		 TwitterUser performingUser = (TwitterUser) actorManager.getActor(actor1Label);
		 TwitterUser targetUser = (TwitterUser) actorManager.getActor(actor2Label);
		 
		 performingUser.destroyFriendship(targetUser.getId());
	}

	@When("^([^/s]+) sends a DM to ([^/s]+)$")
	public void sends_DM_to(String actor1Label, String actor2Label){
		TwitterUser performingUser = (TwitterUser) actorManager.getActor(actor1Label);
		TwitterUser targetUser = (TwitterUser) actorManager.getActor(actor2Label);
		
		SendDmAction sendDM = new SendDmAction(performingUser, targetUser);
		performingUser.execute(sendDM);
	}
	
	@Then("^([^/s]+) should see ([^/s]+)’s message$")
	public void should_see_message_from(String actor1Label, String actor2Label){
		TwitterUser performingUser = (TwitterUser) actorManager.getActor(actor1Label);
		TwitterUser targetUser = (TwitterUser) actorManager.getActor(actor2Label);
		String messageToCheck = ((SendDmAction)targetUser.lastAction()).getMessageText();
		
		Assert.assertTrue(performingUser.isMessageRecieved(messageToCheck));
	}
	
	@Then("^([^/s]+) should get an error response$")
	public void should_get_an_error_response(String actor1Label){
		TwitterUser performingUser = (TwitterUser) actorManager.getActor(actor1Label);
		String response = ((SendDmAction)performingUser.lastAction()).getResponse();
		
		Assert.assertEquals("You cannot send messages to users who are not following you.", response);
	}

	@Then("^([^/s]+) should not receive ([^/s]+)’s message$")
	public void should_not_receive_message_from(String actor1Label, String actor2Label){
		TwitterUser performingUser = (TwitterUser) actorManager.getActor(actor1Label);
		TwitterUser targetUser = (TwitterUser) actorManager.getActor(actor2Label);
		String messageToCheck = ((SendDmAction)targetUser.lastAction()).getMessageText();
			
		Assert.assertFalse(performingUser.isMessageRecieved(messageToCheck));
	}
}
