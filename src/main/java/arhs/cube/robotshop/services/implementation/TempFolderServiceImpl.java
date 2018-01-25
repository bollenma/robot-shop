package arhs.cube.robotshop.services.implementation;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import arhs.cube.robotshop.services.TempFolderService;
import arhs.cube.robotshop.utils.CustomFileUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author bollenma
 */
@Service
public class TempFolderServiceImpl implements TempFolderService {

    private static final String DEFAULT_TEMP_FOLDER_PREFIX = "file";

    private static Logger logger = LoggerFactory.getLogger(TempFolderServiceImpl.class);

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    @Value("${file.temporary.root}")
    private File temporaryRoot;

    /**
     * Create a new temporary folder with the default prefix.
     *
     * @return the temporary folder
     */
    @Override
    public File newTempFolder() {
        return newTempFolder(DEFAULT_TEMP_FOLDER_PREFIX);
    }

    /**
     * Create a new temp folder with a prefix.
     *
     * @param tempFolderPrefix the prefix
     * @return the temp folder
     */

    @Override
    public File newTempFolder(final String tempFolderPrefix) {
        Validate.notNull(tempFolderPrefix);

        if (temporaryRoot == null) {
            temporaryRoot = FileUtils.getTempDirectory();
        }
        final String tempFolderName = tempFolderPrefix + "-" + simpleDateFormat.format(new Date());
        final File ret = CustomFileUtils.provideSubFolder(tempFolderName, temporaryRoot);

        logger.info("New temp folder provided: [{}]", tempFolderName);
        return ret;
    }

    @Override
    public File storeTempUploadMultiPartFile(final byte[] bytes, final String name, final String tempFolderPrefix) {
        return null;
    }

    @Override
    public File storeTempUploadMultiPartFile(final MultipartFile multipartFile, final String tempFolderPrefix) {
        return null;
    }

    @Override
    public File storeFileInTemp(final File file) {
        return null;
    }

    @Override
    public void cleanAllTempFolders() {

    }

    @Override
    public void deleteTempFolder(File file) {

    }
}
