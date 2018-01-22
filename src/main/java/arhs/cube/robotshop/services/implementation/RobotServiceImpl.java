package arhs.cube.robotshop.services.implementation;

import java.util.Collection;
import javax.inject.Inject;

import arhs.cube.robotshop.common.exception.ApplicationException;
import arhs.cube.robotshop.core.Robot;
import arhs.cube.robotshop.core.RobotModel;
import arhs.cube.robotshop.repositories.RobotRepository;
import arhs.cube.robotshop.services.RobotService;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bollenma
 */
@Service
@Transactional
public class RobotServiceImpl implements RobotService {

    private final Logger logger = LoggerFactory.getLogger(RobotServiceImpl.class);

    @Inject
    private RobotRepository robotRepository;

    @Override
    public Robot create(final Robot robot) {
        Validate.notNull(robot);

        if (robot.getId() != null) {
            throw new ApplicationException("Robot to be created already has an id")
                    .addContextValue("robot", robot);
        }

        final Robot ret = robotRepository.save(robot);

        logger.info("Robot created [{}]", ret);

        return ret;
    }

    @Override
    public Robot retrieve(final Long id) {
        Validate.notNull(id);

        final Robot ret = robotRepository.findOne(id);

        logger.info("Robot retrieved [{}]", ret);

        return ret;
    }

    @Override
    public Robot update(final Robot robot) {
        Validate.notNull(robot);

        // Cannot update a robot if there is no id
        final Long id = robot.getId();
        if (id == null) {
            throw new ApplicationException("Robot to be updated has no id")
                    .addContextValue("robot", robot);
        }

        // Cannot update a robot if it doesn't already exists
        final Robot robotToUpdate = this.retrieve(id);
        if (robotToUpdate == null) {
            throw new ApplicationException("Robot with given id doesn't exist")
                    .addContextValue("id", id);
        }

        final Robot ret = robotRepository.save(robot);

        logger.info("Robot updated [{}]", robot);

        return ret;
    }

    @Override
    public void delete(final Long id) {
        Validate.notNull(id);

        robotRepository.delete(id);

        logger.info("Robot deleted [{}]", id);
    }

    @Override
    public void delete(final Collection<Long> ids) {
        Validate.notNull(ids);

        ids.forEach(this::delete);

        logger.info("All robots deleted [{}]", ids);
    }

    @Override
    public Page<Robot> retrieveAll(final Pageable pageable) {
        Validate.notNull(pageable);

        final Page<Robot> ret = robotRepository.findAll(pageable);

        logger.info("Retrieve page [{}/{}] with robots. Total [{}]",
                pageable.getPageNumber() + 1, ret.getTotalPages(), ret.getTotalElements());

        return ret;
    }

    @Override
    public Page<Robot> retrieveAllByModel(final RobotModel model, final Pageable pageable) {
        Validate.notNull(model);
        Validate.notNull(pageable);

        final Page<Robot> ret = robotRepository.findAllByModel(model, pageable);

        logger.info("Retrieve page [{}/{}] with robots for model [{}]. Total [{}]",
                pageable.getPageNumber() + 1, ret.getTotalPages(), model, ret.getTotalElements());

        return ret;
    }

    @Override
    public Page<Robot> search(final String search, final Pageable pageable) {
        Validate.notNull(search);
        Validate.notNull(pageable);
        //TODO implement
        return null;
    }
}
