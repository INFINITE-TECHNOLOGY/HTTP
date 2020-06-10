package io.infinite.http.aws

import com.amazonaws.AmazonWebServiceResponse
import com.amazonaws.http.HttpResponse
import com.amazonaws.http.HttpResponseHandler
import com.amazonaws.util.IOUtils
import groovy.util.logging.Slf4j
import io.infinite.blackbox.BlackBox
import io.infinite.blackbox.BlackBoxLevel

@BlackBox(level = BlackBoxLevel.METHOD)
@Slf4j
class AwsResponseHandler implements HttpResponseHandler<AmazonWebServiceResponse<String>> {

    @Override
    AmazonWebServiceResponse<String> handle(HttpResponse response) throws IOException {
        AmazonWebServiceResponse<String> awsResponse = new AmazonWebServiceResponse<>()
        awsResponse.result = (String) IOUtils.toString(response.content)
        return awsResponse
    }

    @Override
    boolean needsConnectionLeftOpen() {
        return false
    }

}