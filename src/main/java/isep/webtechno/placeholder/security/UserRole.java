package isep.webtechno.placeholder.security;

import com.google.common.collect.Sets;
import java.util.Set;

import static isep.webtechno.placeholder.security.UserPermission.*;

public enum UserRole {

    ADMIN(Sets.newHashSet(USER_READ,USER_WRITE)),
    USER(Sets.newHashSet());

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }


}
