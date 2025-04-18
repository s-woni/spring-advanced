package org.example.expert.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.expert.config.AspectConfig;
import org.example.expert.config.LoggingAOP;
import org.example.expert.config.WebConfig;
import org.example.expert.domain.comment.controller.CommentAdminController;
import org.example.expert.domain.comment.service.CommentAdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CommentAdminController.class)
@Import({WebConfig.class, LoggingAOP.class, AspectConfig.class})
public class CommentAdminControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CommentAdminService commentAdminService;

    @Test
    void deleteComment_로깅_및_유저_어드민_확인() throws Exception {
        long commentId = 1L;

        // mockMvc.perform(delete("/admin/comments/{commentId}", commentId))
        //         .andExpect(status().isOk());

        doNothing().when(commentAdminService).deleteComment(commentId);

        MockHttpServletRequestBuilder requestBuilder = delete("/admin/comments/{commentId}", commentId)
                .contentType(MediaType.APPLICATION_JSON);

        // when & then
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
}
