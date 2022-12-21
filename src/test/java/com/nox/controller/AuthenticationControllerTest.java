package com.nox.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nox.resources.AuthenticationRequest;
import com.nox.resources.AuthenticationResponse;
import com.nox.service.AuthenticationService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    private AuthenticationRequest request;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
        request = new AuthenticationRequest("test12@yahoo.com", "test", "Test 12");
    }

    @Test
    public void testRegisterUser() throws Exception {
        Mockito.when(authenticationService.registerCustomer(request)).thenReturn(AuthenticationResponse.builder().build());
        System.out.println(jsonToString(request));
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/api/auth/register").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonToString(request)).characterEncoding("utf-8"))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print());
    }
 
    // Parsing String format data into JSON format
    private static String jsonToString(final Object obj) throws JsonProcessingException {
        String result;
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            result = jsonContent;
        } catch (JsonProcessingException e) {
            result = "Json processing error";
        }
        return result;
    }
}
