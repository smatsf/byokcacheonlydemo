package com.dod.dha.byok;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class KeyBridgeController {
    private final SalesforceService salesforceService;
    private final KmsService kmsService;

    public KeyBridgeController(SalesforceService salesforceService, KmsService kmsService) {
        this.salesforceService = salesforceService;
        this.kmsService = kmsService;
    }

    @GetMapping("/salesforce/{endpoint}")
    public String getSalesforceData(@PathVariable String endpoint) {
        return salesforceService.fetchSalesforceData(endpoint);
    }

    @GetMapping("/salesforce/keys")
    public String[] getAllKeys() {
        return kmsService.getAllKeys();
    }
    @GetMapping("/salesforce/key/{keyId}")
    public String getKey(@PathVariable String keyId) {
        return kmsService.getKey(keyId);
    }
    @PostMapping("/kms/encrypt")
    public byte[] encryptData(@RequestParam String keyId, @RequestBody String data) {
        return kmsService.encrypt(keyId, data.getBytes());
    }

    @PostMapping("/kms/decrypt")
    public String decryptData(@RequestBody byte[] ciphertext) {
        return new String(kmsService.decrypt(ciphertext));
    }
}

