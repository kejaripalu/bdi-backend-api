package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.BaseIntegrationTest;
import id.go.kejaripalu.bdi.dto.RegisterProdukIntelijenDTO;
import id.go.kejaripalu.bdi.util.JenisProdukIntelijen;
import id.go.kejaripalu.bdi.util.Sektor;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings({"null", "unchecked"})
public class RegisterProdukIntelijenControllerTest extends BaseIntegrationTest {

    private RegisterProdukIntelijenDTO createSampleDTO() {
        return new RegisterProdukIntelijenDTO(
                null, JenisProdukIntelijen.LAPINHAR, "No-Prodin-" + java.util.UUID.randomUUID(), new Date(),
                Sektor.PRODUKSI_INTELIJEN, "Perihal Prodin Test", "Disposisi", 
                "Keterangan", "http://file.url"
        );
    }

    @Test
    void testCreateProdinSuccess() throws Exception {
        String token = getAuthToken();
        RegisterProdukIntelijenDTO request = createSampleDTO();

        mockMvc.perform(post(apiPrefix + "/prodin")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ids").exists())
                .andExpect(jsonPath("$.nomorProduk").exists());
    }

    @Test
    void testCreateProdinValidationError() throws Exception {
        String token = getAuthToken();
        // @NotNull JenisProdukIntelijen jenisProdukIntelijen, @NotBlank String nomorProduk, @NotNull Sektor sektor, @NotBlank String perihal
        RegisterProdukIntelijenDTO request = new RegisterProdukIntelijenDTO(
                null, null, "", new Date(), null, "", null, null, null
        );

        mockMvc.perform(post(apiPrefix + "/prodin")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateProdinSuccess() throws Exception {
        String token = getAuthToken();
        RegisterProdukIntelijenDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/prodin")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterProdukIntelijenDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterProdukIntelijenDTO.class);

        RegisterProdukIntelijenDTO updateRequest = new RegisterProdukIntelijenDTO(
                created.ids(), created.jenisProdukIntelijen(), "Updated-No", 
                created.tanggalProduk(), created.sektor(), created.perihal(),
                created.disposisiPimpinan(), created.keterangan(), created.urlFile()
        );

        mockMvc.perform(put(apiPrefix + "/prodin/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomorProduk").value("Updated-No"));
    }

    @Test
    void testGetProdinById() throws Exception {
        String token = getAuthToken();
        RegisterProdukIntelijenDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/prodin")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterProdukIntelijenDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterProdukIntelijenDTO.class);

        mockMvc.perform(get(apiPrefix + "/prodin/" + created.ids() + "/detail")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ids").value(created.ids()));
    }

    @Test
    void testGetAllProdin() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/prodin")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("pages", "0")
                        .param("sizes", "10")
                        .param("startDate", "2020-01-01")
                        .param("endDate", "2100-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void testSearchProdin() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/prodin/search")
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
    void testCountProdin() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/prodin/count")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("startDate", "2020-01-01")
                        .param("endDate", "2100-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countLapinhar").exists());
    }

    @Test
    void testDeleteProdin() throws Exception {
        String token = getAuthToken();
        RegisterProdukIntelijenDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/prodin")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterProdukIntelijenDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterProdukIntelijenDTO.class);

        mockMvc.perform(delete(apiPrefix + "/prodin/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isAccepted());
    }
}
