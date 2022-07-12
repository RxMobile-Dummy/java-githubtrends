package com.radixweb.trendingrepo.ui.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.radixweb.trendingrepo.R;
import com.radixweb.trendingrepo.data.local.entity.TrendRepoEntity;
import com.radixweb.trendingrepo.databinding.RepoListItemBinding;
import com.radixweb.trendingrepo.ui.custom.recyclerview.RecyclerLayoutClickListener;
import com.radixweb.trendingrepo.utils.AppUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TrendRepoAdapter extends RecyclerView.Adapter<TrendRepoAdapter.CustomViewHolder> implements Filterable {

    private Context context;
    private List<TrendRepoEntity> items;
    private List<TrendRepoEntity> filteredItems;
    private RecyclerLayoutClickListener listener;


    private String lastFilteredLanguage = "All";

    public TrendRepoAdapter(Context context, RecyclerLayoutClickListener listener) {
        this.context = context;
        this.items = new ArrayList<>();
        this.listener = listener;
        this.filteredItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        RepoListItemBinding itemBinding = RepoListItemBinding.inflate(layoutInflater, parent, false);
        CustomViewHolder customItemViewHolder = new CustomViewHolder(itemBinding);

        itemBinding.cardView.setOnClickListener(v -> customItemViewHolder.onCardButtonClick());

        return customItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    public void setItems(List<TrendRepoEntity> items) {
        this.filteredItems.addAll(items);
        getFilter().filter(lastFilteredLanguage);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public TrendRepoEntity getItem(int position) {
        return items.get(position);
    }

    /**
     * @return apply filter according to the language selection
     */
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String language = charSequence.toString();
                lastFilteredLanguage = language;

                if (language.equalsIgnoreCase("All")) {
                    items = filteredItems;

                } else {
                    List<TrendRepoEntity> list = new ArrayList<>();
                    for (TrendRepoEntity trendRepoEntity : filteredItems) {

                        if (language.equalsIgnoreCase(trendRepoEntity.getLanguage())) {
                            list.add(trendRepoEntity);
                        }
                    }
                    items = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = items;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                items = (List<TrendRepoEntity>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    protected class CustomViewHolder extends RecyclerView.ViewHolder {

        private RepoListItemBinding binding;

        public CustomViewHolder(RepoListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


        /**
         * @param trendRepoEntity pass current item detail to bind the view.
         */
        public void bindTo(TrendRepoEntity trendRepoEntity) {
            Picasso.get().load(trendRepoEntity.getOwner().getAvatarUrl())
                    .placeholder(R.drawable.ic_placeholder)
                    .into(binding.itemProfileImg);

            binding.itemTitle.setText(trendRepoEntity.getFullName());
            binding.itemTime.setText(String.format(context.getString(R.string.item_date),
                    AppUtils.getDate(trendRepoEntity.getCreatedAt()),
                    AppUtils.getTime(trendRepoEntity.getCreatedAt())));

            binding.itemDesc.setText(trendRepoEntity.getDescription());

            if (trendRepoEntity.getLanguage() != null) {
                binding.itemImgLanguage.setVisibility(View.VISIBLE);
                binding.itemLikes.setVisibility(View.VISIBLE);
                binding.itemLikes.setText(trendRepoEntity.getLanguage());

                GradientDrawable drawable = (GradientDrawable) context.getResources().getDrawable(R.drawable.ic_rectangle);
                drawable.setColor(AppUtils.getColorByLanguage(context, trendRepoEntity.getLanguage()));
                binding.itemImgLanguage.setBackground(drawable);

            } else {
                binding.itemLikes.setVisibility(View.GONE);
                binding.itemImgLanguage.setVisibility(View.GONE);
            }
        }

        /**
         * card click to manage navigation
         */
        private void onCardButtonClick() {
            listener.redirectToDetailScreen(binding.itemProfileImg, binding.itemTitle, binding.itemImgLanguage, binding.itemLikes, getItem(getLayoutPosition()));
        }
    }
}
