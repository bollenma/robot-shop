package arhs.cube.robotshop.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author bollenma
 */
public class CustomFileUtilsTest {

    private final File testFolder = new File("target/filesystemutils/");

    @Before
    @After
    public final void cleanTestFolder() {
        FileUtils.deleteQuietly(testFolder);
    }

    @Test
    public void testProvideSubFolder() throws Exception {
        FileUtils.forceMkdir(testFolder);
        final File expected = new File(testFolder, "test");

        Assert.assertFalse(expected.exists());

        final File result = CustomFileUtils.provideSubFolder("test", testFolder);

        Assert.assertTrue(result.exists());
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testRetrieveUniqueFileByNameInFolder() throws Exception {
        FileUtils.forceMkdir(testFolder);
        final String filename = "test.txt";
        final File expected = new File(testFolder, filename);
        FileUtils.touch(expected);
        Assert.assertTrue(expected.exists());

        final File result = CustomFileUtils.retrieveFileInFolder(filename, testFolder);
        Assert.assertTrue(result.exists());
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testCopyFile() throws Exception {
        FileUtils.forceMkdir(testFolder);
        Assert.assertTrue(testFolder.exists());

        final File sourceFolder = new File(testFolder, "source");
        final File targetFolder = new File(testFolder, "target");
        FileUtils.forceMkdir(sourceFolder);
        FileUtils.forceMkdir(targetFolder);

        final String filename = "test.txt";
        final File sourceFile = new File(sourceFolder, filename);
        final File targetFile = new File(targetFolder, filename);
        FileUtils.touch(sourceFile);

        Assert.assertTrue(sourceFile.exists());
        Assert.assertFalse(targetFile.exists());

        CustomFileUtils.copyFile(sourceFile, targetFolder);

        Assert.assertTrue(sourceFile.exists());
        Assert.assertTrue(targetFile.exists());
    }

    @Test
    public void testCopyFolder() throws Exception {
        FileUtils.forceMkdir(testFolder);
        Assert.assertTrue(testFolder.exists());

        final File sourceFolder = new File(testFolder, "source");
        final File targetFolder = new File(testFolder, "target");
        FileUtils.forceMkdir(sourceFolder);

        Assert.assertTrue(sourceFolder.exists());
        Assert.assertFalse(targetFolder.exists());

        final String subFolderName = "subfolder";
        final File sourceSubFolder = new File(sourceFolder, subFolderName);
        final File targetSubFolder = new File(targetFolder, subFolderName);
        FileUtils.forceMkdir(sourceSubFolder);

        Assert.assertTrue(sourceSubFolder.exists());
        Assert.assertFalse(targetSubFolder.exists());

        final String filename = "test.txt";
        final File sourceFile = new File(sourceFolder, filename);
        final File targetFile = new File(targetFolder, filename);
        FileUtils.touch(sourceFile);

        Assert.assertTrue(sourceFile.exists());
        Assert.assertFalse(targetFile.exists());

        CustomFileUtils.copyFolder(sourceFolder, targetFolder);

        Assert.assertTrue(sourceFolder.exists());
        Assert.assertTrue(targetFolder.exists());

        Assert.assertTrue(sourceSubFolder.exists());
        Assert.assertTrue(targetSubFolder.exists());

        Assert.assertTrue(sourceFile.exists());
        Assert.assertTrue(targetFile.exists());
    }

    @Test
    public void testWriteFile() throws Exception {
        // delete the output folder
        FileUtils.deleteDirectory(testFolder);
        Assert.assertFalse(testFolder.exists());

        final File file = new File("src/test/resources/file/filename.file");
        final byte[] bytes = FileUtils.readFileToByteArray(file);

        final File outputFile = new File(testFolder, "filename.file");

        final File result = CustomFileUtils.writeFile(bytes, outputFile);

        Assert.assertTrue(result.exists());
        Assert.assertArrayEquals(bytes, FileUtils.readFileToByteArray(result));
    }

    @Test
    public void testIsFolderEmpty() throws Exception {
        FileUtils.forceMkdir(testFolder);
        final File folder = new File(testFolder, "isfolderempty");
        FileUtils.forceMkdir(folder);
        Assert.assertTrue(folder.exists());

        // Empty folder
        boolean result = CustomFileUtils.isFolderEmpty(folder);
        Assert.assertTrue(result);

        final File subfolder = new File(folder, "subfolder");
        FileUtils.forceMkdir(subfolder);
        Assert.assertTrue(subfolder.exists());

        // Contains only a folder
        result = CustomFileUtils.isFolderEmpty(folder);
        Assert.assertFalse(result);

        final File file = new File(folder, "file.txt");
        final boolean fileCreated = file.createNewFile();
        Assert.assertTrue(fileCreated);

        // Contains also a file
        result = CustomFileUtils.isFolderEmpty(folder);
        Assert.assertFalse(result);

    }

    @Test
    public void testRetrieveOrCreateFile() throws Exception {

        final File folder = new File("target/retrieve-or-create");
        final String filename = "test.file";
        final File file = new File(folder, filename);
        FileUtils.deleteQuietly(file);

        Assert.assertFalse(file.exists());

        final File res = CustomFileUtils.retrieveOrCreateFile(folder, filename);

        Assert.assertTrue(res.exists());
        Assert.assertEquals(file, res);
        final long timestamp1 = res.lastModified();

        final File res2 = CustomFileUtils.retrieveOrCreateFile(folder, filename);
        Assert.assertTrue(res2.exists());
        final long timestamp2 = res2.lastModified();

        Assert.assertEquals(timestamp1, timestamp2);
    }
}
