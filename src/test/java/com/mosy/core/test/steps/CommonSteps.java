package com.mosy.core.test.steps;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class CommonSteps {

	public Response response = null;
	public static final String URL = "http://www.kuaidi100.com";
	private static Logger logger = LoggerFactory.getLogger(CommonSteps.class);

	// ("^发送带如下参数的get请求到api\"(.*?)\"$")
	@When("^发送带如下参数的get请求到api\"(\\S*)\"$")
	public void SentGetRequest(String path, Map<String, String> map) {
		logger.debug(path);
		// System.out.println(path);
		for (Entry<String, String> entery : map.entrySet()) {
			logger.debug("key为: {} , value 为  :{},", entery.getKey(), entery.getValue());

		}
		RequestSpecification resquest = given().params(map);
		response = resquest.get(URL + path);
		logger.debug(response.prettyPrint());
		logger.debug(response.asString());
		logger.debug(response.getContentType());
		JsonPath jsonpath=new JsonPath(response.asString());
		

	}
	//
	// @Then(value = "")
	// public void asser() {
	//
	// }
	//
	// @And(value = "")
	// public void and() {
	// }

}
