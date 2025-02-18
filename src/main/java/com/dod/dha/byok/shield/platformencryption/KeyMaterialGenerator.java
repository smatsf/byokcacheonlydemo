package com.dod.dha.byok.shield.platformencryption;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.MGF1ParameterSpec;
//import java.security.spec.OAEPParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import java.security.spec.PSSParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.PSource;
import java.io.*;
import org.springframework.core.io.*;
import org.springframework.context.*;
import java.net.*;
import org.springframework.beans.factory.annotation.Autowired;
public class KeyMaterialGenerator {
	@Autowired
	private ApplicationContext ctx;
	/*
    public static void main(String[] args) throws Exception {
        // Generate a 256-bit AES key
        SecretKey secretKey = generateAESKey();

        // Load RSA public key from a certificate file
        PublicKey publicKey = loadPublicKeyFromCert("byok_certificate.pem");

        // Encrypt the AES key using RSA with SHA-1 OAEP padding
        String encryptedKeySHA1 = encryptKey(secretKey, publicKey, "SHA-1");

        // Encrypt the AES key using RSA with SHA-512 OAEP padding
        String encryptedKeySHA512 = encryptKey(secretKey, publicKey, "SHA-512");

        System.out.println("Encrypted Key (SHA-1 OAEP): " + encryptedKeySHA1);
        System.out.println("Encrypted Key (SHA-512 OAEP): " + encryptedKeySHA512);
    }
*/
    public  SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }

    public  PublicKey loadPublicKeyFromCert(String certPath,String keystorePassword,String alias) throws Exception {
        //FileInputStream fis = new FileInputStream(certPath);
	//Resource res = ctx.getResource("classpath:"+certPath);
	KeyStore keystore = KeyStore.getInstance("JKS");
	URL resource = this.getClass().getClassLoader().getResource(certPath);
	File file = new File(resource.toURI());
	FileInputStream fis = new FileInputStream(file);
	keystore.load(fis,keystorePassword.toCharArray());
	//fis.close();
        //FileInputStream fis =resource.getInputStream();
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        //X509Certificate certificate = (X509Certificate) certFactory.generateCertificate(fis);
        X509Certificate certificate = (X509Certificate) keystore.getCertificate(alias);
	if(certificate!=null)
        return certificate.getPublicKey();
	else
	return null;
    }

    public  String encryptKey(SecretKey secretKey, PublicKey publicKey, String digestAlgorithm) throws Exception {
	Provider bcProvider = new BouncyCastleProvider();
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWith" + digestAlgorithm + "AndMGF1Padding",bcProvider);
        OAEPParameterSpec oaepParams = new OAEPParameterSpec(digestAlgorithm, "MGF1", new MGF1ParameterSpec(digestAlgorithm), PSource.PSpecified.DEFAULT);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey, oaepParams);
        
        byte[] encryptedBytes = cipher.doFinal(secretKey.getEncoded());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}

