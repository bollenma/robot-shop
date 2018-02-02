package arhs.cube.robotshop.builder;

import arhs.cube.robotshop.core.Robot;
import arhs.cube.robotshop.core.RobotModel;

/**
 * @author bollenma
 */
public final class RobotBuilder {

    private RobotBuilder() {
    }

    private final static Float defaultPrice = 10000F;

    /**
     * Build a robot based on name, model. Uses default price.
     *
     * @param name  name of the robot
     * @param model model of the robot
     * @return the built robot.
     */
    public static Robot build(final String name, final RobotModel model) {

        return build(name, model, defaultPrice, name, false);
    }

    /**
     * Build a robot based on name, model. Uses default price.
     *
     * @param name  name of the robot
     * @param model model of the robot
     * @return the built robot.
     */
    public static Robot build(final String name, final RobotModel model, final boolean soldout) {

        return build(name, model, defaultPrice, name, soldout);
    }

    /**
     * Build a robot based on name, model and price
     *
     * @param name  name of the robot
     * @param model model of the robot
     * @param price price of the robot
     * @return the built robot.
     */
    public static Robot build(final String name, final RobotModel model, final Float price, final String pictureHash, final boolean soldout) {

        final Robot ret = new Robot();
        ret.setName(name);
        ret.setModel(model);
        ret.setPrice(price);
        ret.setPictureHash(pictureHash);
        ret.setSoldout(soldout);
        return ret;
    }
}
