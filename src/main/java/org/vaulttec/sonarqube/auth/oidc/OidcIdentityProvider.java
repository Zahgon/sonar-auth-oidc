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

import com.nimbusds.oauth2.sdk.AuthorizationCode;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.server.ServerSide;
import org.sonar.api.server.authentication.Display;
import org.sonar.api.server.authentication.OAuth2IdentityProvider;
import org.sonar.api.server.authentication.UserIdentity;

@ServerSide
public class OidcIdentityProvider implements OAuth2IdentityProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(OidcIdentityProvider.class);

    private final OidcConfiguration config;

    private final OidcClient client;

    private final UserIdentityFactory userIdentityFactory;

    public OidcIdentityProvider(OidcConfiguration config, OidcClient client, UserIdentityFactory userIdentityFactory) {
        this.config = config;
        this.client = client;
        this.userIdentityFactory = userIdentityFactory;
    }

    @Override
    public String getKey() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Display getDisplay() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isEnabled() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean allowsUsersToSignUp() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void init(InitContext context) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void callback(CallbackContext context) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
