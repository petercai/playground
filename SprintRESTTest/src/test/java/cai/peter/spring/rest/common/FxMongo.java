package cai.peter.spring.rest.common;

import javax.annotation.PostConstruct;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class FxMongo {

  @Autowired protected org.springframework.data.mongodb.core.SimpleMongoClientDbFactory mongoFactory;

  protected Jongo jongo;

  private MongoCollection serviceRepo;

  @PostConstruct
  protected void mongoInit() {
    jongo = new Jongo(mongoFactory.getLegacyDb());
    serviceRepo = jongo.getCollection("endpoints");
  }
}
