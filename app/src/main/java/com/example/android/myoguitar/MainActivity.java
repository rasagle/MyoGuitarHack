package com.example.android.myoguitar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.thalmic.myo.Hub;
import com.thalmic.myo.scanner.ScanActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView mLockStateView;
    private TextView mTextView;
    private double currentGyro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLockStateView = (TextView) findViewById(R.id.lock_state);
        mTextView = (TextView) findViewById(R.id.text);
        // First, we initialize the Hub singleton with an application identifier.
        Hub hub = Hub.getInstance();
        if (!hub.init(this, getPackageName())) {
            // We can't do anything with the Myo device if the Hub can't be initialized, so exit.
            Toast.makeText(this, "Couldn't initialize Hub", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        onScanActionSelected();
        // Next, register for DeviceListener callbacks.


        Button b1 = (Button)findViewById(R.id.butt);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Guitar.class));
            }
        });



        new Timer().scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Log.d("stuff", Double.toString(currentGyro));
                if (currentGyro > 50) {


                }

            }
        }, 0, 10);

        /*
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int count = 0;
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(Math.abs(currentGyro) > 35) {
                        Log.d("pressed", "yes");
                        mp.seekTo(0);
                        mp.start();
                        break;
                    }
                    break;
                }
                return true;
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.d("pressed", "yes1");

                    mp2.seekTo(0);
                    mp2.start();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Log.d("pressed", "release");
                }
                return true;

            }
        });
        */
    }

    private void onScanActionSelected() {
        // Launch the ScanActivity to scan for Myos to connect to.
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }
    public void callHub(){
        Hub hub = Hub.getInstance();
        if (!hub.init(this)) {
            Log.e("Error", "Could not initialize the Hub.");
            finish();
            return;
        }
        this.startActivity(new Intent(this, ScanActivity.class));
    }
    /*
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

    */

}
