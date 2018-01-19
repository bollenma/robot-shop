package arhs.cube.robotshop.rest;

import java.net.URI;
import java.net.URISyntaxException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import arhs.cube.robotshop.core.Robot;
import arhs.cube.robotshop.services.RobotService;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bollenma
 */
@RestController
@RequestMapping("/api")
public class RobotRest {

    private final Logger logger = LoggerFactory.getLogger(RobotRest.class);

    @Inject
    private RobotService robotService;


    /**
     * POST /robot -> Create a new {@Link Robot}
     *
     * @param robot   The robot to create
     * @param request the HTTP servlet request
     * @return the created robot
     */
    @RequestMapping(value = "/robot")
    public ResponseEntity<Robot> createRobot(@RequestBody final Robot robot, final HttpServletRequest request) throws URISyntaxException {
        Validate.notNull(robot);

        return ResponseEntity
                .created(new URI("http://test.com"))
                .body(null);
    }

}
