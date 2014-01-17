package com.mracz.twitterdm;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.*;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format="pretty",features="src/test/resources")

public class TwitterDMRunner {}
