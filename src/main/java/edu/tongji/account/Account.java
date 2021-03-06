package edu.tongji.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.tongji.article.Article;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings("serial")
@Entity
@Table(name = "account", uniqueConstraints = {
        @UniqueConstraint(name = "email", columnNames = {"email"}),
        @UniqueConstraint(name = "nickname", columnNames = {"nickname"}),
})
public class Account implements java.io.Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String email;

    @Column
    private String nickname;

    @Column
    private String avatar;

    @JsonIgnore
    @Column
    private String password;

    @ManyToMany(mappedBy = "likedUsers", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Article> likedArticles;

    private String role = "ROLE_USER";

    protected Account() {

    }

    public Account(String email, String password, String nickname, String role) {
        this.setEmail(email);
        this.setPassword(password);
        this.setNickname(nickname);
        this.setRole(role);
        this.setAvatar("default.png");
    }

    public Account(Long id, String email, String nickname) {
        this(email, "dummy_password", nickname, "ROLE_USER");
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String username) {
        this.nickname = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Article> getLikedArticles() {
        return likedArticles;
    }

    public String getAvatarLink() {
        return "/image/" + this.getAvatar();
    }

    public String getLink() {
        return "/article/view/user/" + this.getId();
    }

    public void setLikedArticles(List<Article> likedArticles) {
        this.likedArticles = likedArticles;
    }
}
