package hellojpa.entity;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    private int age;

    @Column(name = "TEAM_ID")
    private Long TeamId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getTeamId() {
        return TeamId;
    }

    public void setTeamId(Long teamId) {
        TeamId = teamId;
    }
}
