package com.elixr.user.controllers;

import com.elixr.user.events.user.RegisterNewUserEvent;
import com.elixr.user.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@AutoConfigureMockMvc
class UserManagementControllerTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    UserManagementController subject;
    @Autowired
    private MockMvc mvc;

    @Test
    void registerUser() throws Exception {
        final RegisterNewUserEvent registerNewUserEvent = RegisterNewUserEvent.builder()
                .name("Anirudh Aggarwal")
                .username("anirudh.aggarwal")
                .password("password").build();

        MvcResult result = this.mvc.perform(
                post("/register").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerNewUserEvent))
        ).andExpect(status().isOk()).andDo(print())
                .andReturn();
        User projection = objectMapper.readValue(result.getResponse().getContentAsString(), User.class);

        assertAll(
                () -> assertNotNull(result.getResponse().getContentAsString()),
                () -> assertNotNull(projection),
                () -> assertEquals(registerNewUserEvent.getUsername(), projection.getUsername()),
                () -> assertEquals(registerNewUserEvent.getName(), projection.getName()),
                () -> assertNotNull(projection.getId()),
                () -> assertNull(projection.getPassword())
        );
    }

    @Test
    void getSubscribers() {
        assertTrue(true);
    }
}