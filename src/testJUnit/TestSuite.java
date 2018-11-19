package testJUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)

@Suite.SuiteClasses({
	LugarTest.class,
	TematicaTest.class,
	TipoTrabajoTest.class,
//	TrabajoTest.class,
	UsuarioTest.class
})

public class TestSuite {

}