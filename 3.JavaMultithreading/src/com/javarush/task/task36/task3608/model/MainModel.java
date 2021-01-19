package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class MainModel implements Model {
    //use special object to keep data for view rendering
    private ModelData modelData = new ModelData();

    //use helpful services
    private UserService userService = new UserServiceImpl();

    private List<User>  getAllUsers() {
        modelData.setDisplayDeletedUserList(false);
        List<User> list_1_100 = userService.getUsersBetweenLevels(1, 100);
        List<User> allUsersList = userService.filterOnlyActiveUsers(list_1_100);
        return allUsersList;
    }

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        modelData.setDisplayDeletedUserList(false);
        // before
//        List<User> usersList = userService.getUsersBetweenLevels(1, 100);

        //refresh model data
        modelData.setUsers(getAllUsers());
    }

    public void loadDeletedUsers() {
        modelData.setDisplayDeletedUserList(true);
        List<User> usersList = userService.getAllDeletedUsers();
        //refresh model data
        modelData.setUsers(usersList);
    }

    public void loadUserById(long userId) {
        User user = userService.getUsersById(userId);
        //refresh model data
        modelData.setActiveUser(user);
    }

    @Override
    public void deleteUserById(long id) {
        userService.deleteUser(id);
        loadUsers();
    }

    public void changeUserData(String name, long id, int level) {
        userService.createOrUpdateUser(name, id, level);
        //refresh model data
        modelData.setUsers(getAllUsers());
    }
}
