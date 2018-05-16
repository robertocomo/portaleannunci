package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AcquistaAnnuncioControllerTest.class, AnnuncioBeanTest.class, AnnuncioTest.class,
	RegistrationBeanTest.class })
public class AllTests {

}
