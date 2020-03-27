package xyz.micka.piglatin.rule;

import org.assertj.core.api.Assertions;

public abstract class AbstractRuleTest {
    public abstract PigLatinRule getRule();

    /**
     * Assert result (target state (result of previous rules) for the rule is constructed as source.toLowerCase()).
     *
     * @param source   the initial state when this rule is applied
     * @param expected expected result after this rule is applied
     */
    protected void assertResult(String source, String expected) {
        assertResult(source, source.toLowerCase(), expected);
    }

    /**
     * Assert result based of invocation of the rule on resultByPrevious state.
     *
     * @param source           the state when this rule is applied
     * @param resultByPrevious state created by previous rules
     * @param expected         expected result after this rule is applied
     */
    protected void assertResult(String source, String resultByPrevious, String expected) {
        StringBuilder target = new StringBuilder(resultByPrevious);

        getRule().transform(source, target);

        Assertions.assertThat(target.toString()).isEqualTo(expected);
    }
}
