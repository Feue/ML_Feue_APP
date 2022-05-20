package com.feue.ml.utils;

import android.app.Activity;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class FileUtils {

    public static boolean uploadFile(String path, String filename) {
        OkHttpClient okhttp = new OkHttpClient();
        File file = new File(path);
        if (path.isEmpty() || !file.exists()) {
            return false;
        }
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", filename, RequestBody.create(MediaType.parse("multipart/form-data"), file))
                .addFormDataPart("filename", filename)
                .build();
        FutureTask<Boolean> task = new FutureTask<>(() -> {
            try {
                ResponseBody responseBody = okhttp.newCall(
                        new Request.Builder().post(body).url("http://10.0.2.2:8080/file/upload").build()
                ).execute().body();
                if (responseBody != null) {
                    return Boolean.parseBoolean(responseBody.string());
                }
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        });
        try {
            new Thread(task).start();
            return task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

//    public static File downloadFile(String filename) {
//        OkHttpClient okhttp = new OkHttpClient();
//        if (filename == null || filename.isEmpty()) {
//            return null;
//        }
//        RequestBody body = new MultipartBody.Builder().addFormDataPart("filename", filename).build();
//        FutureTask<File> task = new FutureTask<>(() -> {
//            ResponseBody responseBody = okhttp.newCall(
//                    new Request.Builder().post(body).url("http://10.0.2.2:8080/file/download").build()
//            ).execute().body();
//            if (responseBody != null) {
//                if (getExternalFilesDir(null) != null) {
//                    File file = new File(getExternalFilesDir(null).toString()+"/"+filename);
//                    try (InputStream inputStream = responseBody.byteStream();
//                         FileOutputStream outputStream = new FileOutputStream(file)) {
//                        byte[] b = new byte[1024];
//                        int n;
//                        if ((n = inputStream.read(b)) != -1) {
//                            outputStream.write(b, 0, n);
//                            while ((n = inputStream.read(b)) != -1) {
//                                outputStream.write(b, 0, n);
//                            }
//                            return file;
//                        } else {
//                            file.delete();
//                            return null;
//                        }
//                    }
//                }
//            }
//            return null;
//        });
//        try {
//            new Thread(task).start();
//            return task.get();
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
