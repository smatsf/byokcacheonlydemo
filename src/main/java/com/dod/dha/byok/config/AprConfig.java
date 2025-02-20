package com.dod.dha.byok.config;
import org.apache.catalina.core.AprLifecycleListener;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.Ssl;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AprConfig {

  @Bean
  public ServletWebServerFactory servletContainer() {
    TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory() {
      @Override
      public Ssl getSsl() {
        // avoid "IllegalStateException: To use SSL, the connector's protocol handler must be an AbstractHttp11JsseProtocol subclass":
        return null;
      }
    };

    // enable APR:
    factory.setProtocol("org.apache.coyote.http11.Http11NioProtocol");

    AprLifecycleListener aprLifecycleListener = new AprLifecycleListener();

    // will throw "FIPS was not available to tcnative at build time. You will need to re-build tcnative against an OpenSSL with FIPS." with default OpenSSL:
    aprLifecycleListener.setFIPSMode("on");

    return factory;
  }
}
