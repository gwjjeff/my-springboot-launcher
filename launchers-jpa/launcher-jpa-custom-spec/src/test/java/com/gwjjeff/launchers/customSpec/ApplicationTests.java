package com.gwjjeff.launchers.customSpec;

import com.gwjjeff.launchers.customSpec.dao.PersonRepository;
import com.gwjjeff.launchers.customSpec.domain.Person;
import com.gwjjeff.launchers.customSpec.support.CustomRepositoryFactoryBean;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by jeff on 2017/5/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
public class ApplicationTests {

    @Autowired
    PersonRepository personRepository;

    @Test
    public void test() throws Exception {
        List<Person> people = personRepository.findByAddress("合肥");
        Assert.assertEquals(2, people.size());

        Person person = personRepository.findByNameAndAddress("bb", "合肥");
        Assert.assertEquals(27, person.getAge().intValue());

        Page<Person> page = personRepository.findByAuto(person, new PageRequest(0, 10));
        Assert.assertEquals(1, page.getNumberOfElements());
    }
}
