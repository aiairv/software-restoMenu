//package it.academy.softwarerestoMenu.repository;
//
//import it.academy.softwarerestoMenu.entity.User;
//import org.apache.catalina.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
////    @Query(value = "Select * from users where username = ?", nativeQuery = true)
////    User getUserByHisUsername(String username);
//    User findByUsername(String username);
//    User findByEmail(String email);
//}

package it.academy.softwarerestoMenu.repository;

        import it.academy.softwarerestoMenu.entity.User;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        @Query(value = "Select * from users where username = ?",nativeQuery = true)
        User getUserByHisUsserName(String userName);
        User findByUsername(String username);

}