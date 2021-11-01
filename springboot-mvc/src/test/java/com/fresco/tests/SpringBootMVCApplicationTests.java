package com.fresco.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.fresco.springboot.SpringBootMVCApplication;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlUnorderedList;

@TestMethodOrder(Alphanumeric.class)
@SpringBootTest(classes = { SpringBootMVCApplication.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class SpringBootMVCApplicationTests {

	@LocalServerPort
	int port;
	final static WebClient webClient = new WebClient();
	static HtmlPage page;
	static String str1, str2;
	public String generateString() {
		Random random = new Random();
		String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		candidateChars.charAt(random.nextInt(candidateChars.length()));
		String randStr = "";
		while (randStr.length() < 25)
			randStr += candidateChars.charAt(random.nextInt(candidateChars.length()));
		return randStr;
	}

	@Test
	public void test1_checkingInitialPage() {
		try {
			page = webClient.getPage("http://localhost:" + port + "/");
			HtmlParagraph p = (HtmlParagraph) page.getElementsByTagName("p").get(0);
			assertEquals(p.asText(), "");
			List<Object> textInputs = page.getByXPath("//input[@type=\"text\"]");
			assertEquals(textInputs.size(), 2);
			List<Object> numberInputs = page.getByXPath("//input[@type=\"number\"]");
			assertEquals(numberInputs.size(), 2);
			List<Object> submitInputs = page.getByXPath("//input[@type=\"submit\"]");
			assertEquals(submitInputs.size(), 3);
			HtmlUnorderedList list = (HtmlUnorderedList) page.getElementsByTagName("ul").get(0);
			assertEquals(list.asText(), "");
		} catch (Exception e) {
			e.printStackTrace();
			assert (false);
		}
	}
	
	@Test
	public void test2_checkingErrorMsgs() {
		try {
			System.out.println("-------------------------------test 2--------------------------------------------");
			HtmlSubmitInput submitInput = (HtmlSubmitInput) page.getByXPath("//input[@type=\"submit\"]").get(0);
			page = submitInput.click();
			HtmlParagraph p = (HtmlParagraph) page.getElementsByTagName("p").get(0);
			System.out.println("-------------------------------assert 1--------------------------------------------");
			assertEquals(p.asText().toLowerCase(), "please enter some input");
			submitInput = (HtmlSubmitInput) page.getByXPath("//input[@type=\"submit\"]").get(2);
			page = submitInput.click();
			p = (HtmlParagraph) page.getElementsByTagName("p").get(0);
			System.out.println("-------------------------------assert 2--------------------------------------------");
			assertEquals(p.asText().toLowerCase(), "please enter some input");
			HtmlInput numberInput = (HtmlInput) page.getByXPath("//input[@type=\"number\"]").get(1);
			numberInput.setValueAttribute("4");
			submitInput = (HtmlSubmitInput) page.getByXPath("//input[@type=\"submit\"]").get(2);
			page = submitInput.click();
			p = (HtmlParagraph) page.getElementsByTagName("p").get(0);
			System.out.println("-------------------------------assert 3--------------------------------------------");
			assertEquals(p.asText().toLowerCase(), "index not found");
		} catch (Exception e) {
			e.printStackTrace();
			assert (false);
		}
	}
	
	@Test
	public void test3_insertingData() {
		try {
			str1 = generateString();
			str2 = generateString();
			HtmlInput inp = (HtmlInput) page.getByXPath("//input[@type=\"text\"]").get(0);
			inp.setValueAttribute(str1);
			HtmlSubmitInput submitInput = (HtmlSubmitInput) page.getByXPath("//input[@type=\"submit\"]").get(0);
			page = submitInput.click();
			HtmlParagraph p = (HtmlParagraph) page.getElementsByTagName("p").get(0);
			assertEquals(p.asText(), "");
			inp = (HtmlInput) page.getByXPath("//input[@type=\"text\"]").get(0);
			inp.setValueAttribute(str2);
			submitInput = (HtmlSubmitInput) page.getByXPath("//input[@type=\"submit\"]").get(0);
			page = submitInput.click();
			HtmlUnorderedList list = (HtmlUnorderedList) page.getElementsByTagName("ul").get(0);
			assertEquals(list.asText(), str1 + "\n" + str2);
		} catch (Exception e) {
			e.printStackTrace();
			assert (false);
		}
	}
	@Test
	public void test4_updatingData() {
		try {
			System.out.println("--------------------test 4------------------------------");
			HtmlUnorderedList list1 = (HtmlUnorderedList) page.getElementsByTagName("ul").get(0);
			System.out.println(list1.asText());
			System.out.println("------------------------------");
			HtmlInput inp = (HtmlInput) page.getByXPath("//input[@type=\"number\"]").get(0);
			inp.setValueAttribute("1");
			inp = (HtmlInput) page.getByXPath("//input[@type=\"text\"]").get(1);
			inp.setValueAttribute(str1);
			HtmlSubmitInput submitInput = (HtmlSubmitInput) page.getByXPath("//input[@type=\"submit\"]").get(1);
			page = submitInput.click();
			HtmlUnorderedList list = (HtmlUnorderedList) page.getElementsByTagName("ul").get(0);
			System.out.println(list.asText());
			System.out.println("------------------------------");
			System.out.println(str1 + "\n" + str1);	
			assertEquals(list.asText(), str1 + "\n" + str1);
		} catch (Exception e) {
			e.printStackTrace();
			assert (false);
		}
	}
	@Test
	public void test5_deletingData() {
		try {
			HtmlInput inp = (HtmlInput) page.getByXPath("//input[@type=\"number\"]").get(1);
			inp.setValueAttribute("1");
			HtmlSubmitInput submitInput = (HtmlSubmitInput) page.getByXPath("//input[@type=\"submit\"]").get(2);
			page = submitInput.click();
			HtmlUnorderedList list = (HtmlUnorderedList) page.getElementsByTagName("ul").get(0);
			System.out.println("--------------------test 5------------------------------");
			System.out.println(list.asText());
			System.out.println(str1);
			assertEquals(list.asText(), str1);
		} catch (Exception e) {
			e.printStackTrace();
			assert (false);
		}
	}

}
