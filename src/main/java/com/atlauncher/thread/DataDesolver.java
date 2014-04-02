package com.atlauncher.thread;

import com.atlauncher.type.Account;
import com.atlauncher.management.Accounts;
import com.atlauncher.management.Resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public final class DataDesolver implements Runnable{
    private final ObjectOutputStream accounts;

    public DataDesolver(){
        try{
            File file = new File(Resources.INSTANCE.getFile("Data"), "user.data");

            if(file.exists()){
                this.accounts = new ObjectOutputStream(new FileOutputStream(new File(Resources.INSTANCE.getFile("Data"), "user.data")));
            } else{
                this.accounts = null;
            }
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void run() {
        if(this.accounts != null){
            try{
                for(Account acc : Accounts.all()){
                    this.accounts.writeObject(acc);
                }
            } catch(Exception ex){
                throw new RuntimeException(ex);
            } finally{
                try {
                    this.accounts.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}