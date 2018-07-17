package yakit.mytestapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by WKPV68 on 7/17/2018.
 */

public class MySumService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

   final IMyAidlInterface.Stub binder = new IMyAidlInterface.Stub() {
       @Override
       public int sun(int first, int second) throws RemoteException {
           return first+second;
       }
   };
}
