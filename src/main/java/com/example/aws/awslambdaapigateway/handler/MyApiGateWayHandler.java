package com.example.aws.awslambdaapigateway.handler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class MyApiGateWayHandler
        extends SpringBootRequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
}
