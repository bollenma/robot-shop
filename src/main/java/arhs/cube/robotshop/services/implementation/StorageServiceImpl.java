package arhs.cube.robotshop.services.implementation;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import arhs.cube.robotshop.common.exception.ApplicationException;
import arhs.cube.robotshop.common.exception.SystemException;
import arhs.cube.robotshop.services.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author bollenma
 */
@Service
public class StorageServiceImpl implements StorageService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Value("${file.storage.root}")
    private String rootPath;

    private Path rootLocation;

    public void init() {

        try {
            if(rootLocation == null){
                rootLocation = Paths.get(rootPath);
            }
            Files.createDirectory(rootLocation);
        } catch (final IOException e) {
            throw new SystemException("Could not initialize storage", e);
        }
    }

    public void store(final MultipartFile file) {
        try {
            final String originalFilename = file.getOriginalFilename();
            final String timestamp = LocalDate.now().toString();
            final Path target = rootLocation.resolve(originalFilename + "-" + timestamp);
            Files.copy(file.getInputStream(), target);
        } catch (Exception e) {
            throw new SystemException("Could not store file", e);
        }
    }

    public Resource loadFile(final String filename) {
        try {
            final Path file = rootLocation.resolve(filename);
            final Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new ApplicationException("File not found");
            }

        } catch (final MalformedURLException e) {
            throw new ApplicationException("URL malformed", e);
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

}