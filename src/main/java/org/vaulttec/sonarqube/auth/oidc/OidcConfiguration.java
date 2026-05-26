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

import org.sonar.api.CoreProperties;
import org.sonar.api.config.Configuration;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.server.ServerSide;
import javax.annotation.CheckForNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static org.sonar.api.CoreProperties.CATEGORY_SECURITY;
import static org.sonar.api.PropertyType.*;

@ServerSide
public class OidcConfiguration {

    private static final String PREFIX = "sonar.auth." + Constants.OIDC_IDENTITY_PROVIDER_KEY;

    private static final String CATEGORY = CATEGORY_SECURITY;

    private static final String SUBCATEGORY = Constants.OIDC_IDENTITY_PROVIDER_KEY;

    static final String ENABLED = PREFIX + ".enabled";

    static final String AUTO_LOGIN = PREFIX + ".autoLogin";

    static final String ISSUER_URI = PREFIX + ".issuerUri";

    static final String CLIENT_ID = PREFIX + ".clientId.secured";

    static final String CLIENT_SECRET = PREFIX + ".clientSecret.secured";

    static final String ALLOW_USERS_TO_SIGN_UP = PREFIX + ".allowUsersToSignUp";

    static final String ID_TOKEN_SIG_ALG = PREFIX + ".idTokenSigAlg";

    static final String ID_TOKEN_SIG_ALG_HMAC = "HS256";

    static final String ID_TOKEN_SIG_ALG_RSA = "RS256";

    static final String ID_TOKEN_SIG_ALG_ECDSA = "ES256";

    static final String SCOPES = PREFIX + ".scopes";

    private static final String SCOPES_DEFAULT_VALUE = "openid email profile";

    static final String LOGIN_STRATEGY = PREFIX + ".loginStrategy";

    static final String LOGIN_STRATEGY_UNIQUE = "Unique";

    static final String LOGIN_STRATEGY_PROVIDER_ID = "Same as OpenID Connect login";

    static final String LOGIN_STRATEGY_PREFERRED_USERNAME = "Preferred username";

    static final String LOGIN_STRATEGY_EMAIL = "Email";

    static final String LOGIN_STRATEGY_CUSTOM_CLAIM = "Custom claim";

    static final String LOGIN_STRATEGY_DEFAULT_VALUE = LOGIN_STRATEGY_PREFERRED_USERNAME;

    static final String LOGIN_STRATEGY_CUSTOM_CLAIM_NAME = PREFIX + ".loginStrategy.customClaim.name";

    private static final String LOGIN_STRATEGY_CUSTOM_CLAIM_NAME_DEFAULT_VALUE = "upn";

    static final String GROUPS_SYNC = PREFIX + ".groupsSync";

    static final String GROUPS_SYNC_CLAIM_NAME = PREFIX + ".groupsSync.claimName";

    private static final String GROUPS_SYNC_CLAIM_NAME_DEFAULT_VALUE = "groups";

    static final String ICON_PATH = PREFIX + ".iconPath";

    private static final String ICON_PATH_DEFAULT_VALUE = "/static/authoidc/openid.svg";

    static final String BACKGROUND_COLOR = PREFIX + ".backgroundColor";

    private static final String BACKGROUND_COLOR_DEFAULT_VALUE = "#236a97";

    static final String LOGIN_BUTTON_TEXT = PREFIX + ".loginButtonText";

    private static final String LOGIN_BUTTON_TEXT_DEFAULT_VALUE = "OpenID Connect";

    private final Configuration config;

    public OidcConfiguration(Configuration config) {
        this.config = config;
    }

    public String getBaseUrl() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getContextPath() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isEnabled() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isAutoLogin() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @CheckForNull
    public String issuerUri() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @CheckForNull
    public String clientId() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String clientSecret() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String scopes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String idTokenSignAlgorithm() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean allowUsersToSignUp() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String loginStrategy() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String loginStrategyCustomClaimName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean syncGroups() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String syncGroupsClaimName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String iconPath() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String backgroundColor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String loginButtonText() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<PropertyDefinition> definitions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
