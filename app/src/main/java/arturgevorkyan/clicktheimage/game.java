package arturgevorkyan.clicktheimage; /**
 * Created by 33 on 06.09.2014.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.widget.TextView;
public class game extends Activity  implements OnLoadCompleteListener{
    int index=0;
    Timer myTimer;
    final int MAX_STREAMS = 5;
    SoundPool sp;
    int soundIdSel;
    int soundIdwrong;
    int soundIdSelR;
    View iv;
    View iv1;
    View iv0;
    int score=0;
    int bscore=0;
    int viewId;
    int iid=-1;
    int indexY;
    String str111;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_SCORE = "score";
    public static final String APP_PREFERENCES_BSCORE = "bscore";
    public static final String APP_PREFERENCES_OBJINDEX= "OBJINDEX";
    public static final String APP_PREFERENCES_iid = "iid";
    public static final String APP_PREFERENCES_ogr = "ogr";
    SharedPreferences mSettings;
    private int[] LEAVES = {

            R.drawable.a0,
            R.drawable.a1,
            R.drawable.a2,
            R.drawable.a4,
            R.drawable.a5,
            R.drawable.a6,
            R.drawable.a7,
            R.drawable.a8,
            R.drawable.a9,
            R.drawable.a10,
            R.drawable.a11,
            R.drawable.a12,
            R.drawable.a29,
            R.drawable.a13,
            R.drawable.a14,
            R.drawable.a15,
            R.drawable.a16,
            R.drawable.a17,
            R.drawable.a28,
            R.drawable.a18,
            R.drawable.a19,
            R.drawable.a20,
            R.drawable.a21,
            R.drawable.a22,
            R.drawable.a23,
            R.drawable.a24,
            R.drawable.a25,
            R.drawable.a26,
            R.drawable.a3,
            R.drawable.a27,
            R.drawable.a30,
            R.drawable.a31,
    };
    private ArrayList<String> sss = new ArrayList<String>();
    Iterator<View> i0;
    public int selX;
    public int selY;
    private Rect mDisplaySize = new Rect();
    int Id;
    int ogr=10;
    private TextView tvHello ;
    int newgame=0;
    Drawable d1;
    Drawable d;
    int soundid1=0;
    int OBJINDEX=0;
    int TY=0;

    float scale,scale2;
    private RelativeLayout mRootLayout;
    private ArrayList<View> mAllImageViews = new ArrayList<View>();
    private ImageView coinedimage;
    private float mScale;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        coinedimage=(ImageView)findViewById(R.id.coinedimage);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        Intent intent = getIntent();
        newgame = intent.getIntExtra("new",0);
        soundid1 = intent.getIntExtra("soundid",0);
        Display display = getWindowManager().getDefaultDisplay();
        display.getRectSize(mDisplaySize);
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        mScale = metrics.density;
        mRootLayout = (RelativeLayout) findViewById(R.id.game1);
        tvHello= (TextView)findViewById(R.id.score);
        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        sp.setOnLoadCompleteListener(this);
        soundIdSel = sp.load(this, R.raw.s, 1);
        soundIdwrong = sp.load(this, R.raw.wrong1, 1);
        soundIdSelR = sp.load(this, R.raw.sr, 1);
        scale=5* mScale;
        scale2=mDisplaySize.height()-50*mScale;


        if (newgame>0) {
            if (mSettings.contains(APP_PREFERENCES_OBJINDEX))
            {
                OBJINDEX = mSettings.getInt(APP_PREFERENCES_OBJINDEX, 0);
            }
            v1();
            if (mSettings.contains(APP_PREFERENCES_SCORE))
            {
                score = mSettings.getInt(APP_PREFERENCES_SCORE, 0);
                tvHello.setText(" Score   " + score);
            }
            if (mSettings.contains(APP_PREFERENCES_BSCORE))
            {
                bscore = mSettings.getInt(APP_PREFERENCES_BSCORE, 0);
            }
            if (mSettings.contains(APP_PREFERENCES_iid))
            {
                iid = mSettings.getInt(APP_PREFERENCES_iid, 0);
            }

            if (mSettings.contains(APP_PREFERENCES_iid))
            {
                ogr = mSettings.getInt(APP_PREFERENCES_ogr, 0);
            }


            d1 = getResources().getDrawable(LEAVES[iid]);
            coinedimage=(ImageView)findViewById(R.id.coinedimage);
            coinedimage.setImageDrawable(d1);
        }
        else
        {
            newgame=1;
            if (mSettings.contains(APP_PREFERENCES_BSCORE))
            {
                bscore = mSettings.getInt(APP_PREFERENCES_BSCORE, 0);
            }
            Id = new Random().nextInt(LEAVES.length-5);
            d1 = getResources().getDrawable(LEAVES[Id]);
            coinedimage.setImageDrawable(d1);
            iid=Id;
            score=0;
            tvHello.setText(" Score   " + score);
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            indexY++;
            if (indexY==20)
            {index++;
                if (index<ogr) {


                    viewId = new Random().nextInt(25);
                    if ( viewId==iid)
                    {
                        viewId=viewId+1;
                    }
                }
                else
                {
                    viewId =iid;
                    index=0;
                }
                d = getResources().getDrawable(LEAVES[viewId]);
                LayoutInflater inflate = LayoutInflater.from(game.this);
                ImageView imageView = (ImageView) inflate.inflate(R.layout.ani_image_view, null);
                imageView.setImageDrawable(d);
                imageView.setId(viewId);
                mRootLayout.addView(imageView);
                mAllImageViews.add(imageView);
                sss.add(String.valueOf(viewId));
                imageView.setX( new Random().nextInt(mDisplaySize.width()-180) + 60);
                imageView.setY(-100);
                LayoutParams animationLayout = (LayoutParams) imageView.getLayoutParams();
                animationLayout.width = (int) (60 * mScale);
                animationLayout.height = (int) (60 * mScale);
                indexY=0;
            }
            i0 = mAllImageViews.iterator();
            while (i0.hasNext())
            {
                iv0 = i0.next();
                iv0.setY( ((iv0.getY()  + scale)));
                iv0.setRotation(iv0.getRotation()+10-TY/10 );
            }

            testCollision();
        }
    };

    private class ExeTimerTask extends TimerTask
    {
        @Override
        public void run()
        {
            mHandler.sendEmptyMessage(Constants.EMPTY_MESSAGE_WHAT);
        }
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status)
    {
        //  Log.d(LOG_TAG, "onLoadComplete, sampleId = " + sampleId + ", status = " + status);
    }

    public boolean onTouchEvent(MotionEvent e)
    {
        if(e.getAction() == MotionEvent.ACTION_DOWN)
            selX = (int) e.getX();
        selY =(int) e.getY();
        testCollision1();
        return true;
    }

    private void testCollision1()
    {
        Iterator<View> i = mAllImageViews.iterator();
        while(i.hasNext())
        {
            iv1 = i.next();
            if ((Math.abs(selX - (iv1.getX()+iv1.getWidth()/2)) <= ( iv1.getWidth()-5))
                    && (Math.abs(selY - (iv1.getY()+iv1.getHeight()/2)) <= ( iv1.getHeight()-5)))
            {
                if ( iid== iv1.getId())
                {
                    if(soundid1==0)
                    {
                        sp.play(soundIdSelR, 1, 1, 0, 0, 1);
                    }

                    sss.remove(String.valueOf(iv1.getId()));
                    mRootLayout.removeView(iv1);
                    i.remove();
                    score++;
                    if (score<113 )
                    {
                        TY=score/4;
                        myTimer.cancel();
                        myTimer = new Timer();
                        myTimer.schedule(new ExeTimerTask(), 0, 43-TY);
                    }
                    else
                    {
                        TY=28;
                    }

                    if (bscore<score)
                    {
                        bscore= score;
                    }
                    Id = new Random().nextInt(LEAVES.length);
                    ogr= new Random().nextInt(6)+6;
                    for(int ik=0;ik<sss.size();ik++)
                    {
                        if (String.valueOf(Id).equals(sss.get(ik)))
                        {
                            Id = new Random().nextInt(6)+25;
                        }
                    }
                    d1 = getResources().getDrawable(LEAVES[Id]);
                    coinedimage.setImageDrawable(d1);
                    iid=Id;
                }
                else
                {

                    if(soundid1==0)
                    {
                        sp.play(soundIdwrong, 1, 1, 0, 0, 1);
                    }
                    if (score>0)
                    {
                        score--;
                        if (score<113 )
                        {
                            TY=score/4;
                            myTimer.cancel();
                            myTimer = new Timer();
                            myTimer.schedule(new ExeTimerTask(), 0, 43-TY);
                        }
                        else
                        {
                            TY=28;
                        }
                    }

                }
            }
        }
        selX=-300;
        tvHello.setText(" Score   "+score);

    }

    private void testCollision()
    {

        Iterator<View> i = mAllImageViews.iterator();
        while(i.hasNext())
        {
            iv = i.next();
            if (iv.getY()>=scale2)
            {
                if ( iid!=iv.getId())
                {
                    sss.remove(String.valueOf(iv.getId()));
                    i.remove();
                    mRootLayout.removeView(iv);
                }

                if (   iid== iv.getId())
                {
                    if(soundid1==0)
                    {
                        sp.play(soundIdwrong, 1, 1, 0, 0, 1);
                    }
                    if (score>0)
                    {
                        score--;
                        if (score<113 )
                        {
                            TY=score/4;
                            myTimer.cancel();
                            myTimer = new Timer();
                            myTimer.schedule(new ExeTimerTask(), 0, 43-TY);
                        }
                        else
                        {
                            TY=28;
                        }
                    }
                    sss.remove(String.valueOf(iv.getId()));

                    i.remove();
                    mRootLayout.removeView(iv);
                    tvHello.setText(" Score   "+score);
                }
            }
        }
        selX=-300;

    }


    @Override
    protected void onPause() {
        super.onPause();
        System.gc();
        myTimer.cancel();
        newgame=0;
        SharedPreferences.Editor editor = mSettings.edit();
        editor.clear();
        editor.putInt(APP_PREFERENCES_SCORE, score);
        editor.putInt(APP_PREFERENCES_BSCORE, bscore);
        editor.putInt(APP_PREFERENCES_iid, iid);
        editor.putInt(APP_PREFERENCES_ogr, ogr);
        editor.putInt(APP_PREFERENCES_OBJINDEX,mAllImageViews.size());
        for (int i=0;i<mAllImageViews.size();i++)
        {
            str111="objid"+i;
            editor.putInt(str111, mRootLayout.getChildAt(i).getId());
            str111="posX"+i;
            editor.putFloat(str111, mRootLayout.getChildAt(i).getX());
            str111="posY"+i;
            editor.putFloat(str111,mRootLayout.getChildAt(i).getY());
            str111="rot"+i;
            editor.putFloat(str111,mRootLayout.getChildAt(i).getRotation());
        }
        editor.apply();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if (score<113 )
        {
            TY=score/4;
            myTimer = new Timer();
            myTimer.schedule(new ExeTimerTask(), 0, 43-TY);
        }
        else
        {
            TY=28;
            myTimer = new Timer();
            myTimer.schedule(new ExeTimerTask(), 0, 43-TY);
        }
    }
    public void v1()
    {
        for (int i=0;i<OBJINDEX;i++)
        {
            str111 = "objid"+i;
            viewId=mSettings.getInt(str111, 0);
            d = getResources().getDrawable(LEAVES[viewId]);
            LayoutInflater inflate = LayoutInflater.from(game.this);
            ImageView imageView = (ImageView) inflate.inflate(R.layout.ani_image_view, null);
            imageView.setImageDrawable(d);
            imageView.setId(viewId);
            str111 ="posX"+i;
            imageView.setX(mSettings.getFloat(str111, 0));
            str111 ="posY"+i;
            imageView.setY(mSettings.getFloat(str111, 0));
            str111="rot"+i;
            imageView.setRotation(mSettings.getFloat(str111, 0));
            mRootLayout.addView(imageView);
            mAllImageViews.add(imageView);
            LayoutParams animationLayout = (LayoutParams) imageView.getLayoutParams();
            animationLayout.width = (int) (60 * mScale);
            animationLayout.height = (int) (60 * mScale);
            indexY = 0;
        }
    }
}