package school.csiapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tobi on 27-Sep-16.
 */

public class CrimeListAdapter extends ArrayAdapter<Crime> {

    private Context context;
    private List<Crime> crimes;

    public CrimeListAdapter(Context context, List<Crime> crimes) {
        super(context, R.layout.crimelistitem, crimes);

        this.context = context;
        this.crimes = crimes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Crime requestedCrime = crimes.get(position);

        View view = convertView;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.crimelistitem, null);
        }

        TextView tvname = (TextView)view.findViewById(R.id.CrimeName);
        tvname.setText(requestedCrime.name);
        TextView tvbounty = (TextView)view.findViewById(R.id.CrimeBounty);
        tvbounty.setText("$" + Integer.toString(requestedCrime.bountyInDollars));
        TextView tvdesc = (TextView)view.findViewById(R.id.CrimeDescrip);
        tvdesc.setText(requestedCrime.description);

        return view;
    }
}
