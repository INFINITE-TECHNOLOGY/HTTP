package io.infinite.http

import com.amazonaws.*
import com.amazonaws.auth.AWS4Signer
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.http.AmazonHttpClient
import com.amazonaws.http.ExecutionContext
import com.amazonaws.http.HttpMethodName
import com.amazonaws.util.StringInputStream
import groovy.transform.ToString
import groovy.util.logging.Slf4j
import io.infinite.blackbox.BlackBox
import io.infinite.blackbox.BlackBoxLevel
import io.infinite.http.aws.AwsErrorResponseHandler
import io.infinite.http.aws.AwsResponseHandler

@BlackBox(level = BlackBoxLevel.METHOD)
@ToString(includeNames = true, includeFields = true, includeSuper = true)
@Slf4j
class SenderAWS extends SenderAbstract {

    @Override
    HttpResponse sendHttpMessage(HttpRequest httpRequest) {
        HttpResponse httpResponse = new HttpResponse()
        if (httpRequest.httpProperties.get("awsAccessKey") == null || httpRequest.httpProperties.get("awsSecretKey") == null) {
            log.warn("Configuration: One of the AWS keys is null")
        }
        if (httpRequest.httpProperties.get("awsServiceName") == null) {
            log.warn("Configuration: AWS service name is null")
        }
        if (httpRequest.httpProperties.get("awsResourceName") == null) {
            log.warn("Configuration: AWS resource name is null")
        }
        Request<Void> awsRequest = new DefaultRequest<Void>(httpRequest.httpProperties.get("awsServiceName") as String)
        awsRequest.httpMethod = HttpMethodName.valueOf(httpRequest.method)
        awsRequest.endpoint = URI.create(httpRequest.url)
        awsRequest.content = new StringInputStream(httpRequest.body)
        awsRequest.headers = httpRequest.headers
        AWS4Signer signer = new AWS4Signer()
        signer.regionName = httpRequest.httpProperties.get("awsRegion") as String
        signer.serviceName = awsRequest.serviceName
        awsRequest.resourcePath = httpRequest.httpProperties.get("awsResourceName") as String
        signer.sign(awsRequest, new BasicAWSCredentials(httpRequest.httpProperties.get("awsAccessKey") as String, httpRequest.httpProperties.get("awsSecretKey") as String))
        Response<AmazonWebServiceResponse<String>> awsResponse
        try {
            log.trace("Sending AWS Request")
            log.trace(awsRequest.toString())
            log.trace(httpRequest.toString())
            ClientConfiguration clientConfiguration = new ClientConfiguration()
            if (httpRequest.httpProperties?.get("proxyHost") != null && httpRequest.httpProperties?.get("proxyPort") != null) {
                clientConfiguration.proxyHost = httpRequest.httpProperties?.get("proxyHost")?.toString()
                clientConfiguration.proxyPort = httpRequest.httpProperties?.get("proxyPort") as Integer
            }
            awsResponse = new AmazonHttpClient(clientConfiguration)
                    .requestExecutionBuilder()
                    .executionContext(new ExecutionContext(true))
                    .request(awsRequest)
                    .errorResponseHandler(new AwsErrorResponseHandler())
                    .execute(new AwsResponseHandler())
            if (awsResponse.awsResponse?.result != null) {
                httpResponse.body = awsResponse.awsResponse.result
            }
            for (headerName in awsResponse.httpResponse.headers.keySet()) {
                httpResponse.headers.put(headerName, awsResponse.httpResponse.headers.get(headerName))
            }
            httpRequest.requestStatus = HttpMessageStatuses.DELIVERED.value()
            httpResponse.status = awsResponse.httpResponse.statusCode
            return httpResponse
        } catch (AmazonServiceException amazonServiceException) {
            fail(httpRequest, amazonServiceException, HttpMessageStatuses.FAILED_RESPONSE)
            httpResponse.status = amazonServiceException.statusCode
            if (amazonServiceException.httpHeaders != null) {
                for (httpHeader in amazonServiceException.httpHeaders.keySet()) {
                    httpResponse.headers.put(httpHeader, amazonServiceException.httpHeaders.get(httpHeader))
                }
            }
            httpResponse.body = amazonServiceException.errorMessage
            return httpResponse
        } catch (Exception exception) {
            fail(httpRequest, exception, HttpMessageStatuses.EXCEPTION)
            throw exception
        } finally {
            log.trace("Received response data:")
            log.trace(httpResponse.toString())
        }
    }

}