package ro.fortech.winewiki.profilemicro.repository;

import org.springframework.data.repository.CrudRepository;
import ro.fortech.winewiki.profilemicro.model.Person;


public interface PersonRepository extends CrudRepository<Person, Long> {

    Person findByUsername(String username);
    Person findByResettoken(String resettoken);
    Person findByMail(String mail);
}
