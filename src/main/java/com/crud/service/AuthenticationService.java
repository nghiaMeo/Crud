package com.crud.service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.crud.dto.request.AuthenticationRequest;
import com.crud.dto.request.IntrospectRequest;
import com.crud.dto.request.LogoutRequest;
import com.crud.dto.request.RefreshTokenRequest;
import com.crud.dto.response.AuthenticationResponse;
import com.crud.dto.response.IntrospectResponse;
import com.crud.exception.AppException;
import com.crud.exception.ErrorCode;
import com.crud.model.InvalidatedToken;
import com.crud.model.User;
import com.crud.repository.InvalidateTokenRepository;
import com.crud.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final InvalidateTokenRepository invalidateTokenRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected int VALIDATION_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected int REFRESHABLE_DURATION;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        var user = userRepository
                .findByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());

        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        var token = generateToken(user);

        return AuthenticationResponse.builder().token(token).authenticated(true).build();
    }

    public IntrospectResponse introspectResponse(IntrospectRequest introspectRequest)
            throws JOSEException, ParseException {
        var token = introspectRequest.getToken();
        var isValid = true;

        try {
            verifyToken(token, false);
        } catch (AppException e) {
            isValid = false;
        }
        return IntrospectResponse.builder().valid(isValid).build();
    }

    public void logout(LogoutRequest logoutRequest) throws ParseException, JOSEException {

        try {
            var signToken = verifyToken(logoutRequest.getToken(), true);
            String jit = signToken.getJWTClaimsSet().getJWTID();
            Date expiration = signToken.getJWTClaimsSet().getExpirationTime();

            var invalidatedToken =
                    InvalidatedToken.builder().id(jit).expiryTime(expiration).build();

            invalidateTokenRepository.save(invalidatedToken);
        } catch (AppException e) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException {

        var signToken = verifyToken(request.getToken(), true);
        var jit = signToken.getJWTClaimsSet().getJWTID();
        var expiration = signToken.getJWTClaimsSet().getExpirationTime();
        var invalidatedToken =
                InvalidatedToken.builder().id(jit).expiryTime(expiration).build();
        invalidateTokenRepository.save(invalidatedToken);

        var username = signToken.getJWTClaimsSet().getSubject();

        var user =
                userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        var token = generateToken(user);

        return AuthenticationResponse.builder().token(token).authenticated(true).build();
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws ParseException, JOSEException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expirationTime = (isRefresh)
                ? new Date(signedJWT
                        .getJWTClaimsSet()
                        .getIssueTime()
                        .toInstant()
                        .plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS)
                        .toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);

        if (!verified && expirationTime.after(new Date())) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        var isExistToken =
                invalidateTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID());
        if (isExistToken) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        return signedJWT;
    }

    private String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now()
                        .plus(VALIDATION_DURATION, ChronoUnit.SECONDS)
                        .toEpochMilli()))
                .issuer("Nghia-meow")
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions())) {
                    role.getPermissions().forEach(p -> stringJoiner.add(p.getName()));
                }
            });
        }
        return stringJoiner.toString();
    }
}
