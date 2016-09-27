package school.csiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CriminalListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criminal_list);

        CriminalProvider CP = new CriminalProvider(this);
        CriminalListAdapter CLA = new CriminalListAdapter(this, CP.GetCriminals());

        ListView lv = (ListView)findViewById(R.id.listview1);


        lv.setAdapter(CLA);


        lv.setOnItemClickListener(new OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(CriminalListActivity.this, MainActivity.class);
                intent.putExtra("criminal", position);
                startActivity(intent);
            }
        });
    }
}
