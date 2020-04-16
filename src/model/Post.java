package model;


import java.time.LocalDate;
import java.util.Objects;

public class Post {
    private String title;
    private String text;
    private String category;
    private LocalDate createDate;

    public Post(String title, String text, String category, LocalDate createDate) {
        this.title = title;
        this.text = text;
        this.category = category;
        this.createDate = createDate;
    }

    public Post() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(title, post.title) &&
                Objects.equals(text, post.text) &&
                Objects.equals(category, post.category) &&
                Objects.equals(createDate, post.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, text, category, createDate);
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", category='" + category + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
