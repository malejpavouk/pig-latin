package xyz.micka.piglatin.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.micka.piglatin.rule.CapitalizationRule;
import xyz.micka.piglatin.rule.ConsonantRule;
import xyz.micka.piglatin.rule.PigLatinRule;
import xyz.micka.piglatin.rule.PunctuationRule;
import xyz.micka.piglatin.rule.VowelRule;

import java.util.List;

public class WordToken extends Token {
    private static final Logger logger = LoggerFactory.getLogger(WordToken.class);
    private static final List<PigLatinRule> RULES = List.of(
            ConsonantRule.INSTANCE, // consonants and vowels are mutually exclusive
            VowelRule.INSTANCE,
            CapitalizationRule.INSTANCE, // capitalize
            PunctuationRule.INSTANCE // put intra-word punctuations where they were in the original word (from end)
    );

    public WordToken(String original) {
        super(original);
    }

    @Override
    public String getPigLatin() {
        var target = new StringBuilder(getOriginal());
        RULES.forEach(rule -> rule.transform(getOriginal(), target));

        logger.debug("Translation of word '{}' is '{}'", getOriginal(), target);

        return target.toString();
    }
}
