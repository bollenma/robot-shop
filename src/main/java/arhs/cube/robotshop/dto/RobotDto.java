package arhs.cube.robotshop.dto;

import arhs.cube.robotshop.core.Robot;
import arhs.cube.robotshop.core.RobotModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author bollenma
 */
public class RobotDto {

    private Long id;
    private String name;
    private String model;
    private Float price;
    private String pictureHash;
    private boolean soldout;

    public RobotDto() {

    }

    public RobotDto(final Robot robot) {
        this.id = robot.getId();
        this.name = robot.getName();
        final RobotModel model = robot.getModel();
        this.model = model == null ? null : model.getName();
        this.price = robot.getPrice();
        this.pictureHash = robot.getPictureHash();
        this.soldout = robot.isSoldout();
    }

    public static RobotDto fromEntity(final Robot robot){
        return new RobotDto(robot);
    }

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPictureHash() {
        return pictureHash;
    }

    public void setPictureHash(String pictureHash) {
        this.pictureHash = pictureHash;
    }

    public boolean isSoldout() {
        return soldout;
    }

    public void setSoldout(boolean soldout) {
        this.soldout = soldout;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("model", model)
                .append("price", price)
                .append("pictureHash", pictureHash)
                .append("soldout", soldout)
                .toString();
    }
}
