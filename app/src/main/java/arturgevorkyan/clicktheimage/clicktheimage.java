package arturgevorkyan.clicktheimage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class clicktheimage extends Activity {

   public static final String APP_PREFERENCES_OBJINDEX= "OBJINDEX";
    public static final String APP_PREFERENCES_BS="bscore";
    public static final String APP_PREFERENCES_S="score";

  SharedPreferences mSettings;
   public static final String APP_PREFERENCES = "mysettings";
 int OBJINDEX1;
    int score,bscore;
    private FrameLayout RLS;
    private RelativeLayout self;
    private TextView BS;
    private TextView S;
    private Button backtogame;
    private ImageView soundonoff;
    Drawable d,d1;
    int soundid=0;
    private int[] soundarr = {
            R.drawable.soundon,
            R.drawable.soundoff,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clicktheimage);
        self = (RelativeLayout) findViewById(R.id.clicktheimage);
        BS=(TextView) findViewById(R.id.bst);
        S=(TextView) findViewById(R.id.pbst);
        RLS = (FrameLayout) findViewById(R.id.FLS);
        self.removeView(RLS);
        backtogame=(Button)findViewById(R.id.backtogame);
        backtogame.setEnabled(false);
        backtogame.setAlpha(0.5f);
        soundonoff=(ImageView)findViewById(R.id.sound);
        d1 = getResources().getDrawable(soundarr[1]);
        d = getResources().getDrawable(soundarr[0]);
        soundonoff.setImageDrawable(d);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.click_the_image, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onClick(View view)
    {
        Intent intent = new Intent(clicktheimage.this, game.class);
        intent.putExtra("new", /*etFName.getText().toString()*/0);
        intent.putExtra("soundid", soundid);
        startActivity(intent);
    }

    public void onsettings(View view)
    {
   if (soundid==0)
   {
       soundonoff.setImageDrawable(d1);
       soundid=1;
   }
   else
   {
       soundonoff.setImageDrawable(d);
       soundid=0;
   }
    }



    protected void onResume() {

        super.onResume();
        if (mSettings.contains(APP_PREFERENCES_OBJINDEX))
        {
            OBJINDEX1 = mSettings.getInt(APP_PREFERENCES_OBJINDEX, 0);

           if (OBJINDEX1>0) {
               backtogame.setEnabled(true);
               backtogame.setAlpha(1.0f);

           }
            else
           {
               backtogame.setEnabled(false);
               backtogame.setAlpha(0.5f);
           }
        }
    }


    public void BackToGame(View view)
    {
        Intent intent = new Intent(clicktheimage.this, game.class);
        intent.putExtra("soundid", soundid);
        intent.putExtra("new", /*etFName.getText().toString()*/1);
        startActivity(intent);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public void score(View view)
    {
        if (mSettings.contains(APP_PREFERENCES_OBJINDEX))
        {
            score = mSettings.getInt(APP_PREFERENCES_S, 0);
            bscore = mSettings.getInt(APP_PREFERENCES_BS, 0);
            BS.setText(""+bscore);
            S.setText(""+score);

        }
        self.addView(RLS);
    }


    public void close(View view)
    {
       self.removeView(RLS);
    }
}
