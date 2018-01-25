package arhs.cube.robotshop.services;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author bollenma
 */
public interface TempFolderService {

    File newTempFolder();

    File newTempFolder(final String tempFolderPrefix);

    File storeTempUploadMultiPartFile(byte[] bytes, String name, String tempFolderPrefix);

    File storeTempUploadMultiPartFile(MultipartFile multipartFile, String tempFolderPrefix);

    File storeFileInTemp(File file);

    void cleanAllTempFolders();

    /**
     * Delete the temp folder (first level) holding the file. Can't delete the root temp folder.
     *
     * @param file the given file
     */
    void deleteTempFolder(File file);

}
