package com.peng.stt_test;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final int PERMISSION = 1;
    Intent intent;
    TextView textView;
    Button sttBtn;
    Button ttsBtn;
    SpeechRecognizer mRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


  //구글 STT 예제

//     ActivityCompat.requestPermissions(this, new String[]{
//               Manifest.permission.INTERNET, Manifest.permission.RECORD_AUDIO}, PERMISSION);
//        }
        // xml의 버튼과 텍스트 뷰 연결
        textView = (TextView) findViewById(R.id.sttResult);
        sttBtn = (Button) findViewById(R.id.sttStart);
        ttsBtn = (Button) findViewById(R.id.tts_Intent);
        //RecognizerIntent 객체 생성


        ttsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intents = new Intent(getApplicationContext(), ttsActivity.class);
                startActivity(intents);
            }
        });

        //================구글 음성인식 =============================
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");
        mRecognizer = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());



        // 버튼을 클릭 이벤트 - 객체에 Context와 listener를 할당한 후 실행
        sttBtn.setOnClickListener(v -> {

            if(checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED )
            {
                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, 1);
                return;
            }
            try
            {
                mRecognizer.startListening(intent);
            }
            catch(SecurityException e)
            {
                e.printStackTrace();
            }

        });


        mRecognizer.setRecognitionListener(new RecognitionListener(){

            @Override
            public void onResults(Bundle results) {
//                String key= "";
//                key = SpeechRecognizer.RESULTS_RECOGNITION;
//                ArrayList<String> mResult = results.getStringArrayList(key);
//                String[] rs = new String[mResult.size()];
//                mResult.toArray(rs);
//                Log.d("DEBUG","정상결과 : " + rs[0]);

                Log.d("되나?","이거타는거임?");
                // 말을 하면 ArrayList에 단어를 넣고 textView에 단어를 이어줍니다.
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                for (int i = 0; i < matches.size(); i++) {
                    textView.setText(matches.get(i));
                }

            }
            @Override
            public void onReadyForSpeech(Bundle params)
            {
                Log.d("시작??","대나??");

            }
            @Override
            public void onRmsChanged(float rmsdB)
            {
                Log.d("여기만타네???","대나??");

            }


            @Override
            public void onPartialResults(Bundle partialResults)
            {
            }
            @Override
            public void onEvent(int eventType, Bundle params)
            {
            }
            @Override
            public void onError(int error)
            {
                Log.d("에러?","에러임?");

            }

            //사용자가 말을 멈춘 후에 호출됩니다.
            @Override
            public void onEndOfSpeech()
            {
                Log.d("되나?","이거타는거임?");

            }

            @Override
            public void onBufferReceived(byte[] buffer)
            {
            }
            @Override
            public void onBeginningOfSpeech()
            {
                Log.v("DEBUG","말해주세요.");
            }
        });

    }


}

