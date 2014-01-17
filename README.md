twitterdm
=========

Twitter DirectMessage test implementations

Test are implemented in Java with Twitter API.

Please follow the instructions below to create credentials for different users:
  1. Go to https://dev.twitter.com/apps
  2. Login if needed
  3. Click on "Create a new application" (in case you don't have one)
  4. Fill the form and create application
  5. Go to "Setting" tab
  6. Select "Read, Write and Access direct messages" access type and update settings.
  7. Go back to "Details" tab
  8. Generate Access Token on the bottom of the site.
  9. Copy the ConsumerKey, ConsumerSecret, AccessTokenKey, AccessTokenSecret values.
  10. Edit the credentials in "twitterd.credentials" file.
      e.g. JoeConsumerKey=WHvw6KJAc59Gvrg8jt6tQ
           ...
           JulieAccessTokenKey=2293559412-rKta4Xzfp3uHi1yrYrtAdeYU9jq4d42pvOHYBHr

Used:
  - Cucumber 1.1.5
  - JUnit 4.1.1
  - Twitter4j 3.0.5
