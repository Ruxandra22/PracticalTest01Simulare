package ro.pub.cs.systems.eim.practicaltest01simulare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01SecondaryActivity extends AppCompatActivity {

    private TextView numberOfClicksTextView = null;
    private Button okButton = null;
    private Button cancelButton = null;
    
    private OnClickButtonListener buttonListener = new OnClickButtonListener();

    private class OnClickButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.ok_button:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.cancel_button:
                    setResult(RESULT_CANCELED, null);
                    break;
            }

            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_secondary);
        
        numberOfClicksTextView = (TextView) findViewById(R.id.number_of_clicks_textView);
        Intent intent = getIntent();

        if(intent != null && intent.getExtras().containsKey("number_of_clicks")) {
            int numberOfClicks = intent.getIntExtra("number_of_clicks", -1);
            numberOfClicksTextView.setText(String.valueOf(numberOfClicks));
        }

        okButton = (Button) findViewById(R.id.ok_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);

        okButton.setOnClickListener(buttonListener);
        cancelButton.setOnClickListener(buttonListener);
        
    }
}
