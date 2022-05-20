package com.amazon.ata.music.playlist.service.dynamodb.models;

import com.amazon.ata.music.playlist.service.converters.AlbumTrackLinkedListConverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Represents a record in the playlists table.
 */
@DynamoDBTable(tableName = "playlists")
public class Playlist {
    @DynamoDBHashKey(attributeName ="id")
    private String id;
    //@DynamoDBAttribute(attributeName = "songList")
    //songLsit should probably correlate to an item in the album_track table
    private List<AlbumTrack> songList;
    @DynamoDBAttribute(attributeName = "name")
    private String name;
    @DynamoDBAttribute(attributeName = "customerId")
    private String customerId;
    @DynamoDBAttribute(attributeName = "songCount")
    private Integer songCount;
    private Set<String> tags;
    @DynamoDBAttribute(attributeName = "name")
    public String getName(){
        return name;
    }

    public String getCustomerId(){
        return customerId;
    }

    public Integer getSongCount(){
        return songCount;
    }
    @DynamoDBAttribute(attributeName = "tags")
    public List<String> getTags(){
        if(tags == null)
            return new ArrayList<String>();
        else
            return new ArrayList<>(tags);
        //List<String> newList = new ArrayList<>(tags);
        //newList.addAll(tags);
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCustomerId(String customerId){
        this.customerId = customerId;
    }

    public void setSongCount(Integer songCount){
        this.songCount = songCount;
    }

    public void setTags(Set<String> tags){
        this.tags = tags;
    }




    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // PARTICIPANTS: You do not need to modify the songList getters/setters or annotations
    @DynamoDBTypeConverted(converter = AlbumTrackLinkedListConverter.class)
    @DynamoDBAttribute(attributeName = "songList")
    public List<AlbumTrack> getSongList() {
        return songList;
    }

    public void setSongList(List<AlbumTrack> songList) {
        this.songList = songList;
    }
}
