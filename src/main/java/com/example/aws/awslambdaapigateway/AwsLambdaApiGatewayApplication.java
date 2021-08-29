package com.example.aws.awslambdaapigateway;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.example.aws.awslambdaapigateway.entity.Student;
import com.example.aws.awslambdaapigateway.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SpringBootApplication
public class AwsLambdaApiGatewayApplication {


	@Autowired
	private StudentRepository studentRepository;

	@Bean
	public Supplier<List<Student>> students(){
		return ()->studentRepository.studentList();
	}

	@Bean
	public Function<APIGatewayProxyRequestEvent,List<Student>> findByName(){
		return (proxyRequestEvent)->studentRepository.studentList().stream().
				filter(student -> student.getName()
						.equals(proxyRequestEvent.getQueryStringParameters().get("name"))).collect(Collectors.toList());
	}

	@Bean
	public Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> getResponse(){

		return value-> {
			APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
			responseEvent.setBody(value.getBody());
			responseEvent.setStatusCode(201);
			responseEvent.setHeaders(Collections.singletonMap("Content-type", "application/json"));
			return responseEvent;
		};
	}

	@Bean
	public MyConsumer myConsumerBean(){
		return new MyConsumer();
	}
	public static void main(String[] args) {
		SpringApplication.run(AwsLambdaApiGatewayApplication.class, args);
	}

}
