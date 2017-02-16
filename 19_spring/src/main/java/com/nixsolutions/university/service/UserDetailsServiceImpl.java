package com.nixsolutions.university.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.university.dao.PersonDAO;
import com.nixsolutions.university.model.Person;
import com.nixsolutions.university.model.PersonType;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private PersonDAO userDao;

  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    com.nixsolutions.university.model.Person user = userDao.getByLogin(username);
    List<GrantedAuthority> authorities = buildUserAuthority(user.getPersonType());
    return buildUserForAuthentication(user, authorities);
  }

  private User buildUserForAuthentication(Person user, List<GrantedAuthority> authorities) {
    return new User(user.getLogin(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
  }

  private List<GrantedAuthority> buildUserAuthority(PersonType userRole) {
    Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
    setAuths.add(new SimpleGrantedAuthority(userRole.getValue()));
    List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
    return Result;
  }

}
