/*
 * OpenID Connect Authentication for SonarQube
 * Copyright (c) 2017 Torsten Juergeleit
 * mailto:torsten AT vaulttec DOT org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vaulttec.sonarqube.auth.oidc;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jwt.JWT;
import com.nimbusds.oauth2.sdk.*;
import com.nimbusds.oauth2.sdk.ResponseType.Value;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.id.Issuer;
import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.oauth2.sdk.token.BearerAccessToken;
import com.nimbusds.openid.connect.sdk.*;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest.Builder;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import com.nimbusds.openid.connect.sdk.op.OIDCProviderMetadata;
import com.nimbusds.openid.connect.sdk.token.OIDCTokens;
import com.nimbusds.openid.connect.sdk.validators.IDTokenValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.server.ServerSide;
import org.sonar.api.server.http.HttpRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ServerSide
public class OidcClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(OidcClient.class);

    private static final ResponseType RESPONSE_TYPE = new ResponseType(Value.CODE);

    private final OidcConfiguration config;

    public OidcClient(OidcConfiguration config) {
        this.config = config;
    }

    public AuthenticationRequest createAuthenticationRequest(String callbackUrl, String state) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public AuthorizationCode getAuthorizationCode(HttpRequest callbackRequest) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public UserInfo getUserInfo(AuthorizationCode authorizationCode, String callbackUrl) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private OIDCTokens getOidcTokens(AuthorizationCode authorizationCode, String callbackUrl, OIDCProviderMetadata providerMetadata) {
        LOGGER.debug("Retrieving OIDC tokens with user info claims set from {}", providerMetadata.getTokenEndpointURI());
        TokenResponse tokenResponse = getTokenResponse(providerMetadata.getTokenEndpointURI(), authorizationCode, callbackUrl);
        if (tokenResponse instanceof TokenErrorResponse) {
            ErrorObject errorObject = ((TokenErrorResponse) tokenResponse).getErrorObject();
            if (errorObject == null || errorObject.getCode() == null) {
                throw new IllegalStateException("Token request failed: No error code returned " + "(identity provider not reachable - check network proxy setting 'http.nonProxyHosts' in 'sonar.properties')");
            } else {
                throw new IllegalStateException("Token request failed: " + errorObject.toJSONObject());
            }
        }
        OIDCTokens oidcTokens = ((OIDCTokenResponse) tokenResponse).getOIDCTokens();
        if (isIdTokenSigned()) {
            validateIdToken(providerMetadata.getIssuer(), providerMetadata.getJWKSetURI(), oidcTokens.getIDToken());
        }
        return oidcTokens;
    }

    protected TokenResponse getTokenResponse(URI tokenEndpointURI, AuthorizationCode authorizationCode, String callbackUrl) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void validateIdToken(Issuer issuer, URI jwkSetURI, JWT idToken) {
        LOGGER.debug("Validating ID token with {} and key set from from {}", getIdTokenSignAlgorithm(), jwkSetURI);
        try {
            IDTokenValidator validator = createValidator(issuer, jwkSetURI.toURL());
            validator.validate(idToken, null);
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Invalid JWK set URL", e);
        } catch (BadJOSEException e) {
            throw new IllegalStateException("Invalid ID token", e);
        } catch (JOSEException e) {
            throw new IllegalStateException("Validating ID token failed", e);
        }
    }

    protected IDTokenValidator createValidator(Issuer issuer, URL jwkSetUrl) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected UserInfoResponse getUserInfoResponse(URI userInfoEndpointURI, BearerAccessToken accessToken) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected OIDCProviderMetadata getProviderMetadata() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private Scope getScope() {
        return Scope.parse(config.scopes());
    }

    private ClientID getClientId() {
        return new ClientID(config.clientId());
    }

    private Secret getClientSecret() {
        String secret = config.clientSecret();
        return secret == null ? new Secret("") : new Secret(secret);
    }

    private boolean isIdTokenSigned() {
        return config.idTokenSignAlgorithm() != null;
    }

    private JWSAlgorithm getIdTokenSignAlgorithm() {
        String algorithmName = config.idTokenSignAlgorithm();
        return algorithmName == null ? null : new JWSAlgorithm(algorithmName);
    }
}
