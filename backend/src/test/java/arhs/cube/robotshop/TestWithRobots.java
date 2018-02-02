package arhs.cube.robotshop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import javax.inject.Inject;

import arhs.cube.robotshop.builder.RobotBuilder;
import arhs.cube.robotshop.builder.RobotModelBuilder;
import arhs.cube.robotshop.core.Robot;
import arhs.cube.robotshop.core.RobotModel;
import arhs.cube.robotshop.repositories.RobotModelRepository;
import arhs.cube.robotshop.repositories.RobotRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @author bollenma
 */
@DataJpaTest
public class TestWithRobots extends SpringAwareTest {

    @Inject
    public RobotModelRepository robotModelRepository;

    @Inject
    public RobotRepository robotRepository;

    // Constants
    final public String MODEL_NAME_DOMESTIC = "DOMESTIC";
    final public String MODEL_NAME_MEDICAL = "MEDICAL";

    final public String SOLDOUT_SUFFIX = "_soldout";
    final public String ROBOT_NAME_DOMESTIC = "robot_domestic";
    final public String ROBOT_NAME_DOMESTIC_SOLDOUT = ROBOT_NAME_DOMESTIC + SOLDOUT_SUFFIX;
    final public String ROBOT_NAME_MEDICAL = "robot_medical";
    final public String ROBOT_NAME_MEDICAL_SOLDOUT = ROBOT_NAME_MEDICAL + SOLDOUT_SUFFIX;

    // Test objects
    public Pageable page = new PageRequest(0, 20);

    public RobotModel modelDomestic;
    public RobotModel modelMedical;
    public Collection<RobotModel> allModels;

    public Robot robotDomestic;
    public Robot robotDomesticSoldout;
    public Collection<Robot> domesticRobots;

    public Robot robotMedical;
    public Robot robotMedicalSoldout;
    public Collection<Robot> medicalRobots;

    public Collection<Robot> allRobots;


    @Before
    public void init() {

        // Create the robot models
        allModels = new HashSet<>();

        modelDomestic = RobotModelBuilder.build(MODEL_NAME_DOMESTIC, "Domestic robot");
        robotModelRepository.save(modelDomestic);
        allModels.add(modelDomestic);

        modelMedical = RobotModelBuilder.build(MODEL_NAME_MEDICAL, "Medical droid");
        robotModelRepository.save(modelMedical);
        allModels.add(modelMedical);

        // Create the robots

        // Domestic robots
        domesticRobots = new HashSet<>();

        robotDomestic = RobotBuilder.build(ROBOT_NAME_DOMESTIC, modelDomestic);
        robotRepository.save(robotDomestic);
        domesticRobots.add(robotDomestic);

        robotDomesticSoldout = RobotBuilder.build(ROBOT_NAME_DOMESTIC_SOLDOUT, modelDomestic, true);
        robotRepository.save(robotDomesticSoldout);
        domesticRobots.add(robotDomesticSoldout);

        // Medical robots
        medicalRobots = new HashSet<>();

        robotMedical = RobotBuilder.build(ROBOT_NAME_MEDICAL, modelMedical);
        robotRepository.save(robotMedical);
        medicalRobots.add(robotMedical);

        robotMedicalSoldout = RobotBuilder.build(ROBOT_NAME_MEDICAL_SOLDOUT, modelMedical, true);
        robotRepository.save(robotMedicalSoldout);
        medicalRobots.add(robotMedicalSoldout);

        allRobots = new HashSet<>();
        allRobots.addAll(domesticRobots);
        allRobots.addAll(medicalRobots);
    }
}
