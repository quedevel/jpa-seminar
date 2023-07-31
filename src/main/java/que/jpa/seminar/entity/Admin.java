package que.jpa.seminar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Admin {

  @Id
  @GeneratedValue
  @Column(name = "ADMIN_ID")
  private Long id;
  private String name;

  @ManyToOne
  @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
  private Team team;

  @OneToOne(mappedBy = "admin")
  private Locker locker;

  @ManyToMany
  @JoinTable(
      name = "ADMIN_SITE",
      joinColumns = @JoinColumn(name = "ADMIN_ID"),
      inverseJoinColumns = @JoinColumn(name = "SITE_ID")
  )
  private List<Site> sites = new ArrayList<>();
}
