package arhs.cube.robotshop.services;

import java.util.Collection;

import arhs.cube.robotshop.core.Robot;
import arhs.cube.robotshop.core.RobotModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bollenma
 */
public interface RobotService {

    Robot create(Robot robot);

    Robot retrieve(Long id);

    Robot update(Robot robot);

    void delete(Long id);

    void delete(Collection<Long> ids);

    Page<Robot> retrieveAll(Pageable pageable);

    Page<Robot> retrieveAllByModel(RobotModel model, Pageable pageable);

    Page<Robot> search(String search, Pageable pageable);
}
