package klab.sugangstar.service;

import klab.sugangstar.domain.User;
import klab.sugangstar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @Transactional
    public void createUser(User user){
        userRepository.save(user);
    }
}
