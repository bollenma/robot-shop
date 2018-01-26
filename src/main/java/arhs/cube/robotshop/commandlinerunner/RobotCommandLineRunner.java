package arhs.cube.robotshop.commandlinerunner;

import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;

import arhs.cube.robotshop.builder.RobotBuilder;
import arhs.cube.robotshop.builder.RobotModelBuilder;
import arhs.cube.robotshop.core.Robot;
import arhs.cube.robotshop.core.RobotModel;
import arhs.cube.robotshop.repositories.RobotModelRepository;
import arhs.cube.robotshop.repositories.RobotRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author bollenma
 */
@Component
public class RobotCommandLineRunner implements CommandLineRunner {

    @Inject
    private RobotModelRepository robotModelRepository;

    @Inject
    private RobotRepository robotRepository;

    @Override
    public void run(final String... strings) throws Exception {
        final Collection<RobotModel> models = new ArrayList<>();

        final RobotModel modelDomestic = RobotModelBuilder.build("DOMESTIC", "Domestic robot");
        final RobotModel modelMedical = RobotModelBuilder.build("MEDICAL", "Medical droid");
        final RobotModel modelProtocol = RobotModelBuilder.build("PROTOCOL", "Protocol droid");
        final RobotModel modelTechnical = RobotModelBuilder.build("TECHNICAL", "Technical robot");

        models.add(modelDomestic);
        models.add(modelMedical);
        models.add(modelProtocol);
        models.add(modelTechnical);

        robotModelRepository.save(models);
        System.out.println("---- Robot Models ----");
        robotModelRepository.findAll().forEach(System.out::println);

        final Collection<Robot> robots = new ArrayList<>();

        robots.add(RobotBuilder.build("K-2SO", modelDomestic, true));
        robots.add(RobotBuilder.build("Juohmaru", modelDomestic));
        robots.add(RobotBuilder.build("2-1B", modelMedical));
        robots.add(RobotBuilder.build("IG-88", modelMedical));
        robots.add(RobotBuilder.build("C-3PO", modelProtocol));
        robots.add(RobotBuilder.build("6PO", modelProtocol));
        robots.add(RobotBuilder.build("F.C.B", modelTechnical, 1000000F, "9VeZLM9tkN", false));
        robots.add(RobotBuilder.build("R2-D2", modelTechnical));
        robots.add(RobotBuilder.build("BB8", modelTechnical));

        robotRepository.save(robots);
        System.out.println("---- Robot ----");
        robotRepository.findAll().forEach(System.out::println);
    }
}
