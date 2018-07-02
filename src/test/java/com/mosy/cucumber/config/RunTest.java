package com.mosy.cucumber.config;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/", plugin = { "pretty", "html:target/cucumber",
		"json:target/cucumber.json" }, glue = { "classpath:com/mosy/core/test/steps/" })
public class RunTest {

}
