package ua.domaly.lwserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.domaly.lwserver.entity.MyUserDetails;
import ua.domaly.lwserver.entity.User;
import ua.domaly.lwserver.repository.UserRepository;

/**
 * Class that implements {@link UserDetailsService}.
 */
@Service
@RequiredArgsConstructor
public class MyUserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));

        return new MyUserDetails(user);
    }
}
