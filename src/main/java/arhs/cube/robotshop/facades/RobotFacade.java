package arhs.cube.robotshop.facades;

import java.util.Collection;

import arhs.cube.robotshop.dto.RobotDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Mapping between DTO robot and Entity robot.
 * No DTO is allowed past the Facade.
 *
 * @author bollenma
 */
public interface RobotFacade {

    RobotDto create(RobotDto robotDto);

    RobotDto retrieve(Long id);

    RobotDto update(RobotDto robotDto);

    void delete(Long id);

    void delete(Collection<Long> ids);

    Page<RobotDto> retrieveAll(Pageable pageable);

    Page<RobotDto> retrieveAllByModel(String modelName, Pageable pageable);

    Page<RobotDto> search(String search, Pageable pageable);

    Page<RobotDto> searchWithModel(String search, String modelName, Pageable pageable);

}
