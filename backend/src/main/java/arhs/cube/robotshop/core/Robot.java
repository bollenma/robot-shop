package arhs.cube.robotshop.core;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import arhs.cube.robotshop.dto.RobotDto;
import lombok.Data;

/**
 * The Robot entity
 *
 * @author bollenma
 */
@Entity
@Data
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

    public Robot() {
    }

    public Robot(final RobotDto dto, final RobotModel model) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.model = model;
        this.price = dto.getPrice();
        this.pictureHash = dto.getPictureHash();
        this.soldout = dto.isSoldout();
    }
}
