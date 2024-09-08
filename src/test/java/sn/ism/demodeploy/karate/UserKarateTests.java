package sn.ism.demodeploy.karate;

import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserKarateTests {

    @Karate.Test
    private Karate testAll(){
        return Karate.run()
                .relativeTo(getClass());
    }

}