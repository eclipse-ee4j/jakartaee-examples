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
package jakartaee.examples.focused.security.restbasicauthcustomstorehandler;

import static jakarta.interceptor.Interceptor.Priority.APPLICATION;
import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import static jakarta.security.enterprise.identitystore.CredentialValidationResult.Status.INVALID;
import static jakarta.security.enterprise.identitystore.CredentialValidationResult.Status.NOT_VALIDATED;

import java.util.HashSet;
import java.util.Set;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.security.enterprise.identitystore.IdentityStoreHandler;

@Alternative
@Priority(APPLICATION)
@ApplicationScoped
public class CustomIdentityStoreHandler implements IdentityStoreHandler {
    
    @Inject
    Instance<IdentityStore> identityStores;
    
    @Override
    public CredentialValidationResult validate(Credential credential) {
        CredentialValidationResult result = null;
        Set<String> groups = new HashSet<>();
        
        for (IdentityStore identityStore : identityStores) {
            result = identityStore.validate(credential);
            if (result.getStatus() == NOT_VALIDATED) {
                // Identity store probably doesn't handle our credential type
                continue;
            }
            
            if (result.getStatus() == INVALID) {
                // Identity store handled our credential type and determined its
                // invalid. End the loop.
                return INVALID_RESULT;
            }
            
            groups.addAll(result.getCallerGroups());
        }
        
        return new CredentialValidationResult(
            result.getCallerPrincipal(), groups);
    }

}
