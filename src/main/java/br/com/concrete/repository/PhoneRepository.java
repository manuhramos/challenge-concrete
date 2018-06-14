package br.com.concrete.repository;

import br.com.concrete.model.Phone;
import br.com.concrete.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Emanuella Cavalcante on 13/06/2018.
 */
public interface PhoneRepository extends CrudRepository<Phone, Long> {
}
