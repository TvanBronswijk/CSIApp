package school.csiapp;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("InflateParams")  // See: https://code.google.com/p/android-developer-preview/issues/detail?id=1203
public class CriminalListAdapter extends ArrayAdapter<Criminal> {

	private Context context;
	private List<Criminal> criminals;

	public CriminalListAdapter(Context context, List<Criminal> criminals) {
		super(context, R.layout.criminallistitem, criminals);
		
		this.context = context;
		this.criminals = criminals;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Criminal requestedCriminal = criminals.get(position);

		View view = convertView;

		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.criminallistitem, null);
		}

		ImageView im = (ImageView) view.findViewById(R.id.Mugshot);
		im.setImageDrawable(requestedCriminal.mugshot);
		TextView tvname = (TextView)view.findViewById(R.id.NameText);
		tvname.setText(requestedCriminal.name);
		TextView tvbounty = (TextView)view.findViewById(R.id.BountyText);
		tvbounty.setText("$" + Integer.toString(requestedCriminal.getBountyInDollars()));
		
		return view;
	}

}
