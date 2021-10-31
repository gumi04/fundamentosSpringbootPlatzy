package com.fundamentos.springboot.fundamentos.service;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final Log LOG = LogFactory.getLog(UserService.class);

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveTransactional(List<User> users){
        users.stream()
                .peek(user -> LOG.info("usuario insertado :"+user))
                .forEach(userRepository::save);
    }

    public List<User> getAllUsers(){
        return  userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.delete(new User(id));
    }

    public User update(User user, Long id) {
         return userRepository.findById(id)
                .map(
                        user1 -> {
                            user1.setEmail(user.getEmail());
                            user1.setName(user.getName());
                            user1.setBirthDate(user.getBirthDate());
                            return  userRepository.save(user1);
                        }
                ).orElse(null);
    }
}
