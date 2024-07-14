package com.abs.wfs.workman.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.stereotype.Component;

@Component
public class GracefulShutdownTomcatConnector implements TomcatConnectorCustomizer {

    private volatile Connector connector;

    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }

    public Connector getConnector() {
        return connector;
    }
}

