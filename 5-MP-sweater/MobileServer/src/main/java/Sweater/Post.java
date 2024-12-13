package Sweater;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "activity_name")
    private String activityName;

    @Column(name = "member_limit")
    private Integer memberLimit;

    @Column(name = "date")
    private String date;    //Date - datatype

    @Column(name = "time")
    private String time;    //Time - datatype

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getMemberLimit() {
        return memberLimit;
    }

    public void setMemberLimit(Integer memberLimit) {
        this.memberLimit = memberLimit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + getId() +
                ", userName= " + getUserName() + '\'' +
                ", activityName=" + getActivityName() + '\'' +
                ", memberLimit" + getMemberLimit().toString() + '\'' +
                ", date=" + getDate() + '\'' +
                ", time=" + getTime() + '\'' +
                ", location=" + getLocation() + '\'' +
                ", description=" + getDescription() + '\'' +
                '}' + '\n';
    }
}
