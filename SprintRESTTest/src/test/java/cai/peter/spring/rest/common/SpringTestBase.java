package cai.peter.spring.rest.common;

import cai.peter.spring.common.FxMongo;
import cai.peter.spring.rest.SprintRestTestApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest(classes= SprintRestTestApplication.class)
@ExtendWith(SpringExtension.class)
public class SpringTestBase extends FxRestAssured{

  @Autowired
  protected FxMongo mg;

}
