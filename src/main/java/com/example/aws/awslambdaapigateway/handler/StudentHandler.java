package com.example.aws.awslambdaapigateway.handler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class StudentHandler extends SpringBootRequestHandler<APIGatewayProxyRequestEvent,Object> {
}
