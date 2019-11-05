package cai.peter.spring.rest.blackbox;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.annotation.Resource;

import org.jongo.MongoCollection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import cai.peter.spring.rest.bean.JacksonBean;
import cai.peter.spring.rest.common.AlphaVantageIntradayResponse;
import cai.peter.spring.rest.common.Endpoint;
import cai.peter.spring.rest.common.FxMongo;
import cai.peter.spring.rest.common.FxRestAssured;
import io.restassured.internal.mapping.Jackson2Mapper;
import io.restassured.response.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:application-context.xml"})
@ComponentScan({"cai.peter.sprint.rest.common.*"})
public class AlphaVantageStockServiceTest extends FxRestAssured {

  @Autowired FxMongo mg;

  @Resource(name = "intraday")
  protected Endpoint endpoint;

  @Before
  public void setUp() throws Exception {
    logInit();

    Endpoint as = mg.getServiceRepo().findOne(new Gson().toJson(endpoint)).as(Endpoint.class);
    as.setPath(endpoint.getPath());
    endpoint = as;
  }

  io.restassured.mapper.factory.Jackson2ObjectMapperFactory factory =
      new io.restassured.mapper.factory.Jackson2ObjectMapperFactory() {

        @Override
        public ObjectMapper create(Type type, String s) {
          return new JacksonBean().objectMapperBuilder().build();
        }
      };

  @After
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

    MongoCollection pini = mg.getJongo().getCollection("transactions");
    pini.remove();
    pini.insert(transactionResponse.getData().toArray());
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
