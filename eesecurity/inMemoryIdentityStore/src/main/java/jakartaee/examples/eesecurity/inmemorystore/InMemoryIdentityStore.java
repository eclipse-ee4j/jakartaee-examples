/*
 * Permission to use, copy, modify, and/or distribute this software for any 
 * purpose with or without fee is hereby granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR(S) DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR(S) BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION
 * OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN
 * CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */
package jakartaee.examples.eesecurity.inmemorystore;

import static java.util.Arrays.asList;
import java.util.HashSet;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import javax.security.enterprise.identitystore.IdentityStore;

/**
 * The in-memory identity store.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@ApplicationScoped
public class InMemoryIdentityStore implements IdentityStore {

    /**
     * Validate the username / password credential.
     * 
     * @param usernamePasswordCredential the username / password credential.
     * @return the result.
     */
    public CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {
        if (usernamePasswordCredential.compareTo("test", "test")) {
            return new CredentialValidationResult("test", new HashSet<>(asList("user")));
        }
        return INVALID_RESULT;
    }
}
