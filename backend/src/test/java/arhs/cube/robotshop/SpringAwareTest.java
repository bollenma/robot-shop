package arhs.cube.robotshop;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bollenma
 */
@RunWith(SpringRunner.class)
@Transactional
public abstract class SpringAwareTest {

    public String alternateCase(final String text) {

        final int nameSize = text.length();

        final StringBuilder alternateCaseName = new StringBuilder(nameSize);
        for (int i = 0; i < nameSize; i++) {
            final char character = text.charAt(i);
            if (i % 2 == 0) {
                alternateCaseName.append(Character.toLowerCase(character));
            } else {
                alternateCaseName.append(Character.toUpperCase(character));
            }
        }
        return alternateCaseName.toString();
    }

}
