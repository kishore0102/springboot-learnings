package com.fresco.beverage;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@TestMethodOrder(Alphanumeric.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class BeverageOrdersApplicationTests {
	@Autowired
	MockMvc mockMvc;

	static List<String> beverages = new ArrayList<String>();
	static List<String> names = new ArrayList<String>();
	static List<Double> prices = new ArrayList<Double>();
	static List<Double> time = new ArrayList<Double>();
	static List<Integer> rands = new ArrayList<Integer>();

	void initData() {
		beverages.add("Cold coffee");
		beverages.add("Plain coffee");
		beverages.add("Cappuccino");
		beverages.add("Espresso");
		beverages.add("Latte");
		beverages.add("Caffe mocha");
		Random rd = new Random();
		for (int i = 0; i < 6; i++) {
			names.add(generateString(false));
			prices.add(Math.floor((rd.nextDouble() * 5 + 5) * 100) / 100);
			time.add(Math.floor(rd.nextDouble() * 5 * 100) / 100);
		}
	}

	String generateString(boolean flag) {
		Random random = new Random();
		String candidateChars = flag ? "1234567890" : "ABCDEFGHIJKLMNOPQRST1234567890";
		StringBuilder randStr = new StringBuilder();
		while (randStr.length() < (flag ? 2 : 10))
			randStr.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
		return randStr.toString();
	}

	@Test
	void test1_initPrices() {
		try {
			initData();
			JSONArray arr = new JSONArray();
			for (int i = 0; i < 6; i++) {
				JSONObject json = new JSONObject();
				json.put("beverage", beverages.get(i));
				json.put("price", prices.get(i));
				json.put("timeToMake", time.get(i));
				arr.put(json);
			}
			System.out.println("---------------------test1-------------------");
			System.out.println(arr.toString());
			System.out.println("---------------------------------------------");
			mockMvc.perform(post("/load-prices").contentType(MediaType.APPLICATION_JSON).content(arr.toString())).andExpect(status().is(200));
		} catch (Exception e) {
			e.printStackTrace();
			assert (false);
		}
	}

	@Test
	void test2_makeOrders() {
		try {
			JSONArray arr = new JSONArray();
			Double total = 0.0;
			Double maxTime = 0.0;
			for (int i = 0; i < 6; i++) {
				JSONObject json = new JSONObject();
				json.put("orderFor", names.get(i));
				// int r = new Random().nextInt(6);
				int r = i;
				json.put("beverage", r);
				total += prices.get(r);
				maxTime = maxTime < time.get(r) ? time.get(r) : maxTime;
				arr.put(json);
			}
			System.out.println("---------------------test1-------------------");
			System.out.println(arr.toString());
			System.out.println("total price - " + total);
			System.out.println("max time - " + maxTime);
			System.out.println("---------------------------------------------");
			mockMvc.perform(post("/make-order").contentType(MediaType.APPLICATION_JSON).content(arr.toString()))
					.andExpect(jsonPath("$.totalPrice", is(total))).andExpect(jsonPath("$.estimatedTime", is(maxTime)));
		} catch (Exception e) {
			e.printStackTrace();
			assert (false);
		}
	}

	@Test
	void test3_getOrders() {
		try {
			String res = mockMvc.perform(get("/get-orders")).andReturn().getResponse().getContentAsString();
			JSONArray arr = new JSONArray(res);
			System.out.println("res length : " + arr.length());
			assertEquals(arr.length(), 1);
			arr = arr.getJSONArray(0);
			System.out.println(arr);
			System.out.println("arr length : " + arr.length());
			assertEquals(arr.length(), 6);
			for(int i = 0; i < 6; i++){
				System.out.println(arr.getJSONObject(i).toString());
				System.out.println(names.get(i));
				System.out.println(beverages.get(i));
				assertEquals(arr.getJSONObject(i).get("orderFor"), names.get(i));
				assertEquals(arr.getJSONObject(i).get("beverage"), beverages.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			assert (false);
		}
	}
	@Test
	void test4_clearOrder() {
		try {
			mockMvc.perform(get("/clear-order/1")).andExpect(status().is(200));
		} catch (Exception e) {
			e.printStackTrace();
			assert (false);
		}
	}
	@Test
	void test5_getOrdersAgain() {
		try {
			String res = mockMvc.perform(get("/get-orders")).andReturn().getResponse().getContentAsString();
			JSONArray arr = new JSONArray(res);
			assertEquals(arr.length(), 0);
		} catch (Exception e) {
			e.printStackTrace();
			assert (false);
		}
	}
}
