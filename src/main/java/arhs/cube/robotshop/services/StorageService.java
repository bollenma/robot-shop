package arhs.cube.robotshop.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * File storage management
 *
 * @author bollenma
 */
public interface StorageService {

    void init();

    void store(MultipartFile file);

    Resource loadFile(String filename);

    void deleteAll();

}
