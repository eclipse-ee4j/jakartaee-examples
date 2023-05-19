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
package jakartaee.examples.focused.security.restbasicauthdbstoreandcustomstore;

import static java.math.BigInteger.ONE;

import java.math.BigInteger;
import java.util.Map;

import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationScoped
@BasicAuthenticationMechanismDefinition(
    realmName = "basicAuth"
)
@DatabaseIdentityStoreDefinition(
    callerQuery = "select password from basic_auth_user where username = ?",
    groupsQuery = "select name from basic_auth_group where username = ?",
    hashAlgorithmParameters = {
        "Pbkdf2PasswordHash.Iterations=3072",
        "Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512",
        "Pbkdf2PasswordHash.SaltSizeBytes=64"
    }
)
@DeclareRoles("user")
@ApplicationPath("/rest")
public class ApplicationConfig extends Application {

    /**
     * Id of the one and only user we populate in out DB.
     */
    private static final BigInteger USER_ID = ONE;

    /**
     * Id of the one and only group we populate in out DB.
     */
    private static final BigInteger GROUP_ID = ONE;

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    @Transactional
    public void onStart(@Observes @Initialized(ApplicationScoped.class) Object applicationContext) {
        passwordHash.initialize(Map.of(
            "Pbkdf2PasswordHash.Iterations", "3072",
            "Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA512",
            "Pbkdf2PasswordHash.SaltSizeBytes", "64"));

        if (entityManager.find(User.class, USER_ID) == null) {
            var user = new User();
            user.id  = USER_ID;
            user.username = "john";
            user.password = passwordHash.generate("secret1".toCharArray());
            entityManager.persist(user);
        }

        if (entityManager.find(Group.class, GROUP_ID) == null) {
            var group = new Group();
            group.id = GROUP_ID;
            group.name = "user";
            group.username = "john";
            entityManager.persist(group);
        }
    }

}

@Entity
@Table(name = "basic_auth_user")
class User {
    @Id
    BigInteger id;

    @Column(name = "password")
    String password;

    @Column(name = "username", unique = true)
    String username;
}

@Entity
@Table(name = "basic_auth_group")
class Group {
    @Column(name = "id")
    @Id
    BigInteger id;

    @Column(name = "name")
    String name;

    @Column(name = "username")
    String username;
}


