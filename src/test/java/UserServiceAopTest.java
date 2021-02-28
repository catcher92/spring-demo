import com.catcher92.demo.springdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by caoxuedong on 2017/2/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceAopTest {

    @Autowired
    private UserService userService;

    @Test
    public void testFindAll(){
        userService.findAll();
    }
}
