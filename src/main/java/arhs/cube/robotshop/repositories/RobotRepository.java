package arhs.cube.robotshop.repositories;

import arhs.cube.robotshop.core.Robot;
import arhs.cube.robotshop.core.RobotModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Robot entity.
 *
 * @author bollenma
 */
public interface RobotRepository extends JpaRepository<Robot, Long> {

    Page<Robot> findAllByModel(RobotModel model, Pageable pageable);

    Robot findByNameContaining(String name);


}
