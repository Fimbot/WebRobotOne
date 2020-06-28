package cz.prorobot.webapp;

import cz.prorobot.webapp.*;
import cz.prorobot.webapp.entity.Uzivatel;
import cz.prorobot.webapp.repository.UzivatelRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {

    @Autowired
    private UzivatelRepository repositoryUzivatel;

    @Test
    public void findById_returnUzivatel()
    {

        Long testId=1L;
        Uzivatel uzivatel = new Uzivatel();
        uzivatel.setJmeno("test_Jmeno");
        uzivatel.setPrijmeni("test_Prijmeni");
        uzivatel.setRole(99);
        uzivatel.setId(testId);

        repositoryUzivatel.save(uzivatel);


        Uzivatel foundedUzivatel = repositoryUzivatel.findById(testId);

        assertThat(foundedUzivatel.toString()).isEqualTo(uzivatel.toString());

        //delete test repositoryUzivatel after test finish
        //repositoryUzivatel.deleteById(testId);
    }


}
