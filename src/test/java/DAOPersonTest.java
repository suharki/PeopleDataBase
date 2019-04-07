import Data.Person;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

abstract class DAOPersontest {

    protected DAOPerson sut;



    @Test
    void findAll() {
        List<Person> students = sut.findAll();

        assertThat(students).hasSize(5);

        Set<Integer> allAges = students.stream().map(Person::getAge).collect(Collectors.toSet());
        assertThat(allAges).containsExactlyInAnyOrder(20, 40, 60, 22, 16);
    }

    @Test
    void findById() {
        Person existingP = findOneOfP();
        int id = existingP.getId();

        Optional<Person> person = sut.findById(id);

        assertThat(person).isPresent();
        assertThat(person.get().getAge()).isEqualTo(22);
        assertThat(person.get().getName()).isEqualTo("student 4");
    }

    @Test
    void create() {
        Person person = new Person();
        person.setName("SDA Student");
        person.setAge(4);

        int id = sut.create(person);

        Optional<Person> createdPerson = sut.findById(id);
        assertThat(createdPerson).isPresent();
        assertThat(createdPerson.get().getAge()).isEqualTo(4);
        assertThat(createdPerson.get().getName()).isEqualTo("SDA Student");
    }

    @Test
    void update() {
        Person existingP = findOneOfP();
        existingP.setName("some new name");

        int updatedPId = sut.update(existingP);

        Optional<Person> updatedP = sut.findById(updatedPId);
        assertThat(updatedP).isPresent();
        assertThat(updatedP.get().getAge()).isEqualTo(22);
        assertThat(updatedP.get().getName()).isEqualTo("some new name");
    }

    @Test
    void delete() {
        Person existingP = findOneOfP();
        int id = existingP.getId();

        sut.delete(id);

        Optional<Person> removedStudent = sut.findById(id);
        assertThat(removedStudent).isNotPresent();
    }

    @Test
    void deleteAll() {

        sut.deleteAll();

        List<Person> students = sut.findAll();
        assertThat(students).hasSize(0);
    }




    private Person findOneOfP() {
        Optional<Person> existingP = sut.findAll()
                .stream()
                .filter(person -> person.getAge() == 22)
                .findFirst();
        assertThat(existingP).isPresent();
        return existingP.get();
    }
}
