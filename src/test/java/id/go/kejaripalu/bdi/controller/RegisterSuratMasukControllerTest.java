package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.BaseIntegrationTest;
import id.go.kejaripalu.bdi.dto.RegisterSuratMasukDTO;
import id.go.kejaripalu.bdi.util.JenisSurat;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("null")
public class RegisterSuratMasukControllerTest extends BaseIntegrationTest {

    private RegisterSuratMasukDTO createSampleDTO() {
        return new RegisterSuratMasukDTO(
                null, new Date(), new Date(), "Asal Test", "No-" + java.util.UUID.randomUUID(), "Perihal Test", new Date(),
                JenisSurat.BIASA, "Isi Disposisi", "Tindak Lanjut", "Keterangan", "http://file.url"
        );
    }

    @Test
    void testCreateSuratMasukSuccess() throws Exception {
        String token = getAuthToken();
        RegisterSuratMasukDTO request = createSampleDTO();

        mockMvc.perform(post(apiPrefix + "/surat-masuk")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ids").exists())
                .andExpect(jsonPath("$.asal").value("Asal Test"));
    }

    @Test
    void testCreateSuratMasukValidationError() throws Exception {
        String token = getAuthToken();
        // asal is @NotBlank, nomorSurat is @NotBlank, perihal is @NotBlank, jenisSurat is @NotNull
        RegisterSuratMasukDTO request = new RegisterSuratMasukDTO(
                null, new Date(), new Date(), "", "", "", new Date(),
                null, "Isi Disposisi", "Tindak Lanjut", "Keterangan", "http://file.url"
        );

        mockMvc.perform(post(apiPrefix + "/surat-masuk")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateSuratMasukSuccess() throws Exception {
        String token = getAuthToken();
        RegisterSuratMasukDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/surat-masuk")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterSuratMasukDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterSuratMasukDTO.class);

        RegisterSuratMasukDTO updateRequest = new RegisterSuratMasukDTO(
                created.ids(), created.tanggalPenerimaanSurat(), created.jamPenerimaanSurat(),
                "Updated Asal", created.nomorSurat(), created.perihal(), created.tanggalSurat(),
                created.jenisSurat(), created.isiDisposisi(), created.tindakLanjutDisposisi(),
                created.keterangan(), created.urlFile()
        );

        mockMvc.perform(put(apiPrefix + "/surat-masuk/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.asal").value("Updated Asal"));
    }

    @Test
    void testGetSuratMasukById() throws Exception {
        String token = getAuthToken();
        RegisterSuratMasukDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/surat-masuk")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterSuratMasukDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterSuratMasukDTO.class);

        mockMvc.perform(get(apiPrefix + "/surat-masuk/" + created.ids() + "/detail")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ids").value(created.ids()));
    }

    @Test
    void testGetSuratMasukByIdNotFound() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/surat-masuk/non-existent-id/detail")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllSuratMasuk() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/surat-masuk")
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
    void testSearchSuratMasuk() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/surat-masuk/search")
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
    void testDeleteSuratMasuk() throws Exception {
        String token = getAuthToken();
        RegisterSuratMasukDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/surat-masuk")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterSuratMasukDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterSuratMasukDTO.class);

        mockMvc.perform(delete(apiPrefix + "/surat-masuk/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isAccepted());

        // Verify it's gone
        mockMvc.perform(get(apiPrefix + "/surat-masuk/" + created.ids() + "/detail")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isNotFound());
    }
}
