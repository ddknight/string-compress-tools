package cc.ddknight.tools;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StringCompressToolsTest {
    private static final Logger LOGGER = Logger.getLogger(StringCompressToolsTest.class.getName());
    private static final String testString = "千呼万唤始出来，阿里巴巴在首次发布java开发手册后，时隔一年终于推出了IDEA的代码检查插件了。插件应该是十月八号就悄悄的发布了，插件源码推迟了5天才上传。如果说java代码规范从意识上统一了团队整体代码开发风格的话，那么规范插件的推出可以强制团队成员遵循编程规范，简直是大java行业内的一大福音。博主使用的开发工具是IDEA，所以下文也是IDEA插件相关的操作，Eclipse用户可以去阿里官方仓库参考，文末会给出相关的地址。好了，接下里就跟着博主一起来开启傻瓜试的阿里java规范IDEA插件的安装体验之路吧,千呼万唤始出来，阿里巴巴在首次发布java开发手册后，时隔一年终于推出了IDEA的代码检查插件了。插件应该是十月八号就悄悄的发布了，插件源码推迟了5天才上传。如果说java代码规范从意识上统一了团队整体代码开发风格的话，那么规范插件的推出可以强制团队成员遵循编程规范，简直是大java行业内的一大福音。博主使用的开发工具是IDEA，所以下文也是IDEA插件相关的操作，Eclipse用户可以去阿里官方仓库参考，文末会给出相关的地址。好了，接下里就跟着博主一起来开启傻瓜试的阿里java规范IDEA插件的安装体验之路吧";
    private static final String TEMP_FILE = FileUtils.getTempDirectoryPath() + File.separator + "compressData.dat";
    private static final String TEMP_FILE_BASE64 = FileUtils.getTempDirectoryPath() + File.separator + "compressDataBase64.dat";

    @Test
    @DisplayName("Test Compress To GZip Byte Function")
    void testCompress() {
        LOGGER.log(Level.INFO, "original String:" + testString);
        LOGGER.log(Level.INFO, "original length:" + testString.length());
        byte[] result = null;
        try {
            result = StringCompressTools.compressString(testString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(result);
        LOGGER.log(Level.INFO, "compressed String:" + new String(result));
        LOGGER.log(Level.INFO, "compressed length:" + new String(result).length());
        File file = new File(TEMP_FILE);
        try {
            FileUtils.writeByteArrayToFile(file, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Test Compress To Base64 Function ")
    void testCompressToBase64() {
        LOGGER.log(Level.INFO, "original String:" + testString);
        LOGGER.log(Level.INFO, "original length:" + testString.length());
        String result = null;
        try {
            result = StringCompressTools.compressStringToBase64(testString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(result);
        LOGGER.log(Level.INFO, "compressed String:" + result);
        LOGGER.log(Level.INFO, "compressed length:" + result.length());
        File file = new File(TEMP_FILE_BASE64);
        try {
            FileUtils.writeStringToFile(file, result, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Test Depress From GZip Bytes Function")
    void testDepress() throws IOException {
        File file = new File(TEMP_FILE);
        byte[] compressedData = FileUtils.readFileToByteArray(file);
        String result = StringCompressTools.depressString(compressedData);
        Assertions.assertEquals(testString, result);
        LOGGER.log(Level.INFO, "original String:" + testString);
        LOGGER.log(Level.INFO, "depressed String:" + result);
    }

    @Test
    @DisplayName("Test Depress From Base64 Function")
    void testDepressFromBase64() throws IOException {
        File file = new File(TEMP_FILE_BASE64);
        String compressedDataBase64 = FileUtils.readFileToString(file, "UTF-8");
        String result = StringCompressTools.depressStringFromBase64(compressedDataBase64);
        Assertions.assertEquals(testString, result);
        LOGGER.log(Level.INFO, "original String:" + testString);
        LOGGER.log(Level.INFO, "depressed String:" + result);
    }
}
