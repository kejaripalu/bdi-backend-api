package id.go.kejaripalu.bdi.security.util;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;

import id.go.kejaripalu.bdi.security.model.AccessJWTToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JWTTokenFactory {
	
	private Environment env;
	
	private Key secret;
	
	public AccessJWTToken createAccessJWTToken(String username, Collection<? extends GrantedAuthority> authorities) {
		Claims claims = Jwts.claims().subject(username)
							.add("scope", authorities.stream().map( a -> a.getAuthority()).collect(Collectors.toList()))
							.build();
		
		Integer expiredMinutes = Integer.parseInt(env.getProperty("app.token-expired"));
		String zoneId = env.getProperty("app.zone-id");
		String issuer = env.getProperty("app.issuer");
		
		//waktu kapan token dibuat
		LocalDateTime currentTime = LocalDateTime.now();
		Date currentTimeDate = Date.from(currentTime.atZone(ZoneId.of(zoneId)).toInstant());		
		
		//waktu kapan token expired
		LocalDateTime expiredTime = currentTime.plusMinutes(expiredMinutes);
		Date expiredTimeDate = Date.from(expiredTime.atZone(ZoneId.of(zoneId)).toInstant());
		
		String token = Jwts.builder().claims(claims)
				.issuer(issuer)
				.issuedAt(currentTimeDate)
				.expiration(expiredTimeDate)
				.signWith(secret)
				.compact();
		
		return new AccessJWTToken(token, claims);
	}	

}
