package com.example.android.myoguitar;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.Arm;
import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.Quaternion;
import com.thalmic.myo.Vector3;
import com.thalmic.myo.XDirection;

import java.io.FileOutputStream;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hui on 10/24/2015.
 */
public class Guitar extends AppCompatActivity {
    private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21,b22
            ,b23,b24;
    public int cordCmajor = 0;
    public int cordDmajor = 0;
    public int cordEmajor = 0;
    public int cordFmajor = 0;
    public int cordGmajor = 0;
    public int cordAmajor = 0;
    public int cordBmajor = 0;
    private TextView mLockStateView;
    private TextView mTextView;
    private double currentGyro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guitar_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setGuitar();

        cordC();
        cordD();
        cordE();
        cordF();
        cordG();
        cordA();
        cordB();
        final MediaPlayer mpA = MediaPlayer.create(this, R.raw.am);
        final MediaPlayer mpB = MediaPlayer.create(this, R.raw.bm);
        final MediaPlayer mpC = MediaPlayer.create(this, R.raw.cm);
        final MediaPlayer mpD = MediaPlayer.create(this, R.raw.dm);
        final MediaPlayer mpE = MediaPlayer.create(this, R.raw.em);
        final MediaPlayer mpF = MediaPlayer.create(this, R.raw.fm);
        final MediaPlayer mpG = MediaPlayer.create(this, R.raw.gm);
        Hub hub = Hub.getInstance();
        if (!hub.init(this, getPackageName())) {
            // We can't do anything with the Myo device if the Hub can't be initialized, so exit.
            Toast.makeText(this, "Couldn't initialize Hub", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        hub.addListener(mListener);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Log.d("stuff", Double.toString(currentGyro));
                if (Math.abs(currentGyro) > 35) {
                    if(cordCmajor == 3){
                        mpC.seekTo(0);
                        mpC.start();
                        Log.d("stuff", "C");
                    }
                    if(cordDmajor == 4){
                        mpD.seekTo(0);
                        mpD.start();
                        Log.d("stuff", "D");
                    }
                    if(cordEmajor == 3){
                        mpE.seekTo(0);
                        mpE.start();
                        Log.d("stuff", "E");
                    }
                    if(cordFmajor == 4){
                        mpF.seekTo(0);
                        mpF.start();
                        Log.d("stuff", "F");
                    }
                    if(cordGmajor == 3){
                        mpG.seekTo(0);
                        mpG.start();
                        Log.d("stuff", "G");
                    }
                    if(cordAmajor == 3){
                        mpA.seekTo(0);
                        mpA.start();
                        Log.d("stuff", "A");
                    }
                    if(cordBmajor == 3){
                        mpB.seekTo(0);
                        mpB.start();
                        Log.d("stuff", "B");
                    }
                }

            }
        }, 0, 10);
    }

    public void setGuitar(){
        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        b3 = (Button)findViewById(R.id.b3);
        b4 = (Button)findViewById(R.id.b4);
        b5 = (Button)findViewById(R.id.b5);
        b6 = (Button)findViewById(R.id.b6);
        b7 = (Button)findViewById(R.id.b7);
        b8 = (Button)findViewById(R.id.b8);
        b9 = (Button)findViewById(R.id.b9);
        b10 = (Button)findViewById(R.id.b10);
        b11 = (Button)findViewById(R.id.b11);
        b12 = (Button)findViewById(R.id.b12);
        b13 = (Button)findViewById(R.id.b13);
        b14 = (Button)findViewById(R.id.b14);
        b15 = (Button)findViewById(R.id.b15);
        b16 = (Button)findViewById(R.id.b16);
        b17 = (Button)findViewById(R.id.b17);
        b18 = (Button)findViewById(R.id.b18);
        b19 = (Button)findViewById(R.id.b19);
        b20 = (Button)findViewById(R.id.b20);
        b21 = (Button)findViewById(R.id.b21);
        b22 = (Button)findViewById(R.id.b22);
        b23 = (Button)findViewById(R.id.b23);
        b24 = (Button)findViewById(R.id.b24);


    }

    public void cordC(){
        Button button5 = (Button) findViewById(R.id.b5);
        Button button9 = (Button) findViewById(R.id.b9);
        Button button14 = (Button) findViewById(R.id.b14);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordCmajor += 1;
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordCmajor +=1;
            }
        });
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordCmajor += 1;
            }
        });
    }
    public void cordD(){
        Button button6 = (Button) findViewById(R.id.b6);
        Button button10 = (Button) findViewById(R.id.b10);
        Button button14 = (Button) findViewById(R.id.b14);
        Button button15 = (Button) findViewById(R.id.b15);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordDmajor += 1;
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordDmajor +=1;
            }
        });
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordDmajor += 1;
            }
        });
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordDmajor += 1;
            }
        });
    }
    public void cordE(){
        Button button4 = (Button) findViewById(R.id.b4);
        Button button9 = (Button) findViewById(R.id.b9);
        Button button8 = (Button) findViewById(R.id.b8);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordEmajor += 1;
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordEmajor +=1;
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordEmajor += 1;
            }
        });
    }
    public void cordF(){
        Button button1 = (Button) findViewById(R.id.b1);
        Button button10 = (Button) findViewById(R.id.b10);
        Button button19 = (Button) findViewById(R.id.b19);
        Button button20 = (Button) findViewById(R.id.b20);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordFmajor += 1;
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordFmajor +=1;
            }
        });
        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordFmajor += 1;
            }
        });
        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordFmajor += 1;
            }
        });
    }
    public void cordG(){
        Button button8 = (Button) findViewById(R.id.b8);
        Button button13 = (Button) findViewById(R.id.b13);
        Button button18 = (Button) findViewById(R.id.b18);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordGmajor += 1;
            }
        });
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordGmajor +=1;
            }
        });
        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordGmajor += 1;
            }
        });
    }
    public void cordA(){
        Button button8 = (Button) findViewById(R.id.b8);
        Button button9 = (Button) findViewById(R.id.b9);
        Button button10 = (Button) findViewById(R.id.b10);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordAmajor += 1;
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordAmajor +=1;
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordAmajor += 1;
            }
        });
    }
    public void cordB(){
        Button button21 = (Button) findViewById(R.id.b21);
        Button button22 = (Button) findViewById(R.id.b22);
        Button button23 = (Button) findViewById(R.id.b23);
        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordBmajor += 1;
            }
        });
        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordBmajor +=1;
            }
        });
        button23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cordBmajor += 1;
            }
        });
    }
    private DeviceListener mListener = new AbstractDeviceListener() {
        // onConnect() is called whenever a Myo has been connected.
        @Override
        public void onConnect(Myo myo, long timestamp) {
            // Set the text color of the text view to cyan when a Myo connects.
            mTextView.setTextColor(Color.CYAN);
        }
        // onDisconnect() is called whenever a Myo has been disconnected.
        @Override
        public void onDisconnect(Myo myo, long timestamp) {
            // Set the text color of the text view to red when a Myo disconnects.
            mTextView.setTextColor(Color.RED);
        }
        // onArmSync() is called whenever Myo has recognized a Sync Gesture after someone has put it on their
        // arm. This lets Myo know which arm it's on and which way it's facing.
        @Override
        public void onArmSync(Myo myo, long timestamp, Arm arm, XDirection xDirection) {
            mTextView.setText(myo.getArm() == Arm.LEFT ? R.string.arm_left : R.string.arm_right);
        }
        // onArmUnsync() is called whenever Myo has detected that it was moved from a stable position on a person's arm after
        // it recognized the arm. Typically this happens when someone takes Myo off of their arm, but it can also happen
        // when Myo is moved around on the arm.
        @Override
        public void onArmUnsync(Myo myo, long timestamp) {
            mTextView.setText(R.string.hello_world);
        }
        // onUnlock() is called whenever a synced Myo has been unlocked. Under the standard locking
        // policy, that means poses will now be delivered to the listener.
        @Override
        public void onUnlock(Myo myo, long timestamp) {
            mLockStateView.setText(R.string.unlocked);
        }
        // onLock() is called whenever a synced Myo has been locked. Under the standard locking
        // policy, that means poses will no longer be delivered to the listener.
        @Override
        public void onLock(Myo myo, long timestamp) {
            mLockStateView.setText(R.string.locked);
        }
        // onOrientationData() is called whenever a Myo provides its current orientation,
        // represented as a quaternion.
        @Override
        public void onOrientationData(Myo myo, long timestamp, Quaternion rotation) {
            // Calculate Euler angles (roll, pitch, and yaw) from the quaternion.
            float roll = (float) Math.toDegrees(Quaternion.roll(rotation));
            float pitch = (float) Math.toDegrees(Quaternion.pitch(rotation));
            float yaw = (float) Math.toDegrees(Quaternion.yaw(rotation));
            // Adjust roll and pitch for the orientation of the Myo on the arm.
            if (myo.getXDirection() == XDirection.TOWARD_ELBOW) {
                roll *= -1;
                pitch *= -1;
            }
            // Next, we apply a rotation to the text view using the roll, pitch, and yaw.
            mTextView.setRotation(roll);
            mTextView.setRotationX(pitch);
            //mTextView.setRotationY(yaw);
        }
        // onPose() is called whenever a Myo provides a new pose.
        @Override
        public void onPose(Myo myo, long timestamp, Pose pose) {
            // Handle the cases of the Pose enumeration, and change the text of the text view
            // based on the pose we receive.
            switch (pose) {
                case UNKNOWN:
                    mTextView.setText(getString(R.string.hello_world));
                    break;
                case REST:
                case DOUBLE_TAP:
                    int restTextId = R.string.hello_world;
                    switch (myo.getArm()) {
                        case LEFT:
                            restTextId = R.string.arm_left;
                            break;
                        case RIGHT:
                            restTextId = R.string.arm_right;
                            break;
                    }
                    mTextView.setText(getString(restTextId));
                    break;
                case FIST:
                    mTextView.setText(getString(R.string.pose_fist));
                    break;
                case WAVE_IN:
                    mTextView.setText(getString(R.string.pose_wavein));
                    break;
                case WAVE_OUT:
                    mTextView.setText(getString(R.string.pose_waveout));
                    break;
                case FINGERS_SPREAD:
                    mTextView.setText(getString(R.string.pose_fingersspread));
                    break;
            }
            if (pose != Pose.UNKNOWN && pose != Pose.REST) {
                // Tell the Myo to stay unlocked until told otherwise. We do that here so you can
                // hold the poses without the Myo becoming locked.
                myo.unlock(Myo.UnlockType.HOLD);
                // Notify the Myo that the pose has resulted in an action, in this case changing
                // the text on the screen. The Myo will vibrate.
                myo.notifyUserAction();
            } else {
                // Tell the Myo to stay unlocked only for a short period. This allows the Myo to
                // stay unlocked while poses are being performed, but lock after inactivity.
                myo.unlock(Myo.UnlockType.TIMED);
            }
        }
        public void onGyroscopeData (Myo myo, long timestamp, Vector3 gyro) {
            //Log.d("gyro", (Double.toString(gyro.x() + gyro.y() + gyro.z() / 3)));

            String asdf = ("X:" + Double.toString(gyro.x()) + " Y:" + Double.toString(gyro.y()) + " Z:" + Double.toString(gyro.z()));
            String asdff = "hi";
            //writer.close();
            FileOutputStream outputStream;
            try {
                outputStream = openFileOutput("output.txt", Context.MODE_PRIVATE);
                outputStream.write(asdff.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            currentGyro = gyro.x() + gyro.y() + gyro.z()/3;
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // We don't want any callbacks when the Activity is gone, so unregister the listener.
        Hub.getInstance().removeListener(mListener);
        if (isFinishing()) {
            // The Activity is finishing, so shutdown the Hub. This will disconnect from the Myo.
            Hub.getInstance().shutdown();
        }
    }

}
