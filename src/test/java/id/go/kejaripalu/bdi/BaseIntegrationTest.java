package id.go.kejaripalu.bdi;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.go.kejaripalu.bdi.security.dto.LoginRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
@SuppressWarnings({"null", "unchecked"})
public abstract class BaseIntegrationTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected id.go.kejaripalu.bdi.security.repository.AppUserRepository userRepository;

    @Autowired
    protected org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @Value("${app.api-url}")
    protected String apiPrefix;

    @org.junit.jupiter.api.BeforeEach
    void setUpUser() {
        userRepository.findByUsername("testuser").ifPresentOrElse(
            user -> {
                user.setPassword(passwordEncoder.encode("password"));
                userRepository.save(user);
            },
            () -> {
                id.go.kejaripalu.bdi.security.domain.AppUser user = new id.go.kejaripalu.bdi.security.domain.AppUser();
                user.setUsername("testuser");
                user.setPassword(passwordEncoder.encode("password"));
                user.setFullName("Test User");
                user.setSecureID(java.util.UUID.randomUUID().toString());
                userRepository.save(user);
            }
        );
    }

    protected String getAuthToken() throws Exception {
        LoginRequestDTO loginRequest = new LoginRequestDTO();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("password");

        MvcResult result = mockMvc.perform(post(apiPrefix + "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        Map<String, String> tokenMap = objectMapper.readValue(response, new com.fasterxml.jackson.core.type.TypeReference<Map<String, String>>() {});
        return tokenMap.get("token");
    }
}
