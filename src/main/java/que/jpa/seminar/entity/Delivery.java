package que.jpa.seminar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Delivery {

  @Id
  @GeneratedValue
  @Column(name = "DELIVERY_ID")
  private Long id;
  private String city;
  private String street;
  private String zipcode;
  private String status;

  @OneToOne(mappedBy = "delivery")
  private Order order;
}
