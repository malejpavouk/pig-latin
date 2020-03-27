package xyz.micka.piglatin.token;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DelimiterTokenTest {

    @Test
    public void pigLatinTranslationIsEqualToOriginal() {
        var delimiter = new DelimiterToken(":");
        Assertions.assertThat(delimiter.getPigLatin()).isEqualTo(delimiter.getOriginal());
    }
}
