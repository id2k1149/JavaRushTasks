package com.javarush.task.task36.task3608;

import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.FakeModel;
import com.javarush.task.task36.task3608.model.MainModel;
import com.javarush.task.task36.task3608.model.Model;
import com.javarush.task.task36.task3608.view.EditUserView;
import com.javarush.task.task36.task3608.view.UsersView;

public class Solution {
    public static void main(String[] args) {
        // before
//        Model model = new FakeModel();
        // after
        Model model = new MainModel();

        Controller controller = new Controller();
        controller.setModel(model);
        
        UsersView usersView = new UsersView();
        usersView.setController(controller);
        controller.setUsersView(usersView);

        EditUserView editUserView = new EditUserView();
        editUserView.setController(controller);
        controller.setEditUserView(editUserView);

        usersView.fireEventShowAllUsers();
        usersView.fireEventOpenUserEditForm(126);
        usersView.fireEventShowDeletedUsers();

    }
}