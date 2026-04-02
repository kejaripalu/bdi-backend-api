package id.go.kejaripalu.bdi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.go.kejaripalu.bdi.security.dto.LoginRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication", description = "Endpoint untuk autentikasi user")
@RestController
@RequestMapping("${app.api-url}")
public class AuthController {

    @Operation(
        summary = "User Login",
        description = "Gunakan endpoint ini untuk mendapatkan JWT token. Masukkan username dan password.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Berhasil Login - Mengembalikan JWT Token"),
            @ApiResponse(responseCode = "401", description = "Gagal Login - Username atau Password salah")
        }
    )
    @SecurityRequirements() // Menandakan bahwa endpoint ini publik (tanpa gembok di Swagger UI)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        // Method ini tidak akan pernah dieksekusi secara nyata karena di-intercept oleh
        // UsernamePasswordAuthProcessingFilter milik Spring Security.
        // Dibuat hanya untuk dokumentasi Swagger UI agar user tahu format request login.
        return ResponseEntity.ok().build();
    }
}
