package arhs.cube.robotshop.repositories;

import java.util.List;

import arhs.cube.robotshop.core.Robot;
import arhs.cube.robotshop.core.RobotType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Robot entity.
 *
 * @author bollenma
 */
public interface RobotRepository extends JpaRepository<Robot, Long> {


    List<Robot> findRobotsByType(RobotType type);

    Robot findRobotByNameContaining(String name);
}
