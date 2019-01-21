package cc.ddknight.tools;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * String Compress Tool
 *
 * @author ddknight
 * @version 1.0.0
 */
public class StringCompressTools {

    /**
     * compress string into gzip byte and transfer to Base 64 format.
     *
     * @param str original String
     * @return gzip byte's base64 string
     * @throws IOException
     */
    public static String compressStringToBase64(String str) throws IOException {
        byte[] temp = compressString(str);
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(temp);
    }

    /**
     * depress string from compressed base64 string
     *
     * @param compressedBase64 compressed base64 String
     * @return depressed string
     * @throws IOException
     */
    public static String depressStringFromBase64(String compressedBase64) throws IOException {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedBase64Byte = decoder.decode(compressedBase64);
        return depressString(decodedBase64Byte);
    }

    /**
     * Compress string into gzip byte arrays
     *
     * @param str original string
     * @return gzip format byte arrays
     */
    public static byte[] compressString(String str) throws IOException {
        byte[] result = null;
        if (StringUtils.isNotBlank(str)) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(os);
            gzipOutputStream.write(str.getBytes());
            gzipOutputStream.close();
            result = os.toByteArray();
            os.close();
        }
        return result;
    }

    /**
     * Depress string from zip byte array
     *
     * @param compressedBytes compressed data
     * @return depressed string.
     */
    public static String depressString(byte[] compressedBytes) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressedBytes);
        GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
        byte[] depressedBytes = IOUtils.toByteArray(gzipInputStream);
        return new String(depressedBytes, StandardCharsets.UTF_8);
    }
}
