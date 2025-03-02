package com.prolificinteractive.materialcalendarview.sample;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.time.LocalDate;

/**
 * In response to the issue comment at
 * https://github.com/prolificinteractive/material-calendarview/issues/8#issuecomment-241205704
 * , test activity with multiple MaterialCalendarViews
 */
public class MultipleViewActivity extends AppCompatActivity {
  //number of MaterialCalendarViews to display in list
  static final int NUM_ENTRIES = 3;

  @BindView(R.id.calendar_list)
  RecyclerView calendarList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_multiple);
    ButterKnife.bind(this);

    //setup RecyclerView
    calendarList.setLayoutManager(new LinearLayoutManager(this));
    calendarList.setAdapter(new MultipleViewAdapter(this));
  }

  /**
   * Adapter for RecyclerView
   */
  class MultipleViewAdapter extends RecyclerView.Adapter<MultipleViewAdapter.EntryViewHolder> {
    final LayoutInflater inflater;

    MultipleViewAdapter(final Context context) {
      inflater = LayoutInflater.from(context);
    }

    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      final View view = inflater.inflate(R.layout.calendar_list_entry, parent, false);
      return new EntryViewHolder(view);
    }

    @Override
    public int getItemCount() {
      return NUM_ENTRIES;
    }

    @Override
    public void onBindViewHolder(EntryViewHolder holder, int position) {
      //set selected date to today
      holder.calendarView.setSelectedDate(LocalDate.now());
    }

    /**
     * View holder for list entry
     */
    class EntryViewHolder extends RecyclerView.ViewHolder {
      final MaterialCalendarView calendarView;

      EntryViewHolder(final View itemView) {
        super(itemView);
        calendarView = itemView.findViewById(R.id.list_entry);
      }
    }
  }
}
