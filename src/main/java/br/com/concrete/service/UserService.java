package br.com.concrete.service;

import br.com.concrete.model.Message;
import br.com.concrete.model.Phone;
import br.com.concrete.model.User;
import br.com.concrete.model.exception.InvalidPasswordException;
import br.com.concrete.model.exception.InvalidSessionException;
import br.com.concrete.model.exception.InvalidTokenException;
import br.com.concrete.model.exception.UserNotFoundException;
import br.com.concrete.model.vo.Login;
import br.com.concrete.repository.PhoneRepository;
import br.com.concrete.repository.UserRepository;
import br.com.concrete.util.Encrypt;
import br.com.concrete.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * Created by Emanuella Cavalcante on 13/06/2018.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User create(User user){
        Date now = new Date();
        user.setCreated(now);
        user.setModified(now);
        user.setLast_login(now);
        user.setPassword(Encrypt.encryptHmac(user.getPassword()));
        user.setToken(JWTUtil.create(String.format("%s:%d", user.getEmail(), System.currentTimeMillis())));
        userRepository.save(user);

        for (Phone phone: user.getPhones()) {
            phone.setUser(user);
            phoneRepository.save(phone);
        }
        return user;
    }

    public User login(Login login) throws UserNotFoundException, InvalidPasswordException {
        User user = userRepository.findByEmail(login.getEmail());

        if(user == null)
            throw new UserNotFoundException(Message.USER_INVALID.getValue());

        if(!user.getPassword().equals(Encrypt.encryptHmac(login.getPassword())))
            throw new InvalidPasswordException(Message.USER_INVALID.getValue());

        user.setModified(new Date());
        user.setToken(JWTUtil.create(String.format("%s:%d", user.getEmail(), System.currentTimeMillis())));
        userRepository.save(user);
        return user;
    }

    public User getUserProfile(String token, String id) throws InvalidTokenException, InvalidSessionException {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent() && !user.get().getToken().equals(token))
            throw new InvalidTokenException(Message.UNAUTHORIZED.getValue());

        if(user.get().validateSession())
            return user.get();

        throw new InvalidSessionException(Message.SESSION_INVALID.getValue());
    }
}
