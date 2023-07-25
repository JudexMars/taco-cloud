package judexdev.tacocloud.repository;

import judexdev.tacocloud.domain.Taco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TacoRepository extends CrudRepository<Taco, String>, PagingAndSortingRepository<Taco, String> {

}
