package br.com.concrete.repository;

import br.com.concrete.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Emanuella Cavalcante on 13/06/2018.
 */
public interface UserRepository extends CrudRepository<User, String> {

    User findByEmail(String email);
}
