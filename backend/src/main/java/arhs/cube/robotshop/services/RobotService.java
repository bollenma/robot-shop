package arhs.cube.robotshop.services;

import java.util.Collection;

import arhs.cube.robotshop.core.Robot;
import arhs.cube.robotshop.core.RobotModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * CRUD service for Robot
 *
 * @author bollenma
 */
public interface RobotService {

    /**
     * Create a new Robot
     *
     * @param robot The robot to be created
     * @return the created robot
     */
    Robot create(Robot robot);

    /**
     * Retrieve the "id" robot
     *
     * @param id id of the robot to get
     * @return the required robot
     */
    Robot retrieve(Long id);

    /**
     * Update the robot
     *
     * @param robot robot to be updated
     * @return the updated robot
     */
    Robot update(Robot robot);

    /**
     * Delete the "id" robot
     *
     * @param id id of the robot to delete
     */
    void delete(Long id);

    /**
     * Delete all the robots for the given ids
     *
     * @param ids ids of the robot to be deleted
     */
    void delete(Collection<Long> ids);

    /**
     * Retrieve a page of all the robots (paginated)
     *
     * @param pageable page configuration
     * @return Page with robots
     */
    Page<Robot> retrieveAll(Pageable pageable);

    /**
     * Retrieve a page of all the robots by model (paginated)
     *
     * @param model    the model of robot
     * @param pageable page configuration
     * @return Page with robots of the required model
     */
    Page<Robot> retrieveAllByModel(RobotModel model, Pageable pageable);

    /**
     * Search for robots (paginated).
     *
     * @param query    Query to search for the robots
     * @param pageable page configuration
     * @return Page with found robots
     */
    Page<Robot> search(String query, Pageable pageable);

    /**
     * Search for robots of a specific model (paginated).
     *
     * @param query    Query to search for the robots
     * @param model    The model of robot
     * @param pageable page configuration
     * @return Page with found robots
     */
    Page<Robot> searchWithModel(String query, RobotModel model, Pageable pageable);

}
