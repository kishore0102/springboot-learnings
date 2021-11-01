package edu.deadshot.springbootwiremock.services;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations="classpath:test.properties")
class SearchServiceTests {

    @Autowired
    private MockMvc mockMvc;

    private WireMockServer wireMockServer;

    @BeforeEach
    void setup() {
        wireMockServer = new WireMockServer(options().bindAddress("localhost").port(8083));
        wireMockServer.stubFor(get(urlPathEqualTo("/getAccounts"))
                .willReturn(okJson("{\n" +
                        "\"accounts\": [\n" +
                        "{\n" +
                        "\"accountNum\": \"11111\",\n" +
                        "\"phoneNum\": \"10001\",\n" +
                        "\"accountName\": \"Account # 1\"\n" +
                        "},\n" +
                        "{\n" +
                        "\"accountNum\": \"22222\",\n" +
                        "\"phoneNum\": \"20001\",\n" +
                        "\"accountName\": \"Account # 2\"\n" +
                        "}\n" +
                        "]\n" +
                        "}")));

        wireMockServer.start();
        System.out.println("wiremock started = " + wireMockServer.isRunning() + " at " +
                wireMockServer.getOptions().bindAddress() + " " + wireMockServer.getOptions().portNumber());
    }

    @Test
    void testCase1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/quick/test"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

}
