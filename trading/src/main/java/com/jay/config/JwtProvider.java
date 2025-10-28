package com.jay.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class JwtProvider {

    private static SecretKey = SecretKey key= Keys.hmacShaKeyFor(JwtConstant.SECRETE_KEY.getBytes());

    public static String generateToken(Authentication auth) {
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(subjet);
        String roles=populateAuthorities(authorities);

        String jwt= Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date()(new Date().getTime()+86400000))
                .claim(name:"email",auth.getName())
                .claim(name:"authorities",roles)
                .signWith(key);
                .compact();
        return jwt;
    }

    public static String getEmailFromToken(String token) {
        token=token.substring(beginIndex: 7);
        Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody()

        String email=String.valueOf()f(claims.get('email'));
        return email;
    }

    private static String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
    Set<String> auth=new HashSet<>();
        for (GrantedAuthority ga : authorities) {
            auth.add(ga.getAuthority());
        }
        return String.join(",",auth);
    }

}
