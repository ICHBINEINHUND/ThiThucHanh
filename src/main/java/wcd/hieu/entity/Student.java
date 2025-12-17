package wcd.hieu.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_student")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "full_name", length = 50)
  private String full_name;

  @Column(name = "class_name", length = 50)
  private String class_name;

  @Column(name = "date_of_birth")
  @Temporal(TemporalType.TIMESTAMP)
  private Date date_of_birth;

  @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
  private List<Mark> marks;

  public Student() {
  }

  public Student(String full_name, String class_name, Date date_of_birth) {
    this.full_name = full_name;
    this.class_name = class_name;
    this.date_of_birth = date_of_birth;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFull_name() {
    return full_name;
  }

  public void setFull_name(String full_name) {
    this.full_name = full_name;
  }

  public String getClass_name() {
    return class_name;
  }

  public void setClass_name(String class_name) {
    this.class_name = class_name;
  }

  public Date getDate_of_birth() {
    return date_of_birth;
  }

  public void setDate_of_birth(Date date_of_birth) {
    this.date_of_birth = date_of_birth;
  }

  public List<Mark> getMarks() {
    return marks;
  }

  public void setMarks(List<Mark> marks) {
    this.marks = marks;
  }
}
