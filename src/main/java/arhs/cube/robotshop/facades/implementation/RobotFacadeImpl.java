package arhs.cube.robotshop.facades.implementation;

import java.util.Collection;
import javax.inject.Inject;

import arhs.cube.robotshop.common.exception.ApplicationException;
import arhs.cube.robotshop.core.Robot;
import arhs.cube.robotshop.core.RobotModel;
import arhs.cube.robotshop.dto.RobotDto;
import arhs.cube.robotshop.facades.RobotFacade;
import arhs.cube.robotshop.services.RobotModelService;
import arhs.cube.robotshop.services.RobotService;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author bollenma
 */
@Service
public class RobotFacadeImpl implements RobotFacade {

    @Inject
    private RobotService robotService;

    @Inject
    private RobotModelService robotModelService;

    @Override
    public RobotDto create(final RobotDto robotDto) {
        Validate.notNull(robotDto);

        // Cannot create a robot with an non-existing model
        final String modelName = robotDto.getModel();
        final RobotModel model = robotModelService.retrieve(modelName);
        if (model == null) {
            throw new ApplicationException("Cannot create robot. Robot model does not exist")
                    .addContextValue("robot", robotDto)
                    .addContextValue("robot model", modelName);
        }

        final Robot robot = new Robot(robotDto, model);

        final Robot createdRobot = robotService.create(robot);

        return new RobotDto(createdRobot);
    }

    @Override
    public RobotDto retrieve(final Long id) {
        Validate.notNull(id);

        final Robot robot = robotService.retrieve(id);

        return new RobotDto(robot);
    }

    @Override
    public RobotDto update(final RobotDto robotDto) {
        Validate.notNull(robotDto);

        // Cannot update a robot with an non-existing model
        final String modelName = robotDto.getModel();
        final RobotModel model = robotModelService.retrieve(modelName);
        if (model == null) {
            throw new ApplicationException("Cannot update robot. Robot model does not exist")
                    .addContextValue("robot", robotDto)
                    .addContextValue("robot model", modelName);
        }

        final Robot robot = new Robot(robotDto, model);

        final Robot updatedRobot = robotService.update(robot);

        return new RobotDto(updatedRobot);

    }

    @Override
    public void delete(final Long id) {
        Validate.notNull(id);

        robotService.delete(id);
    }

    @Override
    public void delete(final Collection<Long> ids) {
        Validate.notNull(ids);

        robotService.delete(ids);
    }

    @Override
    public Page<RobotDto> retrieveAll(final Pageable pageable) {
        Validate.notNull(pageable);

        return robotService.retrieveAll(pageable).map(RobotDto::fromEntity);
    }

    @Override
    public Page<RobotDto> retrieveAllByModel(final String modelName, final Pageable pageable) {
        Validate.notNull(modelName);
        Validate.notNull(pageable);

        // Cannot retrieve robots with an non-existing model
        final RobotModel model = robotModelService.retrieve(modelName);
        if (model == null) {
            throw new ApplicationException("Cannot retrieve robots. Robot model does not exist")
                    .addContextValue("robot model", modelName);
        }

        return robotService.retrieveAllByModel(model, pageable).map(RobotDto::fromEntity);
    }

    @Override
    public Page<RobotDto> search(final String search, final Pageable pageable) {
        Validate.notNull(search);
        Validate.notNull(pageable);

        return robotService.search(search, pageable).map(RobotDto::fromEntity);
    }

    @Override
    public Page<RobotDto> searchWithModel(final String search, final String modelName, final Pageable pageable) {
        Validate.notNull(search);
        Validate.notNull(modelName);
        Validate.notNull(pageable);

        // Cannot retrieve robots with an non-existing model
        final RobotModel model = robotModelService.retrieve(modelName);
        if (model == null) {
            throw new ApplicationException("Cannot search robots. Robot model does not exist")
                    .addContextValue("robot model", modelName);
        }

        return robotService.searchWithModel(search, model, pageable).map(RobotDto::fromEntity);
    }

}
