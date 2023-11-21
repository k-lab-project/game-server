package klab.sugangstar.service;

import klab.sugangstar.domain.User;
import klab.sugangstar.repository.CharacterRepository;
import klab.sugangstar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CharacterRepository characterRepository;
    @Transactional
    public void createUser(User user){
        userRepository.save(user);
    }

    @Transactional
    public Map<String, Long> checkUser(User user){
        Long message = userRepository.findByUsernameAndPassword(user.getLogin_id(),user.getLogin_password());
        Map<String, Long> result = new HashMap<>();
        if(message > 0) {
            Long id = userRepository.findOne(message).getId();
            message = characterRepository.findByUserId(message);
            result.put("character",message);
            result.put("userId",id);
            return result;
        }
        else{
            Long id = userRepository.save(user);
            result.put("character",message);
            result.put("userId",id);
            return result;
        }
    }
}
