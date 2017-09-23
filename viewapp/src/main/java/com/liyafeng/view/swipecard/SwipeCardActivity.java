package com.liyafeng.view.swipecard;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.liyafeng.view.R;

import java.util.LinkedList;
import java.util.Queue;


public class SwipeCardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_card);
        SwipeCardLayout scl_layout = (SwipeCardLayout) findViewById(R.id.scl_layout);

        Queue<CardEntity> data = new LinkedList<>();
        CardEntity cardEntity1 = new CardEntity(R.drawable.f1, "这里是美丽的湖畔");
        CardEntity cardEntity2 = new CardEntity(R.drawable.f2, "这里游泳比较好");
        CardEntity cardEntity3 = new CardEntity(R.drawable.f3, "向往的蓝天白云");
        CardEntity cardEntity4 = new CardEntity(R.drawable.f4, "繁华的都市");
        CardEntity cardEntity5 = new CardEntity(R.drawable.f5, "草原象征着理想");
        data.add(cardEntity1);
        data.add(cardEntity2);
        data.add(cardEntity3);
        data.add(cardEntity4);
        data.add(cardEntity5);
        scl_layout.setAdapter(new SwipeCardLayout.CardAdapter<CardEntity>(data) {
            @Override
            public View bindLayout() {
                return LayoutInflater.from(SwipeCardActivity.this).inflate(R.layout.card_layout, null);
            }

            @Override
            public void bindData(CardEntity data, View convertView) {

                ImageView iv_card = (ImageView) convertView.findViewById(R.id.iv_card);
                TextView tv_card = (TextView) convertView.findViewById(R.id.tv_card);
                iv_card.setImageResource(data.resId);
                tv_card.setText(data.content);
            }
        });
        scl_layout.setOnSwipeListener(new SwipeCardLayout.OnSwipeListener() {
            @Override
            public void onSwipe(int type) {
                switch (type) {
                    case SwipeCardLayout.TYPE_RIGHT:
                        Toast.makeText(SwipeCardActivity.this, "right", Toast.LENGTH_SHORT).show();

                        break;
                    case SwipeCardLayout.TYPE_LEFT:
                        Toast.makeText(SwipeCardActivity.this, "left", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    class CardEntity {

        public CardEntity(int resId, String content) {
            this.resId = resId;
            this.content = content;
        }

        public int resId;
        public String content;
    }
}
