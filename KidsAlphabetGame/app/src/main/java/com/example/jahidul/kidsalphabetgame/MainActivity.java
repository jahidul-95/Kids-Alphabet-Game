package com.example.jahidul.kidsalphabetgame;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import org.askerov.dynamicgrid.DynamicGridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {



    private static final String TAG = MainActivity.class.getSimpleName();
    private DynamicGridView gridView;
    MediaPlayer clapSoundMediaPlayer;
    ArrayList arrayList;

    private String []alphabet={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private  String []reArrangeAlphabet=new String[26];
    private  String []arrangeAlphabet=new String[26];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (DynamicGridView) findViewById(R.id.dynamic_grid);
        clapSoundMediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.clapp);

        Random rgen = new Random();  // Random number generator
        int[] cards = new int[26];

         //--- Initialize the array to the ints 0-25
        for (int i = 0; i < cards.length; i++) {
            cards[i] = i;
        }

        //--- Shuffle by exchanging each element randomly
        for (int i = 0; i < cards.length; i++) {
            int randomPosition = rgen.nextInt(cards.length);
            int temp = cards[i];
            cards[i] = cards[randomPosition];
            cards[randomPosition] = temp;
        }

        for (int i = 0; i < cards.length; i++) {

            reArrangeAlphabet[cards[i]]=alphabet[i];
        }


         arrayList = new ArrayList<>(Arrays.asList(reArrangeAlphabet));
        GridViewAdapter gridViewAdpter = new GridViewAdapter(this, arrayList, 4);
        gridView.setAdapter(gridViewAdpter);
        //Active dragging mode when long click at each Grid view item
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView parent, View view, int position, long id) {
                gridView.startEditMode(position);
                int i=0;
                arrangeAlphabet[i++]=parent.getItemAtPosition(position).toString();
                if(!gridView.isEditMode())
                {
                    check();
                }
                return true;
            }
        });





    }

    @Override
    public void onBackPressed() {
        if (gridView.isEditMode()) {
            gridView.stopEditMode();
            }

         else {
            super.onBackPressed();
        }
    }

    public void check()
    {

       for(int i=0;i<arrangeAlphabet.length;i++) {
           if (arrangeAlphabet[i].equals(alphabet[i])) {
               if (arrangeAlphabet[i].equals("Z")) {
                   clapSoundMediaPlayer.start();

               }

           }
       }



    }
}
