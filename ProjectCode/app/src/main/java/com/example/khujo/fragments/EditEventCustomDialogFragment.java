package com.example.play.bazarmind.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.play.bazarmind.MainActivity;
import com.example.play.bazarmind.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class EditEventCustomDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private EditText newEventTimeEditText, newEventDateEditText;
    private Date mUserReminderDate;
    private TextView mReminderTextView;
    private EditText editTextNewEventInfo, editTextNewEventName, editTextNewEventVenue;
    DrawerLayout drawer;
    Button btnShowOnMap;
    ActionBarDrawerToggle toggle;
    protected ActionBar actionBar;
    Toolbar mToolbar;
    private String eventName = "", eventVenue = "", eventInfo = "";
    private AppCompatButton btnUpdate, btnCancel;
    private int hour, minute, second;
    private int year, month, day;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    String userId;
    String id, name, venue, info, time, date;
    String latitude, longitude;

    public EditEventCustomDialogFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_edit_custom_dialog, container, false);

        if (getArguments() != null) {
            id = getArguments().getString("id");
            name = getArguments().getString("name");
            venue = getArguments().getString("venue");
            info = getArguments().getString("info");
            time = getArguments().getString("time");
            date = getArguments().getString("day");
            latitude = getArguments().getString("latitude");
            longitude = getArguments().getString("longitude");
            convertToTimeDate(time, date);
        }

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userId = firebaseUser.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId).child("events");

        newEventDateEditText = view.findViewById(R.id.newEventDateEditText);
        newEventTimeEditText = view.findViewById(R.id.newEventTimeEditText);
        mReminderTextView = view.findViewById(R.id.newEventDateTimeReminderTextView);
        editTextNewEventInfo = view.findViewById(R.id.editTextNewEventInfo);
        editTextNewEventName = view.findViewById(R.id.editTextNewEventName);
        editTextNewEventVenue = view.findViewById(R.id.editTextNewEventVenue);
        btnShowOnMap = view.findViewById(R.id.btnShowOnMap);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnCancel = view.findViewById(R.id.btnCancel);

        newEventDateEditText.setText(date);
        newEventTimeEditText.setText(time);
        editTextNewEventName.setText(name);
        editTextNewEventInfo.setText(info);
        editTextNewEventVenue.setText(venue);

        mUserReminderDate = null;

        final Drawable cross = getResources().getDrawable(R.drawable.ic_clear_white_24dp);
        if (cross != null) {
            cross.setColorFilter(getResources().getColor(R.color.icons), PorterDuff.Mode.SRC_ATOP);
        }

        btnShowOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri.Builder directionsBuilder = new Uri.Builder()
                        .scheme("https")
                        .authority("www.google.com")
                        .appendPath("maps")
                        .appendPath("dir")
                        .appendPath("")
                        .appendQueryParameter("api", "1")
                        .appendQueryParameter("destination", latitude + "," + longitude);

                startActivity(new Intent(Intent.ACTION_VIEW, directionsBuilder.build()));
//                String place = editTextNewEventVenue.getText().toString();
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("google.navigation:q=" + place));
//                startActivity(intent);
            }
        });

        newEventTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date = new Date();
                hideKeyboard(editTextNewEventName);
                hideKeyboard(editTextNewEventInfo);
                hideKeyboard(editTextNewEventVenue);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
