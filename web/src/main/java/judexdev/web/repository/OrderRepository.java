package judexdev.web.repository;

import judexdev.web.domain.TacoOrder;
import judexdev.web.domain.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

public interface OrderRepository
        extends CrudRepository<TacoOrder, String> {

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

    @Query("SELECT * FROM tacoclouddb.orders WHERE deliveryCity = 'Seattle'")
    List<TacoOrder> readOrdersDeliveredToSeattle();

    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
