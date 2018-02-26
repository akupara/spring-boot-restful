package in.xroc.dev.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "dept")
public class Department extends BaseEntity {
    @NotBlank
    @Column(unique = true)
    @Size(min = 1, max = 20)
    private String code;

    @NotBlank
    @Size(max = 200)
    private String name;

    @Size(max = 200)
    private String location;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
