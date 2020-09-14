package com.devseok95.asynstask_parser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.View;

import com.devseok95.asynstask_parser.databinding.ActivityMainBinding;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    MainAdapter mMainAdapter;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // asynctask
                    new SafeInfoTask().execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.btnRxjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // rxjava
                RxjavaSafeInfo();
            }
        });

    }

    private class SafeInfoTask extends AsyncTask<String, Void, ArrayList<SafeArt>> {

        public SafeInfoTask() {

        }

        @Override
        protected ArrayList<SafeArt> doInBackground(String... params) {
            ArrayList<SafeArt> safeList = new ArrayList<SafeArt>();

            boolean incontents  = false, inid = false, intitle =  false;
            boolean intwrtDt = false, incountryEnname = false, incountryName = false;


            String content = "";
            String countryEnName = "";
            String countryName = "";
            String id = "";
            String title = "";
            String wrtDt = "";


            try {
                String list_url = "http://apis.data.go.kr/1262000/CountrySafetyService/getCountrySafetyList";

                // n개 출력 (ex)
                String numRows = "300";

                // https://www.data.go.kr/dataset/15000760/openapi.do 해당사이트에서 발급받은 키 작성
                String serviceKey = "주석참고";


                String param ="";
                param += "numOfRows=" + numRows;
                param += "&ServiceKey=" + serviceKey;

                URL url = new URL(list_url + "?" + param);
                InputStream in = url.openStream();

                // manifest : android:usesCleartextTraffic="true" 추가
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true); // 네임스페이스 사용여부

                XmlPullParser parser = factory.newPullParser();
                parser.setInput(in, "UTF-8"); // 인코딩 방식입력

                int parserEvent  = parser.getEventType();

                //SafeArt ar = new SafeArt();

                while(parserEvent  != XmlPullParser.END_DOCUMENT) {
                    switch (parserEvent) {
                        case XmlPullParser.START_TAG:

                            if(parser.getName().equals("content")) {
                                incontents = true;
                            }

                            if(parser.getName().equals("id")) {
                                inid = true;
                            }

                            if(parser.getName().equals("title")) {
                                intitle = true;
                            }

                            if(parser.getName().equals("countryName")) {
                                incountryName = true;
                            }

                            if (parser.getName().equals("countryEnName")) {
                                incountryEnname = true;
                            }

                            if(parser.getName().equals("wrtDt")) {
                                intwrtDt = true;
                            }

                            if(parser.getName().equals("message")) {
                                Log.d("test", "Error");
                            }

                            break;

                        case XmlPullParser.TEXT:
                            if(incontents) {
                                content = parser.getText();
                                incontents = false;
                            }

                            if(inid) {
                                id = parser.getText();
                                inid = false;
                            }

                            if(intitle) {
                                title = parser.getText();
                                intitle = false;
                            }

                            if(incountryName) {
                                countryName = parser.getText();
                                incountryName = false;
                            }

                            if(incountryEnname) {
                                countryEnName = parser.getText();
                                incountryEnname = false;
                            }

                            if(intwrtDt) {
                                wrtDt = parser.getText();
                                intwrtDt = false;
                            }


                            break;

                        case XmlPullParser.END_TAG:
                            if(parser.getName().equals("item")) {
                                SafeArt ar = new SafeArt();

                                content = content.replace("&nbsp;", " ");
                                content = content.replace("\n", "<br/>");
                                content = content.replace("&gt;",">");
                                content = content.replace("&lt;","<");
                                content = content.replace("&quot;","\"");

                                ar.countryEnName = countryEnName;
                                ar.countryName = countryName;
                                ar.content = content;
                                ar.id = id;
                                ar.title = title;
                                ar.wrtDt = wrtDt;

                                safeList.add(ar);
                            }

                            break;
                    }

                    parserEvent  = parser.next();

                }

            }catch (Exception e) {
                e.printStackTrace();
            }

            return safeList;
        }

        @Override
        protected void onPostExecute(ArrayList<SafeArt> result) {
            super.onPostExecute(result);

            mMainAdapter = new MainAdapter( MainActivity.this, result, MainActivity.this);
            binding.recyclerView.setAdapter(mMainAdapter);
        }

    }

    private void RxjavaSafeInfo() {
        ArrayList<SafeArt> safeList = new ArrayList<SafeArt>();

        Observable.fromCallable(() -> {
            // doInBackground

            boolean incontents  = false, inid = false, intitle =  false;
            boolean intwrtDt = false, incountryEnname = false, incountryName = false;

            String content = "";
            String countryEnName = "";
            String countryName = "";
            String id = "";
            String title = "";
            String wrtDt = "";


            try {
                String list_url = "http://apis.data.go.kr/1262000/CountrySafetyService/getCountrySafetyList";

                // n개 출력 (ex)
                String numRows = "300";

                // https://www.data.go.kr/dataset/15000760/openapi.do 해당사이트에서 발급받은 키 작성
                String serviceKey = "주석참고";


                String param ="";
                param += "numOfRows=" + numRows;
                param += "&ServiceKey=" + serviceKey;

                URL url = new URL(list_url + "?" + param);
                InputStream in = url.openStream();

                // manifest : android:usesCleartextTraffic="true" 추가
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true); // 네임스페이스 사용여부

                XmlPullParser parser = factory.newPullParser();
                parser.setInput(in, "UTF-8"); // 인코딩 방식입력

                int parserEvent  = parser.getEventType();

                while(parserEvent  != XmlPullParser.END_DOCUMENT) {
                    switch (parserEvent) {
                        case XmlPullParser.START_TAG:

                            if(parser.getName().equals("content")) {
                                incontents = true;
                            }

                            if(parser.getName().equals("id")) {
                                inid = true;
                            }

                            if(parser.getName().equals("title")) {
                                intitle = true;
                            }

                            if(parser.getName().equals("countryName")) {
                                incountryName = true;
                            }

                            if (parser.getName().equals("countryEnName")) {
                                incountryEnname = true;
                            }

                            if(parser.getName().equals("wrtDt")) {
                                intwrtDt = true;
                            }

                            if(parser.getName().equals("message")) {
                                Log.d("test", "Error");
                            }

                            break;

                        case XmlPullParser.TEXT:
                            if(incontents) {
                                content = parser.getText();
                                incontents = false;
                            }

                            if(inid) {
                                id = parser.getText();
                                inid = false;
                            }

                            if(intitle) {
                                title = parser.getText();
                                intitle = false;
                            }

                            if(incountryName) {
                                countryName = parser.getText();
                                incountryName = false;
                            }

                            if(incountryEnname) {
                                countryEnName = parser.getText();
                                incountryEnname = false;
                            }

                            if(intwrtDt) {
                                wrtDt = parser.getText();
                                intwrtDt = false;
                            }


                            break;

                        case XmlPullParser.END_TAG:
                            if(parser.getName().equals("item")) {
                                SafeArt ar = new SafeArt();

                                content = content.replace("&nbsp;", " ");
                                content = content.replace("\n", "<br/>");
                                content = content.replace("&gt;",">");
                                content = content.replace("&lt;","<");
                                content = content.replace("&quot;","\"");


                                ar.countryEnName = countryEnName;
                                ar.countryName = countryName;
                                ar.content = content;
                                ar.id = id;
                                ar.title = title;
                                ar.wrtDt = wrtDt;

                                safeList.add(ar);
                            }

                            break;
                    }

                    parserEvent  = parser.next();

                }

            }catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    // onPostExecute
                    mMainAdapter = new MainAdapter( MainActivity.this, safeList, MainActivity.this);
                    binding.recyclerView.setAdapter(mMainAdapter);
                });
    }
}