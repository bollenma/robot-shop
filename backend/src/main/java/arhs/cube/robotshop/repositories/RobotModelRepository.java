package arhs.cube.robotshop.repositories;

import arhs.cube.robotshop.core.RobotModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Robot entity.
 *
 * @author bollenma
 */
public interface RobotModelRepository extends JpaRepository<RobotModel, Long> {

    RobotModel findByName(String name);

}
