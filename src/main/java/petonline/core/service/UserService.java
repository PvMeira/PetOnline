package petonline.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import petonline.core.config.security.AppUserPrincipal;
import petonline.core.model.User;
import petonline.core.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = this.findByEmail(userName);
		if (null != user) {
			return new AppUserPrincipal(user);
		} else {
			throw new UsernameNotFoundException("Username " + userName + " not found.");
		}
	}

	public User findByEmail(String email) {
		Optional<User> user = this.repository.findByEmail(email);
		return user.orElse(null);
	}
}
