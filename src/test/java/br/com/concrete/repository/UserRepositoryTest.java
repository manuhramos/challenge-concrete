package br.com.concrete.repository;

import br.com.concrete.model.Phone;
import br.com.concrete.model.User;
import br.com.concrete.service.UserService;
import br.com.concrete.util.Encrypt;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Emanuella Cavalcante on 13/06/2018.
 */
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    @Test
    public void createTest() throws Exception {
        User test = new User();
        test.setName("User teste");
        test.setEmail("teste@teste.com");
        test.setPassword("teste");
        Phone phone = new Phone();
        phone.setDdd("11");
        phone.setNumber("12345678");
        ArrayList<Phone> phones = new ArrayList<>();
        phones.add(phone);
        test.setPhones(phones);
        this.service.create(test);
        User user = this.repository.findByEmail("teste@teste.com");
        assertThat(user.getEmail()).isEqualTo("teste@teste.com");
        assertThat(user.getPassword()).isEqualTo(Encrypt.encryptHmac(test.getPassword()));
        assertThat(user.getPhones().size()).isEqualTo(1);
    }

    @After
    public void cleanUp() {
        this.repository.deleteAll();
    }
}
