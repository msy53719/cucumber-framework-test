package com.mosy.core.test.steps;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mosy.core.test.util.AssertUtil;
import com.mosy.core.util.FormatExpecteddData;
import com.mosy.core.util.RedisUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.Assert.assertThat;  

public class CommonSteps {

	private static Logger logger = LoggerFactory.getLogger(CommonSteps.class);
	private Response response = null;
	private static final String URL = "https://www.sojson.com";

	@Given("^设置当前请求的请求header为\"(\\S*)\"$")
	public void setHeader(String header) {
		logger.debug("header 为", header);
	}

	@When("^发送带如下参数的get请求到api\"(\\S*)\"$")
	public void sentGetRequest(String path, Map<String, String> map) {
		logger.debug(path);
		for (Entry<String, String> entery : map.entrySet()) {
			logger.debug("key为: {} , value 为  :{},", entery.getKey(), entery.getValue());
		}
		RequestSpecification resquest = given().params(map);
		response = resquest.get(URL + path);
		logger.debug(response.asString());
	}

	@Then("^请求返回的状态码为 \"(\\S*)\"$")
	public void resCode(String code) {
		logger.debug("code is {}", code);
		Assert.assertEquals(response.getStatusCode(), Integer.parseInt(code));

	}

	@And("^请求返回数据满足如下要求$")
	public void reqData(Map<String, String> map) {
		FormatExpecteddData.getMap(map);
		for (Entry<String, String> entery : map.entrySet()) {
			FormatExpecteddData.getMap(map).put(entery.getKey(), entery.getValue());
			logger.debug("resdata key为: {} , resdata value 为  :{},", entery.getKey(), entery.getValue());

		}
		AssertUtil.assertResToMap(response.asString(), FormatExpecteddData.getMap(map));
	}

	@And("^将请求返回数据中的\"(\\S*)\"缓存到\"(\\S*)\"$")
	public void resDatacache(String jpath, String str) {
		JsonPath jsonpath = new JsonPath(response.asString());
		logger.debug("jpath 为  {},key 为{} value 为{}", jpath, str, jsonpath.get(jpath));

		RedisUtil.getJedis().set(str, jsonpath.get(jpath));
		logger.debug("取出缓存key的值为 ：{}", RedisUtil.getJedis().get(str));

	}
	
	@And("^返回的数据格式符合\"(\\S*)\"$")
	public void resDataJsonLayout(String jsonLayout) {
		 assertThat(response.getBody().prettyPrint(), matchesJsonSchemaInClasspath(jsonLayout));
	}

}
// ("^发送带如下参数的get请求到api\"(.*?)\"$")
// logger.debug(response.prettyPrint());
// logger.debug(response.asString());
// logger.debug(response.getContentType());
// RedisUtil.getJedis().set("name", "XXXXXXXXXX");
// logger.debug("name is :{}", RedisUtil.getJedis().get("name"));
//
// JsonPath jsonpath = new JsonPath(response.asString());
// jsonpath.get("");