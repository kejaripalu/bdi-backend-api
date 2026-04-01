package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.BaseIntegrationTest;
import id.go.kejaripalu.bdi.dto.RegisterEkspedisiDTO;
import id.go.kejaripalu.bdi.util.JenisSurat;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings({"null", "unchecked"})
public class RegisterEkspedisiControllerTest extends BaseIntegrationTest {

    private RegisterEkspedisiDTO createSampleDTO() {
        return new RegisterEkspedisiDTO(
                null, "No-Eks-" + java.util.UUID.randomUUID(), new Date(), "Kepada Test", "Perihal Eks Test",
                "Lampiran", new Date(), new Date(), "Nama Paraf", "Keterangan", "http://file.url", JenisSurat.BIASA
        );
    }

    @Test
    void testCreateEkspedisiSuccess() throws Exception {
        String token = getAuthToken();
        RegisterEkspedisiDTO request = createSampleDTO();

        mockMvc.perform(post(apiPrefix + "/ekspedisi")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ids").exists())
                .andExpect(jsonPath("$.kepada").value("Kepada Test"));
    }

    @Test
    void testCreateEkspedisiValidationError() throws Exception {
        String token = getAuthToken();
        // nomorSurat, kepada, perihal are @NotBlank, jenisSurat is @NotNull
        RegisterEkspedisiDTO request = new RegisterEkspedisiDTO(
                null, "", new Date(), "", "", "Lampiran", new Date(), new Date(), "Nama Paraf", "Keterangan", "http://file.url", null
        );

        mockMvc.perform(post(apiPrefix + "/ekspedisi")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateEkspedisiSuccess() throws Exception {
        String token = getAuthToken();
        RegisterEkspedisiDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/ekspedisi")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterEkspedisiDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterEkspedisiDTO.class);

        RegisterEkspedisiDTO updateRequest = new RegisterEkspedisiDTO(
                created.ids(), created.nomorSurat(), created.tanggalSurat(), "Updated Kepada",
                created.perihal(), created.lampiran(), created.tanggalTandaTerima(), created.jamTandaTerima(),
                created.namaDanParaf(), created.keterangan(), created.urlFile(), created.jenisSurat()
        );

        mockMvc.perform(put(apiPrefix + "/ekspedisi/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.kepada").value("Updated Kepada"));
    }

    @Test
    void testGetEkspedisiById() throws Exception {
        String token = getAuthToken();
        RegisterEkspedisiDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/ekspedisi")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterEkspedisiDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterEkspedisiDTO.class);

        mockMvc.perform(get(apiPrefix + "/ekspedisi/" + created.ids() + "/detail")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ids").value(created.ids()));
    }

    @Test
    void testGetAllEkspedisi() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/ekspedisi")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("pages", "0")
                        .param("sizes", "10")
                        .param("jenisSurat", "BIASA")
                        .param("startDate", "2020-01-01")
                        .param("endDate", "2100-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void testSearchEkspedisi() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/ekspedisi/search")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("pages", "0")
                        .param("sizes", "10")
                        .param("jenisSurat", "BIASA")
                        .param("value", "Test")
                        .param("startDate", "2020-01-01")
                        .param("endDate", "2100-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void testDeleteEkspedisi() throws Exception {
        String token = getAuthToken();
        RegisterEkspedisiDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/ekspedisi")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterEkspedisiDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterEkspedisiDTO.class);

        mockMvc.perform(delete(apiPrefix + "/ekspedisi/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isAccepted());
    }
}
