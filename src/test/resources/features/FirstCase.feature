Feature: 测试 
Scenario: httpget request 
	When 发送带如下参数的get请求到api"/query" 
		|   type   |ems     |
		| postid  | 123243     |
		
		#	Then  返回状态码为"200" 
		#	And   返回参数满足如下 
		#		|jsonpath |value |
		#		|name     |1     |
		#		|id       |111   |
		#	And 将请求结果"Key"的缓存到"#{key}"