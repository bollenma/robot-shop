package arhs.cube.robotshop.services.implementation;

import java.util.Collection;
import javax.inject.Inject;

import arhs.cube.robotshop.core.RobotModel;
import arhs.cube.robotshop.repositories.RobotModelRepository;
import arhs.cube.robotshop.services.RobotModelService;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author bollenma
 */
@Service
public class RobotModelServiceImpl implements RobotModelService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Inject
    private RobotModelRepository robotModelRepository;

    @Override
    public Collection<RobotModel> retrieveAll() {

        final Collection<RobotModel> ret = robotModelRepository.findAll();

        logger.info("Robot models retrieved [{}]", ret);

        return ret;
    }

    @Override
    public RobotModel retrieve(final String modelName) {
        Validate.notNull(modelName);

        final RobotModel ret = robotModelRepository.findByName(modelName);

        logger.info("Robot model retrieved [{}]", ret);

        return ret;
    }
}
