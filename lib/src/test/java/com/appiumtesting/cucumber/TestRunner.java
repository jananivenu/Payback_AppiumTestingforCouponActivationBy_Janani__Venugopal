package com.appiumtesting.cucumber;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber-report.html","json:target/JSONReports/JSONreport.json","junit:target/JUnitReports/junit-report.xml"},
features={"src/test/resources/Features"},glue= {"com.appiumtesting.cucumber"},tags = "@Test")
public class TestRunner {

}
