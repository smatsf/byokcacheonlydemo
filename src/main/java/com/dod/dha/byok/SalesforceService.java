package com.dod.dha.byok;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SalesforceService {
    private final WebClient webClient;
    private final SalesforceAuthService authService;

    @Value("${salesforce.base-url}")
    private String salesforceBaseUrl;

    public SalesforceService(WebClient.Builder webClientBuilder, SalesforceAuthService authService) {
        this.webClient = webClientBuilder.build();
        this.authService = authService;
    }

    @Cacheable("salesforceData")
    public String fetchSalesforceData(String endpoint) {
        String accessToken = authService.getAccessToken();

        return webClient.get()
                .uri(salesforceBaseUrl + endpoint)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}


