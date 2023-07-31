package que.jpa.seminar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Locker {

  @Id
  @GeneratedValue
  @Column(name = "LOCKER_ID")
  private Long id;
  private String name;

  @OneToOne
  @JoinColumn(name = "ADMIN_ID")
  private Admin admin;

}
