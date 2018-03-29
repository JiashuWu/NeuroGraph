package com.example.jiashuwu.neurograph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ParallelLinePracticeResultActivity extends AppCompatActivity {

    private TextView parallel_line_result_result;
    private TextView parallel_line_result_description;
    private Button parallel_line_result_button_done;

    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallel_line_practice_result);

        Log.d("destroy", "starting");

        parallel_line_result_result = (TextView) findViewById(R.id.parallel_line_result_result);
        parallel_line_result_description = (TextView) findViewById(R.id.parallel_line_result_description);
        parallel_line_result_button_done = (Button) findViewById(R.id.parallel_line_result_button_done);

        user_id = 0;
        user_id = Integer.parseInt(getIntent().getStringExtra("user_id").toString());

        for (int i = 0 ; i < Sharing.x_list.size() ; i++)
        {
            Log.d("TAG_X_LIST", String.valueOf(Sharing.x_list.get(i)));
        }

        String [] results = calculateParallelLinePracticeResult.calculateParallelLinePracticeResult();
        parallel_line_result_result.setText(results[0]);
        parallel_line_result_description.setText(results[1]);

        parallel_line_result_button_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParallelLinePracticeResultActivity.this, TestSelectionActivity.class);
                intent.putExtra("user_id", String.valueOf(user_id));
                startActivity(intent);
                ParallelLinePracticeResultActivity.this.finish();
            }
        });


    }
}
