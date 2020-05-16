package com.example.people;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.people.Dao.LoginDao;
import com.example.people.data.model.LoginUser;

import java.util.List;

public class UserRepository {
    private LoginDao loginDao;
    private LiveData<List<LoginUser>>allUsers;
    UserRepository(Context context){
       JobDatabase jobDatabase=JobDatabase.getDatabase(context.getApplicationContext());
       loginDao=jobDatabase.getLoginDao();
      allUsers=loginDao.getAllUserLive();
    }

    LiveData<List<LoginUser>> getAllUsersLive() {
        return  allUsers;
    }
    LiveData<List<LoginUser>> findAllUserLive(String pattern) { return  loginDao.findUserWithPattern(pattern); }

    void insertUsers(LoginUser... loginUsers) {
        new InsertAsyncTask(loginDao).execute(loginUsers);
    }
    void deleteUsers(LoginUser... loginUsers) {
        new DeleteAsyncTask(loginDao).execute(loginUsers);
    }
    void UpdateUsers(LoginUser... loginUsers) {
        new UpdateAsyncTask(loginDao).execute(loginUsers);
    }
    void deleteAllUsers(Void... voids) {
        new DeleteAllUserAsyncTask(loginDao).execute();
    }
    static class  InsertAsyncTask extends AsyncTask<LoginUser ,Void ,Void>{

        private LoginDao loginDao;

        InsertAsyncTask(LoginDao loginDao) {
            this.loginDao = loginDao;
        }

        @Override
        protected Void doInBackground(LoginUser... loginUsers) {
            loginDao.insertUser(loginUsers);
            return null;
        }
    }
    static class  DeleteAsyncTask extends AsyncTask<LoginUser ,Void ,Void>{

        private LoginDao loginDao;

        DeleteAsyncTask(LoginDao loginDao) {
            this.loginDao = loginDao;
        }

        @Override
        protected Void doInBackground(LoginUser... loginUsers) {
            loginDao.deleteUser(loginUsers);
            return null;
        }
    }
    static class  UpdateAsyncTask extends AsyncTask<LoginUser ,Void ,Void>{

        private LoginDao loginDao;

        UpdateAsyncTask(LoginDao loginDao) {
            this.loginDao = loginDao;
        }

        @Override
        protected Void doInBackground(LoginUser... loginUsers) {
            loginDao.updateUser(loginUsers);
            return null;
        }
    }
    static class  DeleteAllUserAsyncTask extends AsyncTask<Void ,Void ,Void>{

        private LoginDao loginDao;

        DeleteAllUserAsyncTask(LoginDao loginDao) {
            this.loginDao = loginDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            loginDao.deleteAllUsers();
            return null;
        }
    }

}
