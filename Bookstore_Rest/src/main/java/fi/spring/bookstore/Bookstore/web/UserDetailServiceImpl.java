package fi.spring.bookstore.Bookstore.web;

	import org.springframework.beans.factory.annotation.Autowired ;  
	import org.springframework.security.core.authority.AuthorityUtils;
	import org.springframework.security.core.userdetails.UserDetails;
	import org.springframework.security.core.userdetails.UserDetailsService;
	import org.springframework.security.core.userdetails.UsernameNotFoundException;
	import org.springframework.stereotype.Service;

import fi.spring.bookstore.Bookstore.domain.User;
import fi.spring.bookstore.Bookstore.domain.UserRepository;
  
	@Service
	public class UserDetailServiceImpl implements UserDetailsService  {
		private final UserRepository upository;

		@Autowired
		public UserDetailServiceImpl(UserRepository userRepository) {
			this.upository = userRepository;
		}

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	    {   
	    	User cuser = upository.findByUsername(username);
	        UserDetails user = new org.springframework.security.core.userdetails.User(username, cuser.getPasswordHash(), 
	        		AuthorityUtils.createAuthorityList(cuser.getRole()));
	        return user;
	    }   
	} 


