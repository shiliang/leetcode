package leetcode.systemdesign;

import leetcode.design.FileSystem;
import org.junit.Test;

public class FileSystemTest {

    FileSystem fileSystem = new FileSystem();
    @Test
    public void create() {
        fileSystem.create("/a", 1);
    }
}