package leetcode.systemdesign;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileSystemTest {

    FileSystem fileSystem = new FileSystem();
    @Test
    public void create() {
        fileSystem.create("/a", 1);
    }
}