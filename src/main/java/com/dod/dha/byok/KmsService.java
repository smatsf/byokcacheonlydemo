package com.dod.dha.byok;
import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.amazonaws.services.kms.model.EncryptRequest;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;

@Service
public class KmsService {
    private final AWSKMS kmsClient = AWSKMSClientBuilder.defaultClient();

    public byte[] encrypt(String keyId, byte[] plaintext) {
        EncryptRequest request = new EncryptRequest()
                .withKeyId(keyId)
                .withPlaintext(ByteBuffer.wrap(plaintext));
        return kmsClient.encrypt(request).getCiphertextBlob().array();
    }

    public byte[] decrypt(byte[] ciphertext) {
        DecryptRequest request = new DecryptRequest()
                .withCiphertextBlob(ByteBuffer.wrap(ciphertext));
        return kmsClient.decrypt(request).getPlaintext().array();
    }
    public String getKey(String keyId) {
	return "dummykey";
	}
    public String[] getAllKeys() {
	String[] keys = {"key1","key2","key3"};
	return keys;
	}
}

