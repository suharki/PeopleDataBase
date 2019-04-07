import Data.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class DAOPerson {

    private static final EntityManagerFactory emf;
    static {// Create EntityManagerFactory using Persistence. This enable you to create EntityManagers.
        emf = Persistence.createEntityManagerFactory("PersonProject");
    }


    List<Person> findAll(){
            EntityManager eM= emf.createEntityManager();
            List<Person> personList =  eM.createQuery("FROM persom",Person.class).getResultList(); //odnosze sie do encji (obiektu)
            eM.close();
            return personList;
        }

        Optional<Person> findById(int id){
            EntityManager eM= emf.createEntityManager();
            Person person = eM.find(Person.class,id);
            eM.close();
            return Optional.ofNullable(person);

        }

        int create(Person person){

            EntityManager eM= emf.createEntityManager();
            eM.getTransaction().begin();
            eM.persist(person);
            eM.getTransaction().commit();
            eM.close();
            return person.getId();


        }

        int update(Person person){

            EntityManager eM= emf.createEntityManager();
            EntityTransaction eT = null;
            try {
                eT = eM.getTransaction();
                eT.begin();
                eM.persist(person);
                eT.commit();
            }
            catch (RuntimeException e){
                eT.rollback();
            }
            finally {
                eM.close();
            }

            return person.getId();

        }

        void delete(int id){

            EntityManager eM= emf.createEntityManager();
            Person person = eM.find(Person.class,id);
            eM.getTransaction().begin();
            eM.remove(person);
            eM.getTransaction().commit();
            eM.close();
        }

        void deleteAll(){

            EntityManager eM= emf.createEntityManager();
            List<Person> studentList =  eM.createQuery("FROM Student",Person.class).getResultList(); //odnosze sie do encji (obiektu)
            EntityTransaction eT = null;
            try {
                eT = eM.getTransaction();
                eT.begin();
                for (Person s:studentList  ) {
                    eM.remove(s);
                }
                eT.commit();

            }catch (RuntimeException e){
                eT.rollback();
            }
            finally {eM.close();}
        }


}


