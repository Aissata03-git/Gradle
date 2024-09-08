package me.essejacques.demotestdeploy;


import me.essejacques.demotestdeploy.entity.User;
import me.essejacques.demotestdeploy.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserServiceTests {

    private  UserServiceImpl userService ;

    @BeforeEach
    public void setUp(){
        userService = new UserServiceImpl();
    }

    @Test
    public void testAddUser(){
      User  testUser = new User(null, "Stephane", "Stephane@doe.com", "123456");
      User userSaved = userService.saveUser(testUser);
        Assertions.assertAll(
                ()-> Assertions.assertNotNull(userSaved.getId()),
                ()-> Assertions.assertEquals("Stephane", userSaved.getName()),
                ()-> Assertions.assertEquals("Stephane@doe.com", userSaved.getAdresse()),
                ()-> Assertions.assertEquals("123456", userSaved.getPassword())
        );
    }

    @Test
    public  void findByIdNotFound(){
        Assertions.assertThrows(
                RuntimeException.class, ()-> userService.fetchUserById(10000L)
        );
    }

    @Test
    void testFetchUserById_ExistingUser() {
        User existingUser = userService.fetchUserById(1L);

        assertNotNull(existingUser, "L'utilisateur avec l'ID 1 doit exister.");
        assertEquals(1L, existingUser.getId(), "L'ID de l'utilisateur doit être 1.");
        assertEquals("John", existingUser.getName(), "Le nom de l'utilisateur doit être 'John'.");
    }

    @Test
    void testFetchUserById_NonExistingUser() {
        Long nonExistingId = 999L;
        assertThrows(RuntimeException.class, () -> userService.fetchUserById(nonExistingId),
                "Une exception RuntimeException doit être levée si l'utilisateur n'existe pas.");
    }

    @Test
    void testFindAllUsers() {
        List<User> users = userService.findAllUsers();

        assertNotNull(users, "La liste des utilisateurs ne doit pas être nulle.");
        assertEquals(3, users.size(), "Il doit y avoir 3 utilisateurs par défaut.");
    }

    @Test
    void testSaveUserAndFetchIt() {
        User newUser = new User(null, "Bob", "bob@dow.com", "password123");
        User savedUser = userService.saveUser(newUser);

        User fetchedUser = userService.fetchUserById(savedUser.getId());

        assertNotNull(fetchedUser, "L'utilisateur doit pouvoir être récupéré après son enregistrement.");
        assertEquals(savedUser.getId(), fetchedUser.getId(), "L'ID de l'utilisateur récupéré doit correspondre à celui sauvegardé.");
    }




}
