package com.wipro.supportservice.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.wipro.supportservice.dto.Feedbackdto;
import com.wipro.supportservice.entities.Feedback;
import com.wipro.supportservice.exception.NotFoundException;
import com.wipro.supportservice.repository.Feedbackrepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {Feedbackservice.class})
@ExtendWith(SpringExtension.class)
class FeedbackserviceTest {
    @MockBean
    private Feedbackrepository feedbackrepository;

    @Autowired
    private Feedbackservice feedbackservice;


    @Test
    void testDeleteFeedbackById() {
        Feedback feedback = new Feedback();
        feedback.setDescription("The characteristics of someone or something");
        feedback.setEmail("jane.doe@example.org");
        feedback.setFeedbackid(1);
        feedback.setName("Name");
        Optional<Feedback> ofResult = Optional.of(feedback);
        doNothing().when(feedbackrepository).deleteById((Integer) any());
        when(feedbackrepository.findById((Integer) any())).thenReturn(ofResult);
        feedbackservice.deleteFeedbackById(1);
        verify(feedbackrepository).findById((Integer) any());
        verify(feedbackrepository).deleteById((Integer) any());
        assertTrue(feedbackservice.getAllFeedback().isEmpty());
    }


    @Test
    void testDeleteFeedbackById2() {
        Feedback feedback = new Feedback();
        feedback.setDescription("The characteristics of someone or something");
        feedback.setEmail("jane.doe@example.org");
        feedback.setFeedbackid(1);
        feedback.setName("Name");
        Optional<Feedback> ofResult = Optional.of(feedback);
        doThrow(new NotFoundException("Msg")).when(feedbackrepository).deleteById((Integer) any());
        when(feedbackrepository.findById((Integer) any())).thenReturn(ofResult);
        assertThrows(NotFoundException.class, () -> feedbackservice.deleteFeedbackById(1));
        verify(feedbackrepository).findById((Integer) any());
        verify(feedbackrepository).deleteById((Integer) any());
    }



    @Test
    void testDeleteFeedbackById4() {
        doNothing().when(feedbackrepository).deleteById((Integer) any());
        when(feedbackrepository.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> feedbackservice.deleteFeedbackById(1));
        verify(feedbackrepository).findById((Integer) any());
    }


    @Test
    void testAddFeedback() {
        Feedback feedback = new Feedback();
        feedback.setDescription("The characteristics of someone or something");
        feedback.setEmail("jane.doe@example.org");
        feedback.setFeedbackid(1);
        feedback.setName("Name");
        when(feedbackrepository.save((Feedback) any())).thenReturn(feedback);
        assertSame(feedback, feedbackservice
                .addFeedback(new Feedbackdto("Name", "jane.doe@example.org", "The characteristics of someone or something")));
        verify(feedbackrepository).save((Feedback) any());
    }


    @Test
    void testAddFeedback3() {
        when(feedbackrepository.save((Feedback) any())).thenThrow(new NotFoundException("Msg"));
        assertThrows(NotFoundException.class, () -> feedbackservice
                .addFeedback(new Feedbackdto("Name", "jane.doe@example.org", "The characteristics of someone or something")));
        verify(feedbackrepository).save((Feedback) any());
    }

    @Test
    void testGetAllFeedback() {
        ArrayList<Feedback> feedbackList = new ArrayList<>();
        when(feedbackrepository.findAll()).thenReturn(feedbackList);
        List<Feedback> actualAllFeedback = feedbackservice.getAllFeedback();
        assertSame(feedbackList, actualAllFeedback);
        assertTrue(actualAllFeedback.isEmpty());
        verify(feedbackrepository).findAll();
    }


    @Test
    void testGetAllFeedback2() {
        when(feedbackrepository.findAll()).thenThrow(new NotFoundException("Msg"));
        assertThrows(NotFoundException.class, () -> feedbackservice.getAllFeedback());
        verify(feedbackrepository).findAll();
    }


    @Test
    void testGetFeedbackById() {
        Feedback feedback = new Feedback();
        feedback.setDescription("The characteristics of someone or something");
        feedback.setEmail("jane.doe@example.org");
        feedback.setFeedbackid(1);
        feedback.setName("Name");
        Optional<Feedback> ofResult = Optional.of(feedback);
        when(feedbackrepository.findById((Integer) any())).thenReturn(ofResult);
        assertSame(feedback, feedbackservice.getFeedbackById(1));
        verify(feedbackrepository).findById((Integer) any());
    }

    @Test
    void testGetFeedbackById2() {
        when(feedbackrepository.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> feedbackservice.getFeedbackById(1));
        verify(feedbackrepository).findById((Integer) any());
    }

    @Test
    void testGetFeedbackByName() {
        ArrayList<Feedback> feedbackList = new ArrayList<>();
        when(feedbackrepository.findByName((String) any())).thenReturn(feedbackList);
        List<Feedback> actualFeedbackByName = feedbackservice.getFeedbackByName("Name");
        assertSame(feedbackList, actualFeedbackByName);
        assertTrue(actualFeedbackByName.isEmpty());
        verify(feedbackrepository).findByName((String) any());
    }

    @Test
    void testGetFeedbackByName2() {
        when(feedbackrepository.findByName((String) any())).thenThrow(new NotFoundException("Msg"));
        assertThrows(NotFoundException.class, () -> feedbackservice.getFeedbackByName("Name"));
        verify(feedbackrepository).findByName((String) any());
    }
}

