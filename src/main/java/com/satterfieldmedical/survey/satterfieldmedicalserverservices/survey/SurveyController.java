package com.satterfieldmedical.survey.satterfieldmedicalserverservices.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SurveyController {

    @Autowired
    SurveyDataService surveyDataService;

    public SurveyController(SurveyDataService surveyDataService) {
        this.surveyDataService = surveyDataService;
    }

    @GetMapping(value = {"/getallsurveys"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Map<String, Object>>> getAllSurveys() {
        Map<String, List<Map<String, Object>>> surveys = this.surveyDataService.getAllSurveys();
        if(surveys == null) {
            surveys = new HashMap<>();
        }
        return surveys;
    }
}
