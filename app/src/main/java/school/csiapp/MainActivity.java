package school.csiapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CriminalProvider CP = new CriminalProvider(this);
        final int index = getIntent().getIntExtra("criminal", 0);
        Criminal SelectedCriminal = CP.GetCriminal(index);

        ImageView IM = (ImageView)findViewById(R.id.imageView) ;
        IM.setImageDrawable(SelectedCriminal.mugshot);

        TextView nametext = (TextView)findViewById(R.id.Name);
        nametext.setText(SelectedCriminal.name);
        nametext = (TextView)findViewById(R.id.Sex);
        nametext.setText(SelectedCriminal.gender);
        nametext = (TextView)findViewById(R.id.Bounty);
        nametext.setText("$" + Integer.toString(SelectedCriminal.getBountyInDollars()));
        nametext = (TextView)findViewById(R.id.Details);
        nametext.setText(SelectedCriminal.description);
        nametext = (TextView)findViewById(R.id.Age);
        nametext.setText(Integer.toString(SelectedCriminal.age));

        ListView lv = (ListView)findViewById(R.id.CrimesList);
        CrimeListAdapter CLA = new CrimeListAdapter(this, SelectedCriminal.crimes);
        lv.setAdapter(CLA);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(MainActivity.this, ReportActivity.class);
                intent.putExtra("criminal", index);
                startActivity(intent);
            }
        });
    }
}
