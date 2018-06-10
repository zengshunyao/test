package category.book.shenrufenxijavaweb.ch02.nio.path;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/5/9 0:38
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class PathExample {
    public static void main(String[] args) throws IOException {
        sun.nio.cs.ext.EUC_CN c;
        ClassLoader classLoader;
        System.out.println(PathExample.class.getClassLoader().getResource("").toString());
//        BootstrapClassLoader s;
//        ExtClassLoader c1;
//        InternalInputBuffer s;
//        Path sourcePath = Paths.get("E:\\1.txt");
//        Path destPath = Paths.get("E:\\1bak.txt");
//        System.out.println(sourcePath);
//        System.out.println(destPath);

//        new PathExample().copyFile(new File("E:\\1.txt"), new File("E:\\1bak.txt"));
        long start = System.currentTimeMillis();
        new PathExample().copyFile("E:\\新建 Microsoft Excel 工作表.xlsx", "E:\\1新建 Microsoft Excel 工作表.xlsx");
        System.out.println((System.currentTimeMillis() - start) + "毫秒");

        Path path = Paths.get("data/logging.properties");
//        Path sourcePath = Paths.get("data/logging.properties");
//        Path destinationPath = Paths.get("data/logging-copy.properties");
        start = System.currentTimeMillis();
        Path sourcePath = Paths.get("E:\\新建 Microsoft Excel 工作表.xlsx");
        Path destPath = Paths.get("E:\\1新建 Microsoft Excel 工作表.xlsx");
        try {

            Files.copy(sourcePath, destPath,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (FileAlreadyExistsException e) {
            //destination file already exists
        } catch (IOException e) {
            //something else went wrong
            e.printStackTrace();
        }
        System.out.println((System.currentTimeMillis() - start) + "毫秒");

        Files.move(sourcePath, destPath,
                StandardCopyOption.REPLACE_EXISTING);

        //walkFileTree
        Files.walkFileTree(path, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("pre visit dir:" + dir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("visit file: " + file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                System.out.println("visit file failed: " + file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println("post visit directory: " + dir);
                return FileVisitResult.CONTINUE;
            }
        });

        //
    }

    /**
     * 拷贝文件
     *
     * @param source
     * @param dest
     * @throws IOException
     */
    public void copyFile(String source, String dest) throws IOException {
        this.copyFile(Paths.get(source).toFile(), Paths.get(dest).toFile());
    }

    /**
     * 拷贝文件
     *
     * @param source
     * @param dest
     * @throws IOException
     */
    public void copyFile(File source, File dest) throws IOException {
//        Path sourcePath = Paths.get(source);
//        Path destPath = Paths.get(dest);
//        System.out.println(sourcePath);
        //
        RandomAccessFile sourceRandomAccessFile = null;
        RandomAccessFile destRandomAccessFile = null;
        try {
            sourceRandomAccessFile = new RandomAccessFile(source, "rw");
            destRandomAccessFile = new RandomAccessFile(dest, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }

        FileChannel sourceFileChannel = sourceRandomAccessFile.getChannel();
        FileChannel descFileChannel = destRandomAccessFile.getChannel();
        try {
            sourceFileChannel.transferTo(0, sourceRandomAccessFile.length(), descFileChannel);
            //或者
            descFileChannel.transferFrom(sourceFileChannel, 0, sourceRandomAccessFile.length());

//            sourceFileChannel.map(FileChannel.MapMode.READ_WRITE,0,sourceRandomAccessFile.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sourceFileChannel.close();
            sourceRandomAccessFile.close();
            descFileChannel.close();
            destRandomAccessFile.close();
        }
    }

    public boolean exists(String path) {
        return exists(Paths.get(path));
    }

    public boolean exists(Path path) {
        //意思是不包含符号链接的文件
        boolean pathExists = Files.exists(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});

        Path path1 = Paths.get("data/subdir");

        try {
            Path newDir = Files.createDirectory(path1);
            return true;
        } catch (FileAlreadyExistsException e) {
            // the directory already exists.
        } catch (IOException e) {
            //something else went wrong
            e.printStackTrace();
        }
        return false;
    }

    //搜索文件
    public void searchingForFiles() {
        Path rootPath = Paths.get("data");
        final String fileToFind = File.separator + "README.txt";

        try {
            Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String fileString = file.toAbsolutePath().toString();
                    //System.out.println("pathString = " + fileString);

                    if (fileString.endsWith(fileToFind)) {
                        System.out.println("file found at path: " + file.toAbsolutePath());
                        return FileVisitResult.TERMINATE;
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //递归删除目录
    public void deletingDirectoriesRecursively() {
        Path rootPath = Paths.get("data/to-delete");

        try {
            Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("delete file: " + file.toString());
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    System.out.println("delete dir: " + dir.toString());
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
