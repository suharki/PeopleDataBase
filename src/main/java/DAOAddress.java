import Data.Address;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class DAOAddress  {

    private static final EntityManagerFactory emf;
    static {// Create EntityManagerFactory using Persistence. This enable you to create EntityManagers.
        emf = Persistence.createEntityManagerFactory("PersonProject");
    }


    Optional<Address> findById(int id){
        EntityManager eM= emf.createEntityManager();
        Address address = eM.find(Address.class,id);
        eM.close();
        return Optional.ofNullable(address);

    }

    int create(Address address){

        EntityManager eM= emf.createEntityManager();
        eM.getTransaction().begin();
        eM.persist(address);
        eM.getTransaction().commit();
        eM.close();
        return address.getId();


    }

    int update(Address address){

        EntityManager eM= emf.createEntityManager();
        EntityTransaction eT = null;
        try {
            eT = eM.getTransaction();
            eT.begin();
            eM.persist(address);
            eT.commit();
        }
        catch (RuntimeException e){
            eT.rollback();
        }
        finally {
            eM.close();
        }

        return address.getId();

    }

    void delete(int id){

        EntityManager eM= emf.createEntityManager();
        Address address= eM.find(Address.class,id);
        eM.getTransaction().begin();
        eM.remove(address);
        eM.getTransaction().commit();
        eM.close();
    }

    void deleteAll(){

        EntityManager eM= emf.createEntityManager();
        List<Address> addressList =  eM.createQuery("FROM address",Address.class).getResultList(); //odnosze sie do encji (obiektu)
        EntityTransaction eT = null;
        try {
            eT = eM.getTransaction();
            eT.begin();
            for (Address s:addressList  ) {
                eM.remove(s);
            }
            eT.commit();

        }catch (RuntimeException e){
            eT.rollback();
        }
        finally {eM.close();}
    }

}
