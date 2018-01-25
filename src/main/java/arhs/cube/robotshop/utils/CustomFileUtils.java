package arhs.cube.robotshop.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import arhs.cube.robotshop.common.exception.ApplicationException;
import arhs.cube.robotshop.common.exception.SystemException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.LoggerFactory;


/**
 * @author bollenma
 */
public final class CustomFileUtils {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomFileUtils.class);

    private CustomFileUtils() {
    }

    /**
     * Return or create a subfolder inside the given parent folder.
     *
     * @param subfolder the name of the subfolder
     * @param parent    the parent folder
     * @return the already existing / created subfolder
     */
    public static File provideSubFolder(final String subfolder, final File parent) {
        Validate.notNull(parent);
        Validate.notNull(subfolder);

        File ret = new File(parent, subfolder);
        if (!ret.exists()) {
            try {
                FileUtils.forceMkdir(ret);
                logger.info("Subfolder [{}] created inside parent folder [{}]", ret, parent);
            } catch (IOException e) {
                throw new SystemException("Cannot create folder", e)
                        .addContextValue("folder", ret)
                        .addContextValue("parent", parent);
            }
        }
        return ret;
    }

    /**
     * Retrieve a file by name in a folder.
     *
     * @param name   the name of the file
     * @param folder the folder
     * @return the requested file
     */
    public static File retrieveFileInFolder(final String name, final File folder) {
        Validate.notNull(folder);
        Validate.notNull(name);

        File ret = new File(folder, name);

        if (!ret.exists() || !ret.isFile()) {
            throw new ApplicationException("No file found in the folder with given name")
                    .addContextValue("folder", folder)
                    .addContextValue("name", name);
        }
        logger.info("File [{}] found in folder [{}]", ret, folder);
        return ret;
    }

    /**
     * Copy a file to a target folder.
     *
     * @param file         the file
     * @param targetFolder the target folder
     * @return the copied file
     */
    public static File copyFile(final File file, final File targetFolder) {
        Validate.notNull(targetFolder);
        Validate.notNull(file);

        if (!targetFolder.isDirectory()) {
            throw new ApplicationException("target is not a directory")
                    .addContextValue("source", file)
                    .addContextValue("target", targetFolder);
        }

        if (!file.isFile()) {
            throw new ApplicationException("Given file is not a file")
                    .addContextValue("file", file)
                    .addContextValue("target", targetFolder);
        }

        final File target = new File(targetFolder, file.getName());
        try {
            FileUtils.copyFile(file, target);
        } catch (IOException e) {
            throw new SystemException("Cannot copy file", e)
                    .addContextValue("origin", file)
                    .addContextValue("target", target);
        }
        logger.info("Copied file [{}] to [{}}", file, target);
        return target;
    }

    /**
     * Copy a folder to a target folder.
     *
     * @param sourceFolder the source folder
     * @param targetFolder the target folder
     * @return the copied folder
     */
    public static File copyFolder(final File sourceFolder, final File targetFolder) {
        Validate.notNull(targetFolder);
        Validate.notNull(sourceFolder);

        if (!sourceFolder.isDirectory()) {
            throw new ApplicationException("at least one of the parameters is not a directory")
                    .addContextValue("source", sourceFolder)
                    .addContextValue("target", targetFolder);
        }

        try {
            FileUtils.copyDirectory(sourceFolder, targetFolder);
        } catch (IOException e) {
            throw new SystemException("Cannot copy folder", e)
                    .addContextValue("origin", sourceFolder)
                    .addContextValue("target", targetFolder);
        }
        logger.info("Copied folder [{}] to [{}}", sourceFolder, targetFolder);

        return targetFolder;
    }

    /**
     * Write down a byte array in an output file.
     *
     * @param bytes      the file in byte array
     * @param outputFile the output file.
     * @return the written file
     */
    public static File writeFile(final byte[] bytes, final File outputFile) {
        Validate.notNull(bytes);
        Validate.notNull(outputFile);

        try {
            FileUtils.writeByteArrayToFile(outputFile, bytes);
        } catch (IOException e) {
            throw new SystemException("cannot write file", e)
                    .addContextValue("file", outputFile);
        }
        return outputFile;
    }

    /**
     * Check if the content of a folder is empty (i.e. doesn't contain any folder nor file)
     *
     * @param folder the folder
     * @return true if the folder is empty, false otherwise
     */
    public static boolean isFolderEmpty(final File folder) {
        Validate.notNull(folder);

        final Collection<File> files = FileUtils.listFilesAndDirs(folder, FileFilterUtils.trueFileFilter(), FileFilterUtils.trueFileFilter());
        // files contains the parent directory
        final boolean ret = files.size() <= 1;

        logger.info("Folder [{}] is empty ? : {}", folder, ret);
        return ret;
    }

    /**
     * Retrieve or create a file on the file system, given a folder and a filename.
     *
     * @param folder   the folder.
     * @param filename the filename.
     * @return the file.
     */
    public static File retrieveOrCreateFile(final File folder, final String filename) {
        Validate.notNull(folder);
        Validate.notNull(filename);

        final File ret = new File(folder, filename);

        return retrieveOrCreateFile(ret);
    }

    /**
     * Retrieve or create a file.
     *
     * @param file the file
     * @return the file
     */
    public static File retrieveOrCreateFile(final File file) {
        Validate.notNull(file);

        if (!file.exists()) {
            try {
                FileUtils.touch(file);
                logger.info("Created file [{}]", file);
            } catch (IOException e) {
                throw new SystemException(e);
            }
        } else {
            logger.info("Retrieved file [{}]", file);
        }

        return file;
    }

    public static boolean isFileEmpty(final File file) {
        try {
            final List<String> lines = FileUtils.readLines(file);
            return lines.isEmpty();
        } catch (IOException e) {
            throw new SystemException("Cannot read file", e)
                    .addContextValue("file", file);
        }
    }

    public static void createFileFromInputStream(final File jsonFileSystem, final InputStream inputStream) {
        try {
            FileUtils.copyInputStreamToFile(inputStream, jsonFileSystem);
        } catch (IOException e) {
            throw new SystemException("Cannot get input stream from file", e)
                    .addContextValue("file", jsonFileSystem);
        }
    }
}
