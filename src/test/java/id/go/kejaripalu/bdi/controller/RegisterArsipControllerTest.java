package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.BaseIntegrationTest;
import id.go.kejaripalu.bdi.dto.RegisterArsipDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("null")
public class RegisterArsipControllerTest extends BaseIntegrationTest {

    private RegisterArsipDTO createSampleDTO() {
        return new RegisterArsipDTO(
                null, new Date(), new Date(), "Diterima Dari Test", "No-Arsip-" + java.util.UUID.randomUUID(), new Date(),
                "Perihal Arsip Test", "Lampiran", "KODE-" + java.util.UUID.randomUUID(), "Keterangan", "http://file.url"
        );
    }

    @Test
    void testCreateArsipSuccess() throws Exception {
        String token = getAuthToken();
        RegisterArsipDTO request = createSampleDTO();

        mockMvc.perform(post(apiPrefix + "/arsip")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ids").exists())
                .andExpect(jsonPath("$.diterimaDari").value("Diterima Dari Test"));
    }

    @Test
    void testCreateArsipValidationError() throws Exception {
        String token = getAuthToken();
        // diterimaDari, nomorSurat, perihal, kodePenyimpanan are @NotBlank
        RegisterArsipDTO request = new RegisterArsipDTO(
                null, new Date(), new Date(), "", "", new Date(), "", "Lampiran", "", "Keterangan", "http://file.url"
        );

        mockMvc.perform(post(apiPrefix + "/arsip")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateArsipSuccess() throws Exception {
        String token = getAuthToken();
        RegisterArsipDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/arsip")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterArsipDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterArsipDTO.class);

        RegisterArsipDTO updateRequest = new RegisterArsipDTO(
                created.ids(), created.tanggalPenerimaanArsip(), created.jamPenerimaanArsip(),
                "Updated Diterima", created.nomorSurat(), created.tanggalSurat(), created.perihal(),
                created.lampiran(), created.kodePenyimpanan(), created.keterangan(), created.urlFile()
        );

        mockMvc.perform(put(apiPrefix + "/arsip/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.diterimaDari").value("Updated Diterima"));
    }

    @Test
    void testGetArsipById() throws Exception {
        String token = getAuthToken();
        RegisterArsipDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/arsip")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterArsipDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterArsipDTO.class);

        mockMvc.perform(get(apiPrefix + "/arsip/" + created.ids() + "/detail")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ids").value(created.ids()));
    }

    @Test
    void testGetAllArsip() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/arsip")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("pages", "0")
                        .param("sizes", "10")
                        .param("startDate", "2020-01-01")
                        .param("endDate", "2100-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void testSearchArsip() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/arsip/search")
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
    void testDeleteArsip() throws Exception {
        String token = getAuthToken();
        RegisterArsipDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/arsip")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andReturn();

        RegisterArsipDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterArsipDTO.class);

        mockMvc.perform(delete(apiPrefix + "/arsip/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isAccepted());
    }
}
