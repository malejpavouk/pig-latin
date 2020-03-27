package xyz.micka.piglatin.token;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WordTokenTest {

    @Test
    public void getPigLatinAppliesAllRules() {
        Assertions.assertThat(new WordToken("cAn't").getPigLatin()).isEqualTo("aNtca'y");
        Assertions.assertThat(new WordToken("Ain't").getPigLatin()).isEqualTo("Aintwa'y");
    }
}