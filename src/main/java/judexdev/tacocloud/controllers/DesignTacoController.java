package judexdev.tacocloud.controllers;

import jakarta.validation.Valid;
import judexdev.tacocloud.domain.Ingredient;
import judexdev.tacocloud.domain.Taco;
import judexdev.tacocloud.domain.TacoOrder;
import judexdev.tacocloud.repository.IngredientRepository;
import judexdev.tacocloud.repository.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientsRepo;
    private final TacoRepository tacoRepo;
    public DesignTacoController(IngredientRepository IngredientRepository, TacoRepository tacoRepo) {
        this.ingredientsRepo = IngredientRepository;
        this.tacoRepo = tacoRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientsRepo.findAll();

        Ingredient.Type[] types = Ingredient.Type.values();
        for (var type: types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        TacoOrder order = new TacoOrder();
        log.info("Created order: " + order);
        return order;
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    private Iterable<Ingredient> filterByType(
            Iterable<Ingredient> ingredients, Ingredient.Type type) {
        return StreamSupport.stream(ingredients.spliterator(), false)
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) return "design";
        tacoOrder.addTaco(taco);
        tacoRepo.save(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }
}
