Out of scope/Known limitations
===
 * Abbreviations
 * Vowels and consonants are evaluated "as written", not phonetically (which will inevevitable cause some anomalies, as Pig Latin is intended to be used in spoken form)
 * **y** is always considered a vowel. Which is not true in English (validated with client that this is expected).
 * Words that are shortened using apostrophe at the end or start of the word ('tis == it is; goin' == going)
 
 
How to build it
===
Project uses gradle wrapper, so just use:
`./gradlew build`
This command builds a fat jar, which includes all the necessary dependencies.

How does it work
===
Reads English words, sentences or paragraphs from standard input. Prints the results on the standard output.

Usage
===
Build the project. Go to `build/libs` directory and execute.
```
$ printf "Hello 'apple', \nstarway can't end this-day." | java -jar pig-latin-1.0.0.jar
Ellohay 'appleway',
starway antca'y endway histay-ayday.
```

Technologies
===
 * **Build**: Gradle (Kotlin DSL)
 * **Code**: Java 11
 * **Quality**: Checkstyle, PMD, JaCoCo
 * **Logging**: SLF4J/Logback
 * **Tests**: jUnit 5
