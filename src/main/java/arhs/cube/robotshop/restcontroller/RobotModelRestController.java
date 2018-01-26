package arhs.cube.robotshop.restcontroller;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import arhs.cube.robotshop.core.RobotModel;
import arhs.cube.robotshop.services.RobotModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bollenma
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class RobotModelRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Inject
    private RobotModelService robotModelService;

    /**
     * GET /models => get the list of all robot models
     *
     * @return The list of all robot models
     */
    @RequestMapping(value = "/models",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<RobotModel>> retrieveAllRobotModels() {

        logger.trace("REST request to get the list of all robot models");

        final Collection<RobotModel> ret = robotModelService.retrieveAll();

        return ResponseEntity.ok().body(ret);
    }

}
