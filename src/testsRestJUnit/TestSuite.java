package testsRestJUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)

@Suite.SuiteClasses({
	TestRestLugar.class,
	TestRestTipoTrabajo.class,
	TestRestTematica.class,
	TestRestUsuario.class,
	TestRestTrabajo.class
})

public class TestSuite {

}