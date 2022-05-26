package com.amazon.ata.music.playlist.service.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * Represents a record in the album_tracks table.
 */
@DynamoDBTable(tableName = "album_tracks")
public class AlbumTrack {
    @DynamoDBHashKey(attributeName = "asin")
    private String asin; //particion key
    @DynamoDBRangeKey(attributeName = "track_number")
    private Integer trackNumber;  //sortkey
    @DynamoDBAttribute(attributeName = "album_name")
    private String albumName;
    @DynamoDBAttribute(attributeName = "song_title")
    private String songTitle;

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public void setTrackNumber(Integer trackNumber){
        this.trackNumber = trackNumber;
    }

    public void setSongTitle(String songTitle){
        this.songTitle = songTitle;
    }

    public void setAlbumName(String albumName){
        this.albumName = albumName;
    }


    public String getAsin(){
        return asin;
    }

    public Integer getTrackNumber(){
        return trackNumber;
    }

    public String getAlbumName(){
        return albumName;
    }

    public String getSongTitle() {
        return songTitle;
    }
}
