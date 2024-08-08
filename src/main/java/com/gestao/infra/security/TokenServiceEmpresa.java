package com.gestao.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.exceptions.JWTVerificationException;
@Service
public class TokenServiceEmpresa {
    @Value("${security.token.secret}")
    private String secret;
    public DecodedJWT validateToken(String token){
// tirar o Bearer
        token= token.replace("Bearer ", "");

        try {
            Algorithm algorithm= Algorithm.HMAC256(secret);
            var tokenDecode= JWT.require(algorithm).build().verify(token)
                    ;
      return tokenDecode;

        }catch (JWTVerificationException ex) {
            ex.printStackTrace();
            return null;
        }


    }



}
