package encoding.gbktoutf8;

/**********************************************************************   
 * &lt;p&gt;文件名：UTF8BOMConverter.java &lt;/p&gt;
 * &lt;p&gt;文件描述：(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyaojava
 * @create 2019/6/17 22:09 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Generic unicode textreader, which will use BOM mark
 * to identify the encoding to be used. If BOM is not found
 * then use a given default or system encoding.
 */
public class UTF8BOMConverter extends Reader {
    PushbackInputStream internalIn;
    InputStreamReader internalIn2 = null;
    String defaultEnc;
    private static final int BOM_SIZE = 4;

    /**
     * @param in         inputstream to be read
     * @param defaultEnc default encoding if stream does not have
     *                   BOM marker. Give NULL to use system-level default.
     */
    UTF8BOMConverter(InputStream in, String defaultEnc) {
        internalIn = new PushbackInputStream(in, BOM_SIZE);
        this.defaultEnc = defaultEnc;
    }

    public String getDefaultEncoding() {
        return defaultEnc;
    }

    /**
     * Get stream encoding or NULL if stream is uninitialized.
     * Call init() or read() method to initialize it.
     */
    public String getEncoding() {
        if (internalIn2 == null) return null;
        return internalIn2.getEncoding();
    }

    /**
     * Read-ahead four bytes and check for BOM marks. Extra bytes are
     * unread back to the stream, only BOM bytes are skipped.
     */
    protected void init() throws IOException {
        if (internalIn2 != null) return;
        String encoding;
        byte bom[] = new byte[BOM_SIZE];
        int n, unread;
        n = internalIn.read(bom, 0, bom.length);
        if ((bom[0] == (byte) 0x00) && (bom[1] == (byte) 0x00) &&
                (bom[2] == (byte) 0xFE) && (bom[3] == (byte) 0xFF)) {
            encoding = "UTF-32BE";
            unread = n - 4;
        } else if ((bom[0] == (byte) 0xFF) && (bom[1] == (byte) 0xFE) &&
                (bom[2] == (byte) 0x00) && (bom[3] == (byte) 0x00)) {
            encoding = "UTF-32LE";
            unread = n - 4;
        } else if ((bom[0] == (byte) 0xEF) && (bom[1] == (byte) 0xBB) &&
                (bom[2] == (byte) 0xBF)) {
            encoding = "UTF-8";
            unread = n - 3;
        } else if ((bom[0] == (byte) 0xFE) && (bom[1] == (byte) 0xFF)) {
            encoding = "UTF-16BE";
            unread = n - 2;
        } else if ((bom[0] == (byte) 0xFF) && (bom[1] == (byte) 0xFE)) {
            encoding = "UTF-16LE";
            unread = n - 2;
        } else {
            // Unicode BOM mark not found, unread all bytes
            encoding = defaultEnc;
            unread = n;
        }
        //System.out.println("read=" + n + ", unread=" + unread);
        if (unread > 0) internalIn.unread(bom, (n - unread), unread);
        // Use given encoding
        if (encoding == null) {
            internalIn2 = new InputStreamReader(internalIn);
        } else {
            internalIn2 = new InputStreamReader(internalIn, encoding);
        }
    }

    public void close() throws IOException {
        init();
        internalIn2.close();
    }

    public int read(char[] cbuf, int off, int len) throws IOException {
        init();
        return internalIn2.read(cbuf, off, len);
    }


    private static void readContentAndSaveWithEncoding(String filePath, String readEncoding, String saveEncoding) throws Exception {
        saveContent(filePath, readContent(filePath, readEncoding), saveEncoding);
    }

    private static void saveContent(String filePath, String content, String encoding) throws Exception {
        FileOutputStream fos = new FileOutputStream(filePath);
        OutputStreamWriter w = new OutputStreamWriter(fos, encoding);
        w.write(content);
        w.flush();
    }

    private static String readContent(String filePath, String encoding) throws Exception {
        FileInputStream file = new FileInputStream(new File(filePath));
        BufferedReader br = new BufferedReader(new UTF8BOMConverter(file, encoding));
        String line = null;
        String fileContent = "";
        while ((line = br.readLine()) != null) {
            fileContent = fileContent + line;
            fileContent += "\r\n";
        }
        return fileContent;
    }

    private static List<String> getPerlineFileName(String filePath) throws Exception {
        FileInputStream file = new FileInputStream(new File(filePath));
        BufferedReader br = new BufferedReader(new InputStreamReader(file, "UTF-8"));
        String line = null;
        List<String> list = new LinkedList<>();
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        return list;
    }


    private static List<String> getAllFilePaths(File filePath, List<String> filePaths) {
        File[] files = filePath.listFiles();
        if (files == null) {
            return filePaths;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                filePaths.add(f.getPath());
                getAllFilePaths(f, filePaths);
            } else {
                filePaths.add(f.getPath());
            }
        }
        return filePaths;
    }

    public static void main(String[] args) throws Exception {
        String suffix = ".java";

        List<String> paths = new ArrayList<String>();
        paths = getAllFilePaths(new File("E:\\ZSY\\studyExample\\oschina\\spring-framework-3.2.6.RELEASE\\spring-aop\\src\\main\\java\\org\\springframework\\aop"), paths);

        List<String> pathList = new ArrayList<String>();
        for (String path : paths) {
            if (path.endsWith(suffix)) {
                pathList.add(path);
            }
        }

        for (String path : pathList) {

            File file = new File(path);
            InputStream in = new java.io.FileInputStream(file);
            byte[] b = new byte[3];
            in.read(b);
            in.close();
            if (b[0] == -17 && b[1] == -69 && b[2] == -65) {
                System.out.println(file.getName() + "：编码为UTF-8");
                continue;
            } else {
                System.out.println(file.getName() + "：可能是GBK，也可能是其他编码");
            }

            //注意如果是GBK编码的文件，需要2个参数为GBK，否则文件会乱码无法恢复
            readContentAndSaveWithEncoding(path, "GBK", "UTF-8");
            System.out.println(path + "转换成功");
        }
    }
}