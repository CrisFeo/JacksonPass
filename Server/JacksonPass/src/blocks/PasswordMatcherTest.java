package blocks;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class PasswordMatcherTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		List<IBlock> password = PasswordMatcher.generatePassword("JACKSON!%");
		assert(password.size()!=0);
	}

}
