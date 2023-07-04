package bzu.order_management.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @GeneratedValue
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="productId",referencedColumnName = "id")
    private Product productID;

    @Column
    private Integer quantity;

    @Column
    private Date updatedAt;
}
