package com.dod.dha.byok;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SalesforceAuthService {
    private final WebClient webClient;

    @Value("${spring.security.oauth2.client.registration.salesforce.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.salesforce.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.provider.salesforce.token-uri}")
    private String tokenUri;

    public SalesforceAuthService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String getAccessToken() {
        String str= webClient.post()
                .uri(tokenUri)
                .bodyValue("grant_type=client_credentials&client_id=" + clientId + "&client_secret=" + clientSecret)
                .retrieve()
                .bodyToMono(SalesforceTokenResponse.class)
                .block()
                .getAccessToken();
	System.out.println("-AccessTkn-"+str);
return str;
    }

    private static class SalesforceTokenResponse {
        private String access_token;

        public String getAccessToken() {
            return access_token;
        }
    }
}


