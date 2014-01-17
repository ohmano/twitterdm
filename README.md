twitterdm
=========

Twitter DirectMessage test implementation.

I used Cucumber-JVM (with JUnit) to execute the scenarios. (TwitterDMRunner.java)
Also used some part of the Actors-Actions-Outcomes framework created by meza (https://github.com/meza/AAO)

### Credentials

You will need 2 different Twitter account.

Please follow the instructions below to create credentials for different accounts:
  1. Go to https://dev.twitter.com/apps
  2. Login if needed
  3. Click on "Create a new application" (in case you don't have one)
  4. Fill the form and create application
  5. Go to "Setting" tab
  6. Select "Read, Write and Access direct messages" access type and update settings.
  7. Go back to "Details" tab
  8. Generate Access Token on the bottom of the site.
  9. Copy the ConsumerKey, ConsumerSecret, AccessTokenKey, AccessTokenSecret values.
  10. Edit the credentials in "twitterdm.credentials" file.  e.g. JoeConsumerKey=WHvw6KJAc59Gvrg8jt6tQ

### Libraries
Used libraries (available in repository):
  - cucumber-java-1.1.5.jar
  - cucumber-junit-1.1.5.jar
  - cucumber-picocontainer-1.1.5.jar
  - hamcrest-core-1.3.jar
  - junit-4.11.jar
  - twitter4j-core-3.0.5-sources.jar
