package org.example.expert.logging;

import org.example.expert.config.WebConfig;
import org.example.expert.domain.comment.controller.CommentAdminController;
import org.example.expert.domain.comment.service.CommentAdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CommentAdminController.class)
@Import(WebConfig.class)
public class CommentAdminControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CommentAdminService commentAdminService;

    @Test
    void deleteComment_로깅_및_유저_어드민_확인() throws Exception {
        long commentId = 1L;

        mockMvc.perform(delete("/admin/comments/{commentId}", commentId))
                .andExpect(status().isOk());
    }
}
