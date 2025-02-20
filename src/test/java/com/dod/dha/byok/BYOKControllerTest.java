package com.dod.dha.byok;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.SpringBootTest;
import com.dod.dha.byok.encryption.*;
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
