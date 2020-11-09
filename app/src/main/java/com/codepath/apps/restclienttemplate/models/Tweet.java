package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tweet {

    public String body;
    public String CreatedAt;
    public User user;
    public long id;
    public String mediaUrl;
    //public Media media;

    public static Tweet fromjson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.CreatedAt = jsonObject.getString("created_at");
        tweet.id = jsonObject.getLong("id");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));

        JSONObject entities = jsonObject.getJSONObject("entities");
        if (entities != null && entities.has("media")) {
            JSONArray medias = entities.getJSONArray("media");
            if (medias.length() > 0) {
                JSONObject media = (JSONObject) medias.get(0);
                if (media.has("media_url")) {
                    tweet.mediaUrl = media.getString("media_url");
                }
            }
        }
        return tweet;

    }

    public static List<Tweet> fromJsonArrary(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            tweets.add(fromjson(jsonArray.getJSONObject(i)));

        }
        return tweets;
    }

    public String getFormattedTimeStamp(String CreatedAt) {

        return TimeFormatter.getTimeDifference(CreatedAt);

    }
}