package com.dpiliotis.ride.hailing.app;

import com.dpiliotis.ride.hailing.app.dto.AnswerDto;
import com.dpiliotis.ride.hailing.app.dto.ApiResponse;
import com.dpiliotis.ride.hailing.app.dto.ChoiceDto;
import com.dpiliotis.ride.hailing.app.dto.QuestionDto;
import com.dpiliotis.ride.hailing.app.dto.SurveyDto;
import com.dpiliotis.ride.hailing.app.dto.SurveyResponseDto;
import com.dpiliotis.ride.hailing.app.dto.UserDto;
import com.dpiliotis.ride.hailing.app.entity.QuestionType;
import com.dpiliotis.ride.hailing.app.service.SurveyResponseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test", "dev"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SurveyIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SurveyResponseService surveyResponseService;

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void testGetUser() {

        // given
        String url = getBaseUrl() + "user/1";

        // when
        ResponseEntity<ApiResponse<UserDto>> response =
                restTemplate.exchange(url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<ApiResponse<UserDto>>() { });

        // then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Tony Montana", response.getBody().getData().getName());
    }

    @Test
    public void testGetSurvey() {

        // given
        String url = getBaseUrl() + "survey/1";

        // when
        ResponseEntity<ApiResponse<SurveyDto>> response =
                restTemplate.exchange(url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<ApiResponse<SurveyDto>>() { });

        // then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("First Survey", response.getBody().getData().getName());
    }

    @Test
    public void testPostSurveyResponse() {

        // given
        String url = getBaseUrl() + "user/1/survey/1/response";

        SurveyDto surveyDto = buildSurveyDto();
        QuestionDto questionDto = surveyDto.getQuestions().get(0);
        ChoiceDto choiceDto = questionDto.getChoices().get(0);

        AnswerDto answerDto = new AnswerDto();
        answerDto.setQuestion(questionDto);
        answerDto.setChoices(Collections.singletonList(choiceDto));

        SurveyResponseDto surveyResponseDto = new SurveyResponseDto();
        surveyResponseDto.setAnswers(Collections.singletonList(answerDto));

        HttpEntity<SurveyResponseDto> entity = new HttpEntity<>(surveyResponseDto);

        // when
        ResponseEntity<ApiResponse<SurveyResponseDto>> response =
                restTemplate.exchange(url,
                        HttpMethod.POST,
                        entity,
                        new ParameterizedTypeReference<ApiResponse<SurveyResponseDto>>() { });

        // then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        SurveyResponseDto result = response.getBody().getData();
        Assertions.assertEquals(1, result.getAnswers().size());
        Assertions.assertNotNull(result.getId());
    }

    private SurveyDto buildSurveyDto() {

        ChoiceDto choice1 = new ChoiceDto();
        choice1.setId(1L);
        choice1.setDescription("choice A");

        ChoiceDto choice2 = new ChoiceDto();
        choice2.setId(2L);
        choice2.setDescription("choice B");

        ChoiceDto choice3 = new ChoiceDto();
        choice3.setId(3L);
        choice3.setDescription("choice C");

        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(1L);
        questionDto.setChoices(Arrays.asList(choice1, choice2, choice3));
        questionDto.setType(QuestionType.MULTIPLE_CHOICE);
        questionDto.setDescription("Question ONE");

        SurveyDto surveyDto = new SurveyDto();
        surveyDto.setId(1L);
        surveyDto.setQuestions(Collections.singletonList(questionDto));
        surveyDto.setName("First Survey");

        return surveyDto;
    }
}
