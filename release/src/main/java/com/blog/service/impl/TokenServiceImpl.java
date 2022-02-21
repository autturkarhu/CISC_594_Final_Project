package com.blog.service.impl;

import com.blog.encryptionUtils.EncryptionDecryptionAES;
import com.blog.entity.SignupEntity;
import com.blog.pojo.AccessToken;
import com.blog.repositories.AccessTokensRepository;
import com.blog.service.TokenService;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

public class TokenServiceImpl implements TokenService {

    private static final String ISSUER = "DEMO.INC";
    private static final String SUBJECT = "Access_Token";

    @Autowired
    private AccessTokensRepository tokenRepository;

    @Value("$token.secretKey")
    private String API_SECRET;

    private EncryptionDecryptionAES encryptionDecryptionAES;
    private Log log = LogFactory.getLog(TokenServiceImpl.class);

    @Override
    public AccessToken createAccessToken(SignupEntity signupEntity) {
        if (StringUtils.isBlank(signupEntity.getRole())) {
            signupEntity.setRole("Blogger");
        }
        AccessToken toSave = new AccessToken("", "", signupEntity.getRole(), getNextYearDate());
        AccessToken persistedToken = tokenRepository.save(toSave);
        persistedToken = createAccessToken(persistedToken, signupEntity);

        return persistedToken;
    }

    private AccessToken createAccessToken(AccessToken persistedToken, SignupEntity signupEntity) {
        try {
            encryptionDecryptionAES = new EncryptionDecryptionAES();
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(API_SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(persistedToken.getId())
                .setIssuedAt(now)
                .setSubject(SUBJECT)
                .setIssuer(ISSUER)
                .claim("userId", signupEntity.getId())
                .claim("username", signupEntity.getUsername())
                .claim("password", encryptionDecryptionAES.decrypt(signupEntity.getPassword()))
                .claim("role", persistedToken.getRole())
                .signWith(signatureAlgorithm, signingKey);

        builder.setExpiration(getNextYearDate());

        persistedToken.setTokenString(builder.compact());
        persistedToken.setIssuer(ISSUER);

        try {
            persistedToken = tokenRepository.save(persistedToken);
        } catch (Exception e) {
            log.error(e);
        }
        return persistedToken;
    }

    @Override
    public TokenInfo getTokenInfo(String encryptedTokenString) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(API_SECRET))
                    .parseClaimsJws(encryptedTokenString).getBody();
            TokenInfo tokenInfo = new TokenInfo();
            tokenInfo.getUsername = (String) claims.get("username");
            tokenInfo.getRole = (String) claims.get("role");
            tokenInfo.getUserId = (String) claims.get("userId");
            tokenInfo.getPassword = (String) claims.get("password");
            tokenInfo.getTokenString = encryptedTokenString;
            tokenInfo.getTokenId = claims.getId();
            return tokenInfo;
        } catch (ExpiredJwtException e) {
            log.error(e);
        } catch (UnsupportedJwtException | IllegalArgumentException | SignatureException | MalformedJwtException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean deleteToken(AccessToken tokenToBeDeleted) {
        try {
            tokenRepository.delete(tokenToBeDeleted);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error(e);
        }
        return Boolean.FALSE;
    }

    private Date getNextYearDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        return cal.getTime();
    }
}