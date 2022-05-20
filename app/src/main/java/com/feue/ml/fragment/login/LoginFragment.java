/*
 * Copyright (C) 2022 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.feue.ml.fragment.login;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.feue.ml.core.BaseFragment;
import com.feue.ml.utils.MMKVUtils;
import com.feue.ml.utils.RandomUtils;
import com.feue.ml.utils.Result;
import com.feue.ml.utils.SettingUtils;
import com.feue.ml.utils.TokenUtils;
import com.feue.ml.utils.Utils;
import com.feue.ml.utils.XToastUtils;
import com.feue.ml.R;
import com.feue.ml.activity.MainActivity;
import com.feue.ml.databinding.FragmentLoginBinding;
import com.feue.ml.utils.sdkinit.UMengInit;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.utils.ThemeUtils;
import com.xuexiang.xui.utils.ViewUtils;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.LoadingDialog;
import com.xuexiang.xutil.app.ActivityUtils;
import com.xuexiang.xutil.net.JsonUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


/**
 * 登录页面
 *
 * @author xuexiang
 * @since 2019-11-17 22:15
 */
@Page(anim = CoreAnim.none)
public class LoginFragment extends BaseFragment<FragmentLoginBinding> implements View.OnClickListener {

    private View mJumpView;

    private CountDownButtonHelper mCountDownHelper;

    private LoadingDialog mLoadingDialog;

