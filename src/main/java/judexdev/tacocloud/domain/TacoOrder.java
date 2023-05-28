package judexdev.tacocloud.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class TacoOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date placedAt;

    @Column(length = 50)
    @NotBlank(message="Delivery name is required")
    private String deliveryName;

    @Column(length = 50)
    @NotBlank(message="Street is required")
    private String deliveryStreet;

    @Column(length = 50)
    @NotBlank(message="City is required")
    private String deliveryCity;

    @Column(length = 2)
    @NotBlank(message="State is required")
    private String deliveryState;

    @Column(length = 10)
    @NotBlank(message="Zip is required")
    private String deliveryZip;

    @Column(length = 16)
    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;

    @Column(length = 5)
    @Pattern(regexp = "^(0[1-9]|1[0-2])(/)([2-9]\\d)$",
    message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Column(length = 3)
    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}
