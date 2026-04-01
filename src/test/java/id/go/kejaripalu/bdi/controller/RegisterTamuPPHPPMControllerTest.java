package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.BaseIntegrationTest;
import id.go.kejaripalu.bdi.dto.RegisterTamuPPHPPMDTO;
import id.go.kejaripalu.bdi.util.JenisKelamin;
import id.go.kejaripalu.bdi.util.JenisPelayanan;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings({"null", "unchecked"})
public class RegisterTamuPPHPPMControllerTest extends BaseIntegrationTest {

    private RegisterTamuPPHPPMDTO createSampleDTO() {
        return new RegisterTamuPPHPPMDTO(
                null, JenisPelayanan.PPH, "Petugas Test", new Date(), new Date(), 
                "Tamu Test", "Tempat Lahir", new Date(), "Alamat Test", 
                JenisKelamin.LAKILAKI, "No-HP", "test@test.com", "Pekerjaan Test", 
                "No-Identitas", "Organisasi", "Informasi Test", 
                "Dokumen", "Keterangan", "http://file.url"
        );
    }

    @Test
    void testCreateTamuSuccess() throws Exception {
        String token = getAuthToken();
        RegisterTamuPPHPPMDTO request = createSampleDTO();

        mockMvc.perform(post(apiPrefix + "/pphppm")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ids").exists())
                .andExpect(jsonPath("$.namaTamu").exists());
    }

    @Test
    void testCreateTamuValidationError() throws Exception {
        String token = getAuthToken();
        // @NotBlank String namaPetugasPenerima, @NotBlank String namaTamu, @NotBlank String informasiYangDisampaikan
        RegisterTamuPPHPPMDTO request = new RegisterTamuPPHPPMDTO(
                null, null, "", new Date(), new Date(), "", null, null, null, null, null, null, null, null, null, "", null, null, null
        );

        mockMvc.perform(post(apiPrefix + "/pphppm")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateTamuSuccess() throws Exception {
        String token = getAuthToken();
        RegisterTamuPPHPPMDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/pphppm")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterTamuPPHPPMDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterTamuPPHPPMDTO.class);

        RegisterTamuPPHPPMDTO updateRequest = new RegisterTamuPPHPPMDTO(
                created.ids(), created.jenisPelayanan(), created.namaPetugasPenerima(), created.tanggal(),
                created.jam(), "Updated Tamu", created.tempatLahirTamu(), created.tanggalLahirTamu(),
                created.alamat(), created.jenisKelamin(), created.nomorHandphone(), created.email(),
                created.pekerjaan(), created.nomorIdentitas(), created.namaOrganisasi(), 
                created.informasiYangDisampaikan(), created.dokumenYangDisampaikan(), 
                created.keterangan(), created.urlFile()
        );

        mockMvc.perform(put(apiPrefix + "/pphppm/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.namaTamu").value("Updated Tamu"));
    }

    @Test
    void testGetTamuById() throws Exception {
        String token = getAuthToken();
        RegisterTamuPPHPPMDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/pphppm")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterTamuPPHPPMDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterTamuPPHPPMDTO.class);

        mockMvc.perform(get(apiPrefix + "/pphppm/" + created.ids() + "/detail")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ids").value(created.ids()));
    }

    @Test
    void testGetAllTamu() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/pphppm")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("pages", "0")
                        .param("sizes", "10")
                        .param("startDate", "2020-01-01")
                        .param("endDate", "2100-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void testSearchTamu() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/pphppm/search")
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
    void testCountTamu() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/pphppm/count")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("startDate", "2020-01-01")
                        .param("endDate", "2100-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countPPH").exists());
    }

    @Test
    void testDeleteTamu() throws Exception {
        String token = getAuthToken();
        RegisterTamuPPHPPMDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/pphppm")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andReturn();

        RegisterTamuPPHPPMDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterTamuPPHPPMDTO.class);

        mockMvc.perform(delete(apiPrefix + "/pphppm/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isAccepted());
    }
}
