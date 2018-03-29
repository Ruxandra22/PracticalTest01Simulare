package ro.pub.cs.systems.eim.practicaltest01simulare;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01MainActivity extends AppCompatActivity {

    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

    private EditText leftEditText = null;
    private EditText rightEditText = null;
    private Button leftButton = null;
    private Button rightButton = null;
    private Button navigateToSecondaryActivityButton = null;
    private String serviceStatus = Constants.SERVICE_STOPPED;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            int leftClicks = Integer.parseInt(leftEditText.getText().toString());
            int rightClicks = Integer.parseInt(rightEditText.getText().toString());

            if(leftClicks + rightClicks > Constants.NUMBER_OF_CLICKS_THRESHOLD
                    && serviceStatus.equals(Constants.SERVICE_STOPPED)) {

                Intent intent = new Intent(getApplicationContext(), PracticalTest01Service.class);
                intent.putExtra("firstNumber", leftClicks);
                intent.putExtra("secondNumber", rightClicks);
                getApplicationContext().startService(intent);
                serviceStatus = Constants.SERVICE_STARTED;
            }
            switch(view.getId()) {
                case R.id.left_button:
                    int leftNumberOfClicks = Integer.parseInt(leftEditText.getText().toString());
                    leftNumberOfClicks++;
                    leftEditText.setText(String.valueOf(leftNumberOfClicks));
                    break;

                case R.id.right_button:
                    int rightNumberOfCLicks =Integer.parseInt(rightEditText.getText().toString());
                    rightNumberOfCLicks++;
                    rightEditText.setText(String.valueOf(rightNumberOfCLicks));
                    break;

                case R.id.navigate_to_secondary_activity_button:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01SecondaryActivity.class);
                    int totalNumberOfCLicks = Integer.parseInt(leftEditText.getText().toString()) +
                            Integer.parseInt(rightEditText.getText().toString());
                    intent.putExtra("number_of_clicks", totalNumberOfCLicks);
                    startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Service.class);
        stopService(intent);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);

        leftEditText = (EditText) findViewById(R.id.left_edit_text);
        rightEditText = (EditText) findViewById(R.id.right_edit_text);
        leftEditText.setText(String.valueOf(0));
        rightEditText.setText(String.valueOf(0));

        leftButton = (Button) findViewById(R.id.left_button);
        rightButton = (Button) findViewById(R.id.right_button);
        leftButton.setOnClickListener(buttonClickListener);
        rightButton.setOnClickListener(buttonClickListener);

        navigateToSecondaryActivityButton = (Button) findViewById(R.id.navigate_to_secondary_activity_button);
        navigateToSecondaryActivityButton.setOnClickListener(buttonClickListener);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("leftCount", leftEditText.getText().toString());
        savedInstanceState.putString("rightCount", rightEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        if(savedInstanceState.containsKey("leftCount")) {
            leftEditText.setText(savedInstanceState.getString("leftCount"));
        }
        else {
            leftEditText.setText(String.valueOf(0));
        }

        if(savedInstanceState.containsKey("rightCount")) {
            rightEditText.setText(savedInstanceState.getString("rightCount"));
        }
        else {
            rightEditText.setText(String.valueOf(0));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with the result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }
}
