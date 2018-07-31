Feature: 测试 
Scenario: httpget request 
	When 发送带如下参数的get请求到api"/open/api/weather/json.shtml" 
		|   city   |上海   |		
	Then 请求返回的状态码为 "200" 
	And  请求返回数据满足如下要求 
		|JsonPath |ExpectedValue |
		|date     |20180703    |
		|city     |上海                    |
	#And 将请求返回数据中的"data.quality"缓存到"#{mykey}"