package com.ahman.smartblood.ui.request.presenter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahman.smartblood.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class DonorAdapter extends ArrayAdapter {
    public String name[];
    public String bloodGroup[];
    public String dob[];
    public String gender[];
    public String birthDate[];
    public int profile[];

    public DonorAdapter(Context context, String[] name, String[] bloodGroup, String[] dob, String[] gender, String[] birthDate, int[] profile) {
        super(context, R.layout.donor_row, name);
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.dob = dob;
        this.gender = gender;
        this.birthDate = birthDate;
        this.profile = profile;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.donor_row, parent, false);
        TextView names = view.findViewById(R.id.donorName);
        TextView group = view.findViewById(R.id.donorGroup);
        TextView birth_date = view.findViewById(R.id.donorDob);
        TextView sex = view.findViewById(R.id.donorSex);
        ImageView profilo = view.findViewById(R.id.profile);
        names.setText(name[position]);
        group.setText(bloodGroup[position]);
        birth_date.setText(dob[position]);
        sex.setText(gender[position]);
        profilo.setImageResource(profile[position]);

        return view;

    }
}
