package bzu.order_management.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product<Product_orderPk> {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String slug;

    @Column
    private String name;

    @Column
    private String reference;

    @Column
    private Long price;

    @Column
    private Long vat;

    @Column
    private Boolean stackable;

    @OneToMany(mappedBy = "productId")
    private List<Product_order> productId;

    @OneToMany(mappedBy = "productID")
    private List<Stock> stock;


}
