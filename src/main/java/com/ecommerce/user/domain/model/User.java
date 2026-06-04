package com.ecommerce.user.domain.model;

import java.util.Set;

public class User {

    private Long id;
    private String email;
    private String password;
    private String name;
    private AuthProvider provider;
    private String providerId;
    private Set<Role> roles;
    private boolean enabled;

    public User() {}

    public User(Long id, String email, String password, String name, AuthProvider provider, String providerId, Set<Role> roles, boolean enabled) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.provider = provider;
        this.providerId = providerId;
        this.roles = roles;
        this.enabled = enabled;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public AuthProvider getProvider() { return provider; }
    public void setProvider(AuthProvider provider) { this.provider = provider; }
    public String getProviderId() { return providerId; }
    public void setProviderId(String providerId) { this.providerId = providerId; }
    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}
