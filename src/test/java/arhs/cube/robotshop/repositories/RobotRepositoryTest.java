package arhs.cube.robotshop.repositories;

import java.util.List;

import arhs.cube.robotshop.TestWithRobots;
import arhs.cube.robotshop.core.Robot;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.domain.Page;

/**
 * @author bollenma
 */
public class RobotRepositoryTest extends TestWithRobots {

    @Test
    public void findAllByModel() {
        final Page<Robot> resultPage = robotRepository.findAllByModel(modelDomestic, page);
        final List<Robot> result = resultPage.getContent();

        Assert.assertNotNull(result);
        Assert.assertEquals("The method found all the domestic robots created in TestWithRobots",
                domesticRobots.size(), result.size());
        result.forEach(robot -> Assert.assertEquals("The robot must be domestic", modelDomestic, robot.getModel()));
    }

    @Test
    public void findAllByNameIgnoreCaseContaining() {

        Page<Robot> resultPage = robotRepository.findAllByNameIgnoreCaseContaining(MODEL_NAME_DOMESTIC, page);
        List<Robot> result = resultPage.getContent();

        Assert.assertNotNull(result);
        Assert.assertEquals("The method must find robots without a complete name",
                2, result.size());
        Assert.assertTrue("The method found the robot with name " + robotDomestic.getName(),
                result.contains(robotDomestic));
        Assert.assertTrue("The method found the robot with name " + robotDomesticSoldout.getName(),
                result.contains(robotDomesticSoldout));

        // Ignore case
        final String alternateCaseName = alternateCase(MODEL_NAME_DOMESTIC);
        Assert.assertNotEquals(MODEL_NAME_DOMESTIC, alternateCaseName);

        resultPage = robotRepository.findAllByNameIgnoreCaseContaining(alternateCaseName, page);
        result = resultPage.getContent();

        Assert.assertNotNull(result);
        Assert.assertEquals("The method must ignore the case",
                2, result.size());
        Assert.assertTrue("The method found the robot with name " + robotDomestic.getName(),
                result.contains(robotDomestic));
        Assert.assertTrue("The method found the robot with name " + robotDomesticSoldout.getName(),
                result.contains(robotDomesticSoldout));
    }

    @Test
    public void findAllByNameIgnoreCaseContainingAndModel() throws Exception {
        // The method ignores case and combines both the name and the model
        final String alternateCaseSoldout = alternateCase(SOLDOUT_SUFFIX);
        final Page<Robot> resultPage =
                robotRepository.findAllByNameIgnoreCaseContainingAndModel(alternateCaseSoldout, modelDomestic, page);
        final List<Robot> result = resultPage.getContent();

        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertTrue("The method found the robot with name " + robotDomesticSoldout.getName(),
                result.contains(robotDomesticSoldout));
    }

}