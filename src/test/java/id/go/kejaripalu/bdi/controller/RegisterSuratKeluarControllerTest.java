package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.BaseIntegrationTest;
import id.go.kejaripalu.bdi.dto.RegisterSuratKeluarDTO;
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
public class RegisterSuratKeluarControllerTest extends BaseIntegrationTest {

    private RegisterSuratKeluarDTO createSampleDTO() {
        return new RegisterSuratKeluarDTO(
                null, new Date(), "No-Keluar-" + java.util.UUID.randomUUID(), "Kepada Test", "Perihal Keluar Test",
                JenisSurat.BIASA, "Lampiran", "Keterangan", "http://file.url"
        );
    }

    @Test
    void testCreateSuratKeluarSuccess() throws Exception {
        String token = getAuthToken();
        RegisterSuratKeluarDTO request = createSampleDTO();

        mockMvc.perform(post(apiPrefix + "/surat-keluar")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ids").exists())
                .andExpect(jsonPath("$.perihal").value("Perihal Keluar Test"));
    }

    @Test
    void testCreateSuratKeluarValidationError() throws Exception {
        String token = getAuthToken();
        // nomorSurat, kepada, perihal are @NotBlank
        RegisterSuratKeluarDTO request = new RegisterSuratKeluarDTO(
                null, new Date(), "", "", "", JenisSurat.BIASA, "Lampiran", "Keterangan", "http://file.url"
        );

        mockMvc.perform(post(apiPrefix + "/surat-keluar")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateSuratKeluarSuccess() throws Exception {
        String token = getAuthToken();
        RegisterSuratKeluarDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/surat-keluar")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterSuratKeluarDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterSuratKeluarDTO.class);

        RegisterSuratKeluarDTO updateRequest = new RegisterSuratKeluarDTO(
                created.ids(), created.tanggalSurat(), created.nomorSurat(), "Updated Kepada",
                created.perihal(), created.jenisSurat(), created.lampiran(), created.keterangan(), created.urlFile()
        );

        mockMvc.perform(put(apiPrefix + "/surat-keluar/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.kepada").value("Updated Kepada"));
    }

    @Test
    void testGetSuratKeluarById() throws Exception {
        String token = getAuthToken();
        RegisterSuratKeluarDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/surat-keluar")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterSuratKeluarDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterSuratKeluarDTO.class);

        mockMvc.perform(get(apiPrefix + "/surat-keluar/" + created.ids() + "/detail")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ids").value(created.ids()));
    }

    @Test
    void testGetSuratKeluarByIdNotFound() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/surat-keluar/non-existent-id/detail")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllSuratKeluar() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/surat-keluar")
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
    void testSearchSuratKeluar() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/surat-keluar/search")
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
    void testDeleteSuratKeluar() throws Exception {
        String token = getAuthToken();
        RegisterSuratKeluarDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/surat-keluar")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterSuratKeluarDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterSuratKeluarDTO.class);

        mockMvc.perform(delete(apiPrefix + "/surat-keluar/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isAccepted());

        mockMvc.perform(get(apiPrefix + "/surat-keluar/" + created.ids() + "/detail")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isNotFound());
    }
}
