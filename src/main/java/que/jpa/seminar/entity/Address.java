package que.jpa.seminar.entity;

import jakarta.persistence.Embeddable;

/**
 * packageName    : que.jpa.seminar.entity
 * fileName       : Address
 * author         : quedevel
 * date           : 2023-08-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-08-08        quedevel       최초 생성
 */
@Embeddable
public class Address {

  private String city;
  private String street;
  private String zipcode;

}
