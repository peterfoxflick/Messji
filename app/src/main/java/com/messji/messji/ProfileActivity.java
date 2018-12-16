package com.messji.messji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.messji.messji.deals.DailyDeal;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Daily Deal stuff
        TextView emoji = this.findViewById(R.id.dailyDealEmoji);
        TextView title = this.findViewById(R.id.dailyTitle);
        TextView description = this.findViewById(R.id.dailyDesc);

        DailyDeal dailyDeal = DailyDeal.getTodaysDeal(null);

        emoji.setText(dailyDeal.getImageEmoji());
        title.setText(dailyDeal.getTitle());
        description.setText(dailyDeal.getDescription());


        //Char count
        Integer charCountNum = Database.loadCharCount(this);
        TextView charCount = this.findViewById(R.id.charCount);
        charCount.setText(charCountNum.toString());
    }
}
