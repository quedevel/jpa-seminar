package que.jpa.seminar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem {

  @Id
  @GeneratedValue
  @Column(name = "ORDER_ITEM_ID")
  private Long id;
  private Long orderPrice;
  private Long count;

  @ManyToOne
  @JoinColumn(name = "ORDER_ID")
  private Order order;

  @ManyToOne
  @JoinColumn(name = "ITEM_ID")
  private Item item;

}
