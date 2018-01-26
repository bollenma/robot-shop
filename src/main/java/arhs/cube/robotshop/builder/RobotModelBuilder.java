package arhs.cube.robotshop.builder;

import arhs.cube.robotshop.core.RobotModel;

/**
 * @author bollenma
 */
public class RobotModelBuilder {

    /**
     * Build a robot model based on name and label
     *
     * @param name  Name of the model
     * @param label Label of the model to be displayed
     * @return
     */
    public static RobotModel build(final String name, final String label) {

        final RobotModel ret = new RobotModel();
        ret.setName(name);
        ret.setLabel(label);
        return ret;
    }

}
