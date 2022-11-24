package shop.mtcoding.bank.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.bank.dto.UserReqDto.JoinReqDto;

@ActiveProfiles("test")
@AutoConfigureMockMvc // Mock환경에 MVC 패턴을 지원함
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class UserApiControllerTest {

    private static final String APPLICATION_JSON_UTF8 = "application/json; charset=utf-8";
    // private static final String APPLICATION_FORM_URLENCODED =
    // "application/x-www-form-urlencoded; charset=utf-8";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Test
    public void join_test() throws Exception {
        // given
        JoinReqDto joinReqDto = new JoinReqDto();
        joinReqDto.setUsername("ssar");
        joinReqDto.setPassword("1234");
        joinReqDto.setEmail("ssar@nate.com");
        String requestBody = om.writeValueAsString(joinReqDto);
        // when
        ResultActions resultActions = mvc
                .perform(post("/join")
                        .content(requestBody)
                        .contentType(APPLICATION_JSON_UTF8));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("디버그 : " + responseBody);
        // then 검증은 2가지는 하자(상태코드와 핵심데이터)
        resultActions.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.data.username").value("ssar"));
    }
}
