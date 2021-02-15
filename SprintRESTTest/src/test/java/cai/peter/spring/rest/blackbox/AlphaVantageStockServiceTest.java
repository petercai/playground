package cai.peter.spring.rest.blackbox;

import static com.mongodb.client.model.Filters.eq;

import cai.peter.spring.json.JacksonUtil;
import cai.peter.spring.rest.common.SpringTestBase;
import com.mongodb.client.MongoCollection;
import java.io.IOException;
import java.lang.reflect.Type;

import javax.annotation.Resource;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import cai.peter.spring.common.AlphaVantageIntradayResponse;
import cai.peter.spring.common.Endpoint;
import cai.peter.spring.common.FxMongo;
import io.restassured.internal.mapping.Jackson2Mapper;
import io.restassured.response.Response;


public class AlphaVantageStockServiceTest extends SpringTestBase {

  @Autowired FxMongo mg;

  @Resource(name = "intraday")
  protected Endpoint endpoint;

  @BeforeEach
  public void setUp() throws Exception {
    logInit();

    Endpoint as = mg.getServiceRepo().find(eq("active", endpoint.isActive())).first();
    as.setPath(endpoint.getPath());
    endpoint = as;
  }

  io.restassured.mapper.factory.Jackson2ObjectMapperFactory factory =
      new io.restassured.mapper.factory.Jackson2ObjectMapperFactory() {

        @Override
        public ObjectMapper create(Type type, String s) {
          return JacksonUtil.createMapper();
        }
      };

  @AfterEach
  public void tearDown() throws IOException {
    logTeardown();
  }

  @Test
  public void testIntradayTransactions() throws Exception {

    Response res =
        buildJSONRequest()
            .header("TraceId", "123456789")
            //                .queryParam("from_date", "2019-10-01")
            //                .queryParam("to_date", "2019-10-31")
            //                .pathParam("key", "xyz")
            .get(endpoint.getUrl() /*+ "/transactions/{key}"*/);

    AlphaVantageIntradayResponse transactionResponse =
        res.getBody().as(AlphaVantageIntradayResponse.class, new Jackson2Mapper(factory));

    res.then().statusCode(200);

    MongoCollection<AlphaVantageIntradayResponse> transactions = mg.getMongoCollection("transactions", AlphaVantageIntradayResponse.class);
  }

  @Test
  public void testDelete() throws Exception {

    Response res = buildJSONRequest().delete(endpoint.getUrl() + "/delete");

    res.then().statusCode(204);
  }

  @Test
  public void testPut() throws Exception {

    Response res =
        buildJSONRequest()
            //            .body(change)
            .put(endpoint.getUrl() + "/put");

    res.then().statusCode(200);
  }

  @Test
  public void testCreate() throws Exception {

    Response res =
        buildJSONRequest()
            //            .body(
            //                  new JacksonBean()
            //                      .objectMapperBuilder()
            //                      .build()
            //                      .writeValueAsString(abc))
            .post(endpoint.getUrl() + "/create");
    ;
    res.then().statusCode(200);
  }
}
