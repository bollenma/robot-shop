package arhs.cube.robotshop.commandlinerunner;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import arhs.cube.robotshop.builder.RobotBuilder;
import arhs.cube.robotshop.core.Robot;
import arhs.cube.robotshop.core.RobotModel;
import arhs.cube.robotshop.repositories.RobotRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author bollenma
 */
@Component
public class RobotCommandLineRunner implements CommandLineRunner {

    @Inject
    private RobotRepository robotRepository;

    @Override
    public void run(final String... strings) throws Exception {
        final Collection<Robot> robots = new ArrayList<>();

        robots.add(RobotBuilder.build("K-2SO", RobotModel.DOMESTIC, true));
        robots.add(RobotBuilder.build("Juohmaru", RobotModel.DOMESTIC));
        robots.add(RobotBuilder.build("2-1B", RobotModel.MEDICAL));
        robots.add(RobotBuilder.build("IG-88", RobotModel.MEDICAL));
        robots.add(RobotBuilder.build("C-3PO", RobotModel.PROTOCOL));
        robots.add(RobotBuilder.build("6PO", RobotModel.PROTOCOL));
        robots.add(RobotBuilder.build("R2-D2", RobotModel.TECHNICAL));
        robots.add(RobotBuilder.build("BB8", RobotModel.TECHNICAL));

        robotRepository.save(robots);
        robotRepository.findAll().forEach(System.out::println);
    }
}
