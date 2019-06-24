package com.yuangao.java.codefellowship;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CodefellowshipApplicationTests {

	@InjectMocks
	private ApplicationUserController applicationUserController;
	private MockMvc mockMvc;

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(applicationUserController).build();
	}

	@Test
	public void test_homePage() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().isOk());
	}

	@Test
	public void test_userSignup() throws Exception {
		this.mockMvc.perform(get("/signup")).andExpect(status().isOk());
	}

	@Test
	public void test_userLogin() throws Exception {
		this.mockMvc.perform(get("/login")).andExpect(status().isOk());
	}

}
