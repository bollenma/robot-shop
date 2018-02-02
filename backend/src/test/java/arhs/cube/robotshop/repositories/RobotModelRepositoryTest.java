package arhs.cube.robotshop.repositories;

import arhs.cube.robotshop.TestWithRobots;
import arhs.cube.robotshop.core.RobotModel;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author bollenma
 */
public class RobotModelRepositoryTest extends TestWithRobots {

    @Test
    public void findByName() throws Exception {
        RobotModel result = robotModelRepository.findByName(MODEL_NAME_DOMESTIC);
        Assert.assertEquals("The method must find the searched model",
                modelDomestic, result);

        final String alternateCaseName = alternateCase(MODEL_NAME_DOMESTIC);
        result = robotModelRepository.findByName(alternateCaseName);
        Assert.assertNull("The method must ignore the case", result);

        final int nameSize = MODEL_NAME_DOMESTIC.length();
        final String abbreviatedName = StringUtils.abbreviate(MODEL_NAME_DOMESTIC, nameSize / 2);
        result = robotModelRepository.findByName(abbreviatedName);
        Assert.assertNull("The method must have the whole word to find the model", result);
    }

}