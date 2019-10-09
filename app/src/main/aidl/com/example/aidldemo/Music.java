package com.example.aidldemo;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;

import java.net.URL;

public class Music implements Parcelable {
        /**
         * 音乐名的首字母
         */
        private String tag;

        /**
         * 音乐名
         */
        private String name;

        /**
         * 音乐id
         */
        private int id;

        /**
         * 专辑名
         */
        private String albumName;

        /**
         * 作者
         */
        private String artist;

        /**
         * 时长
         */
        private int duration;

        /**
         * 歌曲的大小
         */
        private long size;

        /**
         * 音乐的路径
         */
        private String path;

        /**
         * 音乐的Uri
         */
        private Uri uri;

        /**
         * 音乐的url
         */
        private URL url;

        /**
         * 用于判断是否是本地音乐
         */
        private boolean isLocalMusic;

        public Music(){

        }

        public Music(String name, int id, String albumName, String artist,
                     int duration, long size, String path){
            this.name = name;
            this.id = id;
            this.albumName = albumName;
            this.artist = artist;
            this.duration = duration;
            this.size = size;
            this.path = path;
            Uri contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            this.uri = ContentUris.withAppendedId(contentUri, id);
            this.url = null;
            this.isLocalMusic = true;
        }


        protected Music(Parcel in) {
            tag = in.readString();
            name = in.readString();
            id = in.readInt();
            albumName = in.readString();
            artist = in.readString();
            duration = in.readInt();
            size = in.readLong();
            path = in.readString();
            uri = in.readParcelable(Uri.class.getClassLoader());
            isLocalMusic = in.readByte() != 0;
        }

        public static final Creator<Music> CREATOR = new Creator<Music>() {
            @Override
            public Music createFromParcel(Parcel in) {
                return new Music(in);
            }

            @Override
            public Music[] newArray(int size) {
                return new Music[size];
            }
        };

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAlbumName() {
            return albumName;
        }

        public void setAlbumName(String albumName) {
            this.albumName = albumName;
        }

        public Uri getUri() {
            return uri;
        }

        public void setUri(Uri uri) {
            this.uri = uri;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public long getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public URL getUrl() {
            return url;
        }

        public void setUrl(URL url) {
            this.url = url;
        }

        public static Music valueOf(Cursor cursor){

            return new Music(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)),
                    cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(tag);
            dest.writeString(name);
            dest.writeInt(id);
            dest.writeString(albumName);
            dest.writeString(artist);
            dest.writeInt(duration);
            dest.writeLong(size);
            dest.writeString(path);
            dest.writeParcelable(uri, flags);
            dest.writeByte((byte) (isLocalMusic ? 1 : 0));
        }
}
