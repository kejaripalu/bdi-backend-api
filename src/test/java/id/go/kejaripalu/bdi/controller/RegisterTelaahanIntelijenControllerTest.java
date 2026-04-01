package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.BaseIntegrationTest;
import id.go.kejaripalu.bdi.dto.RegisterTelaahanIntelijenDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("null")
public class RegisterTelaahanIntelijenControllerTest extends BaseIntegrationTest {

    private RegisterTelaahanIntelijenDTO createSampleDTO() {
        return new RegisterTelaahanIntelijenDTO(
                null, new Date(), "No-Telaahan-" + java.util.UUID.randomUUID(), "Pembuat Test", "Perihal Telaahan Test", 
                "Lampiran", "Tindak Lanjut", "Keterangan", "http://file.url"
        );
    }

    @Test
    void testCreateTelaahanSuccess() throws Exception {
        String token = getAuthToken();
        RegisterTelaahanIntelijenDTO request = createSampleDTO();

        mockMvc.perform(post(apiPrefix + "/lahin")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ids").exists())
                .andExpect(jsonPath("$.nomor").exists());
    }

    @Test
    void testCreateTelaahanValidationError() throws Exception {
        String token = getAuthToken();
        // @NotBlank String nomor, @NotBlank String pembuat, @NotBlank String perihal
        RegisterTelaahanIntelijenDTO request = new RegisterTelaahanIntelijenDTO(
                null, new Date(), "", "", "", null, null, null, "http://file.url"
        );

        mockMvc.perform(post(apiPrefix + "/lahin")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateTelaahanSuccess() throws Exception {
        String token = getAuthToken();
        RegisterTelaahanIntelijenDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/lahin")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterTelaahanIntelijenDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterTelaahanIntelijenDTO.class);

        RegisterTelaahanIntelijenDTO updateRequest = new RegisterTelaahanIntelijenDTO(
                created.ids(), created.tanggal(), "Updated-No", created.pembuat(), created.perihal(), 
                created.lampiran(), created.tindakLanjut(), created.keterangan(), created.urlFile()
        );

        mockMvc.perform(put(apiPrefix + "/lahin/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomor").value("Updated-No"));
    }

    @Test
    void testGetTelaahanById() throws Exception {
        String token = getAuthToken();
        RegisterTelaahanIntelijenDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/lahin")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterTelaahanIntelijenDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterTelaahanIntelijenDTO.class);

        mockMvc.perform(get(apiPrefix + "/lahin/" + created.ids() + "/detail")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ids").value(created.ids()));
    }

    @Test
    void testGetAllTelaahan() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/lahin")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("pages", "0")
                        .param("sizes", "10")
                        .param("startDate", "2020-01-01")
                        .param("endDate", "2100-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void testSearchTelaahan() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/lahin/search")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("pages", "0")
                        .param("sizes", "10")
                        .param("value", "Test")
                        .param("startDate", "2020-01-01")
                        .param("endDate", "2100-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void testDeleteTelaahan() throws Exception {
        String token = getAuthToken();
        RegisterTelaahanIntelijenDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/lahin")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterTelaahanIntelijenDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterTelaahanIntelijenDTO.class);

        mockMvc.perform(delete(apiPrefix + "/lahin/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isAccepted());
    }
}
