package arhs.cube.robotshop.services;

import java.util.Collection;

import arhs.cube.robotshop.core.Robot;
import arhs.cube.robotshop.core.RobotType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bollenma
 */
public interface RobotService {

    Robot create(Robot robot);

    Robot retrieve(Long id);

    Robot update(Robot robot);

    void delete(Robot robot);

    void delete(Long id);

    Collection<Robot> retrieveAll();

    Collection<Robot> retrieveAllByType(RobotType type);

    Page<Robot> retrieveAllPaginated(Pageable pageable);

    Page<Robot> retrieveAllByTypePaginated(RobotType type, Pageable pageable);

    Page<Robot> searchPaginated(String search, Pageable pageable);
}
