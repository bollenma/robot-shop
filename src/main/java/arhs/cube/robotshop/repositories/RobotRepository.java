package arhs.cube.robotshop.repositories;

import java.util.List;

import arhs.cube.robotshop.core.Robot;
import arhs.cube.robotshop.core.RobotType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Robot entity.
 *
 * @author bollenma
 */
public interface RobotRepository extends JpaRepository<Robot, Long> {


    List<Robot> findAllByType(RobotType type);

    Page<Robot> findAllByType(RobotType type, Pageable pageable);

    Robot findByNameContaining(String name);


}
