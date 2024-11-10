import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.fra.uas.BeanExampleApplication;
import edu.fra.uas.controller.BeanController;

@SpringBootTest(classes = BeanExampleApplication.class) 
class ControllerTest {

    @Autowired
    private BeanController beanController;

    @Test
    void testController() {
        String message = "Das ist ein Test";
        assertThat(beanController.putMessage(message)).isEqualTo(" put message: " + message);
    }

    @Test
    void testIncrementCounter() {
        beanController.incrementCounter();
        assertThat(beanController.getCounter()).isEqualTo(1);
    }

    @Test
    void testGetCounter() {
        assertThat(beanController.getCounter()).isEqualTo(1);
    }
}
