package xyz.micka.piglatin.rule;

import org.junit.jupiter.api.Test;

class ConsonantRuleTest extends AbstractRuleTest {

    @Test
    public void helloGetsTransformedToEllohay() {
        assertResult("hello", "ellohay");
    }

    @Test
    public void transformationWorksForSingleCharStrings() {
        assertResult("h", "hay");
    }

    @Test
    public void doesNotModifyWordsEndingWithWay() {
        assertResult("starway", "starway");
    }


    @Override
    public PigLatinRule getRule() {
        return ConsonantRule.INSTANCE;
    }
}