//                int hour = calendar.get(Calendar.HOUR_OF_DAY);
//                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(EditEventCustomDialogFragment.this, hour, minute, DateFormat.is24HourFormat(getContext()));
                timePickerDialog.show(getActivity().getFragmentManager(), "TimeFragment");
            }
        });

        newEventDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hideKeyboard(editTextNewEventName);
                hideKeyboard(editTextNewEventInfo);
                hideKeyboard(editTextNewEventVenue);

                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(EditEventCustomDialogFragment.this, year, month, day);
                datePickerDialog.show(getActivity().getFragmentManager(), "DateFragment");
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventName = editTextNewEventName.getText().toString();
                eventInfo = editTextNewEventInfo.getText().toString();
                eventVenue = editTextNewEventVenue.getText().toString();
                if (eventName.isEmpty() || eventInfo.isEmpty() || eventVenue.isEmpty()) {
                    Toast.makeText(getActivity(), "Field can't be empty", Toast.LENGTH_SHORT).show();
                } else if (mUserReminderDate != null && mUserReminderDate.before(new Date())) {
                    Toast.makeText(getActivity(), "Date in the past", Toast.LENGTH_SHORT).show();
                } else {
                    updateEvent();
                    Toast.makeText(getActivity(), "Event Updated", Toast.LENGTH_SHORT).show();
                    dismiss();
//                    getActivity().finish();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return view;
    }

    private void convertToTimeDate(String time, String day) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("d MMM, yyyy");
        try {
            Date newTime = dateFormat.parse(time);
            Date newDate = dateFormat2.parse(day);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(newDate);

            this.hour = newTime.getHours();
            this.minute = newTime.getMinutes();
            this.year = calendar.get(Calendar.YEAR);
            this.month = calendar.get(Calendar.MONTH);
            this.day = calendar.get(Calendar.DAY_OF_MONTH);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void updateEvent() {
        Map newList = new HashMap();
        newList.put("name", eventName);
        newList.put("info", eventInfo);
        newList.put("venue", eventVenue);
        newList.put("time", newEventTimeEditText.getText().toString());
        newList.put("date", newEventDateEditText.getText().toString());
        //newList.put("hasReminder", mUserHasReminder);
        databaseReference.child(id).setValue(newList, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference reference) {
                //Toast.makeText(getActivity(), "Event added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void hideKeyboard(EditText et) {

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }

    private void goBack() {
        actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        toggle = ((MainActivity) getActivity()).getToggle();
        actionBar.setDisplayHomeAsUpEnabled(false);
        drawer = getActivity().findViewById(R.id.drawer_layout);
        toggle.setDrawerIndicatorEnabled(true);
        toggle = new ActionBarDrawerToggle(getActivity(), drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        ((MainActivity) getActivity()).setNavigationVisibility(true);
        //getActivity().onBackPressed();
        loadFragment(new EventsFragment());
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        setDate(year, month, day);
    }

    private void setDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        int hour, minute;

        Calendar reminderCalendar = Calendar.getInstance();
        reminderCalendar.set(year, month, day);

        if (reminderCalendar.before(calendar)) {
            return;
        }

        if (mUserReminderDate != null) {
            calendar.setTime(mUserReminderDate);
        }

        if (DateFormat.is24HourFormat(getContext())) {
            hour = calendar.get(Calendar.HOUR_OF_DAY);
        } else {
            hour = calendar.get(Calendar.HOUR);
        }
        minute = calendar.get(Calendar.MINUTE);

        calendar.set(year, month, day, hour, minute);
        mUserReminderDate = calendar.getTime();
        setReminderTextView();
//        setDateAndTimeEditText();
        setDateEditText();
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        setTime(hour, minute);
    }

    public void setTime(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year, month, day, hour, minute, 0);
        mUserReminderDate = calendar.getTime();

        setReminderTextView();
//        setDateAndTimeEditText();
        setTimeEditText();
    }

    public void setReminderTextView() {
        if (mUserReminderDate != null) {
            mReminderTextView.setVisibility(View.VISIBLE);
            if (mUserReminderDate.before(new Date())) {
                mReminderTextView.setText(getString(R.string.date_error_check_again));
                mReminderTextView.setTextColor(Color.RED);
                return;
            }
            Date date = mUserReminderDate;
            String dateString = formatDate("d MMM, yyyy", date);
            String timeString;
            String amPmString = "";

            if (DateFormat.is24HourFormat(getContext())) {
                timeString = formatDate("k:mm", date);
            } else {
                timeString = formatDate("h:mm", date);
                amPmString = formatDate("a", date);
            }
            //String finalString = String.format(getResources().getString(R.string.remind_date_and_time), dateString, timeString, amPmString);
            //mReminderTextView.setTextColor(getResources().getColor(R.color.secondary_text));
            //mReminderTextView.setText(finalString);
        } else {
            mReminderTextView.setVisibility(View.INVISIBLE);
        }
    }

    private void setDateAndTimeEditText() {

        if (mUserReminderDate != null) {
            String userDate = formatDate("d MMM, yyyy", mUserReminderDate);
            String formatToUse;
            if (DateFormat.is24HourFormat(getContext())) {
                formatToUse = "k:mm";
            } else {
                formatToUse = "h:mm a";
            }
            String userTime = formatDate(formatToUse, mUserReminderDate);
            newEventTimeEditText.setText(userTime);
            newEventDateEditText.setText(userDate);
        } else {
            newEventDateEditText.setText(getString(R.string.date_reminder_default));
            boolean time24 = DateFormat.is24HourFormat(getContext());
            Calendar cal = Calendar.getInstance();
            if (time24) {
                cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + 1);
            } else {
                cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
            }
            cal.set(Calendar.MINUTE, 0);
            mUserReminderDate = cal.getTime();
            String timeString;
            if (time24) {
                timeString = formatDate("k:mm", mUserReminderDate);
            } else {
                timeString = formatDate("h:mm a", mUserReminderDate);
            }
            newEventTimeEditText.setText(timeString);
        }
    }

    public void setDateEditText() {
        String dateFormat = "d MMM, yyyy";
        newEventDateEditText.setText(formatDate(dateFormat, mUserReminderDate));
    }

    public void setTimeEditText() {
        String dateFormat;
        if (DateFormat.is24HourFormat(getContext())) {
            dateFormat = "k:mm";
        } else {
            dateFormat = "h:mm a";
        }
        newEventTimeEditText.setText(formatDate(dateFormat, mUserReminderDate));
    }

    public static String formatDate(String formatString, Date dateToFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString);
        return simpleDateFormat.format(dateToFormat);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }
}
