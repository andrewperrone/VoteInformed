package com.example.voteinformed.data.repository;

import android.content.Context;
import android.net.DnsResolver;
import android.util.Log;
import android.widget.Toast;

import androidx.room.Room;

import com.example.voteinformed.data.api.CommitteeApi;
import com.example.voteinformed.data.api.CommitteeDto;
import com.example.voteinformed.data.api.RetrofitClient;
import com.example.voteinformed.data.database.VoteInformed_Database;
import com.example.voteinformed.data.entity.Committee;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

import retrofit2.Call;
import retrofit2.Response;

public class VoteInformed_Repository {

    private final String DB_NAME = "VoteInformed DB";
    private VoteInformed_Database voteInformedDatabase; // NOTE: Shouldn't need this in the constructor because we are using context, maybe
    Context context;

    public VoteInformed_Repository(Context context)
    {
        this.context = context;
        voteInformedDatabase = Room.databaseBuilder(context, VoteInformed_Database.class, DB_NAME).build();
        Toast.makeText(context, "Database initialized...", Toast.LENGTH_LONG).show();
    }

    //--------------- Insert Task --------------------

    public void fetchAndSaveCommittees(String token) {
        CommitteeApi api = RetrofitClient.getClient().create(CommitteeApi.class);

        String filter = "(BodyTypeName eq 'Committee') and BodyActiveFlag eq 1";

        api.getCommittees(token, filter).enqueue(new Callback<List<CommitteeDto>>() {
            @Override
            public void onResponse(Call<List<CommitteeDto>> call, Response<List<CommitteeDto>> response) {
                if (response.isSuccessful()) {
                    new Thread(() -> {
                        List<CommitteeDto> dtos = response.body();
                        List<Committee> entities = new ArrayList<>();

                        for (CommitteeDto dto : dtos) {
                            Committee e = new Committee();
                            e.BodyID = dto.BodyID;
                            e.BodyName = dto.BodyName;
                            e.BodyTypeName = dto.BodyTypeName;
                            e.BodyActiveFlag = dto.BodyActiveFlag;
                            entities.add(e);
                        }

                        voteInformedDatabase.committee_Dao().insertAll(entities);
                    }).start();
                } else {
                    Log.e("API", "Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<CommitteeDto>> call, Throwable t) {
                Log.e("API", "Error: " + t.getMessage());
            }
        });

    }


}
