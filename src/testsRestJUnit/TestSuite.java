package testsRestJUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)

@Suite.SuiteClasses({
	TestRestLugar.class,
	TestRestTematica.class,
	TestRestUsuario.class

//	TipoTrabajoTest.class,
//	TrabajoTest.class,
//	UsuarioTest.class
})

public class TestSuite {

}