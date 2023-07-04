package bzu.order_management.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(Product_orderPK.class)
public class Product_order {

    @Id
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="productId" , referencedColumnName = "id")
    private Product productId;

    @Id
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="orderId" , referencedColumnName = "id")
    private Order orderId;


    @Column
    private Integer quantity;

    @Column
    private Long price;

    @Column
    private Long vat;

}
