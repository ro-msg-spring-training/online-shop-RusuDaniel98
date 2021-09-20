package ro.msg.learning.shop;

import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ro.msg.learning.shop.controller.DummyDatabaseRecordsController;
import ro.msg.learning.shop.controller.OrderCreationController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@Log
class ShopApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private OrderCreationController orderCreationController;

	@Autowired
	DummyDatabaseRecordsController dummyController;

	@BeforeEach
	void initOrderCreationAndDummyControllers() {
		orderCreationController = new OrderCreationController();
		dummyController = new DummyDatabaseRecordsController();
	}

	@Test
	void contextLoads() {
	}

	// checks if a correct order can be placed.
	@Test
	void goodOrderCreationIsSuccessful() {
		try {
			// insert dummy values in the database.
			mvc.perform(post("/dummy")
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());

			// POST HTTP request to create the Order.
			MvcResult orderCreationRequestResult = mvc.perform(post("/order")
							.contentType(MediaType.APPLICATION_JSON)
					.content(
							"{\n" +
									"    \"timestamp\" : \"2021-02-05T12:59:11.332\",\n" +
									"    \"addressCountry\" : \"Romania\",\n" +
									"    \"addressCity\" : \"Cluj-Napoca\",\n" +
									"    \"addressCounty\" : \"Cluj\",\n" +
									"    \"addressStreetAddress\" : \"Observatorului 34\",\n" +
									"    \"orderDetails\" : [\n" +
									"        {\n" +
									"            \"product\" :\n" +
									"            {\n" +
									"                \"id\" : 3\n" +
									"            },\n" +
									"            \"quantity\" : 3\n" +
									"        },\n" +
									"        {\n" +
									"            \"product\" :\n" +
									"            {\n" +
									"                \"id\" : 8\n" +
									"            },\n" +
									"            \"quantity\" : 3\n" +
									"        }\n" +
									"    ]\n" +
									"}"
					))
					.andExpect(status().isOk())
					.andReturn();

			String content = orderCreationRequestResult.getResponse().getContentAsString();
			assert (content.equals("Order created."));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// checks if a wrong order (missing stock) can be placed.
	@Test
	void badOrderCreationIsUnsuccessful() {
		try {
			// clear the database
			mvc.perform(delete("/dummy"))
					.andExpect(status().isOk());
			// insert dummy values in the database.
			mvc.perform(post("/dummy")
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());

			// POST HTTP request to create the Order -- but with too big quantity values
			MvcResult orderCreationRequestResult = mvc.perform(post("/order")
							.contentType(MediaType.APPLICATION_JSON)
							.content(
									"{\n" +
											"    \"timestamp\" : \"2021-02-05T12:59:11.332\",\n" +
											"    \"addressCountry\" : \"Romania\",\n" +
											"    \"addressCity\" : \"Cluj-Napoca\",\n" +
											"    \"addressCounty\" : \"Cluj\",\n" +
											"    \"addressStreetAddress\" : \"Observatorului 34\",\n" +
											"    \"orderDetails\" : [\n" +
											"        {\n" +
											"            \"product\" :\n" +
											"            {\n" +
											"                \"id\" : 3\n" +
											"            },\n" +
											"            \"quantity\" : 3000\n" +
											"        },\n" +
											"        {\n" +
											"            \"product\" :\n" +
											"            {\n" +
											"                \"id\" : 8\n" +
											"            },\n" +
											"            \"quantity\" : 9876\n" +
											"        }\n" +
											"    ]\n" +
											"}"
							))
					.andExpect(status().isOk())
					.andReturn();

			String content = orderCreationRequestResult.getResponse().getContentAsString();
			assert (content.equals("Order failed."));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
