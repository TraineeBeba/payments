//package com.epam.payments.database.impl.mysql;
//
//import ConnectionPool;
//import UserDAO;
//import UserEntity;
//import Role;
//import UserState;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
//class UserDAOImplTest {
//    @Mock
//    Connection mockConnection;
//    @Mock
//    PreparedStatement mockPreparedStatement;
//    @Mock
//    ResultSet mockResultSet;
//    @Mock
//    UserEntity mockUserEntity;
//
//    UserDAO userDAO;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        userDAO = new MySQLUserDAOImpl();
//    }
//
//    @Test
//    void create() throws SQLException {
//        try(MockedStatic<ConnectionPool> mockedStatic = Mockito.mockStatic(ConnectionPool.class)) {
//            // Arrange
//            UserState mockState = mock(UserState.class);
//            Role mockRole = mock(Role.class);
//
//            mockedStatic.when(ConnectionPool::getConnection).thenReturn(mockConnection);
//            when(mockConnection.prepareStatement(Queries.UPDATE_USER)).thenReturn(mockPreparedStatement);
//            when(mockUserEntity.getUsername()).thenReturn("testuser");
//            when(mockUserEntity.getPassword()).thenReturn("password");
//            when(mockUserEntity.getRole()).thenReturn(mockRole);
//            when(mockUserEntity.getState()).thenReturn(mockState);
//
//            when(mockRole.getId()).thenReturn(1L);
//            when(mockState.getId()).thenReturn(1L);
//
//            when(mockPreparedStatement.executeUpdate()).thenReturn(1);
//            when(mockConnection.prepareStatement(Queries.CREATE_USER)).thenReturn(mockPreparedStatement);
//
//            // Act
//            userDAO.save(mockUserEntity);
//
//            // Assert
//            verify(mockConnection).prepareStatement(Queries.CREATE_USER);
//            verify(mockPreparedStatement).setLong(1, mockRole.getId());
//            verify(mockPreparedStatement).setLong(2, mockState.getId());
//            verify(mockPreparedStatement).setString(3, mockUserEntity.getUsername());
//            verify(mockPreparedStatement).setString(4, mockUserEntity.getPassword());
//            verify(mockPreparedStatement).executeUpdate();
//        }
//    }
//
//    @Test
//    void testUpdate() throws SQLException {
//        try(MockedStatic<ConnectionPool> mockedStatic = Mockito.mockStatic(ConnectionPool.class)) {
//            // Arrange
//            Long userId = 1L;
//            UserEntity mockUserEntity = mock(UserEntity.class);
//            Role mockRole = mock(Role.class);
//            UserState mockState = mock(UserState.class);
//            when(mockUserEntity.getRole()).thenReturn(mockRole);
//            when(mockUserEntity.getState()).thenReturn(mockState);
//
//            mockedStatic.when(ConnectionPool::getConnection).thenReturn(mockConnection);
//            when(mockPreparedStatement.executeUpdate()).thenReturn(1);
//            when(mockConnection.prepareStatement(Queries.UPDATE_USER)).thenReturn(mockPreparedStatement);
//            when(mockState.getId()).thenReturn(1L);
//            when(mockRole.getId()).thenReturn(2L);
//
//            // Act
//            userDAO.update(mockUserEntity);
//
//            // Assert
//            verify(mockConnection).prepareStatement(Queries.UPDATE_USER);
//            verify(mockPreparedStatement).setLong(1, mockRole.getId());
//            verify(mockPreparedStatement).setLong(2, mockState.getId());
//            verify(mockPreparedStatement).setString(3, mockUserEntity.getUsername());
//            verify(mockPreparedStatement).setString(4, mockUserEntity.getPassword());
//            verify(mockPreparedStatement).executeUpdate();
//        }
//    }
//
//
//    @Test
//    void testGetById() throws SQLException {
//        try(MockedStatic<ConnectionPool> mockedStatic = Mockito.mockStatic(ConnectionPool.class)) {
//            // Arrange
//            Long id = 1L;
//            UserState mockState = mock(UserState.class);
//            Role mockRole = mock(Role.class);
//
//            mockedStatic.when(ConnectionPool::getConnection).thenReturn(mockConnection);
//            when(mockResultSet.next()).thenReturn(true);
//            when(mockResultSet.getLong("id")).thenReturn(id);
//            when(mockResultSet.getLong("role_id")).thenReturn(1L);
//            when(mockResultSet.getLong("state_id")).thenReturn(1L);
//            when(mockResultSet.getString("username")).thenReturn("username");
//            when(mockResultSet.getString("password")).thenReturn("password");
//
//            when(mockPreparedStatement.execute()).thenReturn(true);
//            when(mockPreparedStatement.getResultSet()).thenReturn(mockResultSet);
//
//            when(mockConnection.prepareStatement(Queries.SELECT_USER_BY_ID)).thenReturn(mockPreparedStatement);
//            when(ConnectionPool.getConnection()).thenReturn(mockConnection);
//
//            when(mockRole.getId()).thenReturn(1L);
//            when(mockState.getId()).thenReturn(1L);
//
//            // Act
//            UserEntity result = userDAO.findById(id);
//
//            // Assert
//            verify(mockConnection).prepareStatement(Queries.SELECT_USER_BY_ID);
//            verify(mockPreparedStatement).setLong(1, id);
//            verify(mockPreparedStatement).execute();
//            verify(mockResultSet).close();
//
//            assertNotNull(result);
//            assertEquals(1L, result.getId());
//            assertEquals(mockRole.getId(), result.getRole().getId());
//            assertEquals(mockState.getId(), result.getState().getId());
//            assertEquals("username", result.getUsername());
//            assertEquals("password", result.getPassword());
//        }
//    }
//
//    @Test
//    void testGetByUsername() throws SQLException {
//        try(MockedStatic<ConnectionPool> mockedStatic = Mockito.mockStatic(ConnectionPool.class)) {
//            // Arrange
//            String username = "testuser";
//            UserState mockState = mock(UserState.class);
//            Role mockRole = mock(Role.class);
//
//            mockedStatic.when(ConnectionPool::getConnection).thenReturn(mockConnection);
//            when(mockResultSet.next()).thenReturn(true);
//            when(mockResultSet.getLong("id")).thenReturn(1L);
//            when(mockResultSet.getLong("role_id")).thenReturn(1L);
//            when(mockResultSet.getLong("state_id")).thenReturn(1L);
//            when(mockResultSet.getString("username")).thenReturn(username);
//            when(mockResultSet.getString("password")).thenReturn("password");
//
//            when(mockPreparedStatement.execute()).thenReturn(true);
//            when(mockPreparedStatement.getResultSet()).thenReturn(mockResultSet);
//
//            when(mockConnection.prepareStatement(Queries.SELECT_USER_BY_NAME)).thenReturn(mockPreparedStatement);
//            when(ConnectionPool.getConnection()).thenReturn(mockConnection);
//
//            when(mockRole.getId()).thenReturn(1L);
//            when(mockState.getId()).thenReturn(1L);
//
//            // Act
//            UserEntity result = userDAO.findByUsername(username);
//
//            // Assert
//            verify(mockConnection).prepareStatement(Queries.SELECT_USER_BY_NAME);
//            verify(mockPreparedStatement).setString(1, username);
//            verify(mockPreparedStatement).execute();
//            verify(mockResultSet).close();
//
//            assertNotNull(result);
//            assertEquals(1L, result.getId());
//            assertEquals(mockRole.getId(), result.getRole().getId());
//            assertEquals(mockState.getId(), result.getState().getId());
//            assertEquals(username, result.getUsername());
//            assertEquals("password", result.getPassword());
//        }
//    }
//
//    @Test
//    void TestGetSortedList() throws SQLException {
//        try(MockedStatic<ConnectionPool> mockedStatic = Mockito.mockStatic(ConnectionPool.class)) {
//            // Arrange
//            Connection mockConnection = mock(Connection.class);
//            PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
//            ResultSet mockResultSet = mock(ResultSet.class);
//
//            mockedStatic.when(ConnectionPool::getConnection).thenReturn(mockConnection);
//            when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
//            when(mockPreparedStatement.getResultSet()).thenReturn(mockResultSet);
//            when(mockResultSet.next()).thenReturn(true, true, false); // two user entities returned
//            when(mockResultSet.getLong("id")).thenReturn(1L, 2L);
//            when(mockResultSet.getLong("role_id")).thenReturn(1L);
//            when(mockResultSet.getLong("state_id")).thenReturn(1L);
//            when(mockResultSet.getString("username")).thenReturn("testuser1", "testuser2");
//            when(mockResultSet.getString("password")).thenReturn("password1", "password2");
//
//            UserEntity mockUserEntity1 = mock(UserEntity.class);
//            UserEntity mockUserEntity2 = mock(UserEntity.class);
//            Role mockRole = mock(Role.class);
//            UserState mockState = mock(UserState.class);
//            when(mockUserEntity1.getId()).thenReturn(1L);
//            when(mockUserEntity2.getId()).thenReturn(2L);
//            when(mockUserEntity1.getRole()).thenReturn(mockRole);
//            when(mockUserEntity2.getRole()).thenReturn(mockRole);
//            when(mockRole.getId()).thenReturn(1L);
//            when(mockState.getId()).thenReturn(1L);
//            when(mockUserEntity1.getState()).thenReturn(mockState);
//            when(mockUserEntity2.getState()).thenReturn(mockState);
//            when(mockUserEntity1.getUsername()).thenReturn("testuser1");
//            when(mockUserEntity2.getUsername()).thenReturn("testuser2");
//            when(mockUserEntity1.getPassword()).thenReturn("password1");
//            when(mockUserEntity2.getPassword()).thenReturn("password2");
//
//            // Act
//            List<UserEntity> sortedUsers = userDAO.getSortedList("username", 0, 10);
//
//            // Assert
//            assertEquals(2, sortedUsers.size());
//            assertEquals(mockUserEntity1.getId(), sortedUsers.get(0).getId());
//            assertEquals(mockUserEntity1.getRole().getId(), sortedUsers.get(0).getRole().getId());
//            assertEquals(mockUserEntity1.getState().getId(), sortedUsers.get(0).getState().getId());
//            assertEquals(mockUserEntity1.getUsername(), sortedUsers.get(0).getUsername());
//            assertEquals(mockUserEntity1.getPassword(), sortedUsers.get(0).getPassword());
//
//            assertEquals(mockUserEntity2.getId(), sortedUsers.get(1).getId());
//            assertEquals(mockUserEntity2.getRole().getId(), sortedUsers.get(1).getRole().getId());
//            assertEquals(mockUserEntity2.getState().getId(), sortedUsers.get(1).getState().getId());
//            assertEquals(mockUserEntity2.getUsername(), sortedUsers.get(1).getUsername());
//            assertEquals(mockUserEntity2.getPassword(), sortedUsers.get(1).getPassword());
//        }
//    }
//
//    @Test
//    public void testExistsByUsername() throws SQLException {
//        String username = "testuser";
//        boolean expectedExists = true;
//
//        // Mock Connection and PreparedStatement
//        Connection mockConnection = mock(Connection.class);
//        PreparedStatement mockStatement = mock(PreparedStatement.class);
//
//        // Mock the behavior of the connection and prepared statement
//        when(mockConnection.prepareStatement(Queries.EXISTS_USER_BY_NAME)).thenReturn(mockStatement);
//
//        // Mock the result set and its behavior
//        ResultSet mockResultSet = mock(ResultSet.class);
//        when(mockResultSet.next()).thenReturn(true);
//        when(mockResultSet.getBoolean("exists")).thenReturn(expectedExists);
//        when(mockStatement.execute()).thenReturn(true);
//        when(mockStatement.getResultSet()).thenReturn(mockResultSet);
//
//        // Mock the behavior of the ConnectionPool
//        try (MockedStatic<ConnectionPool> mockedStatic = Mockito.mockStatic(ConnectionPool.class)) {
//            mockedStatic.when(ConnectionPool::getConnection).thenReturn(mockConnection);
//
//            // Call the method to test
//            boolean actualExists = userDAO.existsByUsername(username);
//
//            // Verify the expected behavior
//            assertEquals(expectedExists, actualExists);
//            verify(mockConnection).prepareStatement(Queries.EXISTS_USER_BY_NAME);
//            verify(mockStatement).setString(1, username);
//            verify(mockStatement).execute();
//            verify(mockResultSet).next();
//            verify(mockResultSet).getBoolean("exists");
//        }
//    }
//
//    @Test
//    public void testExistsByUsernameAndPassword() throws SQLException {
//        String username = "testuser";
//        String password = "password";
//        boolean expectedExists = true;
//
//        // Mock Connection and PreparedStatement
//        Connection mockConnection = mock(Connection.class);
//        PreparedStatement mockStatement = mock(PreparedStatement.class);
//
//        // Mock the behavior of the connection and prepared statement
//        when(mockConnection.prepareStatement(Queries.EXISTS_USER_BY_NAME_AND_PASSWORD)).thenReturn(mockStatement);
//
//        // Mock the result set and its behavior
//        ResultSet mockResultSet = mock(ResultSet.class);
//        when(mockResultSet.next()).thenReturn(true);
//        when(mockResultSet.getBoolean("exists")).thenReturn(expectedExists);
//        when(mockStatement.execute()).thenReturn(true);
//        when(mockStatement.getResultSet()).thenReturn(mockResultSet);
//
//        // Mock the behavior of the ConnectionPool
//        try (MockedStatic<ConnectionPool> mockedStatic = Mockito.mockStatic(ConnectionPool.class)) {
//            mockedStatic.when(ConnectionPool::getConnection).thenReturn(mockConnection);
//
//            // Call the method to test
//            boolean actualExists = userDAO.existsByUsernameAndPassword(username, password);
//
//            // Verify the expected behavior
//            assertEquals(expectedExists, actualExists);
//            verify(mockConnection).prepareStatement(Queries.EXISTS_USER_BY_NAME_AND_PASSWORD);
//            verify(mockStatement).setString(1, username);
//            verify(mockStatement).setString(2, password);
//            verify(mockStatement).execute();
//            verify(mockResultSet).next();
//            verify(mockResultSet).getBoolean("exists");
//        }
//    }
//
//    @Test
//    public void testIsBlocked() throws SQLException {
//        String username = "testuser";
//        boolean expectedBlocked = true;
//
//        // Mock Connection and PreparedStatement
//        Connection mockConnection = mock(Connection.class);
//        PreparedStatement mockStatement = mock(PreparedStatement.class);
//
//        // Mock the result set and its behavior
//        ResultSet mockResultSet = mock(ResultSet.class);
//        when(mockResultSet.next()).thenReturn(true);
//        when(mockResultSet.getLong("state_id")).thenReturn(UserState.BLOCKED.getId());
//
//        // Mock the behavior of the connection and prepared statement
//        when(mockConnection.prepareStatement(Queries.SELECT_USER_STATE_BY_NAME)).thenReturn(mockStatement);
//        when(mockStatement.execute()).thenReturn(true);
//        when(mockStatement.getResultSet()).thenReturn(mockResultSet);
//
//        // Mock the behavior of the ConnectionPool
//        try (MockedStatic<ConnectionPool> mockedStatic = Mockito.mockStatic(ConnectionPool.class)) {
//            mockedStatic.when(ConnectionPool::getConnection).thenReturn(mockConnection);
//
//            // Call the method to test
//            boolean actualBlocked = userDAO.isBlocked(username);
//
//            // Verify the expected behavior
//            assertEquals(expectedBlocked, actualBlocked);
//            verify(mockConnection).prepareStatement(Queries.SELECT_USER_STATE_BY_NAME);
//            verify(mockStatement).setString(1, username);
//            verify(mockStatement).execute();
//            verify(mockResultSet).next();
//            verify(mockResultSet).getLong("state_id");
//        }
//    }
//}