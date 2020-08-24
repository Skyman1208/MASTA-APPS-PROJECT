package com.example.up.navigation.menuNav.home.subject.retrieveNDisplayData;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.up.R;
import com.example.up.navigation.menuNav.uploadData.UploadModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>  {
    private Context mContext;
    private List<UploadModel> mUploadModels;
    private List<UploadModel> exampleListFull;
    private OnItemClickListener mListener;

    public ImageAdapter(Context context, List<UploadModel> uploadModels) {
        mContext = context;
        mUploadModels = uploadModels;
        exampleListFull = new ArrayList<>(mUploadModels);
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_retrieve_data_design, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, final int position) {
        UploadModel uploadModelCurrent = mUploadModels.get(position);
        holder.textViewName.setText(uploadModelCurrent.getName());
        holder.imageView.setImageResource(R.drawable.qr);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(mContext, ViewQrCode.class);
                UploadModel clickedItem = mUploadModels.get(position);
                detailIntent.putExtra(ImagesActivity.EXTRA_URL, clickedItem.getLink());
                detailIntent.putExtra(ImagesActivity.EXTRA_NAME, clickedItem.getName());
                mContext.startActivity(detailIntent);
            }
        });
        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadModel selectedItem = mUploadModels.get(position);
                final String selectedLink = selectedItem.getLink();
                if (selectedLink.isEmpty()) {
                    Toast.makeText(mContext, "Url does not exist",Toast.LENGTH_SHORT).show();
                } else{
                    Uri uri = Uri.parse(selectedLink); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUploadModels.size();
    }

    public void filterList(ArrayList<UploadModel> filteredList) {
        mUploadModels = filteredList;
        notifyDataSetChanged();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView textViewName;
        public CircleImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name);
            imageView = itemView.findViewById(R.id.cv_viewQrCode);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }
        }
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem doWhatever = menu.add(Menu.NONE, 1, 1, "Do whatever");
            MenuItem delete = menu.add(Menu.NONE, 2, 2, "Delete");

            doWhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {

                    switch (item.getItemId()) {
                        case 1:
                            mListener.onWhatEverClick(position);
                            return true;
                        case 2:
                            mListener.onDeleteClick(position);
                            return true;
                    }
                }
            }
            return false;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void goButtonClicked(int position);

        void onWhatEverClick(int position);

        void onDeleteClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
}