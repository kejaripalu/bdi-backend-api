package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.BaseIntegrationTest;
import id.go.kejaripalu.bdi.dto.RegisterOperasiIntelijenDTO;
import id.go.kejaripalu.bdi.util.BidangDirektorat;
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
public class RegisterOperasiIntelijenControllerTest extends BaseIntegrationTest {

    private RegisterOperasiIntelijenDTO createSampleDTO() {
        return new RegisterOperasiIntelijenDTO(
                null, BidangDirektorat.IPOLHANKAM, Sektor.KESATUAN_PERSATUAN_BANGSA, "No-Ops-" + java.util.UUID.randomUUID(), new Date(),
                "Perihal Ops Test", "Petugas Test", "Hasil Test", "Keterangan", "http://file.url"
        );
    }

    @Test
    void testCreateOpsinSuccess() throws Exception {
        String token = getAuthToken();
        RegisterOperasiIntelijenDTO request = createSampleDTO();

        mockMvc.perform(post(apiPrefix + "/opsin")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ids").exists())
                .andExpect(jsonPath("$.nomor").exists());
    }

    @Test
    void testCreateOpsinValidationError() throws Exception {
        String token = getAuthToken();
        // nomor, perihal, namaPetugasPelaksana are @NotBlank, bidangDirektorat, sektor are @NotNull
        RegisterOperasiIntelijenDTO request = new RegisterOperasiIntelijenDTO(
                null, null, null, "", new Date(), "", "", "Hasil Test", "Keterangan", "http://file.url"
        );

        mockMvc.perform(post(apiPrefix + "/opsin")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateOpsinSuccess() throws Exception {
        String token = getAuthToken();
        RegisterOperasiIntelijenDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/opsin")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterOperasiIntelijenDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterOperasiIntelijenDTO.class);

        RegisterOperasiIntelijenDTO updateRequest = new RegisterOperasiIntelijenDTO(
                created.ids(), created.bidangDirektorat(), created.sektor(), "Updated-Ops",
                created.tanggal(), created.perihal(), created.namaPetugasPelaksana(),
                created.hasilPelaksanaanOperasi(), created.keterangan(), created.urlFile()
        );

        mockMvc.perform(put(apiPrefix + "/opsin/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomor").value("Updated-Ops"));
    }

    @Test
    void testGetOpsinById() throws Exception {
        String token = getAuthToken();
        RegisterOperasiIntelijenDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/opsin")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterOperasiIntelijenDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterOperasiIntelijenDTO.class);

        mockMvc.perform(get(apiPrefix + "/opsin/" + created.ids() + "/detail")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ids").value(created.ids()));
    }

    @Test
    void testGetAllOpsin() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/opsin")
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
    void testSearchOpsin() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/opsin/search")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("pages", "0")
                        .param("sizes", "10")
                        .param("value", "Test")
                        .param("bidangDirektorat", "IPOLHANKAM")
                        .param("startDate", "2020-01-01")
                        .param("endDate", "2100-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void testDeleteOpsin() throws Exception {
        String token = getAuthToken();
        RegisterOperasiIntelijenDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/opsin")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterOperasiIntelijenDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterOperasiIntelijenDTO.class);

        mockMvc.perform(delete(apiPrefix + "/opsin/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isAccepted());
    }
}
