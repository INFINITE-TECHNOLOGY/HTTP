# Infinite Technology ∞ HTTP

|Attribute\Release type|Stable|
|----------------------|------|
|Version|1.0.x|
|Branch|[master](https://github.com/INFINITE-TECHNOLOGY/HTTP)|
|CI Build status|[![Build Status](https://travis-ci.com/INFINITE-TECHNOLOGY/HTTP.svg?branch=master)](https://travis-ci.com/INFINITE-TECHNOLOGY/HTTP)|
|Test coverage|[![codecov](https://codecov.io/gh/INFINITE-TECHNOLOGY/HTTP/branch/master/graphs/badge.svg)](https://codecov.io/gh/INFINITE-TECHNOLOGY/HTTP/branch/master/graphs)|
|Library (Maven)|[oss.jfrog.org snapshot](https://oss.jfrog.org/artifactory/webapp/#/artifacts/browse/tree/General/oss-snapshot-local/io/infinite/HTTP/1.0.0-SNAPSHOT)|

## Purpose

Java/Groovy HTTP Client library.

## Features

This HTTP Client library supports the following connection features:

- HTTP (plaintext)
- HTTPS
- HTTPS without server certificate validations (i.e. self-signed certificates)
- Basic Authorization
- AWS Signature v4
- Proxy
- Connection timeout
- Read timeout
- Detailed logging (via [BlackBox](https://github.com/INFINITE-TECHNOLOGY/BLACKBOX))

## Gradle

> ❗ Via **JCenter** repository

```groovy
compile "io.i-t:http:1.0.0"
```

## Try it now!

```groovy
import io.infinite.http.HttpRequest
import io.infinite.http.HttpResponse
import io.infinite.http.SenderDefaultHttps

        HttpRequest httpRequest = new HttpRequest(
                url: "https://google.com",
                method: "GET"
        )
        HttpResponse httpResponse = new HttpResponse()
        new SenderDefaultHttps().sendHttpMessage(httpRequest, httpResponse)
        if (httpResponse.status != 200) {
            throw new Exception("Error HTTP Code")
        }
```