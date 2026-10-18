package com.example.mypersonalai;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private LinearLayout messages;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.rgb(16, 24, 40));
        getWindow().setNavigationBarColor(Color.rgb(16, 24, 40));

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.rgb(246, 248, 252));
        root.setPadding(dp(16), dp(12), dp(16), dp(12));

        TextView title = new TextView(this);
        title.setText("My Personal AI");
        title.setTextSize(25);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setTextColor(Color.rgb(20, 32, 56));
        root.addView(title);

        TextView subtitle = new TextView(this);
        subtitle.setText("Your Jarvis project · Version 1.0");
        subtitle.setTextSize(14);
        subtitle.setTextColor(Color.rgb(95, 105, 125));
        subtitle.setPadding(0, dp(4), 0, dp(14));
        root.addView(subtitle);

        ScrollView scroll = new ScrollView(this);
        messages = new LinearLayout(this);
        messages.setOrientation(LinearLayout.VERTICAL);
        messages.setPadding(0, dp(8), 0, dp(8));
        scroll.addView(messages);
        root.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1));

        addMessage("AI", "Hello! I'm your first AI app prototype. The chat screen works, but the live AI connection is not added yet. We'll connect it safely in the next step.", false);

        LinearLayout composer = new LinearLayout(this);
        composer.setOrientation(LinearLayout.HORIZONTAL);
        composer.setGravity(Gravity.CENTER_VERTICAL);
        input = new EditText(this);
        input.setSingleLine(true);
        input.setHint("Type a message…");
        composer.addView(input, new LinearLayout.LayoutParams(0, dp(52), 1));
        Button send = new Button(this);
        send.setText("Send");
        composer.addView(send, new LinearLayout.LayoutParams(dp(88), dp(52)));
        root.addView(composer);

        send.setOnClickListener(v -> {
            String text = input.getText().toString().trim();
            if (!text.isEmpty()) {
                addMessage("You", text, true);
                input.setText("");
                addMessage("AI", "I received your message. The AI brain will be connected in the next setup step.", false);
            }
        });
        setContentView(root);
    }

    private void addMessage(String who, String text, boolean user) {
        TextView bubble = new TextView(this);
        bubble.setText(who + "\n" + text);
        bubble.setTextSize(15);
        bubble.setTextColor(user ? Color.WHITE : Color.rgb(30, 41, 59));
        bubble.setPadding(dp(14), dp(11), dp(14), dp(11));
        bubble.setBackgroundColor(user ? Color.rgb(53, 106, 230) : Color.WHITE);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
        lp.setMargins(0, dp(5), 0, dp(5));
        bubble.setLayoutParams(lp);
        messages.addView(bubble);
    }

    private int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }
}
