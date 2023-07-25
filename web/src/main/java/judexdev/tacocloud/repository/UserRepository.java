package judexdev.tacocloud.repository;

import judexdev.tacocloud.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    User findByUsername(String username);
}
