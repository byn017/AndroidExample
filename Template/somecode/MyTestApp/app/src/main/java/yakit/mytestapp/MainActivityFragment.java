package yakit.mytestapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private IMyAidlInterface myAidlInterface = null;
    private EditText first,second,result;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);


        first = (EditText) rootView.findViewById(R.id.first);
        second =(EditText) rootView.findViewById(R.id.second);
        result =(EditText) rootView.findViewById(R.id.result);

        Button calc = (Button) rootView.findViewById(R.id.calc);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myAidlInterface != null) {
                    try {
                        int firstNum = Integer.parseInt(String.valueOf(first.getText()));
                        int secondNum = Integer.parseInt(String.valueOf(second.getText()));
                        int sum = myAidlInterface.sun(firstNum,secondNum);
                        result.setText(""+sum);
                    } catch (Exception e) {
                    }
                }
            }
        });

        Intent intent = new Intent(getContext(),MySumService.class);
        getContext().bindService(intent,connection, Context.BIND_AUTO_CREATE);
        return rootView;
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
