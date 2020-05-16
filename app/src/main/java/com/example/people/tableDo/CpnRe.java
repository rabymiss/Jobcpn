package com.example.people.tableDo;

import android.content.Context;
import android.os.AsyncTask;


import com.example.people.Dao.EditmsgDao;
import com.example.people.Entity.cpn.CpnMessage;
import com.example.people.JobDatabase;

import java.util.List;

public class CpnRe {

    private List<CpnMessage> list;
    private EditmsgDao editmsgDao;
    CpnRe(Context context) {

        JobDatabase jobDatabase = JobDatabase.getDatabase(context.getApplicationContext());
        editmsgDao = jobDatabase.getEditmsgDao();
        list = editmsgDao.list();
    }

    public void DeleAll(){

        new  CpnRe.DeleteAllAsncTask(editmsgDao).execute();
    }
    public void InsertUser( CpnMessage...cpnMessages){

        new CpnRe.InsertAsyncTask(editmsgDao).execute(cpnMessages);
    }
    List<CpnMessage> allAccout(){return  list;}
    public static class InsertAsyncTask extends AsyncTask<CpnMessage,Void,Void>{
        private EditmsgDao editmsgDao;
        InsertAsyncTask( EditmsgDao editmsgDao){
            this.editmsgDao=editmsgDao;

        }

        @Override
        protected Void doInBackground(CpnMessage... cpnMessages) {
            editmsgDao.insertmsg(cpnMessages);
            return null;
        }
    }


    public static class DeleteAllAsncTask extends AsyncTask<Void,Void,Void>{
        private EditmsgDao editmsgDao;
        DeleteAllAsncTask(EditmsgDao editmsgDao){this.editmsgDao=editmsgDao;}
        @Override
        protected Void doInBackground(Void... voids) {
            editmsgDao.deleteAll();
            return null;
        }
    }


}
