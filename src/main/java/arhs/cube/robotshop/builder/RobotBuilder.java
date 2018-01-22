package arhs.cube.robotshop.builder;

import arhs.cube.robotshop.core.Robot;
import arhs.cube.robotshop.core.RobotModel;

/**
 * @author bollenma
 */
public final class RobotBuilder {

    private RobotBuilder() {
    }

    private final static RobotModel defaultModel = RobotModel.ASTROMECH;
    private final static Integer defaultPrice = 100000;

    /**
     * Build a robot based on name. Uses default model and price.
     *
     * @param name name of the robot
     * @return the built robot.
     */
    public static Robot build(final String name) {

        return build(name, defaultModel, defaultPrice);
    }

    /**
     * Build a robot based on name, model. Uses default price.
     *
     * @param name  name of the robot
     * @param model model of the robot
     * @return the built robot.
     */
    public static Robot build(final String name, final RobotModel model) {

        return build(name, model, defaultPrice);
    }

    /**
     * Build a robot based on name, model and price
     *
     * @param name  name of the robot
     * @param model model of the robot
     * @param price price of the robot
     * @return the built robot.
     */
    public static Robot build(final String name, final RobotModel model, final Integer price) {

        final Robot ret = new Robot();
        ret.setName(name);
        ret.setModel(model);
        ret.setPrice(price);
        return ret;
    }
}
