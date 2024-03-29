package cai.peter.spring.rest.common;

import com.google.common.base.Suppliers;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.function.Supplier;

import org.apache.commons.io.output.TeeOutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;


import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.RequestSpecification;
import cai.peter.spring.json.JacksonUtil;

public class FxRestAssured {

  protected Supplier<RestAssuredConfig> configurator =
    () -> {
      return RestAssuredConfig.config()
        .sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation())
        .and()
        .redirect(RedirectConfig.redirectConfig().followRedirects(false))
        .httpClient(
          HttpClientConfig.httpClientConfig()
            .setParam("http.socket.timeout", Integer.valueOf(10000)))
        .httpClient(
          HttpClientConfig.httpClientConfig()
            .setParam("http.connection.timeout", Integer.valueOf(10000)));
    };

  protected Supplier<ObjectMapper> jacksonMapper = Suppliers.memoize(() -> {
    return JacksonUtil.createMapper();
  });

  private static PrintStream logger = new PrintStream(
    new TeeOutputStream(System.out, new ByteArrayOutputStream()));

  private static RequestLoggingFilter reqLogFilter =
    new RequestLoggingFilter(logger) {
      @Override
      public Response filter(
        FilterableRequestSpecification requestSpec,
        FilterableResponseSpecification responseSpec,
        FilterContext ctx) {
        logger.println(
          ">>--------- Request: "
            + requestSpec.getMethod()
            + " "
            + requestSpec.getUserDefinedPath()
            + " -------------------------------------------");

        return super.filter(requestSpec, responseSpec, ctx);
      }
    };
  private static ResponseLoggingFilter resLogFilter =
    new ResponseLoggingFilter(logger) {

      @Override
      public Response filter(
        FilterableRequestSpecification requestSpec,
        FilterableResponseSpecification responseSpec,
        FilterContext ctx) {
        logger.println("<<---------- Response ----------------------------------------");
        return super.filter(requestSpec, responseSpec, ctx);
      }
    };

  protected void flushLogger() throws IOException {
    logger.flush();
  }

  protected void logInit() {
    RestAssured.replaceFiltersWith(reqLogFilter, resLogFilter);
  }

  protected void logTeardown() throws IOException {
    flushLogger();
  }

  protected RequestSpecification buildJSONRequest() {
    return RestAssured.given()
      .config((RestAssuredConfig) configurator.get())
      .contentType(ContentType.JSON);
  }


  protected RequestSpecification buildAuthenticatedRequest() {
    return RestAssured.given()
      .config((RestAssuredConfig) configurator.get())
      .contentType(ContentType.JSON)
      .header("Authorization", sessionId);
  }

  protected RequestSpecification buildAuthenticatedRequest(String sessionId) {
    return RestAssured.given()
      .config((RestAssuredConfig) configurator.get())
      .contentType(ContentType.JSON)
      .header("Authorization", sessionId);
  }

  protected String sessionId;

  protected String formLogin(String user, String pwd, String base) {
    RestAssured.baseURI = base;
    Response auth = RestAssured.given().urlEncodingEnabled(true)
      .param("username", user)
      .param("password", pwd)
      .when()
      .post("/auth");
    auth.then()
      .statusCode(200);

    sessionId = (String) auth.path("sessionId");
    return sessionId;
  }

  public <T> T fromJson(String content, Class<T> type) throws Exception {
    return jacksonMapper.get().readValue(content, type);
  }
}
