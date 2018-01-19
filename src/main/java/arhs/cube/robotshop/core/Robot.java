package arhs.cube.robotshop.core;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The Robot entity
 *
 * @author bollenma
 */
@Entity
public class Robot {

    @Id
    private Long id;

    @Column
    private String name;
    private RobotType type;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public RobotType getType() {
        return type;
    }

    public void setType(final RobotType type) {
        this.type = type;
    }
}
