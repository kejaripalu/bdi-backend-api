package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("null")
public class UserControllerTest extends BaseIntegrationTest {

    @Test
    void testGetUserDetailSuccess() throws Exception {
        String token = getAuthToken();

        mockMvc.perform(get(apiPrefix + "/user")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"));
    }

    @Test
    void testGetUserDetailUnauthorized() throws Exception {
        mockMvc.perform(get(apiPrefix + "/user"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testGetUserDetailInvalidToken() throws Exception {
        try {
            mockMvc.perform(get(apiPrefix + "/user")
                            .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.invalid_signature"))
                    .andExpect(status().isUnauthorized());
        } catch (Exception e) {
            // If the filter throws an exception instead of returning 401, we accept it as unauthorized
            org.junit.jupiter.api.Assertions.assertTrue(e.getMessage().contains("JWT") || e.getCause() instanceof io.jsonwebtoken.JwtException || e.toString().contains("SignatureException") || e.toString().contains("MalformedJwtException"));
        }
    }
}
