package com.jyq.android.net.gson;

/*
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
           佛祖保佑       永无BUG
 */

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.jyq.android.net.modal.User;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2017/3/30.
 */

public class UserRoleAdapter extends TypeAdapter<User.UserType> {
//    @Override
//    public User.UserType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//        return User.UserType.TypeOf(json.getAsString());
//    }
//
//    @Override
//    public JsonElement serialize(User.UserType src, Type typeOfSrc, JsonSerializationContext context) {
//        return new JsonPrimitive(src.getType());
//    }

    @Override
    public void write(JsonWriter out, User.UserType value) throws IOException {
        out.value(value.getType());
    }

    @Override
    public User.UserType read(JsonReader in) throws IOException {
        return User.UserType.TypeOf(in.nextString());
    }
}
