package arhs.cube.robotshop.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The Robot entity
 *
 * @author bollenma
 */
@Entity
public class Robot {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private RobotModel model;
    private Integer price;

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

    public RobotModel getModel() {
        return model;
    }

    public void setModel(final RobotModel model) {
        this.model = model;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(final Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("model", model)
                .append("price", price)
                .toString();
    }
}
