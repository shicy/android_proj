package org.shicy.common.datasource.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.shicy.common.utils.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 数据库访问
 * Created by Shicy on 2015/10/16.
 */
public class DbHelper extends SQLiteOpenHelper {

    private ScriptAdapter adapter;

    /**
     * 构造方法，开启一个数据库
     * @param context 应用上下文
     * @param name 数据库名称
     * @param adapter 数据脚本
     */
    public DbHelper(Context context, String name, ScriptAdapter adapter) {
        super(context, name, null, adapter.getLastVersion());
        this.adapter = adapter;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        File[] files = adapter.getScripts(0);
        for (File file: files) {
            execFile(db, file);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int i = oldVersion + 1; i <= newVersion; i++) {
            File[] files = adapter.getScripts(i);
            for (File file: files) {
                execFile(db, file);
            }
        }
    }

    /**
     * 执行数据库脚本文件
     * @param db 当前数据库
     * @param file 数据库脚本文件
     */
    private void execFile(SQLiteDatabase db, File file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            while (true) {
                String script = getScript(reader);
                if (script != null)
                    db.execSQL(script);
                else
                    break;
            }
        }
        catch (FileNotFoundException fe) {
            Log.i("DbHepler", "数据库脚本文件不存在：" + file.getName(), fe);
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (Exception e) {
                    //
                }
            }
        }
    }

    /**
     * 获取单个数据库脚本，以分号结尾，去除“/”注释
     * @param reader 数据库脚本文件行读取器
     * @return 一个可执行的数据库脚本
     */
    private String getScript(BufferedReader reader) {
        StringBuilder script = new StringBuilder();
        try {
            String sql;
            while ((sql = reader.readLine()) != null) {
                int index = sql.indexOf("//");
                sql = sql.substring(0, index).trim();
                if (sql.endsWith(";")) {
                    if (sql.length() > 1)
                        script.append(sql.substring(0, sql.length() - 1));
                    if (script.length() == 0)
                        return getScript(reader);
                    break;
                }
                else if (sql.length() > 0)
                    script.append(sql);
            }
        }
        catch (IOException ie) {
            //
        }
        return StringUtils.trimToNull(script.toString());
    }

    /**
     *
     */
    public interface ScriptAdapter {
        int getLastVersion();
        File[] getScripts(int version);
    }

}
