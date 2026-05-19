package sfs.adapter.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sfs.ports.api.UserService;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRestController.class)
class UserRestControllerTest {

    @SpringBootApplication
    static class TestConfig {
    }

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void getUserById_ShouldReturnStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/users/123"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllUsers_ShouldReturnStatusOk() throws Exception {
        when(userService.getAllUsers()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk());
    }

    @Test
    void activateUser_ShouldReturnStatusOk() throws Exception {
        mockMvc.perform(put("/api/v1/users/123/activate"))
                .andExpect(status().isOk());
    }

    @Test
    void deactivateUser_ShouldReturnStatusOk() throws Exception {
        mockMvc.perform(put("/api/v1/users/123/deactivate"))
                .andExpect(status().isOk());
    }

    @Test
    void findUserByLoginFragment_ShouldReturnStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/users/search/contains")
                        .param("loginFragment", "admin"))
                .andExpect(status().isOk());
    }

    @Test
    void findUserByExactLogin_ShouldReturnStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/users/search/exact")
                        .param("login", "admin_user"))
                .andExpect(status().isOk());
    }

    @Test
    void updateUser_ShouldReturnStatusOk_WhenRequestIsValid() throws Exception {
        String requestJson = "{\"firstName\":\"Anna\", \"lastName\":\"Nowak\"}";

        mockMvc.perform(put("/api/v1/users/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
    }
}