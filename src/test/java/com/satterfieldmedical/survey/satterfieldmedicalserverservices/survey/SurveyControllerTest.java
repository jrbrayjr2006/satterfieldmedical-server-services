package com.satterfieldmedical.survey.satterfieldmedicalserverservices.survey;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SurveyControllerTest {

    @Mock
    SurveyDataService mockSurveyDataService;

    @InjectMocks
    SurveyController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockSurveyDataService = mock(SurveyDataService.class);
        this.controller = new SurveyController(mockSurveyDataService);
    }

    @Test
    public void shouldBeAbleToUseGetAllSurveysEndpoint() throws Exception {
        //Given
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        //Then
        this.mockMvc.perform(get("/getallsurveys"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUseSurveyDataServiceToGetSurveys() throws Exception {
        //Given
        this.controller = new SurveyController(mockSurveyDataService);

        //When
        this.controller.getAllSurveys();

        //Then
        verify(mockSurveyDataService).getAllSurveys();
    }

    @Test
    public void shouldRetrieveAllSurveys() {
        //Given
        Map<String, List<Map<String, Object>>> surveys = new HashMap<>();

        //When
        when(mockSurveyDataService.getAllSurveys()).thenReturn(surveys);
        Map<String, List<Map<String, Object>>> actual = controller.getAllSurveys();

        //Then
        assertNotNull(actual);
    }
}