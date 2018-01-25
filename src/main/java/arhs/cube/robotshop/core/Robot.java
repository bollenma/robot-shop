package arhs.cube.robotshop.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The Robot entity
 *
 * @author bollenma
 */
@Entity
public class Robot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private RobotModel model;

    @NotNull
    private Integer price;

    @NotNull
    private String pictureHash;

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

    public String getPictureHash() {
        return pictureHash;
    }

    public void setPictureHash(final String pictureHash) {
        this.pictureHash = pictureHash;
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
