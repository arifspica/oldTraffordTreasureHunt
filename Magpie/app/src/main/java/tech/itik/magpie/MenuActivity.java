package tech.itik.magpie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button themeButton1;
    private Button themeButton2;
    private Button themeButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        themeButton1 = (Button) findViewById(R.id.theme_button_1);
        themeButton2 = (Button) findViewById(R.id.theme_button_2);
        themeButton3 = (Button) findViewById(R.id.theme_button_3);

        themeButton1.setOnClickListener(this);
        themeButton2.setOnClickListener(this);
        themeButton3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int themeId = 0;
        if (view.getId() == R.id.theme_button_1) {
            themeId = 1;
        } else if (view.getId() == R.id.theme_button_2) {
            themeId = 2;
        } else if (view.getId() == R.id.theme_button_3) {
            themeId = 3;
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("theme", themeId);
        startActivity(intent);
    }
}
