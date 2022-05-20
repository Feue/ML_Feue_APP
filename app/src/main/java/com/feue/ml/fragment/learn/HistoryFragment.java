package com.feue.ml.fragment.learn;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.feue.ml.R;
import com.feue.ml.adapter.base.broccoli.BroccoliSimpleDelegateAdapter;
import com.feue.ml.adapter.base.delegate.SimpleDelegateAdapter;
import com.feue.ml.adapter.entity.HistoryInfo;
import com.feue.ml.core.BaseFragment;
import com.feue.ml.databinding.FragmentHistoryBinding;
import com.feue.ml.utils.DemoDataProvider;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.adapter.simple.AdapterItem;
import com.xuexiang.xui.widget.popupwindow.popup.XUISimplePopup;
import com.xuexiang.xutil.display.DensityUtils;

import me.samlss.broccoli.Broccoli;

@Page(name = "观看记录")
public class HistoryFragment extends BaseFragment<FragmentHistoryBinding> {

    private SimpleDelegateAdapter<HistoryInfo> mHistoryAdapter;

    private XUISimplePopup mPopup;

    @NonNull
    @Override
    protected FragmentHistoryBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentHistoryBinding.inflate(inflater, container, false);
    }

    private void initPopup() {
        AdapterItem[] popupItem = new AdapterItem[] {
                new AdapterItem("删除")
        };
        mPopup = new XUISimplePopup(getContext(), popupItem)
                .create(DensityUtils.dip2px(getContext(), 100), DensityUtils.dip2px(getContext(), 200), (adapter, item, position) -> {});
    }

    @Override
    protected void initViews() {
        initPopup();

        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(virtualLayoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        binding.recyclerView.setRecycledViewPool(viewPool);

        mHistoryAdapter = new BroccoliSimpleDelegateAdapter<HistoryInfo>(R.layout.adapter_history_list_item,
                new LinearLayoutHelper(), DemoDataProvider.getDemoHistoryInfos()) {
            @Override
            protected void onBindData(RecyclerViewHolder holder, HistoryInfo model, int position) {
                if (model != null) {
                    holder.image(R.id.history_course_image, model.getImageSrc());
                    holder.text(R.id.history_chapter_title, model.getChapterName());
                    holder.text(R.id.history_chapter_about, model.getChapterAbout());
                    holder.text(R.id.view_time, model.getTimeStr());

                    holder.click(R.id.history_card_view, v -> {
                        openNewPage(VideoFragment.class);
                    });

                    holder.click(R.id.history_action_more, v -> {
                        mPopup.show(holder.getView(R.id.history_action_more));
                    });
                }
            }

            @Override
            protected void onBindBroccoli(RecyclerViewHolder holder, Broccoli broccoli) {
                broccoli.addPlaceholders(
                        holder.findView(R.id.history_course_image),
                        holder.findView(R.id.history_chapter_title),
                        holder.findView(R.id.history_chapter_about),
                        holder.findView(R.id.view_time)
                );
            }
        };

        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        delegateAdapter.addAdapter(mHistoryAdapter);

        binding.recyclerView.setAdapter(delegateAdapter);
    }

    @Override
    protected void initListeners() {
        //下拉刷新
        binding.refreshLayout.setOnRefreshListener(refreshLayout -> {
            // TODO: 2020-02-25 这里只是模拟了网络请求
            refreshLayout.getLayout().postDelayed(() -> {
                mHistoryAdapter.refresh(DemoDataProvider.getDemoHistoryInfos());
                refreshLayout.finishRefresh();
            }, 1000);
        });
        //上拉加载
        binding.refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            // TODO: 2020-02-25 这里只是模拟了网络请求
            refreshLayout.getLayout().postDelayed(() -> {
                mHistoryAdapter.loadMore(DemoDataProvider.getDemoHistoryInfos());
                refreshLayout.finishLoadMore();
            }, 1000);
        });
        binding.refreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果
    }
}
