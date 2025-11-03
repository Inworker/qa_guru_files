package guru.qa;
import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadingFilesFromZipTest {

    @Test
    void ReadingPdfFromZip() throws Exception {
        ClassLoader cl = this.getClass().getClassLoader();

        // Проверяем, что ресурс существует
        InputStream resourceStream = cl.getResourceAsStream("WorkDavay.zip");
        if (resourceStream == null) {
            throw new FileNotFoundException("Не найден архив: WorkDavay.zip");
        }

        try (ZipInputStream zis = new ZipInputStream(resourceStream)) {
            ZipEntry entry;
            boolean pdfFound = false;

            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("В архиве есть файл: " + entry.getName());

                // Проверка имени файла
                if (entry.getName().endsWith("WorkDavay.pdf")) {
                    PDF pdf = new PDF(zis);

                    Assertions.assertEquals(233, pdf.numberOfPages);
                    System.out.println("Кол-во страниц: " + pdf.numberOfPages);

                    Assertions.assertEquals("JUnit User Guide", pdf.title);
                    System.out.println("Название книги: " + pdf.title);

                    pdfFound = true;
                }
            }

            if (!pdfFound) {
                System.out.println("PDF файл не найден в архиве");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void ReadingCsvFromZip() throws Exception {
        ClassLoader cl = this.getClass().getClassLoader();

        // Проверяем, что ресурс существует
        InputStream resourceStream = cl.getResourceAsStream("WorkDavay.zip");
        if (resourceStream == null) {
            throw new FileNotFoundException("Не найден архив: WorkDavay.zip");
        }

        try (ZipInputStream zis = new ZipInputStream(resourceStream)) {
            ZipEntry entry;
            boolean csv = false;

            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("В архиве есть файл: " + entry.getName());

                // Проверка имени файла
                if (entry.getName().endsWith("example.csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis, StandardCharsets.UTF_8));
                    List<String[]> data = csvReader.readAll();

                    // Проверяем первую строку
                    assertArrayEquals(new String[]{"Selenide", "https://selenide.org"}, data.get(0));
                    assertEquals("Selenide", data.get(0)[0]);
                    assertEquals("https://selenide.org", data.get(0)[1]);

                    // Проверяем вторую строку
                    assertArrayEquals(new String[]{"JUnit 5", "https://junit.org"}, data.get(1));
                    assertEquals("JUnit 5", data.get(1)[0]);
                    assertEquals("https://junit.org", data.get(1)[1]);

                    csv = true;
                }
            }

            if (!csv) {
                System.out.println("csv файл не найден в архиве");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void ReadingXlsFromZip() throws Exception {
        ClassLoader cl = this.getClass().getClassLoader();

        // Проверяем, что ресурс существует
        InputStream resourceStream = cl.getResourceAsStream("WorkDavay.zip");
        if (resourceStream == null) {
            throw new FileNotFoundException("Не найден архив: WorkDavay.zip");
        }

        try (ZipInputStream zis = new ZipInputStream(resourceStream)) {
            ZipEntry entry;
            boolean xls = false;

            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("В архиве есть файл: " + entry.getName());

                // Проверка имени файла
                if (entry.getName().endsWith("XLS10.xls")) {
                    XLS xlsfile = new XLS(zis);

                    String firstName = xlsfile.excel.getSheetAt(0).getRow(3).getCell(1).getStringCellValue();
                    String lastName = xlsfile.excel.getSheetAt(0).getRow(3).getCell(2).getStringCellValue();

                    System.out.println("Проверяем имя + фамилия: " + firstName + " " + lastName);
                    Assertions.assertEquals("Philip", firstName);
                    Assertions.assertEquals("Gent", lastName);
                    System.out.println("Все проверки успешны, идем пить пиво");

                    xls = true;
                }
            }

            if (!xls) {
                System.out.println("csv файл не найден в архиве");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}