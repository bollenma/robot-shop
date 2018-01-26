package arhs.cube.robotshop.core;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import arhs.cube.robotshop.dto.RobotDto;
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

    @ManyToOne
    @NotNull
    private RobotModel model;

    @NotNull
    private Float price;

    @NotNull
    private String pictureHash;

    @NotNull
    private boolean soldout;

    public Robot(){

    }

    public Robot(final RobotDto dto, final RobotModel model){
        this.id = dto.getId();
        this.name = dto.getName();
        this.model = model;
        this.price = dto.getPrice();
        this.pictureHash = dto.getPictureHash();
        this.soldout = dto.isSoldout();
    }


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

    public Float getPrice() {
        return price;
    }

    public void setPrice(final Float price) {
        this.price = price;
    }

    public String getPictureHash() {
        return pictureHash;
    }

    public void setPictureHash(final String pictureHash) {
        this.pictureHash = pictureHash;
    }

    public boolean isSoldout() {
        return soldout;
    }

    public void setSoldout(final boolean soldout) {
        this.soldout = soldout;
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
