package com.dod.dha.byok;
import java.security.MessageDigest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FipsTest {
	@Test
    public  void Test1() throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest("test".getBytes("UTF-8"));
        System.out.println(javax.xml.bind.DatatypeConverter.printHexBinary(hash));
    }
}

