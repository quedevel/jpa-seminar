package que.jpa.seminar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Site {

  @Id
  @GeneratedValue
  @Column(name = "SITE_ID")
  private Long id;
  private String name;

  @ManyToMany(mappedBy = "sites")
  private List<Admin> admins = new ArrayList<>();

}
