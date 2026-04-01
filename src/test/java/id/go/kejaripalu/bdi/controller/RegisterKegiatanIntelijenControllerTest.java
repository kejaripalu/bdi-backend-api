package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.BaseIntegrationTest;
import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenDTO;
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
public class RegisterKegiatanIntelijenControllerTest extends BaseIntegrationTest {

    private RegisterKegiatanIntelijenDTO createSampleDTO() {
        return new RegisterKegiatanIntelijenDTO(
                null, BidangDirektorat.IPOLHANKAM, Sektor.KESATUAN_PERSATUAN_BANGSA, "No-Keg-" + java.util.UUID.randomUUID(), new Date(),
                "Perihal Keg Test", "Petugas Test", "Hasil Test", "Keterangan", "http://file.url"
        );
    }

    @Test
    void testCreateKegiatanSuccess() throws Exception {
        String token = getAuthToken();
        RegisterKegiatanIntelijenDTO request = createSampleDTO();

        mockMvc.perform(post(apiPrefix + "/kegiatan")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ids").exists())
                .andExpect(jsonPath("$.nomor").exists());
    }

    @Test
    void testCreateKegiatanValidationError() throws Exception {
        String token = getAuthToken();
        // nomor, perihal, namaPetugasPelaksana are @NotBlank, bidangDirektorat, sektor are @NotNull
        RegisterKegiatanIntelijenDTO request = new RegisterKegiatanIntelijenDTO(
                null, null, null, "", new Date(), "", "", "Hasil Test", "Keterangan", "http://file.url"
        );

        mockMvc.perform(post(apiPrefix + "/kegiatan")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateKegiatanSuccess() throws Exception {
        String token = getAuthToken();
        RegisterKegiatanIntelijenDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/kegiatan")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterKegiatanIntelijenDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterKegiatanIntelijenDTO.class);

        RegisterKegiatanIntelijenDTO updateRequest = new RegisterKegiatanIntelijenDTO(
                created.ids(), created.bidangDirektorat(), created.sektor(), "Updated-No",
                created.tanggal(), created.perihal(), created.namaPetugasPelaksana(),
                created.hasilPelaksanaanKegiatan(), created.keterangan(), created.urlFile()
        );

        mockMvc.perform(put(apiPrefix + "/kegiatan/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomor").value("Updated-No"));
    }

    @Test
    void testGetKegiatanById() throws Exception {
        String token = getAuthToken();
        RegisterKegiatanIntelijenDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/kegiatan")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterKegiatanIntelijenDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterKegiatanIntelijenDTO.class);

        mockMvc.perform(get(apiPrefix + "/kegiatan/" + created.ids() + "/detail")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ids").value(created.ids()));
    }

    @Test
    void testGetAllKegiatan() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/kegiatan")
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
    void testSearchKegiatan() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/kegiatan/search")
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
    void testDeleteKegiatan() throws Exception {
        String token = getAuthToken();
        RegisterKegiatanIntelijenDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/kegiatan")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterKegiatanIntelijenDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterKegiatanIntelijenDTO.class);

        mockMvc.perform(delete(apiPrefix + "/kegiatan/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isAccepted());
    }
}
