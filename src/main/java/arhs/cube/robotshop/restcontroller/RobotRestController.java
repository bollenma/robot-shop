package arhs.cube.robotshop.restcontroller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import arhs.cube.robotshop.builder.RobotBuilder;
import arhs.cube.robotshop.core.Robot;
import arhs.cube.robotshop.core.RobotModel;
import arhs.cube.robotshop.dto.RobotDto;
import arhs.cube.robotshop.facades.RobotFacade;
import arhs.cube.robotshop.services.RobotModelService;
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
@CrossOrigin(origins = "http://localhost:4200")
public class RobotRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Inject
    private RobotFacade robotFacade;

    @Inject
    private RobotModelService robotModelService;

    /**
     * POST /robots => Create a new Robot
     *
     * @param robotDto The robot DTO to be created
     * @param request  the HTTP servlet request
     * @return the created robotDto
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/robots",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RobotDto> create(@RequestBody final RobotDto robotDto, final HttpServletRequest request) throws URISyntaxException {
        Validate.notNull(robotDto);

        logger.trace("REST request to create robot [{}]", robotDto);

        final RobotDto ret = robotFacade.create(robotDto);

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
    public ResponseEntity<RobotDto> retrieve(@PathVariable final Long id) {
        Validate.notNull(id);

        logger.trace("REST request to get Robot with id [{}]", id);

        final RobotDto ret = robotFacade.retrieve(id);

        return ret != null ? ResponseEntity.ok().body(ret) : ResponseEntity.notFound().build();
    }

    /**
     * PUT /robots => update the robot
     *
     * @param robotDto new values for the robot
     * @return the updated robot
     */
    @RequestMapping(value = "/robots",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RobotDto> update(@RequestBody final RobotDto robotDto) {
        Validate.notNull(robotDto);

        logger.trace("REST request to update Robot with [{}]", robotDto);

        final RobotDto ret = robotFacade.update(robotDto);

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

        logger.trace("REST request to delete robot [{}]", id);

        robotFacade.delete(id);
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

        logger.trace("REST request to delete robots [{}]", ids);

        robotFacade.delete(ids);
    }

    /**
     * GET /robots => get all robots (paginated)
     *
     * @param pageable page configuration
     * @return Page with robots
     */
    @RequestMapping(value = "/robots",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<RobotDto>> retrieveAll(final Pageable pageable) {
        Validate.notNull(pageable);

        logger.trace("REST request to get all robots, paginated");

        final Page<RobotDto> ret = robotFacade.retrieveAll(pageable);

        return ResponseEntity.ok().body(ret);
    }

    /**
     * GET /robots/model/:modelName => get all the robots by model (paginated)
     *
     * @param modelName Name of the model for the robots to retrieve
     * @param pageable  page configuration
     * @return Page with robots of the required model
     */
    @RequestMapping(value = "/robots/model/{modelName}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<RobotDto>> retrieveAllByModel(@PathVariable final String modelName, final Pageable pageable) {
        Validate.notNull(modelName);

        logger.trace("REST request to get all robots of model [{}], paginated", modelName);

        final Page<RobotDto> ret = robotFacade.retrieveAllByModel(modelName, pageable);

        return ResponseEntity.ok().body(ret);
    }

    // TODO search & search with model
}
