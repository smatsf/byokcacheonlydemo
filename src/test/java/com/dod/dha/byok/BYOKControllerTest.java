package com.dod.byok;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.*;
import org.springframework.test.context.junit4.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.dod.dha.byok.encryption.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes=SalesforceBYOKDownloader.class)
@AutoConfigureMockMvc
public class BYOKControllerTest{
	@Autowired
	private MockMvc mockMVC;
	@Test
	public void testToken() throws Exception{
		mockMVC.perform(get("/api/getToken").secure( true ))
		.andExpect(status().isFound());
		//.andExpect(content().string(""));
	}
}
