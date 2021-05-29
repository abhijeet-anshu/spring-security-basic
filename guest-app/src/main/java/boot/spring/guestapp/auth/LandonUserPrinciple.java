package boot.spring.guestapp.auth;

import com.google.common.collect.ImmutableSet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class LandonUserPrinciple implements UserDetails {
    private User user;
    private List<AuthGroup> authGroups;

    public LandonUserPrinciple(User user, List<AuthGroup> authGroups) {
        super();
        this.user = user;
        this.authGroups = authGroups;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(null==authGroups)
            return Collections.emptySet();
        ImmutableSet.Builder<GrantedAuthority> setBuilder = new ImmutableSet.Builder<>();
        authGroups.forEach(authGroup ->
                setBuilder.add(new SimpleGrantedAuthority(authGroup.getAuthGroup())));
        return setBuilder.build();
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
