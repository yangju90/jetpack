package indi.mat.work.behavior_collect;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetJsonUtil {
    static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (AssetManager assetManager = context.getAssets();
             BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)))) {
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            Log.e("GetJsonUtil", e.getMessage());
        }

        return stringBuilder.toString();
    }
}
