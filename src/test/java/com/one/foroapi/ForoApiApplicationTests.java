package com.one.foroapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("all")
class ForoApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;


	@Test
	@WithMockUser(username = "test", password = "password123")
	public void registerUser() throws Exception {

		String json = """
                {
                "firstName": "test",
                "lastName": "test",
                "username": "test1",
                "email": "test1@gmail.com",
                "password": "password123"
                }
                """;
		MvcResult result = mockMvc.perform(post("/api/users/create")
						.content(json)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andReturn();

		String responseContent = result.getResponse().getContentAsString();

		System.out.println(responseContent);
	}


	@Test
	@WithMockUser(username = "test", password = "password123")
	public void loginUser() throws Exception {
		String json = """
        {
            "username": "test",
            "password": "password123"
        }
        """;
		MvcResult result = mockMvc.perform(post("/api/login")
						.content(json)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		String responseContent = result.getResponse().getContentAsString();

		System.out.println(responseContent);
	}


	@Test
	@WithMockUser(username = "test", password = "password123")
	public void createCategories() throws Exception {

		String json = """
		{
			"name": "test",
			"description": "test"
		}
		""";

		MvcResult result = mockMvc.perform(post("/api/categories/create")
						.content(json)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andReturn();


		String responseContent = result.getResponse().getContentAsString();

		System.out.println(responseContent);
	}

	@Test
	@WithMockUser(username = "test", password = "password123")
	public void createTopic() throws Exception {
		String json = """
		{
			"title": "test",
			"description": "test",
			"userId": 1,
			"categoryId": 1
		}
		""";
		MvcResult result = mockMvc.perform(post("/api/topics/create")
						.content(json)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andReturn();

		String responseContent = result.getResponse().getContentAsString();

		System.out.println(responseContent);
	}

	@Test
	@WithMockUser(username = "test", password = "password123")
	public void createPost() throws Exception {
		String json = """
		{
			"title": "test",
			"content": "test",
			"userId": 1,
			"topicId": 1
		}
		""";
		MvcResult result = mockMvc.perform(post("/api/posts/create")
						.content(json)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andReturn();

		String responseContent = result.getResponse().getContentAsString();

		System.out.println(responseContent);
	}

}
