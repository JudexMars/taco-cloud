package judexdev.tacocloud.controllers.api;

import judexdev.tacocloud.domain.Taco;
import judexdev.tacocloud.domain.TacoOrder;
import judexdev.tacocloud.repository.OrderRepository;
import judexdev.tacocloud.repository.TacoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin("http://tacocloud:8080")
public class TacoController {
    private final TacoRepository tacoRepo;
    private final OrderRepository orderRepo;

    public TacoController(TacoRepository tacoRepo, OrderRepository orderRepo) {
        this.tacoRepo = tacoRepo;
        this.orderRepo = orderRepo;
    }

    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoRepo.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") String id) {
        Optional<Taco> taco = tacoRepo.findById(id);
        return taco
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public TacoOrder patchOrder(@PathVariable String orderId, @RequestBody TacoOrder patch) {
        Optional<TacoOrder> originalOpt = orderRepo.findById(orderId);
        if (originalOpt.isPresent()) {
            TacoOrder original = originalOpt.get();

            if (patch.getTacos() != null) original.setTacos(patch.getTacos());
            if (patch.getCcCVV() != null) original.setCcCVV(patch.getCcCVV());
            if (patch.getCcExpiration() != null) original.setCcExpiration(patch.getCcExpiration());
            if (patch.getCcNumber() != null) original.setCcNumber(patch.getCcNumber());
            if (patch.getDeliveryCity() != null) original.setDeliveryCity(patch.getDeliveryCity());
            if (patch.getDeliveryName() != null) original.setDeliveryName(patch.getDeliveryName());
            if (patch.getDeliveryState() != null) original.setDeliveryState(patch.getDeliveryState());
            if (patch.getDeliveryStreet() != null) original.setDeliveryStreet(patch.getDeliveryStreet());
            if (patch.getDeliveryZip() != null) original.setDeliveryZip(patch.getDeliveryZip());

            return orderRepo.save(original);
        }
        return null;
    }

    @PutMapping(path = "/{orderId}", consumes = "application/json")
    public TacoOrder putOrder(@PathVariable String orderId, @RequestBody TacoOrder order) {
        order.setId(orderId);
        return orderRepo.save(order);
    }

    @DeleteMapping(path = "/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable String orderId) {
        try {
            orderRepo.deleteById(orderId);
        } catch (IllegalArgumentException ignored) {}
    }
}
