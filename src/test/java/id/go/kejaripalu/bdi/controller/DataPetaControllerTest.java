package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.BaseIntegrationTest;
import id.go.kejaripalu.bdi.dto.DataPetaDTO;
import id.go.kejaripalu.bdi.util.BidangDirektorat;
import id.go.kejaripalu.bdi.util.SektorPeta;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("null")
public class DataPetaControllerTest extends BaseIntegrationTest {

    private DataPetaDTO createSampleDTO() {
        return new DataPetaDTO(
                null, BidangDirektorat.IPOLHANKAM, SektorPeta.KEJAHATAN_SIBER, new Date(), 
                "Lokasi Test", -0.9103, 119.8827, "Siapa Test", "Apa Test", 
                "Mengapa Test", "Bagaimana Test", "Keterangan"
        );
    }

    @Test
    void testCreateDataPetaSuccess() throws Exception {
        String token = getAuthToken();
        DataPetaDTO request = createSampleDTO();

        mockMvc.perform(post(apiPrefix + "/data-peta")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ids").exists())
                .andExpect(jsonPath("$.lokasi").value("Lokasi Test"));
    }

    @Test
    void testCreateDataPetaValidationError() throws Exception {
        String token = getAuthToken();
        // @NotNull BidangDirektorat bidangDirektorat, @NotNull SektorPeta sektor, @NotBlank String lokasi, @NotNull Double latitude, @NotNull Double longitude, @NotBlank String siapa, @NotBlank String apa, @NotBlank String mengapa, @NotBlank String bagaimana
        DataPetaDTO request = new DataPetaDTO(
                null, null, null, new Date(), "", null, null, "", "", "", "", "Ket"
        );

        mockMvc.perform(post(apiPrefix + "/data-peta")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateDataPetaSuccess() throws Exception {
        String token = getAuthToken();
        DataPetaDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/data-peta")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        DataPetaDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), DataPetaDTO.class);

        DataPetaDTO updateRequest = new DataPetaDTO(
                created.ids(), created.bidangDirektorat(), created.sektor(), created.tanggal(),
                "Updated Lokasi", created.latitude(), created.longitude(), created.siapa(),
                created.apa(), created.mengapa(), created.bagaimana(), created.keterangan()
        );

        mockMvc.perform(put(apiPrefix + "/data-peta/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lokasi").value("Updated Lokasi"));
    }

    @Test
    void testGetDataPetaById() throws Exception {
        String token = getAuthToken();
        DataPetaDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/data-peta")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        DataPetaDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), DataPetaDTO.class);

        mockMvc.perform(get(apiPrefix + "/data-peta/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ids").value(created.ids()));
    }

    @Test
    void testGetAllDataPeta() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/data-peta")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("pages", "0")
                        .param("sizes", "10")
                        .param("bidangDirektorat", "IPOLHANKAM")
                        .param("startDate", "2020-01-01")
                        .param("endDate", "2100-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void testGetPetaSimbol() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/data-peta/peta-simbol")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("bidangDirektorat", "IPOLHANKAM")
                        .param("startDate", "2020-01-01")
                        .param("endDate", "2100-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testDeleteDataPeta() throws Exception {
        String token = getAuthToken();
        DataPetaDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/data-peta")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andReturn();

        DataPetaDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), DataPetaDTO.class);

        mockMvc.perform(delete(apiPrefix + "/data-peta/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isAccepted());
    }
}
