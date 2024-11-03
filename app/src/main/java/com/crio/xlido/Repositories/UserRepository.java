package com.crio.xlido.Repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import com.crio.xlido.entities.User;

public class UserRepository {

    private final Map<Long, User> storage = new HashMap<>();
    private AtomicLong id = new AtomicLong(0);
    

    public User save(User entity){
        
        User user = new User(id.incrementAndGet(),entity);
        storage.putIfAbsent(user.getId(), user );
        return user;
    }

    public List<String> listAll(ArrayList<User> users){

        return null;
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    } 
}
