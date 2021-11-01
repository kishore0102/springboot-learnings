package edu.deadshot.springbootbasicauth1.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.deadshot.springbootbasicauth1.models.User;

public class CustomUserDetails implements UserDetails {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] rolesArray = user.getRoles().split(",");
        ArrayList<String> rolesList = new ArrayList<String>(Arrays.asList(rolesArray));
        Collection<GrantedAuthority> authorities = rolesList.stream().map(auth -> new SimpleGrantedAuthority(auth))
                .collect(Collectors.toList());
        System.out.println("roles -> " + user.getUsername() + " " + authorities.toString());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
