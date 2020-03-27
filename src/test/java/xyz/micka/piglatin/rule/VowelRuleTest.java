package xyz.micka.piglatin.rule;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class VowelRuleTest extends AbstractRuleTest {

    @Test
    public void appleGetsTransformedToAppleway() {
        assertResult("apple", "appleway");
    }

    @Test
    public void transformationWorksForSingleCharStrings() {
        assertResult("a", "away");
    }

    @Test
    public void doesNotModifyWordsEndingWithWay() {
        assertResult("away", "away");
    }

    @Override
    public PigLatinRule getRule() {
        return VowelRule.INSTANCE;
    }
}