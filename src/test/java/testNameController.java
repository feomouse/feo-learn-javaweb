import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import controllers.NameController;
import doa.HibernateDoa;
import doa.IHibernateDoa;
import doa.IMyBatisDoa;
import doa.MyBatisDoa;
import domin.Person;
import junit.framework.TestCase;

public class testNameController extends TestCase {
    private MyBatisDoa doa = new MyBatisDoa();
	
	@Test
	public void testDoa() {
		assertEquals("boy", doa.check("feo"));
	}
	
	@Test
	public void testControllerGetSexByName() {
		String sex = doa.getPersonSexByName("feo");
		assertEquals("boy", sex);
		
        //assertEquals("feo", doa.getNameById(1));
	}
	
	@Test
	public void testInsertPerson() {
		Person pers = new Person(3, "xixi", "girl");
		assertEquals(true, doa.insertPerson(pers));
	}
	
	@Test
	public void testUpdatePerson() {
		assertEquals("jama", doa.setPersonName("lilk", "jama"));
	}
}
