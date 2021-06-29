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
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    final int PERMISSION = 1;
    Intent intent;
    TextView textView;
    Button sttBtn;
    SpeechRecognizer mRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



  //구글 STT 예제

        // 퍼미션 체크
//        if (Build.VERSION.SDK_INT >= 23) {
//            ActivityCompat.requestPermissions(this, new String[]{
//                    Manifest.permission.INTERNET, Manifest.permission.RECORD_AUDIO}, PERMISSION);
//        }
        // xml의 버튼과 텍스트 뷰 연결

        textView = (TextView) findViewById(R.id.sttResult);
        sttBtn = (Button) findViewById(R.id.sttStart);
        //RecognizerIntent 객체 생성

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
            public void onRmsChanged(float rmsdB)
            {
            }
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
            }
            @Override
            public void onEndOfSpeech()
            {
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

//    private RecognitionListener listener = new RecognitionListener() {
//        @Override
//        public void onReadyForSpeech(Bundle params) {
//
//            Toast.makeText(getApplicationContext(), "음성인식을 시작합니다.", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onBeginningOfSpeech(){
//            Log.v("DEBUG","말해주세요.");
//
//        }
//
//
//        @Override
//        public void onRmsChanged(float rmsdB) {
//            Log.d("??","여깉??");
//
//        }
//
//        public void onResults (Bundle results){

//            }
//        }
//
//        @Override
//        public void onBufferReceived(byte[] buffer) {}
//
//        @Override
//        public void onEndOfSpeech() {}
//
//
//        @Override
//        public void onError(int error) {
//            String message;
//
//            switch (error) {
//                case SpeechRecognizer.ERROR_AUDIO:
//                    message = "오디오 에러";
//                    break;
//                case SpeechRecognizer.ERROR_CLIENT:
//                    message = "클라이언트 에러";
//                    break;
//                case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
//                    message = "퍼미션 없음";
//                    break;
//                case SpeechRecognizer.ERROR_NETWORK:
//                    message = "네트워크 에러";
//                    break;
//                case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
//                    message = "네트웍 타임아웃";
//                    break;
//                case SpeechRecognizer.ERROR_NO_MATCH:
//                    message = "찾을 수 없음";
//                    break;
//                case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
//                    message = "RECOGNIZER가 바쁨";
//                    break;
//                case SpeechRecognizer.ERROR_SERVER:
//                    message = "서버가 이상함";
//                    break;
//                case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
//                    message = "말하는 시간초과";
//                    break;
//                default:
//                    message = "알 수 없는 오류임";
//                    break;
//            }
//            Toast.makeText(getApplicationContext(), "에러가 발생하였습니다. : " + message,Toast.LENGTH_SHORT).show();
//
//        }
//
//        @Override public void onPartialResults (Bundle partialResults){}
//
//        @Override public void onEvent (int eventType, Bundle params){}
//
//
//
//        };

}

