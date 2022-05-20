package com.feue.ml.fragment.practice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.feue.ml.R;
import com.feue.ml.adapter.CommonRecyclerViewAdapter;
import com.feue.ml.adapter.base.broccoli.BroccoliSimpleDelegateAdapter;
import com.feue.ml.adapter.base.delegate.SimpleDelegateAdapter;
import com.feue.ml.adapter.entity.QuestionInfo;
import com.feue.ml.core.BaseFragment;
import com.feue.ml.databinding.FragmentAnswerBinding;
import com.feue.ml.utils.DemoDataProvider;
import com.feue.ml.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import me.samlss.broccoli.Broccoli;

@Page(name = "答题")
public class AnswerFragment extends BaseFragment<FragmentAnswerBinding> {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mPagerLayoutManager;
    private SnapHelper mSnapHelper;

    private SimpleDelegateAdapter<QuestionInfo> mQuestionAdapter;

    @NonNull
    @Override
    protected FragmentAnswerBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentAnswerBinding.inflate(inflater, container, false);
    }

    @Override
    protected TitleBar initTitle() {
        return super.initTitle();
    }

    @Override
    protected void initViews() {
        mRecyclerView = new RecyclerView(getContext());
        mPagerLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mPagerLayoutManager);

        mQuestionAdapter = new BroccoliSimpleDelegateAdapter<QuestionInfo>(R.layout.adapter_item_common_recycler_view,
                new LinearLayoutHelper(), DemoDataProvider.getDemoQuestionInfos()) {
            @Override
            protected void onBindData(RecyclerViewHolder holder, QuestionInfo model, int position) {
                if (model != null) {
                    holder.text(R.id.now_question_num, String.valueOf(position+1));
                    holder.text(R.id.total_question_num, String.valueOf(100));
                    holder.text(R.id.question_textView, model.getDescription());
                    SuperTextView option0 = (SuperTextView) holder.findView(R.id.option0);
                    option0.setLeftString(model.getOptions()[0]);
                    SuperTextView option1 = (SuperTextView) holder.findView(R.id.option1);
                    option1.setLeftString(model.getOptions()[1]);
                    SuperTextView option2 = (SuperTextView) holder.findView(R.id.option2);
                    option2.setLeftString(model.getOptions()[2]);
                    SuperTextView option3 = (SuperTextView) holder.findView(R.id.option3);
                    option3.setLeftString(model.getOptions()[3]);

                    holder.click(R.id.check_answer, v -> XToastUtils.success("答案正确"));
                }
            }

            @Override
            protected void onBindBroccoli(RecyclerViewHolder holder, Broccoli broccoli) {
                broccoli.addPlaceholders(
                        holder.findView(R.id.question_textView),
                        holder.findView(R.id.option0),
                        holder.findView(R.id.option1),
                        holder.findView(R.id.option2),
                        holder.findView(R.id.option3)
                );
            }
        };
        mRecyclerView.setAdapter(mQuestionAdapter);
        mQuestionAdapter.refresh(DemoDataProvider.getDemoQuestionInfos());

        binding.pagerWrap.addView(mRecyclerView);
        // PagerSnapHelper每次只能滚动一个item;用LinearSnapHelper则可以一次滚动多个，并最终保证定位
        // mSnapHelper = new LinearSnapHelper();
        mSnapHelper = new PagerSnapHelper();
        mSnapHelper.attachToRecyclerView(mRecyclerView);
    }
}
