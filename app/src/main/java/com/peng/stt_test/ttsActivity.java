package com.peng.stt_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

import static android.speech.tts.TextToSpeech.ERROR;

public class ttsActivity extends AppCompatActivity {
    private TextToSpeech tts;
    private Button btn_Speak;
    private EditText txtText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tts_activity_main);

            btn_Speak = findViewById(R.id.button01);
            txtText = findViewById(R.id.editText);

            tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != ERROR) {
                        // 언어를 선택한다.
                        tts.setLanguage(Locale.KOREAN);
                    }
                }
            });

            btn_Speak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tts.speak(txtText.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                }
            });

        }
        @Override
        public void onDestroy () {
            super.onDestroy();
            if (tts != null) {
                tts.stop();
                tts.shutdown();
                tts = null;
            }
        }

    }
