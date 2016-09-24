package de.culo.lucyo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import de.culo.lucyo.R;
import de.culo.lucyo.lights.LucyoLightController;

public class LightTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_test);
        findViewById(R.id.on).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LucyoLightController.turnOn(view.getContext());
            }
        });
        findViewById(R.id.off).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LucyoLightController.turnOff(view.getContext());
            }
        });
    }
}
