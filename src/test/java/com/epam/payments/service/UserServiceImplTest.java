//package com.epam.payments.service;
//
//import com.epam.payments.database.DAOFactory;
//import UserDAO;
//import UserServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockedStatic;
//import org.mockito.Mockito;
//
//import java.sql.SQLException;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//
//class UserServiceImplTest {
//    private UserServiceImpl userServiceImpl;
//    private UserDAO userDAO;
//
//    @Test
//    public void testIsValidAuthentication() {
//        userServiceImpl = new UserServiceImpl();
//        userDAO = mock(UserDAO.class);
//        UserServiceImpl spyUserServiceImpl = spy(userServiceImpl);
//
//        try(MockedStatic<DAOFactory> mockedStatic = Mockito.mockStatic(DAOFactory.class)) {
//
//            mockedStatic.when(() -> DAOFactory.getUserDAO()).thenReturn(userDAO);
//            when(spyUserServiceImpl.isValidInputData(anyString(), anyString())).thenReturn(null);
//            when(userDAO.existsByUsernameAndPassword("john", "P@ssword1")).thenReturn(false);
//            when(userDAO.existsByUsernameAndPassword("jane", "P@ssword2")).thenReturn(true);
//            when(userDAO.isBlocked("jane")).thenReturn(true);
//
//            String result = spyUserServiceImpl.isValidAuthentication("john", "P@ssword1");
//            assertEquals("alertError.user.not_found", result); // expect "alertError.user.not_found" when user is not found
//
//            result = spyUserServiceImpl.isValidAuthentication("jane", "P@ssword2");
//            assertEquals("alertError.user.blocked", result); // expect "alertError.user.blocked" when user is blocked
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    public void testIsValidRegistration() {
//        UserServiceImpl userServiceImpl = new UserServiceImpl();
//        UserServiceImpl spyUserServiceImpl = spy(userServiceImpl);
//        UserDAO userDAO = mock(UserDAO.class);
//
//        try (MockedStatic<DAOFactory> mockedStatic = Mockito.mockStatic(DAOFactory.class)) {
//            mockedStatic.when(() -> DAOFactory.getUserDAO()).thenReturn(userDAO);
//            when(spyUserServiceImpl.isValidInputData(anyString(), anyString())).thenReturn(null);
//            when(userDAO.existsByUsername("new_user")).thenReturn(false);
//            when(userDAO.existsByUsername("old_john")).thenReturn(true);
//
//            String result = spyUserServiceImpl.isValidRegistration("new_user", "P@ssword1");
//            assertNull(result); // expect null when input data is valid
//
//            result = spyUserServiceImpl.isValidRegistration("old_john", "P@ssword1");
//            assertEquals("alertError.login.username_taken", result); // expect "alertError.login.username_taken" when username is taken
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    public void testIsValidInputData() {
//        userServiceImpl = mock(UserServiceImpl.class);
//        String username = "test123";
//        String password = "Test@123";
//
//        when(userServiceImpl.isValidLogin(username)).thenReturn(null);
//        when(userServiceImpl.isValidPassword(password)).thenReturn(null);
//
//        assertNull(userServiceImpl.isValidInputData(username, password));
//    }
//
//    @Test
//    public void testIsValidLogin(){
//        userServiceImpl = new UserServiceImpl();
//
//        // Valid login
//        String result = userServiceImpl.isValidLogin("test123");
//        assertNull(result);
//
//        // Invalid login length
//        result = userServiceImpl.isValidLogin("abc");
//        assertEquals("alertError.login.length", result);
//
//        // Invalid login symbols
//        result = userServiceImpl.isValidLogin("test!@#");
//        assertEquals("alertError.login.symbols", result);
//    }
//
//    @Test
//    public void testIsValidPassword(){
//        userServiceImpl = new UserServiceImpl();
//
//        // Valid password
//        String result = userServiceImpl.isValidPassword("Test@123");
//        assertNull(result);
//
//        // Invalid password length
//        result = userServiceImpl.isValidPassword("pass");
//        assertEquals("alertError.password.length", result);
//
//        // Invalid password: missing uppercase letter
//        result = userServiceImpl.isValidPassword("test@123");
//        assertEquals("alertError.password.uppercase", result);
//
//        // Invalid password: missing lowercase letter
//        result = userServiceImpl.isValidPassword("TEST@123");
//        assertEquals("alertError.password.lowercase", result);
//
//        // Invalid password: missing digit
//        result = userServiceImpl.isValidPassword("Test@pass");
//        assertEquals("alertError.password.digit", result);
//
//        // Invalid password: missing special symbol
//        result = userServiceImpl.isValidPassword("Test123pass");
//        assertEquals("alertError.password.special_symbols", result);
//    }
//}