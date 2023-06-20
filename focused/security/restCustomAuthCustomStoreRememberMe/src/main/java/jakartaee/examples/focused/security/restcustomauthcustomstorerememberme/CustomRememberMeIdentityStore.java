/*
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR(S) DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR(S) BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER
 * RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT,
 * NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE
 * USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package jakartaee.examples.focused.security.restcustomauthcustomstorerememberme;


import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.CallerPrincipal;
import jakarta.security.enterprise.credential.RememberMeCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.RememberMeIdentityStore;

import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;

/**
 * A custom remember-me identity store that will be picked up automatically by Jakarta Security.
 *
 * <p>
 * Jakarta Security picks up any enabled CDI bean that implements <code>RememberMeIdentityStore</code>.
 *
 * @author Arjan Tijms
 *
 */
@ApplicationScoped
public class CustomRememberMeIdentityStore implements RememberMeIdentityStore {

    private final Map<String, CredentialValidationResult> tokenToIdentityMap = new ConcurrentHashMap<>();

    @Override
    public CredentialValidationResult validate(RememberMeCredential credential) {
        if (tokenToIdentityMap.containsKey(credential.getToken())) {
            return tokenToIdentityMap.get(credential.getToken());
        }

        return INVALID_RESULT;
    }

    @Override
    public String generateLoginToken(CallerPrincipal callerPrincipal, Set<String> groups) {
        var token = UUID.randomUUID().toString();

        tokenToIdentityMap.put(token, new CredentialValidationResult(callerPrincipal, groups));

        return token;
    }

    @Override
    public void removeLoginToken(String token) {
        tokenToIdentityMap.remove(token);
    }

}