    @NonNull
    @Override
    protected FragmentLoginBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentLoginBinding.inflate(inflater, container, false);
    }

    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle()
                .setImmersive(true);
        titleBar.setBackgroundColor(Color.TRANSPARENT);
        titleBar.setTitle("");
        titleBar.setLeftImageDrawable(ResUtils.getVectorDrawable(getContext(), R.drawable.ic_login_close));
        titleBar.setActionTextColor(ThemeUtils.resolveColor(getContext(), R.attr.colorAccent));
        mJumpView = titleBar.addAction(new TitleBar.TextAction(R.string.title_jump_login) {
            @Override
            public void performAction(View view) {
                onLoginSuccess();
            }
        });
        return titleBar;
    }

    @Override
    protected void initViews() {
//        mCountDownHelper = new CountDownButtonHelper(binding.btnGetVerifyCode, 60);
        //隐私政策弹窗
        if (!SettingUtils.isAgreePrivacy()) {
            Utils.showPrivacyDialog(getContext(), (dialog, which) -> {
                dialog.dismiss();
                handleSubmitPrivacy();
            });
        }
        boolean isAgreePrivacy = SettingUtils.isAgreePrivacy();
        binding.cbProtocol.setChecked(isAgreePrivacy);
        refreshButton(isAgreePrivacy);
        binding.cbProtocol.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SettingUtils.setIsAgreePrivacy(isChecked);
            refreshButton(isChecked);
        });
        mLoadingDialog = WidgetUtils.getLoadingDialog(getContext())
                .setIconScale(0.4f)
                .setLoadingSpeed(8);
    }

    @Override
    protected void initListeners() {
//        binding.btnGetVerifyCode.setOnClickListener(this);
        binding.btnLogin.setOnClickListener(this);
        binding.tvOtherLogin.setOnClickListener(this);
        binding.tvForgetPassword.setOnClickListener(this);
        binding.tvUserProtocol.setOnClickListener(this);
        binding.tvPrivacyProtocol.setOnClickListener(this);
    }

    private void refreshButton(boolean isChecked) {
        ViewUtils.setEnabled(binding.btnLogin, isChecked);
        ViewUtils.setEnabled(mJumpView, isChecked);
    }

    private void handleSubmitPrivacy() {
        SettingUtils.setIsAgreePrivacy(true);
        UMengInit.init();
        // 应用市场不让默认勾选
//        ViewUtils.setChecked(cbProtocol, true);
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        int id = v.getId();
//        if (id == R.id.btn_get_verify_code) {
//            if (binding.etPhoneNumber.validate()) {
//                getVerifyCode(binding.etPhoneNumber.getEditValue());
//            }
//        } else if (id == R.id.btn_login) {
        if (id == R.id.btn_login) {
//            XToastUtils.toast(binding.etPassword.getEditValue());
            if (binding.etPhoneNumber.validate()) {
                if (binding.etPassword.validate()) {
                    loginByPassword(binding.etPhoneNumber.getEditValue(), binding.etPassword.getEditValue());
                }
            }
        } else if (id == R.id.tv_other_login) {
//            XToastUtils.info("其他登录方式");
        } else if (id == R.id.tv_forget_password) {
            XToastUtils.info("请联系管理员");
        } else if (id == R.id.tv_user_protocol) {
            Utils.gotoProtocol(this, false, true);
        } else if (id == R.id.tv_privacy_protocol) {
            Utils.gotoProtocol(this, true, true);
        }

    }

    /**
     * 获取验证码
     */
    private void getVerifyCode(String phoneNumber) {
        // TODO: 2020/8/29 这里只是界面演示而已
        XToastUtils.warning("只是演示，验证码请随便输");
        mCountDownHelper.start();
    }

    /**
     * 根据验证码登录
     *
     * @param phoneNumber 手机号
     * @param verifyCode  验证码
     */
    private void loginByVerifyCode(String phoneNumber, String verifyCode) {
        // TODO: 2020/8/29 这里只是界面演示而已
        onLoginSuccess();
    }

    /**
     * 根据密码登录
     *
     * @param phoneNumber 手机号
     * @param password 密码
     */
    private void loginByPassword(String phoneNumber, String password) {
        mLoadingDialog.show();
        OkHttpClient okhttp = new OkHttpClient();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("phone", phoneNumber);
        paramMap.put("password", password);
        String json = JsonUtil.toJson(paramMap);
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        new AsyncTask<Void, Void, Result>() {
            @Override
            protected void onPreExecute() {
                mLoadingDialog.show();
            }
            @Override
            protected Result doInBackground(Void... voids) {
                try {
                   ResponseBody responseBody = okhttp.newCall(
                           new Request.Builder().post(body).url("http://10.0.2.2:8080/user/register").build()
                   ).execute().body();
                   if (responseBody != null) {
                       return JsonUtil.fromJson(responseBody.string(), Result.class);
                   }
                   return null;
               } catch (IOException e) {
                   e.printStackTrace();
                   return null;
               }
            }
            @Override
            protected void onPostExecute(Result result) {
                Log.e("login result", result.toString());
                mLoadingDialog.dismiss();
                if (result == null) {
                    XToastUtils.error("服务器异常");
                } else if (result.isSuccess()) {
                    onLoginSuccess(result.getResult());
                } else {
                    XToastUtils.error(result.getMessage());
                }
            }
        }.execute();
    }

    /**
     * 登录成功的处理
     */
    private void onLoginSuccess() {
        String token = RandomUtils.getRandomNumbersAndLetters(16);
        if (TokenUtils.handleLoginSuccess(token)) {
            popToBack();
            ActivityUtils.startActivity(MainActivity.class);
        }
    }

    private void onLoginSuccess(Map<String, Object> map) {
        MMKVUtils.put("user_id", Double.valueOf(String.valueOf(map.get("id"))).longValue());
        MMKVUtils.put("user_username", String.valueOf(map.get("username")));
        MMKVUtils.put("user_type", String.valueOf(map.get("type")));
        MMKVUtils.put("user_name", String.valueOf(map.get("name")));
        MMKVUtils.put("user_clazz", String.valueOf(map.get("clazz")));
        MMKVUtils.put("user_phone", String.valueOf(map.get("clazz")));
        MMKVUtils.put("user_email", String.valueOf(map.get("email")));
        popToBack();
        ActivityUtils.startActivity(MainActivity.class);
    }

    @Override
    public void onDestroyView() {
        if (mCountDownHelper != null) {
            mCountDownHelper.recycle();
        }
        super.onDestroyView();
    }
}

