package id.go.kejaripalu.bdi.security.model;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RawAccesJWTToken implements Token {
	
	private String token;
	
	public Jws<Claims> parseClaim(SecretKey secret) {
		return Jwts.parser().verifyWith(secret).build().parseSignedClaims(token);
	}

	@Override
	public String getToken() {
		return token;
	}

}
