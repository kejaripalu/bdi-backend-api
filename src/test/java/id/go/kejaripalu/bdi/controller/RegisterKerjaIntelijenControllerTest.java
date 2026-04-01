package id.go.kejaripalu.bdi.controller;

import id.go.kejaripalu.bdi.BaseIntegrationTest;
import id.go.kejaripalu.bdi.dto.RegisterKerjaIntelijenDTO;
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

@SuppressWarnings("null")
public class RegisterKerjaIntelijenControllerTest extends BaseIntegrationTest {

    private RegisterKerjaIntelijenDTO createSampleDTO() {
        return new RegisterKerjaIntelijenDTO(
                null, new Date(), new Date(), "Sumber Bapul", "A1",
                "Uraian Test", "Catatan", "Disposisi", "Tindak Lanjut", 
                "Keterangan", "http://file.url", BidangDirektorat.EKOKEU, Sektor.KEUANGAN_NEGARA
        );
    }

    @Test
    void testCreateRKISuccess() throws Exception {
        String token = getAuthToken();
        RegisterKerjaIntelijenDTO request = createSampleDTO();

        mockMvc.perform(post(apiPrefix + "/rki")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ids").exists())
                .andExpect(jsonPath("$.sumberBapul").value("Sumber Bapul"));
    }

    @Test
    void testCreateRKIVallidationError() throws Exception {
        String token = getAuthToken();
        // @NotBlank String sumberBapul, @NotBlank String nilaiDataInformasi, @NotBlank String uraianPeristiwaMasalah, @NotNull BidangDirektorat bidangDirektorat, @NotNull Sektor sektor
        RegisterKerjaIntelijenDTO request = new RegisterKerjaIntelijenDTO(
                null, new Date(), new Date(), "", "", "", null, null, null, null, "http://file.url", null, null
        );

        mockMvc.perform(post(apiPrefix + "/rki")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateRKISuccess() throws Exception {
        String token = getAuthToken();
        RegisterKerjaIntelijenDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/rki")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterKerjaIntelijenDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterKerjaIntelijenDTO.class);

        RegisterKerjaIntelijenDTO updateRequest = new RegisterKerjaIntelijenDTO(
                created.ids(), created.tanggalWaktuDiterima(), created.jamWaktuDiterima(), "Updated Sumber",
                created.nilaiDataInformasi(), created.uraianPeristiwaMasalah(),
                created.catatan(), created.disposisiTindakan(), created.tindakLanjut(), 
                created.keterangan(), created.urlFile(), created.bidangDirektorat(), created.sektor()
        );

        mockMvc.perform(put(apiPrefix + "/rki/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sumberBapul").value("Updated Sumber"));
    }

    @Test
    void testGetRKIById() throws Exception {
        String token = getAuthToken();
        RegisterKerjaIntelijenDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/rki")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        RegisterKerjaIntelijenDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterKerjaIntelijenDTO.class);

        mockMvc.perform(get(apiPrefix + "/rki/" + created.ids() + "/detail")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ids").value(created.ids()));
    }

    @Test
    void testGetAllRKI() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/rki")
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
    void testSearchRKI() throws Exception {
        String token = getAuthToken();
        mockMvc.perform(get(apiPrefix + "/rki/search")
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
    void testDeleteRKI() throws Exception {
        String token = getAuthToken();
        RegisterKerjaIntelijenDTO createRequest = createSampleDTO();

        MvcResult result = mockMvc.perform(post(apiPrefix + "/rki")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andReturn();

        RegisterKerjaIntelijenDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), RegisterKerjaIntelijenDTO.class);

        mockMvc.perform(delete(apiPrefix + "/rki/" + created.ids())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isAccepted());
    }
}
