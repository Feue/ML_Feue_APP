package com.feue.ml.fragment.practice;

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
import com.feue.ml.adapter.entity.PracticeInfo;
import com.feue.ml.core.BaseFragment;
import com.feue.ml.databinding.FragmentPracticeListBinding;
import com.feue.ml.utils.DemoDataProvider;
import com.feue.ml.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import me.samlss.broccoli.Broccoli;

@Page
public class PracticeListFragment extends BaseFragment<FragmentPracticeListBinding> {

    private String category;

    private SimpleDelegateAdapter<PracticeInfo> mPracticeAdapter;

    public PracticeListFragment() {
    }

    public PracticeListFragment(String category) {
        this.category = category;
    }

    @NonNull
    @Override
    protected FragmentPracticeListBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentPracticeListBinding.inflate(inflater, container, false);
    }

    @Override
    protected TitleBar initTitle() {
        return null;
    }

    @Override
    protected void initViews() {
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(virtualLayoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        binding.recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        mPracticeAdapter = new BroccoliSimpleDelegateAdapter<PracticeInfo>(R.layout.adapter_practice_list_item,
                new LinearLayoutHelper(), DemoDataProvider.getDemoPracticeInfos()) {
            @Override
            protected void onBindData(RecyclerViewHolder holder, PracticeInfo model, int position) {
                if (model != null) {
                    holder.text(R.id.practice_item_name, model.getName());
                    holder.text(R.id.question_num, "共"+model.getQuestionNum()+"题");

                    holder.click(R.id.practice_card_view, v -> {
                        XToastUtils.toast(model.getName());
                        openNewPage(AnswerFragment.class);
                    });
                }
            }

            @Override
            protected void onBindBroccoli(RecyclerViewHolder holder, Broccoli broccoli) {
                broccoli.addPlaceholders(
                        holder.findView(R.id.practice_item_name),
                        holder.findView(R.id.question_num)
                );
            }
        };

        DelegateAdapter adapter = new DelegateAdapter(virtualLayoutManager);
        adapter.addAdapter(mPracticeAdapter);
        binding.recyclerView.setAdapter(adapter);

        mPracticeAdapter.refresh(DemoDataProvider.getDemoPracticeInfos());
    }
}
