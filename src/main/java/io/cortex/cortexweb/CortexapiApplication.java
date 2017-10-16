package io.cortex.cortexweb;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class CortexapiApplication {

	public static void main(String[] args) throws SQLException {
//		Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
		SpringApplication.run(CortexapiApplication.class, args);
	}

	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatEmbedded() {

		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();

		tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
			if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
				//-1 means unlimited
				((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
			}
		});
		return tomcat;
	}

	// Set maxPostSize of embedded tomcat server to 100 megabytes
	@Bean
	EmbeddedServletContainerCustomizer containerCustomizer() throws Exception {
		return (ConfigurableEmbeddedServletContainer container) -> {
			if (container instanceof TomcatEmbeddedServletContainerFactory) {
				TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
				tomcat.addConnectorCustomizers(
						(connector) -> {
							connector.setMaxPostSize(100000000); // 100 MB
						}
				);
			}
		};
	}
}
