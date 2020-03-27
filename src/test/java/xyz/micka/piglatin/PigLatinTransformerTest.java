package xyz.micka.piglatin;

import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

class PigLatinTransformerTest {
    public static final Charset UTF8 = StandardCharsets.UTF_8;
    private PigLatinTransformer transformer = new PigLatinTransformer();

    @Test
    public void wordContainingHyphenIsHandledAsTwoWords() {
        assertTransformerResult("this-thing", "histay-hingtay");
    }

    @Test
    public void punctuationAtTheEndOfTheWordsStaysThere() {
        assertTransformerResult("end.", "endway.");
    }

    @Test
    public void quotationUsingApostrophesIsTreatedAsOutsideOfTheWord() {
        assertTransformerResult("'apple'", "'appleway'");
    }

    @Test
    public void quotationUsingSingleQuotesIsTreatedAsOutsideOfTheWord() {
        assertTransformerResult("’apple’", "’appleway’");
    }

    private void assertTransformerResult(String source, String expected) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos, true, StandardCharsets.UTF_8);
        transformer.transform(IOUtils.toInputStream(source, UTF8), out);
        Assertions.assertThat(baos.toString(StandardCharsets.UTF_8)).isEqualTo(expected);
    }
}