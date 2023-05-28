package judexdev.tacocloud.repository;

import judexdev.tacocloud.domain.TacoOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository
        extends CrudRepository<TacoOrder, Long> {

    /*
    This is a magnificent example of Spring Data magic! The framework reads the method's name and generates
    the needed query by itself. As we already know, the implementation is also created automatically which makes
    the whole Data Persistence process much easier!
     */
    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    /**
     * Finds all orders with specified zip-code that were placed between two provided dates
     * @param deliveryZip zip-code
     * @param startDate the earliest possible date
     * @param endDate the latest possible date
     * @return list of orders
     */
    List<TacoOrder> readTacoOrderByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);

    @Query("select o from TacoOrder o where o.deliveryCity='Seattle'")
    List<TacoOrder> readOrdersDeliveredToSeattle();
}
