package wcd.hieu.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "t_mark")
public class Mark {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "subject", length = 50)
  private String subject;

  @Column(name = "core_value")
  private Float score_value;

  @ManyToOne
  @JoinColumn(name = "student_id", referencedColumnName = "id")
  private Student student;

  public Mark() {
  }

  public Mark(String subject, Float score_value, Student student) {
    this.subject = subject;
    this.score_value = score_value;
    this.student = student;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public Float getScore_value() {
    return score_value;
  }

  public void setScore_value(Float score_value) {
    this.score_value = score_value;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }
}
