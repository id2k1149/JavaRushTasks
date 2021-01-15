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


    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        List<User> usersList = userService.getUsersBetweenLevels(1, 100);
        //refresh model data
        modelData.setUsers(usersList);
    }

    public void loadDeletedUsers() {
        List<User> usersList = userService.getAllDeletedUsers();
        //refresh model data
        modelData.setUsers(usersList);
    }
}
