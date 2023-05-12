package judexdev.tacocloud.repository;

import judexdev.tacocloud.domain.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
