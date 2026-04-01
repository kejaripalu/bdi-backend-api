package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.BaseIntegrationTest;
import id.go.kejaripalu.bdi.dto.RegisterPenkumLuhkumDTO;
import id.go.kejaripalu.bdi.util.JenisKegiatanPenkumLuhkum;
import id.go.kejaripalu.bdi.util.ProgramPenkumLuhkum;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("null")
public class RegisterPenkumLuhkumControllerTest extends BaseIntegrationTest {

    private RegisterPenkumLuhkumDTO createSampleDTO() {
        return new RegisterPenkumLuhkumDTO(
                null, "No-SPrint-" + java.util.UUID.randomUUID(), new Date(), "Sasaran Test", new Date(), 
                "Tempat Test", "Materi Test", 100, "Keterangan", 
                "http://foto1.url", "http://foto2.url", "http://foto3.url", "http://foto4.url",
                JenisKegiatanPenkumLuhkum.PENERANGAN_HUKUM, ProgramPenkumLuhkum.BINMATKUM
        );
    }

    @Test
    void testCreatePenkumSuccess() throws Exception {
        String token = getAuthToken();
        RegisterPenkumLuhkumDTO request = createSampleDTO();

        mockMvc.perform(post(apiPrefix + "/penkumluhkum")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ids").exists())
                .andExpect(jsonPath("$.sasaranKegiatan").value("Sasaran Test"));
    }

    @Test
    void testCreatePenkumValidationError() throws Exception {
        String token = getAuthToken();
        // @NotBlank String nomorSuratPerintah, @NotBlank String sasaranKegiatan, @NotBlank String tempat, @NotBlank String materi, @NotNull Integer jumlahPeserta, @NotNull JenisKegiatanPenkumLuhkum jenisKegiatan, @NotNull ProgramPenkumLuhkum program
        RegisterPenkumLuhkumDTO request = new RegisterPenkumLuhkumDTO(
                null, "", new Date(), "", new Date(), "", "", null, "Keterangan", 
                null, null, null, null, null, null
        );

        mockMvc.perform(post(apiPrefix + "/penkumluhkum")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdatePenkumSuccess() throws Exception {
        String token = getAuthToken();
        RegisterPenkumLuhkumDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/penkumluhkum")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterPenkumLuhkumDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterPenkumLuhkumDTO.class);

        RegisterPenkumLuhkumDTO updateRequest = new RegisterPenkumLuhkumDTO(
                created.ids(), created.nomorSuratPerintah(), created.tanggalSuratPerintah(), "Updated Sasaran",
                created.tanggalKegiatan(), created.tempat(), created.materi(), created.jumlahPeserta(),
                created.keterangan(), created.urlFoto1(), created.urlFoto2(), created.urlFoto3(), 
                created.urlFoto4(), created.jenisKegiatan(), created.program()
        );

        mockMvc.perform(put(apiPrefix + "/penkumluhkum/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sasaranKegiatan").value("Updated Sasaran"));
    }

    @Test
    void testGetPenkumById() throws Exception {
        String token = getAuthToken();
        RegisterPenkumLuhkumDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/penkumluhkum")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterPenkumLuhkumDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterPenkumLuhkumDTO.class);

        mockMvc.perform(get(apiPrefix + "/penkumluhkum/" + created.ids() + "/detail")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ids").value(created.ids()));
    }

    @Test
    void testGetAllPenkum() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/penkumluhkum")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("pages", "0")
                        .param("sizes", "10")
                        .param("jenisKegiatan", "PENKUM")
                        .param("startDate", "2020-01-01")
                        .param("endDate", "2100-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void testSearchPenkum() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/penkumluhkum/search")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("pages", "0")
                        .param("sizes", "10")
                        .param("value", "Test")
                        .param("jenisKegiatan", "PENKUM")
                        .param("startDate", "2020-01-01")
                        .param("endDate", "2100-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void testCountPenkum() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/penkumluhkum/count")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("startDate", "2020-01-01")
                        .param("endDate", "2100-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countBinmatkum").exists());
    }

    @Test
    void testDeletePenkum() throws Exception {
        String token = getAuthToken();
        RegisterPenkumLuhkumDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/penkumluhkum")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterPenkumLuhkumDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterPenkumLuhkumDTO.class);

        mockMvc.perform(delete(apiPrefix + "/penkumluhkum/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isAccepted());
    }
}
