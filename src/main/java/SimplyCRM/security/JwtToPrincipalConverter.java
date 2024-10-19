package SimplyCRM.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.List;

@Component
public class JwtToPrincipalConverter {
    public UserPrincipal convert(DecodedJWT jwt){
        return UserPrincipal.builder().userId(Long.valueOf(jwt.getSubject())).email(jwt.getClaim("e").asString()).authorities(extractAuthritiesFromClaim(jwt)).build();
    }

    private List<SimpleGrantedAuthority> extractAuthritiesFromClaim(DecodedJWT jwt){
        var claim = jwt.getClaim("a");
        if (claim.isNull() || claim.isMissing()) return List.of();
        return claim.asList(SimpleGrantedAuthority.class);
    }
}