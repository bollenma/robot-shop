package arhs.cube.robotshop.restcontroller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

import arhs.cube.robotshop.services.StorageService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author bollenma
 */
@RestController
@RequestMapping("/api")
public class UploadRestController {

    @Inject
    private StorageService storageService;

    private final static Logger logger = LoggerFactory.getLogger(UploadRestController.class);

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(@RequestParam("file") final MultipartFile uploadfile) {

        logger.debug("REST request for single file upload");

        if (uploadfile.isEmpty()) {
            return ResponseEntity.ok("Please select a file");
        }

        storageService.store(uploadfile);
        final String originalFilename = uploadfile.getOriginalFilename();

        return ResponseEntity.ok().body(originalFilename);
    }
}