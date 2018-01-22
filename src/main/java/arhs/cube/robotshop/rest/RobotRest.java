package arhs.cube.robotshop.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import arhs.cube.robotshop.core.Robot;
import arhs.cube.robotshop.core.RobotModel;
import arhs.cube.robotshop.services.RobotService;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * POST /robots => Create a new Robot
     *
     * @param robot   The robot to create
     * @param request the HTTP servlet request
     * @return the created robot
     */
    @RequestMapping(value = "/robots",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Robot> create(@RequestBody final Robot robot, final HttpServletRequest request) throws URISyntaxException {
        Validate.notNull(robot);

        logger.debug("REST request to create robot [{}]", robot);

        final Robot ret = robotService.create(robot);

        final String uri = request.getRequestURI() + "/" + ret.getId();

        return ResponseEntity
                .created(new URI(uri))
                .body(ret);
    }

    /**
     * GET /robots/:id => get the "id" robot
     *
     * @param id id of the robot to get
     * @return the required robot
     */
    @RequestMapping(value = "/robots/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Robot> retrieve(@PathVariable final Long id) {
        Validate.notNull(id);

        logger.debug("REST request to get Robot with id [{}]", id);

        final Robot ret = robotService.retrieve(id);

        return ret != null ? ResponseEntity.ok().body(ret) : ResponseEntity.notFound().build();
    }

    /**
     * PUT /robots => update the robot
     *
     * @param robot new values for the robot
     * @return the updated robot
     */
    @RequestMapping(value = "/robots",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Robot> update(@RequestBody final Robot robot) {
        Validate.notNull(robot);

        logger.debug("REST request to update Robot with [{}]", robot);

        final Robot ret = robotService.update(robot);

        return ret != null ? ResponseEntity.ok().body(ret) : ResponseEntity.notFound().build();
    }

    /**
     * DELETE /robots/:id => delete the "id" robot
     *
     * @param id Id of the robot to be deleted
     */
    @RequestMapping(value = "/robots/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable final Long id) {
        Validate.notNull(id);

        logger.debug("REST request to delete robot [{}]", id);

        robotService.delete(id);
    }

    /**
     * POST /robots/delete => delete the robots listed
     *
     * @param ids ids of the robots to be deleted
     */
    @RequestMapping(value = "/robots/delete",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteBatch(@RequestBody final Collection<Long> ids) {
        Validate.notNull(ids);

        logger.debug("REST request to delete robots [{}]", ids);

        robotService.delete(ids);
    }

    /**
     * GET /robots => get all robots (paginated)
     *
     * @return Page with robots
     */
    @RequestMapping(value = "/robots",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Robot>> retrieveAll(final Pageable pageable) {
        Validate.notNull(pageable);

        logger.debug("REST request to get all robots, paginated");

        final Page<Robot> ret = robotService.retrieveAll(pageable);

        return ResponseEntity.ok().body(ret);
    }

    /**
     * GET /robots/model/:model => get all the robots by model (paginated)
     *
     * @return Page with robots of the required model
     */
    @RequestMapping(value = "/robots/model/{model}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Robot>> retrieveAllByModel(@PathVariable final RobotModel model, final Pageable pageable){
        Validate.notNull(model);

        final Page<Robot> ret = robotService.retrieveAllByModel(model, pageable);

        return ResponseEntity.ok().body(ret);
    }
}
