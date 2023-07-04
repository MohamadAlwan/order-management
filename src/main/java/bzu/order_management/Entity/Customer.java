package bzu.order_management.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String firstName;
    @Column
    private String LastName;
    @Column
    private Date bornAt;

    @OneToMany(mappedBy = "customerId")
    private List<Order> orders;
}
