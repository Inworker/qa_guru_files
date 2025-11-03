package guru.qa;

import com.google.gson.Gson;
import guru.qa.model.Glossary;
import guru.qa.model.GlossaryInner;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.InputStreamReader;
import java.io.Reader;

public class JacksonParsingJsonText {

    private ClassLoader cl = JacksonParsingJsonText.class.getClassLoader();
    private static final Gson gson = new Gson();

    @Description("Парсинг json с помощью Jackson")
    @Test
    void jsonFileParsingImproveTest() throws Exception
    {
        try (Reader reader = new InputStreamReader(cl.getResourceAsStream("glossary.json")))
        {

            Glossary actual = gson.fromJson(reader, Glossary.class);
            Assertions.assertEquals("example glossary", actual.getTitle());
            Assertions.assertEquals(234234, actual.getID());

            GlossaryInner inner = actual.getGlossary();
            Assertions.assertEquals("SGML", inner.getSortAs());


        }
    }
}
