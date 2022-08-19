package com.ldm.blog.po;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//实体类

//和数据库对应的一些注解，具备和数据库对于生成的能力
@Entity
//对应数据库那个table的名字
@Table(name = "t_blog")
public class Blog {

    //代表是主键
    @Id
    //自动生成
    @GeneratedValue
    private Long id;

    private String title;
    //已在数据库中将content的类型转为LongText
    private String content;
    //首图
    private String firstPicture;
    //原创、转载
    private String flag;
    //浏览数
    private Integer views;
    //支持赞赏
    private boolean appreciation;
    //支持分享
    private boolean shareStatement;
    //支持评论
    private boolean commentabled;
    //发布
    private boolean published;
    //评论
    private boolean recommend;

    //不用放到数据库的属性，即正常属性
    @Transient
    private String tagIds;

    private String description;

    //对应到数据库，需要生成的时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;


    //实体类之间的关系
    //在“一”的地方作为关系被维护端，mappedBy = "xxx"，xxx为“一”的一端

    //blog对type是多对一的关系
    //many的一端作为关系维护端
    @ManyToOne
    private Type type;

    //blog对tag是多对多的关系
    //这里可以设置级联关系，新增一个博客的同时新增一个标签
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();

    //blog对user是多对一的
    @ManyToOne
    private User user;

    //blog对comment是一对多的
    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();


    public Blog() {
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
  //新增的tag的id的setter和getter方法
    public String getTagIds() {
        return tagIds;
    }
    public void setTagIds(String tagIds) {

        this.tagIds = tagIds;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //处理tagsId
    public void init(){

        this.tagIds = tagsToIds(this.getTags());
    }

    //获取的内容形式为1,2,3
    private String tagsToIds(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for(Tag tag : tags){
                if(flag){
                    ids.append(",");
                }
                else{
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }
        else{
            return tagIds;
        }
    }



}
