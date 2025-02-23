package com.dod.dha.byok;
import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.amazonaws.services.kms.model.EncryptRequest;
import org.springframework.stereotype.Service;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.kms.model.DescribeKeyRequest;
//import com.amazonaws.services.kms.model.DescribeKeyResponse;
import com.amazonaws.services.kms.model.DescribeKeyResult;
//import com.amazonaws.services.kms.model.KMSException;
import java.nio.ByteBuffer;

@Service
public class KmsService {
     // Replace with your AWS credentials (or use IAM roles if running on AWS)
        String accessKey = "your-access-key";
        String secretKey = "your-secret-key";
        String region = "us-west-2"; // Replace with your region
        String keyId = "arn:aws:kms:us-west-2:123456789012:key/abc123"; // Replace with your key ID or ARN

        // Create AWS credentials
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

        // Build the KMS client
        AWSKMS kmsClient = AWSKMSClientBuilder.standard()
            .withRegion(region)
            .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
            .build();
    //private final AWSKMS kmsClient = AWSKMSClientBuilder.defaultClient();

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
            String keyIdResult = "";
/* try {
            // Create the DescribeKeyRequest with the KeyId
            DescribeKeyRequest describeKeyRequest = new DescribeKeyRequest().withKeyId(keyId);

            // Call KMS to describe the key
            DescribeKeyResult describeKeyResult = kmsClient.describeKey(describeKeyRequest);

            // Retrieve the key metadata
            keyIdResult = describeKeyResult.getKeyMetadata().getKeyId();
            String keyArn = describeKeyResult.getKeyMetadata().getKeyArn();
            String keyState = describeKeyResult.getKeyMetadata().getKeyState();

            // Output the key metadata
            System.out.println("Key ID: " + keyIdResult);
            System.out.println("Key ARN: " + keyArn);
            System.out.println("Key State: " + keyState);

        } catch (KMSException e) {
            System.out.println("Error occurred while describing the key: " + e.getMessage());
        }
*/	return keyIdResult;
	}
    public String[] getAllKeys() {
	String[] keys = {"key1","key2","key3"};
	return keys;
	}
}

