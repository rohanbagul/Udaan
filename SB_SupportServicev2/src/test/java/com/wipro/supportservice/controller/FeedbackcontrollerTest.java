package com.wipro.supportservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.supportservice.dto.Feedbackdto;
import com.wipro.supportservice.entities.Feedback;
import com.wipro.supportservice.service.Feedbackservice;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {Feedbackcontroller.class})
@ExtendWith(SpringExtension.class)
class FeedbackcontrollerTest {
    @Autowired
    private Feedbackcontroller feedbackcontroller;

    @MockBean
    private Feedbackservice feedbackservice;


    @Test
    void testAddfeedback() throws Exception {
        Feedback feedback = new Feedback();
        feedback.setDescription("The characteristics of someone or something");
        feedback.setEmail("jane.doe@example.org");
        feedback.setFeedbackid(1);
        feedback.setName("Name");
        when(feedbackservice.addFeedback((Feedbackdto) any())).thenReturn(feedback);

        Feedbackdto feedbackdto = new Feedbackdto();
        feedbackdto.setDescription("The characteristics of someone or something");
        feedbackdto.setEmail("jane.doe@example.org");
        feedbackdto.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(feedbackdto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/udaan/feedback/addfeedback")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(feedbackcontroller)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"feedbackid\":1,\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"description\":\"The characteristics of"
                                        + " someone or something\"}"));
    }


    @Test
    void testDeletefeedback() throws Exception {
        doNothing().when(feedbackservice).deleteFeedbackById(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/udaan/feedback/deleteFeedback/{feedbackid}", 1);
        MockMvcBuilders.standaloneSetup(feedbackcontroller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }




    @Test
    void testGetFeedbackByUserName() throws Exception {
        when(feedbackservice.getFeedbackByName((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/udaan/feedback/getFeedbackByUserName/{name}", "Name");
        MockMvcBuilders.standaloneSetup(feedbackcontroller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetallfeedback() throws Exception {
        when(feedbackservice.getAllFeedback()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/udaan/feedback/getallfeedback");
        MockMvcBuilders.standaloneSetup(feedbackcontroller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetfeedbackByid() throws Exception {
        Feedback feedback = new Feedback();
        feedback.setDescription("The characteristics of someone or something");
        feedback.setEmail("jane.doe@example.org");
        feedback.setFeedbackid(1);
        feedback.setName("Name");
        when(feedbackservice.getFeedbackById(anyInt())).thenReturn(feedback);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/udaan/feedback/getfeedbackByid/{feedbackid}", 1);
        MockMvcBuilders.standaloneSetup(feedbackcontroller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"feedbackid\":1,\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"description\":\"The characteristics of"
                                        + " someone or something\"}"));
    }
}

