package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.BaseIntegrationTest;
import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenPamstraDTO;
import id.go.kejaripalu.bdi.util.Sektor;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings({"null", "unchecked"})
public class RegisterKegiatanIntelijenPamstraControllerTest extends BaseIntegrationTest {

    private RegisterKegiatanIntelijenPamstraDTO createSampleDTO() {
        String uuid = java.util.UUID.randomUUID().toString();
        return new RegisterKegiatanIntelijenPamstraDTO(
                null, Sektor.INFRASTRUKTUR_JALAN, "Nama Kegiatan", "Sumber Dana", "Instansi",
                new BigDecimal("1000000"), "No-Surat-" + uuid, new Date(), 
                "Tempat Pemaparan", new Date(), "Telaahan Intelijen", 
                true, "Tindak Lanjut Keterangan",
                "No-SPrint-" + uuid, new Date(), "Nama Petugas",
                new BigDecimal("900000"), id.go.kejaripalu.bdi.util.HasilPamstra.SELESAI, 
                "Hasil Keterangan", "No-Kertas-Kerja-" + uuid, new Date(),
                "Keterangan", "http://file.url"
        );
    }

    @Test
    void testCreatePamstraSuccess() throws Exception {
        String token = getAuthToken();
        RegisterKegiatanIntelijenPamstraDTO request = createSampleDTO();

        mockMvc.perform(post(apiPrefix + "/kegiatan-pamstra")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ids").exists())
                .andExpect(jsonPath("$.namaKegiatan").value("Nama Kegiatan"));
    }

    @Test
    void testCreatePamstraValidationError() throws Exception {
        String token = getAuthToken();
        // @NotNull Sektor sektor, @NotBlank String namaKegiatan, @NotBlank String sumberDana, @NotBlank String instansi, @NotNull BigDecimal paguAnggaran, @NotBlank String nomorSuratPermohonan
        RegisterKegiatanIntelijenPamstraDTO request = new RegisterKegiatanIntelijenPamstraDTO(
                null, null, "", "", "", null, "", new Date(), 
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null
        );

        mockMvc.perform(post(apiPrefix + "/kegiatan-pamstra")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdatePamstraSuccess() throws Exception {
        String token = getAuthToken();
        RegisterKegiatanIntelijenPamstraDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/kegiatan-pamstra")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterKegiatanIntelijenPamstraDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterKegiatanIntelijenPamstraDTO.class);

        RegisterKegiatanIntelijenPamstraDTO updateRequest = new RegisterKegiatanIntelijenPamstraDTO(
                created.ids(), created.sektor(), "Updated Kegiatan", created.sumberDana(),
                created.instansi(), created.paguAnggaran(), created.nomorSuratPermohonan(),
                created.tanggalSuratPermohonan(), created.tempatPemaparan(), created.tanggalPemaparan(),
                created.telaahanIntelijen(), created.tindakLanjut(), created.tindakLanjutKeterangan(),
                created.nomorSprintWalpam(), created.tanggalSprintWalpam(), created.namaPetugasPelaksana(),
                created.nilaiKontrak(), created.hasilPelaksanaan(), created.hasilPelaksanaanKeterangan(),
                created.nomorKertasKerja(), created.tanggalKertasKerja(), created.keterangan(), created.urlFile()
        );

        mockMvc.perform(put(apiPrefix + "/kegiatan-pamstra/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.namaKegiatan").value("Updated Kegiatan"));
    }

    @Test
    void testGetPamstraById() throws Exception {
        String token = getAuthToken();
        RegisterKegiatanIntelijenPamstraDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/kegiatan-pamstra")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterKegiatanIntelijenPamstraDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterKegiatanIntelijenPamstraDTO.class);

        mockMvc.perform(get(apiPrefix + "/kegiatan-pamstra/" + created.ids() + "/detail")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ids").value(created.ids()));
    }

    @Test
    void testGetAllPamstra() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/kegiatan-pamstra")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .param("pages", "0")
                        .param("sizes", "10")
                        .param("startDate", "2020-01-01")
                        .param("endDate", "2100-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void testSearchPamstra() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/kegiatan-pamstra/search")
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
    void testDeletePamstra() throws Exception {
        String token = getAuthToken();
        RegisterKegiatanIntelijenPamstraDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/kegiatan-pamstra")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterKegiatanIntelijenPamstraDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterKegiatanIntelijenPamstraDTO.class);

        mockMvc.perform(delete(apiPrefix + "/kegiatan-pamstra/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isAccepted());
    }
}
