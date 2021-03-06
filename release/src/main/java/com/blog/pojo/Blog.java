package com.blog.pojo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedTimestamp;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Blog {

    private String blogID;
    private String blogName;
    private String blogDescription;
    private List<String> posts;
    private String userID;
    private Date createdOn;
    private Date modifiedOn;

    public Blog(){

    }

    public Blog(String blogName, String blogDescription, String userID) {
        this();
        this.blogName = blogName;
        this.blogDescription = blogDescription;
        this.posts = new ArrayList<>();
        this.userID = userID;
        this.createdOn = new Date();
    }

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getBlogID() {
        return blogID;
    }
    public void setBlogID(String blogID) {
        this.blogID = blogID;
    }

    @DynamoDBAttribute
    public String getBlogName() {
        return blogName;
    }
    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    @DynamoDBAttribute
    public List<String> getPosts() {
        return posts;
    }
    public void setPosts(List<String> posts) {
        this.posts = posts;
    }

    @DynamoDBAttribute
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @DynamoDBAttribute
    public String getBlogDescription() {
        return blogDescription;
    }
    public void setBlogDescription(String blogDescription) {
        this.blogDescription = blogDescription;
    }

    @DynamoDBAutoGeneratedTimestamp
    public Date getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
    @DynamoDBAttribute
    public Date getModifiedOn() {
        return modifiedOn;
    }
    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
    @Override
    public String toString() {
        return "Blog [blogID=" + blogID + ", blogName=" + blogName + ", blogDescription=" + blogDescription
                + ", userID=" + userID + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((blogDescription == null) ? 0 : blogDescription.hashCode());
        result = prime * result + ((blogID == null) ? 0 : blogID.hashCode());
        result = prime * result + ((blogName == null) ? 0 : blogName.hashCode());
        result = prime * result + ((posts == null) ? 0 : posts.hashCode());
        result = prime * result + ((userID == null) ? 0 : userID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Blog other = (Blog) obj;
        if (blogDescription == null) {
            if (other.blogDescription != null)
                return false;
        } else if (!blogDescription.equals(other.blogDescription))
            return false;
        if (blogID == null) {
            if (other.blogID != null)
                return false;
        } else if (!blogID.equals(other.blogID))
            return false;
        if (blogName == null) {
            if (other.blogName != null)
                return false;
        } else if (!blogName.equals(other.blogName))
            return false;
        if (posts == null) {
            if (other.posts != null)
                return false;
        } else if (!posts.equals(other.posts))
            return false;
        if (userID == null) {
            if (other.userID != null)
                return false;
        } else if (!userID.equals(other.userID))
            return false;
        return true;
    }
}