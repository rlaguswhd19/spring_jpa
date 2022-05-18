package com.hj.spring_jpa.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest
@AutoConfigureDataJpa
@DisplayName("계층형 구조 Test")
public class JunitTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    static void setUp() {
        System.setProperty("runtimeEnv", "local");
    }

    public ResultActions getPost() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get("/posts"));
    }

    @Nested
    @DisplayName("PostController 클래스 테스트")
    class PostControllerTest {

        @Nested
        @DisplayName("getPost 메소드는")
        class DescribeGetPost {

            @Nested
            @DisplayName("pageable 파라미터가 있다면")
            class ContextWithPageable {

                @Test
                @DisplayName("PagedModel을 리턴한다")
                public void ItReturnPagedModel() {
                    System.out.println("PagedModel");
                }
            }

            @Nested
            @DisplayName("pageable 파라미터가 없다면")
            class ContextWhenNotPageable {

                @Test
                @DisplayName("400으로 응답한다")
                public void ItResponse400() {
                    System.out.println("400 Response");
                }
            }
        }
    }
}
