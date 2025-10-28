package guru.qa;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideFilesTest {
    @Test
    void downloadFileTest() throws Exception
    {
        open("https://github.com/junit-team/junit-framework/blob/main/README.md");
        File download = $(".react-blob-header-edit-and-raw-actions [href*='/main/README.md']").download();
        System.out.println();
        InputStream inputStream = new FileInputStream(download);
    }

}
