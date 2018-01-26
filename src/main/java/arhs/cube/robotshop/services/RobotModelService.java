package arhs.cube.robotshop.services;

import java.util.Collection;

import arhs.cube.robotshop.core.RobotModel;

/**
 * @author bollenma
 */
public interface RobotModelService {

    Collection<RobotModel> retrieveAll();

    /**
     * Retrieve a Robot model by its name
     *
     * @param modelName Name of the model
     * @return The robot model
     */
    RobotModel retrieve(String modelName);
}
