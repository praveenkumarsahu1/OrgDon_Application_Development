package com.example.orgdon;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MyAdapter extends FirebaseRecyclerAdapter<model, MyAdapter.myViewHolder> {

    public MyAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myViewHolder holder,
                                    @SuppressLint("RecyclerView") final int position,
                                    @NonNull final model model) {

        // Set donor data
        holder.name.setText(model.getName());
        holder.address.setText(model.getAddress());
        holder.donated.setText(model.getDonated());
        holder.age.setText(model.getAge());
        holder.blood.setText(model.getBlood());
        holder.hospital.setText(model.getHospital());

        // 📞 Call button
        holder.call.setOnClickListener(v -> {
            String call = model.getPhone();
            if (call != null && !call.isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + call));
                v.getContext().startActivity(intent);
            }
        });

        // 📩 SMS button
        holder.message.setOnClickListener(v -> {
            String phoneNumber = model.getPhone();
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                smsIntent.setData(Uri.parse("smsto:" + phoneNumber)); // only SMS apps
                smsIntent.putExtra("sms_body", "Hello, I am contacting you regarding organ donation.");
                v.getContext().startActivity(smsIntent);
            }
        });

        // ✏ Edit button
        holder.edit.setOnClickListener(view -> {
            final DialogPlus dialogPlus = DialogPlus.newDialog(holder.name.getContext())
                    .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                    .setExpanded(true, ViewGroup.LayoutParams.WRAP_CONTENT)
                    .create();

            View myView = dialogPlus.getHolderView();

            // Find views
            final TextInputEditText name = myView.findViewById(R.id.nameEdit);
            final TextInputEditText address = myView.findViewById(R.id.addressEdit);
            final TextInputEditText donated = myView.findViewById(R.id.donatedEdit);
            final TextInputEditText age = myView.findViewById(R.id.ageEdit);
            final TextInputEditText blood = myView.findViewById(R.id.bloodEdit);
            final TextInputEditText phone = myView.findViewById(R.id.phoneEdit);
            final TextInputEditText hospital = myView.findViewById(R.id.hospitalEdit);
            Button submit = myView.findViewById(R.id.usubmit);

            // Pre-fill fields
            name.setText(model.getName());
            address.setText(model.getAddress());
            donated.setText(model.getDonated());
            age.setText(model.getAge());
            blood.setText(model.getBlood());
            phone.setText(model.getPhone());
            hospital.setText(model.getHospital());

            dialogPlus.show();

            // ✅ Update donor in Firebase
            submit.setOnClickListener(view1 -> {
                Map<String, Object> map = new HashMap<>();
                map.put("name", Objects.requireNonNull(name.getText()).toString().trim());
                map.put("address", Objects.requireNonNull(address.getText()).toString().trim());
                map.put("donated", Objects.requireNonNull(donated.getText()).toString().trim());
                map.put("age", Objects.requireNonNull(age.getText()).toString().trim());
                map.put("blood", Objects.requireNonNull(blood.getText()).toString().trim());
                map.put("phone", Objects.requireNonNull(phone.getText()).toString().trim());
                map.put("hospital", Objects.requireNonNull(hospital.getText()).toString().trim());

                FirebaseDatabase.getInstance().getReference().child("donors")
                        .child(Objects.requireNonNull(getRef(position).getKey()))
                        .updateChildren(map)
                        .addOnSuccessListener(aVoid -> dialogPlus.dismiss())
                        .addOnFailureListener(e -> dialogPlus.dismiss());
            });
        });

        // ❌ Delete button
        holder.delete.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
            builder.setTitle("Delete Donor?");
            builder.setMessage("Are you sure you want to delete this donor?");

            builder.setPositiveButton("Yes", (dialogInterface, i) ->
                    FirebaseDatabase.getInstance().getReference().child("donors")
                            .child(Objects.requireNonNull(getRef(position).getKey()))
                            .removeValue());

            builder.setNegativeButton("No", (dialogInterface, i) -> {
                // Do nothing if cancelled
            });

            builder.show();
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new myViewHolder(view);
    }

    static class myViewHolder extends RecyclerView.ViewHolder {
        final Button edit, delete;
        final ImageButton call, message;
        final TextView name, address, donated, age, blood, hospital;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameText);
            address = itemView.findViewById(R.id.address);
            donated = itemView.findViewById(R.id.lastDonated);
            age = itemView.findViewById(R.id.tvage);
            blood = itemView.findViewById(R.id.bloodgroup);
            hospital = itemView.findViewById(R.id.hospital);
            call = itemView.findViewById(R.id.phone);
            message = itemView.findViewById(R.id.message); // 👈 SMS button
            edit = itemView.findViewById(R.id.editButton);
            delete = itemView.findViewById(R.id.deleteButton);
        }
    }
}
