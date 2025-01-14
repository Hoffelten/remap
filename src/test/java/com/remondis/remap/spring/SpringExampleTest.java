package com.remondis.remap.spring;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import com.remondis.remap.Mapper;
import com.remondis.remap.Mapping;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
class SpringExampleTest {

  @Autowired
  Mapper<Person, Human> mapper1;

  @Autowired
  Mapper<Human, Person> mapper2;

  @Test
  void testPersonHumanMapper() {
    Person person = new Person("Bob");
    Human human = mapper1.map(person);
    assertThat(human.getName()).isEqualTo("Bob");
  }

  @Test
  void testHumanPersonMapper() {
    Human human = new Human("ET");
    Person person = mapper2.map(human);
    assertThat(person.getName()).isEqualTo("ET");
  }

  @Configuration
  static class TestConfiguration {

    @Bean
    Mapper<Person, Human> personHumanMapper() {
      return Mapping.from(Person.class)
          .to(Human.class)
          .mapper();
    }

    @Bean
    Mapper<Human, Person> humanPersonMapper() {
      return Mapping.from(Human.class)
          .to(Person.class)
          .mapper();
    }

  }

  public static class Person {
    private String name;

    public Person() {
    }

    public Person(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

  public static class Human {
    private String name;

    public Human() {
    }

    public Human(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

}
