package com.carreservation.searchservice.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.http.HttpHeaders;

import java.net.URI;

@Configuration
@EnableElasticsearchRepositories(basePackages = "*")
public class RestClientConfig extends AbstractElasticsearchConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(RestClientConfig.class);

//    @Bean
//    public AdminService adminService() {
//        return new AdminService();
//    }

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        final String stringUrl = "https://elastic:T9H0HcHNFm9A3W14KIMKP6Ab@car-reservation-system.es.us-central1.gcp.cloud.es.io:9243";
        final URI uri = URI.create(stringUrl);

        String host = uri.getHost();
        int port = uri.getPort() == -1 ? 9200 : uri.getPort();
        final ClientConfiguration.MaybeSecureClientConfigurationBuilder builder = ClientConfiguration.builder().connectedTo(host + ":" + port);

        // enable SSL if https is being used in the URL
        boolean isSsl = "https".equals(uri.getScheme());
        if (isSsl) {
            builder.usingSsl();
        }

        builder.withDefaultHeaders(compatibilityHeaders());

        // enable basic auth if specified
        String userInfo = uri.getUserInfo();
        if (userInfo != null) {
            final String[] userPass = userInfo.split(":", 2);
            builder.withBasicAuth(userPass[0], userPass[1]);
        }

        logger.info("Elasticsearch server [{}:{}] ssl[{}] auth[{}]", host, port, isSsl, userInfo != null);
        return RestClients.create(builder.build()).rest();
    }

    private org.springframework.http.HttpHeaders compatibilityHeaders() {
        HttpHeaders compatibilityHeaders = new HttpHeaders();
        compatibilityHeaders.add("Accept", "application/vnd.elasticsearch+json;compatible-with=7");
        compatibilityHeaders.add("Content-Type", "application/vnd.elasticsearch+json;"
                + "compatible-with=7");
        return compatibilityHeaders;
    }
}