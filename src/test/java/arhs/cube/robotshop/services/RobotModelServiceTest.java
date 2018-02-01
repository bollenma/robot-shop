package arhs.cube.robotshop.services;

import java.util.Collection;
import javax.inject.Inject;

import arhs.cube.robotshop.TestWithRobots;
import arhs.cube.robotshop.core.RobotModel;
import arhs.cube.robotshop.services.implementation.RobotModelServiceImpl;
import arhs.cube.robotshop.services.implementation.RobotServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.junit.Assert.*;

/**
 * @author bollenma
 */
public class RobotModelServiceTest extends TestWithRobots {

    @TestConfiguration
    static class RobotModelServiceTestConfiguration {
        @Bean
        public RobotModelService robotModelService() {
            return new RobotModelServiceImpl();
        }
    }

    @Inject
    private RobotModelService robotModelService;

    @Test
    public void retrieveAll() throws Exception {
        final Collection<RobotModel> result = robotModelService.retrieveAll();
        Assert.assertNotNull(result);
        Assert.assertEquals("The method must found all the models created in TestWithRobots",
                allModels.size(), result.size());
    }

    @Test
    public void retrieve() throws Exception {
        final RobotModel result = robotModelService.retrieve(MODEL_NAME_DOMESTIC);
        Assert.assertNotNull(result);
        Assert.assertEquals("Retrieved model must be equal to the Domestic model created in TestWithRobots",
                modelDomestic, result);
    }

}