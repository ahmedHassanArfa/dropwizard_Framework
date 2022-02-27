package conrad.codeworkshop.core;


import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

public final class Config extends Configuration {

  @NotNull
  private boolean shouldDoSearch;

  @NotNull
  private String elasticsearchHost;

  @NotNull
  private int elasticsearchPort;

  public boolean isShouldDoSearch() {
    return shouldDoSearch;
  }

  public String getElasticsearchHost() {
    return elasticsearchHost;
  }

  void setElasticsearchHost(final String elasticsearchHost) {
    this.elasticsearchHost = elasticsearchHost;
  }

  public int getElasticsearchPort() {
    return elasticsearchPort;
  }

  public void setElasticsearchPort(int elasticsearchPort) {
    this.elasticsearchPort = elasticsearchPort;
  }

}
