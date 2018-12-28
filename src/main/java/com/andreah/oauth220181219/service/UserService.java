package com.andreah.oauth220181219.service;

import java.util.List;

public interface UserService {
    User save(User user);
    List<User> findAll();
    void delete(long id);
}
