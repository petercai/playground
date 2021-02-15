package cai.peter.spring.common;

import java.util.Map;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Endpoint {
  private String name;
  private String alias;
  private String host;
  private String version;
  private String path;
  private String auth;
  private String user;
  private String password;
  private Map<String,String> parameters;
  private Map<String,String> headers;
  private String category;
  private String comment;
  private boolean active;

  public String getUrl(){
    return "https://"+host+"/api/"+version+ Optional.ofNullable(path).orElse("");
  }

  public String getBase() {
    return "https://"+host+"/api/"+version;
  }

  public String getHostOnly() {
    return "https://"+host;
  }

}
/*
{
  "name": "",
  "alias": "",
  "host": "",
  "version": "",
  "path": "",
  "user": "",
  "password": "",
  "parameters": {},
  "headers": {},
  "category": "",
  "comment": "",
  "active": true
},
{
  "name": "Alpha Vantage - Intraday",
  "alias": "intraday",
  "host": "https://www.alphavantage.co",
  "path": "query",
  "auth": "TCJVXY3RYRW5AT6T",
  "parameters": {"function":"TIME_SERIES_INTRADAY",
                "symbol":"BNS.TO",
                "interval":"5min",
                "outputsize":"full",
                "apikey":"TCJVXY3RYRW5AT6T"},
  "headers": {},
  "category": "",
  "comment": "",
  "active": true
}

 */