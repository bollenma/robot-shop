package arhs.cube.robotshop.facades;

import java.util.Collection;

import arhs.cube.robotshop.dto.RobotDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The facade makes the link between the DTO robot and Entity robot.
 * No DTO is allowed past the Facade.
 *
 * @author bollenma
 */
public interface RobotFacade {

    /**
     * Create a new Robot
     *
     * @param robotDto The robot to be created
     * @return the created robot
     */
    RobotDto create(RobotDto robotDto);

    /**
     * Retrieve the "id" robot
     *
     * @param id id of the robot to get
     * @return the required robot
     */
    RobotDto retrieve(Long id);

    /**
     * Update the robot
     *
     * @param robotDto robot to be updated
     * @return the updated robot
     */
    RobotDto update(RobotDto robotDto);

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
    Page<RobotDto> retrieveAll(Pageable pageable);

    /**
     * Retrieve a page of all the robots by model (paginated)
     *
     * @param modelName Name of the model
     * @param pageable  page configuration
     * @return Page with robots of the required model
     */
    Page<RobotDto> retrieveAllByModel(String modelName, Pageable pageable);

    /**
     * Search for robots (paginated).
     *
     * @param query    Query to search for the robots
     * @param pageable page configuration
     * @return Page with found robots
     */
    Page<RobotDto> search(String query, Pageable pageable);

    /**
     * Search for robots of a specific model (paginated).
     *
     * @param query     Query to search for the robots
     * @param modelName Name of the model
     * @param pageable  page configuration
     * @return Page with found robots
     */
    Page<RobotDto> searchWithModel(String query, String modelName, Pageable pageable);

}
