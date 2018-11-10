package ro.fortech.winewiki.apigateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayPersonDto;
import ro.fortech.winewiki.apigateway.service.PersonService;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl
        implements UserDetailsService, ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private PersonService apiGatewayUserService;

    @Transactional (readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApiGatewayPersonDto user = apiGatewayUserService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
//
        Set<GrantedAuthority> grantedAuthorities = new LinkedHashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true,
                true, true, grantedAuthorities);
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {

    }
}
