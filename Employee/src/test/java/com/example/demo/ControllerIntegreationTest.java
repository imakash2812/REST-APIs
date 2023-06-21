package com.example.demo;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes=ControllerIntegreationTest.class)
public class ControllerIntegreationTest {
/** Here we need to start the springboot server because here our controller intracts with database using rest template for testing environment can intract with the real database as well as
 * as dummy database but we need to prefer dummy database because our real database is used by the many people so sometimes they make updates the real database due to that sometimes we get test failure 
	*/
//	@Test
//	@Order(1)
//	void getAllEmployeesIntegrationTest() throws JSONException
//	{
//		String expected="[\r\n"
//				+ "  {\r\n"
//				+ "    \"id\": \"1\",\r\n"
//				+ "    \"name\": \"Akash\",\r\n"
//				+ "    \"department\": \"Java\",\r\n"
//				+ "    \"phoneNo\": \"9667646841\"\r\n"
//				+ "  },\r\n"
//				+ "  {\r\n"
//				+ "    \"id\": \"2\",\r\n"
//				+ "    \"name\": \"Akash Maurya\",\r\n"
//				+ "    \"department\": \"Java Cloud\",\r\n"
//				+ "    \"phoneNo\": \"9667646841\"\r\n"
//				+ "  },\r\n"
//				+ "  {\r\n"
//				+ "    \"id\": \"3\",\r\n"
//				+ "    \"name\": \"Vishal\",\r\n"
//				+ "    \"department\": \"Electrical\",\r\n"
//				+ "    \"phoneNo\": \"64585749847\"\r\n"
//				+ "  },\r\n"
//				+ "  {\r\n"
//				+ "    \"id\": \"4\",\r\n"
//				+ "    \"name\": \"Vishal Maurya\",\r\n"
//				+ "    \"department\": \"ECE\",\r\n"
//				+ "    \"phoneNo\": \"80746547635\"\r\n"
//				+ "  },\r\n"
//				+ "  {\r\n"
//				+ "    \"id\": \"5\",\r\n"
//				+ "    \"name\": \" Jai Singh\",\r\n"
//				+ "    \"department\": \"Mechanical\",\r\n"
//				+ "    \"phoneNo\": \"60494745347\"\r\n"
//				+ "  },\r\n"
//				+ "  {\r\n"
//				+ "    \"id\": \"6\",\r\n"
//				+ "    \"name\": \"Ajay\",\r\n"
//				+ "    \"department\": \"Technical\",\r\n"
//				+ "    \"phoneNo\": \"9695873634\"\r\n"
//				+ "  },\r\n"
//				+ "  {\r\n"
//				+ "    \"id\": \"7\",\r\n"
//				+ "    \"name\": \"Ajay\",\r\n"
//				+ "    \"department\": \"Technical\",\r\n"
//				+ "    \"phoneNo\": \"9695873634\"\r\n"
//				+ "  }\r\n"
//				+ "]";
//		
//		TestRestTemplate restTemplate=new TestRestTemplate();// Its a springboot inbuilt framework through this restTeamplate we can send/trigger the request URL and we will get the response and we will do validations on the response
//		ResponseEntity<String> response= restTemplate.getForEntity("http://localhost:8081/employee/getemployees",String.class);
//		System.out.println(response.getStatusCode());
//		System.out.println(response.getBody());
//		
//		JSONAssert.assertEquals(expected,response.getBody(), false);
//	}
//	
//	@Test
//	@Order(2)
//	void getAllEmployeesByIdIntegrationTest() throws JSONException
//	{
//		String expected="{\r\n"
//				+ "  \"id\": \"2\",\r\n"
//				+ "  \"name\": \"Akash Maurya\",\r\n"
//				+ "  \"department\": \"Java Cloud\",\r\n"
//				+ "  \"phoneNo\": \"9667646841\"\r\n"
//				+ "}";
//		
//		TestRestTemplate restTemplate=new TestRestTemplate();// Its a springboot inbuilt framework through this restTeamplate we can send/trigger the request URL and we will get the response and we will do validations on the response
//		ResponseEntity<String> response= restTemplate.getForEntity("http://localhost:8081/employee/getemployees/2",String.class);
//		System.out.println(response.getStatusCode());
//		System.out.println(response.getBody());
//		
//		JSONAssert.assertEquals(expected,response.getBody(), false);
//	}
//	
	@Test
	@Order(3)
	void getAllEmployeesByNameIntegrationTest() throws JSONException
	{
		String expected="{\r\n"
				+ "  \"id\": \"4\",\r\n"
				+ "  \"name\": \"Vishal Maurya\",\r\n"
				+ "  \"department\": \"ECE\",\r\n"
				+ "  \"phoneNo\": \"80746547635\"\r\n"
				+ "}";
		
		TestRestTemplate restTemplate=new TestRestTemplate();// Its a springboot inbuilt framework through this restTeamplate we can send/trigger the request URL and we will get the response and we will do validations on the response
		ResponseEntity<String> response= restTemplate.getForEntity("http://localhost:8081/employee/getemployees/name?name=Vishal Maurya",String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		
		JSONAssert.assertEquals(expected,response.getBody(), false);
	}
	
//	@Test
//	@Order(4)
//	void addEmployeeIntegrationTest() throws JSONException
//	{
//		Employee employee=new Employee("11","Ajay","Technical","9695873634");
//		
//		String expected="{\r\n"
//				+ "  \"id\": \"11\",\r\n"
//				+ "  \"name\": \"Ajay\",\r\n"
//				+ "  \"department\": \"Technical\",\r\n"
//				+ "  \"phoneNo\": \"9695873634\"\r\n"
//				+ "}";
//		TestRestTemplate restTemplate=new TestRestTemplate();// Its a springboot inbuilt framework through this restTeamplate we can send/trigger the request URL and we will get the response and we will do validations on the response
//		HttpHeaders headers=new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<Employee> request=new HttpEntity<Employee>(employee,headers);
//		
//		ResponseEntity<String> response= restTemplate.postForEntity("http://localhost:8081/employee/addemployee",request,String.class);
//		System.out.println(response.getBody());
//		
//		assertEquals(HttpStatus.CREATED, response.getStatusCode());
//		JSONAssert.assertEquals(expected, response.getBody(), false);
//	}
}