package arhs.cube.robotshop.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

import arhs.cube.robotshop.TestWithRobots;
import arhs.cube.robotshop.builder.RobotBuilder;
import arhs.cube.robotshop.core.Robot;
import arhs.cube.robotshop.services.implementation.RobotServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;

/**
 * @author bollenma
 */
public class RobotServiceTest extends TestWithRobots {

    @TestConfiguration
    static class RobotServiceTestConfiguration {
        @Bean
        public RobotService robotService() {
            return new RobotServiceImpl();
        }
    }

    @Inject
    private RobotService robotService;

    @Test
    public void create() throws Exception {
        final Robot toBeCreated = RobotBuilder.build("toBeCreated", modelMedical, 100F, "hash", false);
        final Robot result = robotService.create(toBeCreated);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getId());

        toBeCreated.setId(result.getId());
        Assert.assertEquals(toBeCreated, result);
    }

    @Test
    public void retrieve() throws Exception {
        final Robot toBeCreated = RobotBuilder.build("toBeCreated", modelMedical, 100F, "hash", false);
        final Robot expected = robotService.create(toBeCreated);
        final Robot result = robotService.retrieve(expected.getId());

        Assert.assertNotNull(result);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void update() throws Exception {
        final Robot toBeCreated = RobotBuilder.build("toBeCreated", modelMedical, 100F, "hash", false);
        final Robot created = robotRepository.save(toBeCreated);
        final Robot createdSnapshot = new Robot();
        BeanUtils.copyProperties(created, createdSnapshot);
        Assert.assertEquals("The copied robot must be equal to the original robot",
                created, createdSnapshot);

        final Robot toBeUpdated = new Robot();
        BeanUtils.copyProperties(created, toBeUpdated);

        // Update the robot
        toBeUpdated.setName("updatedName");
        toBeUpdated.setSoldout(true);
        toBeUpdated.setPrice(99999F);

        final Robot updated = robotService.update(toBeUpdated);
        Assert.assertNotEquals("Updated robot must be different to the created robot (outside spring context)",
                createdSnapshot, updated);
        Assert.assertEquals("toBeUpdated & Updated robots must be equal",
                toBeUpdated, updated);
        Assert.assertEquals("Updated & created robots must have the same id",
                createdSnapshot.getId(), updated.getId());
    }

    @Test
    public void delete() throws Exception {
        final Robot toBeCreated = RobotBuilder.build("toBeCreated", modelMedical, 100F, "hash", false);
        final Robot created = robotRepository.save(toBeCreated);

        final Long id = created.getId();
        robotService.delete(id);

        final Robot result = robotRepository.findOne(id);
        Assert.assertNull("Robot must not be found anymore", result);
    }

    @Test
    public void deleteMultiple() throws Exception {
        final Robot toBeCreated1 = RobotBuilder.build("toBeCreated1", modelMedical, 100F, "hash", false);
        final Robot toBeCreated2 = RobotBuilder.build("toBeCreated2", modelMedical, 100F, "hash", false);
        final Robot created1 = robotRepository.save(toBeCreated1);
        final Robot created2 = robotRepository.save(toBeCreated2);

        final Long id1 = created1.getId();
        final Long id2 = created2.getId();

        final Collection<Long> toBeDeleted = new ArrayList<>();
        toBeDeleted.add(id1);
        toBeDeleted.add(id2);

        robotService.delete(toBeDeleted);

        final Robot result1 = robotRepository.findOne(id1);
        Assert.assertNull("Robot must not be found anymore", result1);
        final Robot result2 = robotRepository.findOne(id2);
        Assert.assertNull("Robot must not be found anymore", result2);
    }

    @Test
    public void retrieveAll() throws Exception {

        final Page<Robot> resultPage = robotService.retrieveAll(page);
        final List<Robot> result = resultPage.getContent();
        Assert.assertNotNull(result);
        Assert.assertEquals("The method must found all the robots created in TestWithRobots",
                allRobots.size(), result.size());
    }

    @Test
    public void retrieveAllByModel() throws Exception {
        final Page<Robot> resultPage = robotService.retrieveAllByModel(modelMedical, page);
        final List<Robot> result = resultPage.getContent();
        Assert.assertNotNull(result);
        Assert.assertEquals("The method must found all the medical robots created in TestWithRobots",
                medicalRobots.size(), result.size());
        result.forEach(robot -> Assert.assertEquals("The robot must be medical",
                modelMedical, robot.getModel()));
    }

    @Test
    public void search() throws Exception {
        Page<Robot> resultPage = robotService.search(MODEL_NAME_DOMESTIC, page);
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

        resultPage = robotService.search(alternateCaseName, page);
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
    public void searchWithModel() throws Exception {
        // The method ignores case and combines both the name and the model
        final String alternateCaseSoldout = alternateCase(SOLDOUT_SUFFIX);
        final Page<Robot> resultPage =
                robotService.searchWithModel(alternateCaseSoldout, modelDomestic, page);
        final List<Robot> result = resultPage.getContent();

        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertTrue("The method found the robot with name " + robotDomesticSoldout.getName(),
                result.contains(robotDomesticSoldout));
    }

}