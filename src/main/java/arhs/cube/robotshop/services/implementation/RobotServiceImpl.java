package arhs.cube.robotshop.services.implementation;

import java.util.Collection;
import javax.inject.Inject;

import arhs.cube.robotshop.common.exception.ApplicationException;
import arhs.cube.robotshop.core.Robot;
import arhs.cube.robotshop.core.RobotType;
import arhs.cube.robotshop.repositories.RobotRepository;
import arhs.cube.robotshop.services.RobotService;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author bollenma
 */
@Service
public class RobotServiceImpl implements RobotService {

    private Logger logger = LoggerFactory.getLogger(RobotServiceImpl.class);

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

        return null;
    }

    @Override
    public Robot update(final Robot robot) {
        Validate.notNull(robot);

        if (robot.getId() == null) {
            throw new ApplicationException("Robot to be updated has no id")
                    .addContextValue("robot", robot);
        }

        final Robot ret = robotRepository.save(robot);

        logger.info("Robot updated [{}]", robot);

        return ret;
    }

    @Override
    public void delete(final Robot robot) {
        Validate.notNull(robot);

        delete(robot.getId());
    }

    @Override
    public void delete(final Long id) {
        Validate.notNull(id);

        robotRepository.delete(id);

        logger.info("Robot deleted [{}]", id);
    }

    @Override
    public Collection<Robot> retrieveAll() {

        final Collection<Robot> ret = robotRepository.findAll();

        logger.info("All robots retrieved. Total [{}]", ret.size());
        return ret;
    }

    @Override
    public Collection<Robot> retrieveAllByType(final RobotType type) {
        Validate.notNull(type);

        final Collection<Robot> ret = robotRepository.findAllByType(type);

        logger.info("All robots retrieved for type [{}]. Total [{}]", type, ret.size());
        return ret;
    }

    @Override
    public Page<Robot> retrieveAllPaginated(final Pageable pageable) {
        Validate.notNull(pageable);

        final Page<Robot> ret = robotRepository.findAll(pageable);

        logger.info("All robots retrieved, paginated. Total [{}], page [{}], size [{}]",
                ret.getTotalElements(), pageable.getPageNumber(), pageable.getPageSize());

        return ret;
    }

    @Override
    public Page<Robot> retrieveAllByTypePaginated(final RobotType type, final Pageable pageable) {
        final Page<Robot> ret = robotRepository.findAllByType(type, pageable);

        logger.info("All robots retrieved for type, paginated. Total [{}], page [{}], size [{}]",
                ret.getTotalElements(), pageable.getPageNumber(), pageable.getPageSize());

        return ret;
    }

    @Override
    public Page<Robot> searchPaginated(final String search, final Pageable pageable) {
        Validate.notNull(pageable);
        return null;
    }
}
