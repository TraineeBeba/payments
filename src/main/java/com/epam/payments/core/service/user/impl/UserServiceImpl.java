package com.epam.payments.core.service.user.impl;

import com.epam.payments.core.database.dao.UserDAO;
import com.epam.payments.core.model.dto.UserDTO;
import com.epam.payments.core.model.entity.UserEntity;
import com.epam.payments.core.model.mapper.UserMapper;
import com.epam.payments.core.service.user.exception.AuthenticationException;
import com.epam.payments.core.service.user.exception.RegistrationException;
import com.epam.payments.core.service.user.exception.UserNotFoundException;
import com.epam.payments.core.service.user.exception.UserValidationException;
import com.epam.payments.core.service.user.UserService;
import com.epam.payments.core.utils.Utils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epam.payments.core.model.enums.role.Role.ROLE_USER;
import static com.epam.payments.core.model.enums.state.UserState.BLOCKED;
import static com.epam.payments.core.model.enums.state.UserState.UNBLOCKED;

public class UserServiceImpl implements UserService {
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDTO authenticateUser(String login, String password) throws AuthenticationException, UserValidationException {
        String encryptedPassword = Utils.encrypt(password);

        if (!userDAO.existsByUsernameAndPassword(login, encryptedPassword)){
            throw new AuthenticationException("alertError.user.bad_login");
        } else if (userDAO.isBlocked(login)) {
            throw new AuthenticationException("alertError.user.blocked");
        }

        return UserMapper.INSTANCE.toDTO(userDAO.findByUsername(login));
    }

    @Override
    public void registerUser(String username, String password) throws RegistrationException, UserValidationException {
        validateLogin(username);
        validatePassword(password);
        String encryptedPassword = Utils.encrypt(password);

        if (userDAO.existsByUsername(username)){
            throw new RegistrationException("alertError.login.username_taken");
        }

        UserEntity user = new UserEntity();
        user.setState(UNBLOCKED);
        user.setRole(ROLE_USER);
        user.setUsername(username);
        user.setPassword(encryptedPassword);

        userDAO.save(user);
    }

    @Override
    public void blockUser(Long userId) throws UserNotFoundException {
        Optional<UserEntity> optionalUser = Optional.of(userDAO.findById(userId));
        UserEntity userEntity = optionalUser.orElseThrow(() ->new UserNotFoundException("щось там"));
        userEntity.setState(BLOCKED);

        userDAO.update(userEntity);
    }

    @Override
    public void unblockUser(Long userId) throws UserNotFoundException{
        Optional<UserEntity> optionalUser = Optional.of(userDAO.findById(userId));
        UserEntity userEntity = optionalUser.orElseThrow(() ->new UserNotFoundException("щось там"));
        userEntity.setState(UNBLOCKED);

        userDAO.update(userEntity);
    }

    private void validateLogin(String login) throws UserValidationException {
        String exMsg = null;

        if (login.length() < 6 || login.length() > 16) {
            exMsg = "alertError.login.length";

        } else if (!login.matches("^\\w*$")) {
            // Login can only contain letters, numbers, and underscores
            exMsg =  "alertError.login.symbols";
        }

        if(exMsg != null) {
            throw new UserValidationException(exMsg);
        }
    }

    private void validatePassword(String password) throws UserValidationException {
        String exMsg = null;

        // Password must be at least 6 characters long and no more than 16 characters long
        // and contain at least one uppercase letter,
        // one lowercase letter, one digit, and one special character (@, #, $, %)
        if (password.length() < 6 || password.length() > 12) {
            exMsg = "alertError.password.length";

        } else if (!password.matches(".*[A-Z].*")) {
            exMsg = "alertError.password.uppercase";

        } else if (!password.matches(".*[a-z].*")) {
            exMsg = "alertError.password.lowercase";

        } else if (!password.matches(".*\\d.*")) {
            exMsg = "alertError.password.digit";

        } /*else if (!password.matches(".[@#$%].")) {
            exMsg = "alertError.password.special_character";
        }*/
        if(exMsg != null) {
            throw new UserValidationException(exMsg);
        }
    }

    public List<UserEntity> findAll() {
        return userDAO.findAll();
    }

    public UserEntity findById(Long id) {
        return userDAO.findById(id);
    }

    public void save(UserEntity entity) {
        userDAO.save(entity);
    }

    public void update(UserEntity entity) {
        userDAO.update(entity);
    }

    public UserEntity findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public List<UserDTO> getSortedList(String userSort, int offset, int recordsPerPage)  {
        List<UserEntity> sortedEntityList = userDAO.getSortedList(
                userSort,
                offset,
                recordsPerPage
        );
        List<UserDTO> sortedDTOList = new ArrayList<>();

        for (UserEntity userEntity : sortedEntityList) {
            sortedDTOList.add(UserMapper.INSTANCE.toDTO(userEntity));
        }

        return sortedDTOList;
    }

    @Override
    public int getNoOfRecords() {
        return userDAO.getNoOfRecords();
    }

    public boolean existsByUsername(String username) {
        return userDAO.existsByUsername(username);
    }

    public boolean existsByUsernameAndPassword(String username, String password) {
        return userDAO.existsByUsernameAndPassword(username, password);
    }

    public boolean isBlocked(String username) {
        return userDAO.isBlocked(username);
    }
}
